package com.kfwy.hkp.sys.cpy.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.sys.cpy.dao.ICompanyDbDao;
import com.kfwy.hkp.sys.cpy.entity.CompanyEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by davidcun on 2018/5/25.
 */
@Repository
public class CompanyDbDaoImpl extends AbstractMyBatisDao<CompanyEntity,Long> implements ICompanyDbDao {
}
