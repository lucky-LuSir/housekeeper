package com.kfwy.hkp.bi.count.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.bi.count.dto.CommonReportDto;
import com.kfwy.hkp.bi.count.entity.MarketAnalysisEntity;

import java.util.Date;
import java.util.List;

/**
 * Create By hjh on 2019/1/9
 */
public interface IMarketAnalysisManager extends IManager<MarketAnalysisEntity> {

    public CommonReportDto<MarketAnalysisEntity> count(Date startTime, Date endTime, String code);
}
