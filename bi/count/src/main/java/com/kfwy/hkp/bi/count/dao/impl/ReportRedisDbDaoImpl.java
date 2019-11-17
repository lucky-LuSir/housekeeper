package com.kfwy.hkp.bi.count.dao.impl;

import com.gniuu.framework.dataaccess.redis.AbstractRedisDao;
import com.kfwy.hkp.bi.count.dao.IReportRedisDbDao;
import com.kfwy.hkp.bi.count.dto.ReportDto;
import org.springframework.stereotype.Repository;

/**
 * Create By hjh on 2018/10/26
 */
@Repository
public class ReportRedisDbDaoImpl extends AbstractRedisDao<ReportDto> implements IReportRedisDbDao {


}
