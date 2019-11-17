package com.kfwy.hkp.sys.remind.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.sys.remind.dao.IRemindDownDbDao;
import com.kfwy.hkp.sys.remind.entity.RemindDownEntity;
import org.springframework.stereotype.Repository;

/**
 * @Auther: HJH
 * @Date: 2019/4/19
 * @Description: TODO
 */
@Repository
public class RemindDownDbDaoImpl extends AbstractMyBatisDao<RemindDownEntity, Long> implements IRemindDownDbDao {
}
