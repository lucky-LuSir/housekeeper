/*
package com.kfwy.hkp.log.manager.impl;

import cn.hutool.core.util.ObjectUtil;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.utils.JsonMapper;
import com.kfwy.hkp.common.enums.OperationType;
import com.kfwy.hkp.log.annotation.BaseOperLog;
import com.kfwy.hkp.log.annotation.OperLog;
import com.kfwy.hkp.log.dao.BaseOperationDao;
import com.kfwy.hkp.log.entity.BaseOperationEntity;
import com.kfwy.hkp.log.entity.OperationEntity;
import com.kfwy.hkp.log.manager.BaseOperationManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

*/
/**
 * 基础操作日志表(BaseOperation)服务实现类
 *
 * @author liwensihan
 * @since 2019-05-28 11:00:12
 *//*

@Service("baseOperationManager")
public class BaseOperationManagerImpl extends AbstractManager<BaseOperationEntity> implements BaseOperationManager {


    @Autowired
    private BaseOperationDao baseOperationDao;

    @Autowired
    private TaskExecutor taskExecutor;


    @Override
    protected IMyBatisBaseDao<BaseOperationEntity, Long> getMyBatisDao() {
        return this.baseOperationDao;
    }

    @Override
    public void save(HttpServletRequest request, String path, Object handler) {
        UserEntity user = (UserEntity) CurrentContext.getUserInfo();
        if (ObjectUtil.isNotNull(user)) {
            taskExecutor.execute(() -> {

                BaseOperationEntity baseOperationEntity = new BaseOperationEntity(user.getUserName(), new Date(), false, path, user.getUserCode());

                baseOperationDao.insert(baseOperationEntity);

            });
        }

    }

    @Override
    public List<String> findUrlsByOptCode(String optCode) {

        return this.baseOperationDao.findUrlsByOptCode(optCode);

    }
}*/
