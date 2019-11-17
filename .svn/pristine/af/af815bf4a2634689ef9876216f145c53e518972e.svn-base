package com.kfwy.hkp.sys.remind.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.RedisLock;
import com.kfwy.hkp.sys.remind.business.IRemindDownManager;
import com.kfwy.hkp.sys.remind.dao.IRemindDownDbDao;
import com.kfwy.hkp.sys.remind.entity.RemindDownEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: HJH
 * @Date: 2019/4/19
 * @Description: TODO
 */
@Service
public class RemindDownManagerImpl extends AbstractManager<RemindDownEntity> implements IRemindDownManager {
    private static final int TIMEOUT = 5 * 1000; //超时时间 5s
    @Autowired
    private RedisLock redisLock;

    @Autowired
    private IRemindDownDbDao remindDownDbDao;

    @Override
    protected IMyBatisBaseDao<RemindDownEntity, Long> getMyBatisDao() {
        return remindDownDbDao;
    }


    @Override
    public int create(RemindDownEntity re) {
        //加锁
        long time = System.currentTimeMillis () + TIMEOUT;
        if (! redisLock.lock ("ky:hkp:remind_down:"+re.getOwnerCode (), String.valueOf (time))) { //如果返回
            throw new RemoveStackBusinessException ("请勿重复提醒!");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("ownerCode", re.getOwnerCode());
        map.put("downType", re.getDownType());
        map.put("hasOperate",false);
        map.put("isDeleted", false);
        RemindDownEntity rde = remindDownDbDao.selectUniqueByMap(map);
        if (rde != null) {
            throw new RemoveStackBusinessException ("已提醒下架，请勿重复提醒!");
        }
        OperateFillUtils.fill(re);
        re.setRemindCode(CodeGenUtils.generate("rd"));
        int i = remindDownDbDao.insert (re);

        redisLock.unlock ("ky:hkp:remind_down:"+re.getOwnerCode (),String.valueOf (time));
        return i;
    }

}
