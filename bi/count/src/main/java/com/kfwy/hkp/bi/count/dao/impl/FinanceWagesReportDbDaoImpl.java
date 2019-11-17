package com.kfwy.hkp.bi.count.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.bi.count.dao.IFinanceWagesReportDbDao;
import com.kfwy.hkp.bi.count.dao.IPersonnelReportDbDao;
import com.kfwy.hkp.bi.count.dto.FinanceWagesReportDto;
import com.kfwy.hkp.bi.count.dto.PersonnelReportDto;
import org.springframework.stereotype.Repository;

@Repository
public class FinanceWagesReportDbDaoImpl extends AbstractMyBatisDao<FinanceWagesReportDto,Long> implements IFinanceWagesReportDbDao {

}
