package com.kfwy.hkp.base.history.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.user.entity.BaseUserEntity;
import com.kfwy.hkp.base.history.business.IHistoryManager;
import com.kfwy.hkp.base.history.dao.IHistoryDbDao;
import com.kfwy.hkp.base.history.entity.HistoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description manager
 * @Auth xpp
 * @Date 2018/12/8
 * @Version 1.0
 */
@Service
public class HistoryManagerImpl extends AbstractManager<HistoryEntity> implements IHistoryManager {


    @Autowired
    private IHistoryDbDao historyDbDao;


    @Override
    protected IMyBatisBaseDao<HistoryEntity, Long> getMyBatisDao() {
        return this.historyDbDao;
    }

    @Override
    public  HistoryEntity createHistory(HistoryEntity historyEntity) {
        //加入生成编码
        String genCode = CodeGenUtils.generate();
        historyEntity.setHistoryCode(genCode);

        //加入创建人修改人
        OperateFillUtils.fill(historyEntity);
        //加入所属人的基本信息电话
        BaseUserEntity baseUser = CurrentContext.getUserInfo();
        if(baseUser != null){
            historyEntity.setOptCode(baseUser.getUserCode());
            historyEntity.setOptName(baseUser.getUserName());
        }
        historyDbDao.insert(historyEntity);

        return historyEntity;

    }

    @Override
    public void createHistory(List<HistoryEntity> historyEntities) {
        //加入生成编码
        for (HistoryEntity historyEntity:historyEntities){
            String genCode = CodeGenUtils.generate();
            historyEntity.setHistoryCode(genCode);
            //加入创建人修改人
            OperateFillUtils.fill(historyEntity);
            //加入所属人的基本信息电话
            BaseUserEntity baseUser = CurrentContext.getUserInfo();
            if(baseUser != null){
                historyEntity.setOptCode(baseUser.getUserCode());
                historyEntity.setOptName(baseUser.getUserName());
            }
        }

        historyDbDao.batchInsert(historyEntities);
    }


}
