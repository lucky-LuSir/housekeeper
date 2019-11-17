package com.kfwy.hkp.sys.parttime.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.sys.parttime.dao.IParttimeDbDao;
import com.kfwy.hkp.sys.parttime.entity.ParttimeEntity;
import org.springframework.stereotype.Repository;

/**
 * Create By hjh on 2018/11/7
 */
@Repository
public class ParttimeDbDaoImpl extends AbstractMyBatisDao<ParttimeEntity,Long> implements IParttimeDbDao {
}
