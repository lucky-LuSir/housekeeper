package com.kfwy.hkp.bi.count.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.bi.count.dto.CommonReportDto;
import com.kfwy.hkp.bi.count.entity.NotOrderPersonEntity;

import java.util.Date;

/**
 * @Auther: HJH
 * @Date: 2019/6/13
 * @Description: TODO
 */
public interface INotOrderPersonManager extends IManager<NotOrderPersonEntity> {

    public CommonReportDto<NotOrderPersonEntity> select(Date startTime, Date endTime, String code);
}
