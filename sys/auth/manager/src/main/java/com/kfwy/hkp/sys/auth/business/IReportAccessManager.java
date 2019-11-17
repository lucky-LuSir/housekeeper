package com.kfwy.hkp.sys.auth.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.sys.auth.entity.ReportAccessEntity;

import java.util.List;
import java.util.Map;

/**
 * @Auther: HJH
 * @Date: 2019/7/11
 * @Description: TODO
 */
public interface IReportAccessManager extends IManager<ReportAccessEntity> {

    public int creates(ReportAccessEntity ra, List<String> deptCodes);

    public int createAll(List<String> userCodes, List<String> deptCodes);

    public List<ReportAccessEntity> selectDeptAccess(Map<String, Object> param);
}
