package com.kfwy.hkp.bi.count.business;

import com.gniuu.framework.common.business.IManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.bi.count.dto.PersonnelReportDto;

import java.util.Map;

public interface IPersonnelReportManager extends IManager<PersonnelReportDto> {

    /**
     * 人事版报表查询
     * @param map
     * @param start
     * @param pageSize
     * @return
     */
    PageResult<PersonnelReportDto> personnelReportSearch(Map<String, Object> map, Integer start, Integer pageSize);
}
