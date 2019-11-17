package com.kfwy.hkp.bi.count.business;

import com.gniuu.framework.common.business.IManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.bi.count.dto.FinanceWagesReportDto;
import com.kfwy.hkp.bi.count.dto.PersonnelReportDto;

import java.util.List;
import java.util.Map;

public interface IFinanceWagesReportManager extends IManager<FinanceWagesReportDto> {

    /**
     * 人事版报表查询
     * @param map
     * @param start
     * @param pageSize
     * @return
     */
    PageResult<FinanceWagesReportDto> financeWagesReportSearch(Map<String, Object> map, Integer start, Integer pageSize);

    /**
     * 导出
     * @param param
     * @return
     */
    public List<FinanceWagesReportDto> query(Map<String,Object> param);


    }
