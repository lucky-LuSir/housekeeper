package com.kfwy.hkp.trade.wage.business;


import com.gniuu.framework.common.business.IManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.trade.wage.entity.WageEntity;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by lzp on 2019/5/8.
 */
public interface IWageManager extends IManager<WageEntity> {

    PageResult<WageEntity> wageReport(Map<String, Object> map, Integer start, Integer pageSize) throws ParseException;
    List<WageEntity> wageReport(Map<String, Object> map) throws ParseException;

    List<WageEntity> wageDetailsReport(Map<String,Object> param) throws ParseException;
}
