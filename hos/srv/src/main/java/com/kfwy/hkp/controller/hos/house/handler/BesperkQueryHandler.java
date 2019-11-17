package com.kfwy.hkp.controller.hos.house.handler;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.utils.BeanMapConvertUtils;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.hos.house.api.BespeakApi.BespeakApiClient;
import com.kfwy.hkp.hos.house.entity.BespeakEntity;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2019/4/15.
 */
@Component
public class BesperkQueryHandler {

    @Autowired
    private IDeptManager deptManager;

    public Map queryMap(BespeakEntity bespeakEntity) throws IllegalAccessException, ParseException {
        Map param = new HashMap(16);
        param.putAll(BeanMapConvertUtils.convertExclude(bespeakEntity));

        createTimeStartAndEnd(param,bespeakEntity);
        selectEmpCodeOrDeptCodes(param,bespeakEntity);
        return param;
    }

    private void createTimeStartAndEnd(Map param,BespeakEntity houseQuery) throws ParseException {
        //创建时间
        if (null != houseQuery.getCreateTimeStart()) {
            param.put("createTimeStart", DateFormatUtil.getCurrentDate(houseQuery.getCreateTimeStart(), DateFormatUtil.DateTimeFormatString));
        }
        //创建结束时间
        if (null != houseQuery.getCreateTimeEnd()) {
            param.put("createTimeEnd", DateFormatUtil.getCurrentDate(houseQuery.getCreateTimeEnd(), DateFormatUtil.DateTimeFormatString));
        }
    }

    private void selectEmpCodeOrDeptCodes(Map param,BespeakEntity houseQuery){
        if(deptManager.hasDeptLeader(CurrentContext.getUserInfo().getOwnerDeptCode(),CurrentContext.getUserCode())){
            List<String> deptCodes= BespeakApiClient.findSelectBespeakPower(CurrentContext.getUserCode(),CurrentContext.getUserInfo().getOwnerDeptCode());
            if(StringUtils.isNotEmpty(CurrentContext.getUserInfo().getOwnerDeptCode())){
                param.put("deptCodes",deptCodes);
            }
        }else{
            param.put("empCode",CurrentContext.getUserCode());
        }
    }
}
