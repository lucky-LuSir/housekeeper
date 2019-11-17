package com.kfwy.hkp.controller.crm.houseowner.vo;

import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.utils.BeanMapConvertUtils;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerEntity;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerFollowupEntity;
import com.kfwy.hkp.hos.house.entity.HouseQueryEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hjh on 2018/10/17.
 */
public class HouseownerFollowupVo extends HouseownerFollowupEntity {

    protected String keyword;


    /**
     * 查询类型 :  1 我的 2 同部门
     */
    public String queryType;

    /**
     * 创建信息
     */
    protected Date createTimeStart;

    protected Date createTimeEnd;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    @Override
    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }


    /**

     * 房源编码
     */
    protected String houseCode;

    protected String empName;

    protected String ownerName;


    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Map queryMap(HouseownerFollowupVo HouseownerFollowupVo){
        Map param = new HashMap(16);
        try {
            param.putAll(BeanMapConvertUtils.convertExclude(HouseownerFollowupVo));
            createTimeStartAndEnd(param,HouseownerFollowupVo);
        }catch (Exception e){

        }
        return param;
    }

    /**
     * 创建时间
     * @param param
     * @param houseQuery
     */
    private void createTimeStartAndEnd(Map param,HouseownerFollowupVo HouseownerFollowupVo) {
        //创建时间
        if (null != HouseownerFollowupVo.getCreateTimeStart()) {
            param.put("createTimeStart", DateUtil.beginOfDay(HouseownerFollowupVo.getCreateTimeStart()));
        }
        //创建结束时间
        if (null != HouseownerFollowupVo.getCreateTimeEnd()) {
            param.put("createTimeEnd", DateUtil.endOfDay(HouseownerFollowupVo.getCreateTimeEnd()));
        }
    }
}
