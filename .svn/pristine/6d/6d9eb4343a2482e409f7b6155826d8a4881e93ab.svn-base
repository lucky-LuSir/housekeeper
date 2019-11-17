package com.kfwy.hkp.bi.count.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.entity.ReportEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/10/26
 */
public interface IReportDbDao extends IMyBatisBaseDao<ReportEntity, Long> {

    /*
     *  查询房源委托
     * */
    public int selectHouseDepute(Map<String, Object> param);

    /*
     *  查询有效客户
     * */
    public int selectValidCus(Map<String, Object> param);

    /*
     *  查询客户上架数
     * */
    public int selectCusUpCount(Map<String, Object> param);

    /*
     *  查询房源上架数
     * */
    public int selectHosUpCount(Map<String, Object> param);

    /*
     *  部门维度查询获客
     * */
    public int selectByDeptFocusCount(Map<String, Object> param);

    /*
     *  人员维度查询获客
     * */
    public int selectByUserFocusCount(Map<String, Object> param);

    /*
     *  查询订单金额
     * */
    public List<BigDecimal> selectOrderTotalMoney(Map<String, Object> param);

    /*
     *  查询订单金额
     * */
    public BigDecimal selectOrderActualMoney(Map<String, Object> param);
}
