package com.kfwy.hkp.sys.auth.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.sys.auth.entity.ReportAccessEntity;

import java.util.List;
import java.util.Map;

/**
 * @Auther: HJH
 * @Date: 2019/7/11
 * @Description: TODO
 */
public interface IReportAccessDbDao extends IMyBatisBaseDao<ReportAccessEntity,Long> {

    public List<ReportAccessEntity> selectDeptAccess(Map<String, Object> param);

    public List<String> selectAccess(String userCode);

    public int deleteByUserCode(String userCode);

    public int deleteAllByUserCodes(List<String> userCodes);

}
