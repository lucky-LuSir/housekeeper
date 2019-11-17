package com.kfwy.hkp.bi.count.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.bi.count.dto.CommonReportDto;
import com.kfwy.hkp.bi.count.entity.LocationAnalysisEntity;

/**
 * Create By xpp on 2019/1/9
 */
public interface ILocationAnalysisManager extends IManager<LocationAnalysisEntity> {

    public CommonReportDto<LocationAnalysisEntity> count(String code);

    public CommonReportDto<LocationAnalysisEntity> locMapCount(String code);
}
