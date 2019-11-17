package com.kfwy.hkp.bi.count.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.bi.count.entity.RecordEntity;

import java.util.Date;
import java.util.List;

/**
 * Create By hjh on 2019/2/22
 */
public interface IRecordManager extends IManager<RecordEntity> {

    public List<RecordEntity> count(String code, Date startTime, Date endTime);
}
