package com.kfwy.hkp.log.manager.impl;

import cn.hutool.core.util.ObjectUtil;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.context.RequestContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.common.mongo.dao.IBaseDao;
import com.kfwy.hkp.common.mongo.service.BaseService;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.common.utils.PageModel;
import com.kfwy.hkp.log.dao.BaseMongoRep;
import com.kfwy.hkp.log.dao.IBaseOperationMongoDao;
import com.kfwy.hkp.log.entity.BaseOperationEntity;
import com.kfwy.hkp.log.manager.BaseOperationMongoManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author liwensihan
 * @date 2019年5月30日
 */
@Service
public class BaseOperationMongoManagerImpl extends BaseService<BaseOperationEntity> implements BaseOperationMongoManager {


    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private IBaseOperationMongoDao baseOperationMongoDao;
    @Autowired
    private BaseMongoRep baseMongoRep;

    @Override
    protected IBaseDao<BaseOperationEntity> getDao() {
        return baseOperationMongoDao;
    }


    @Override
    public void save(HttpServletRequest request, String path, Object handler) {
        UserEntity user = (UserEntity) CurrentContext.getUserInfo();
        RequestContext requestContext = CurrentContext.getRequestContext();
        taskExecutor.execute(() -> {
            if (ObjectUtil.isNotNull(user)) {

                BaseOperationEntity baseOperationEntity =
                        new BaseOperationEntity(user.getUserName(), new Date(), false,
                                path, user.getUserCode(), requestContext.getRequestIp(),
                                requestContext.getClientType().getName());

                baseOperationMongoDao.save(baseOperationEntity);
            }

        });
    }

    @Override
    public void save(String path, String requestParam) {


        UserEntity user = (UserEntity) CurrentContext.getUserInfo();
        RequestContext requestContext = CurrentContext.getRequestContext();

        taskExecutor.execute(() -> {

            if (ObjectUtil.isNotNull(user)) {
                BaseOperationEntity baseOperationEntity =
                        new BaseOperationEntity(user.getUserName(), new Date(), false,
                                path, user.getUserCode(), requestContext.getRequestIp(),
                                requestContext.getClientType().getName(),requestParam);

                //baseOperationMongoDao.save(baseOperationEntity);
                baseMongoRep.save(baseOperationEntity);
            }

        });
    }

    @Override
    public PageResult<BaseOperationEntity> findByMap(Map<String, Object> map, Integer start, Integer pageSize, String create_time, Boolean aFalse) {
        List<String> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        if (map.get("id") != null && map.get("id") != "") {
            keys.add("id");
            values.add(map.get("id"));
        }

        if (map.get("url") != null && map.get("url") != "") {
            keys.add("url");
            values.add(map.get("url"));
        }

        if (map.get("optCode") != null && map.get("optCode") != "") {
            keys.add("optCode");
            values.add(map.get("optCode"));
        }

        if (map.get("createTimeStart") != null) {
            keys.add("createTimeStart");
            values.add(DateFormatUtil.dayBegin((Date) map.get("createTimeStart")));
        }

        if (map.get("createTimeEnd") != null) {
            keys.add("createTimeEnd");
            values.add(DateFormatUtil.dayEnd((Date) map.get("createTimeEnd")));
        }

        PageModel<BaseOperationEntity> model = this.pageByProps(start, pageSize, map,
                keys.size() > 0 ? keys.toArray(new String[keys.size()]) : new String[]{}, values.size() > 0 ? values.toArray() : new Object[]{}, "createTime desc");
        if (model.getList() != null) {

            return new PageResult(model.getPageNo(), model.getTotalCount(), model.getPageSize(), model.getList());

        } else {
            return null;
        }

    }

    @Override
    public List<BaseOperationEntity> findUrlsByOptCode() {
       return getDao().findColumnByProp("url");
    }

    @Override
    public List<BaseOperationEntity> findClientType() {
        return getDao().findColumnByProp("clientType");
    }

    @Override
    public BaseOperationEntity findOne(Map<String, Object> map) {
        List<String> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        if (map.get("id") != null && map.get("id") != "") {
            keys.add("id");
            values.add(map.get("id"));
        }
        if (map.get("url") != null && map.get("url") != "") {
            keys.add("url");
            values.add(map.get("url"));
        }
        if (map.get("optCode") != null && map.get("optCode") != "") {
            keys.add("optCode");
            values.add(map.get("optCode"));
        }


        return this.uniqueByProps(map, (keys.toArray(new String[keys.size()])), values.toArray());
    }
}