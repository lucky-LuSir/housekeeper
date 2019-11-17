package com.kfwy.hkp.bi.count.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.bi.count.dto.CommonReportDto;
import com.kfwy.hkp.bi.count.entity.ConvertRateEntity;

import java.util.Date;

/**
 * Create By hjh on 2019/1/14
 */
public interface IConvertRateManager extends IManager<ConvertRateEntity> {

    public CommonReportDto<ConvertRateEntity> count(Date startTime, Date endTime, String code);

}
