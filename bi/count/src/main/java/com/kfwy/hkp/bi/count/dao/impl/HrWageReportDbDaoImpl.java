package com.kfwy.hkp.bi.count.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.bi.count.dao.IHrWageReportDbDao;
import com.kfwy.hkp.bi.count.dto.HrWageReportDto;
import org.springframework.stereotype.Repository;

@Repository
public class HrWageReportDbDaoImpl extends AbstractMyBatisDao<HrWageReportDto,Long> implements IHrWageReportDbDao {

}
