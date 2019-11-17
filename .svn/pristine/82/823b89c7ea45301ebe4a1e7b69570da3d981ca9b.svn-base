package com.kfwy.hkp.controller.hos.house.handler;

import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.utils.BeanMapConvertUtils;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.hos.house.entity.HouseQueryEntity;
import com.kfwy.hkp.hos.house.enums.DaysNotFollowupType;
import com.kfwy.hkp.hos.house.enums.HouseQueryType;
import com.kfwy.hkp.hos.house.enums.HouseType;
import com.kfwy.hkp.hos.house.enums.OrderFlagType;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @AUTHOR 李文思汗
 * @Description 类描述:
 */
@Component
public class HouseQueryHandler {
    @Autowired
    private  IDeptManager deptManager;

    public Map queryMap(HouseQueryEntity houseQuery) throws IllegalAccessException {
        Map param = new HashMap(16);
        if (houseQuery==null){
            houseQuery  = new HouseQueryEntity();
        }
        param.putAll(BeanMapConvertUtils.convertExclude(houseQuery));

        OrderFlagType.setOrderFlagType(param, houseQuery.getOrderFlag());

        DaysNotFollowupType.setDaysNotFollowup(param,houseQuery.getDaysNotFollowupType(),houseQuery.getDaysNotFollowup());

        param.put("userCode", CurrentContext.getUserCode());
        param.put("isDeleted", Boolean.FALSE);

        houseQueryType(param,houseQuery);
        floorFlag(param,houseQuery);
        createTimeStartAndEnd(param,houseQuery);
        createEnterTimeStartAndEnd(param,houseQuery);
        if (null!=CurrentContext.getCpyCode () && ""!=CurrentContext.getCpyCode ()){
            param.put("cpyCode",CurrentContext.getCpyCode());
        }

        return param;
    }

    /**
     * 楼层设置 #underlayer底楼
     *         #notUnderlayer 楼上
     * @param param
     * @param houseQuery
     */
    private void floorFlag(Map param,HouseQueryEntity houseQuery) {
        if (StringUtils.isNotEmpty(houseQuery.getFloorFlag())) {
            if (houseQuery.getFloorFlag().equals("1")) {
                param.put("underlayer", true);
            } else if (houseQuery.getFloorFlag().equals("2")) {
                param.put("notUnderlayer", true);
            }
        }
    }

    /**
     * 入住时间
     * @param param
     * @param houseQuery
     */
    private void createEnterTimeStartAndEnd(Map param,HouseQueryEntity houseQuery) {
        if (null != houseQuery.getStartEnterTime()) {
            param.put("startEnterTime", DateUtil.beginOfDay(houseQuery.getStartEnterTime()));
        }
        if (null !=houseQuery.getEndEnterTime()) {
            param.put("endEnterTime", DateUtil.endOfDay(houseQuery.getEndEnterTime()));
        }
    }

    /**
     * 关键字查询
     * @param param
     * @param keyword
     */
    public void keyword(Map param, String keyword) {
        if (StringUtils.isNotEmpty(keyword)) {
            //判断模糊搜索是否是数字
            boolean isNumber = StringUtils.isNumeric(keyword);
            if (isNumber && keyword.length() != 11&&keyword.length() != 8&&!keyword.startsWith("0")) {
                Integer keywordNumber = Integer.parseInt(keyword);
                param.put("keywordNumber", keywordNumber);
            } else {
                param.put("keyword", keyword);
            }
        }
    }

    /**
     * 创建时间
     * @param param
     * @param houseQuery
     */
    private void createTimeStartAndEnd(Map param,HouseQueryEntity houseQuery) {
        //创建时间
        if (null != houseQuery.getCreateTimeStart()) {
            param.put("createTimeStart", DateUtil.beginOfDay(houseQuery.getCreateTimeStart()));
        }
        //创建结束时间
        if (null != houseQuery.getCreateTimeEnd()) {
            param.put("createTimeEnd", DateUtil.endOfDay(houseQuery.getCreateTimeEnd()));
        }
    }

    /**
     * 查询类型
     * @param param
     * @param houseQuery
     */
    private void houseQueryType(Map param,HouseQueryEntity houseQuery) {
        if (StringUtils.isNotEmpty(houseQuery.getQueryType())) {

            switch (houseQuery.getQueryType()) {
                case HouseQueryType.MINE:
                    param.put("empCode", CurrentContext.getUserCode());
                    break;
                case HouseQueryType.COLLECT:
                    param.put("hasFavorite", Boolean.TRUE);
                    param.put("favEmpCode", CurrentContext.getUserCode());
                    break;
                case HouseQueryType.AGENT:
                    param.put("houseType", HouseType.AGENT);
                    break;
                case HouseQueryType.PLATFORM:
                    param.put("houseType", HouseType.PLATFORM);
                    break;
                case HouseQueryType.NEARBY:

                    break;

                case HouseQueryType.ALL:

                    break;
                case HouseQueryType.UNION:
                    param.put("hasCoo", Boolean.TRUE);
                    param.put("cooEmpCode", CurrentContext.getUserCode());
                    break;
                case HouseQueryType.DEPT:

                    UserEntity cur = (UserEntity) CurrentContext.getUserInfo();

                    String leaderCode = deptManager.getDeptEntity(cur.getOwnerDeptCode()).getLeaderCode();

                    if (leaderCode.equals(CurrentContext.getUserCode())){

                        List<String> deptCodes = deptManager.getDownDeptCode(cur.getOwnerDeptCode());

                        if (deptCodes!=null && deptCodes.size()>=1){
                            param.put("createDeptCodes",deptCodes);
                        }else {
                            param.put("createDeptCode", CurrentContext.getUserInfo().getOwnerDeptCode());
                        }
                    }else{
                        param.put("createDeptCode", CurrentContext.getUserInfo().getOwnerDeptCode());
                    }
                    break;

                default:
                    throw new RemoveStackBusinessException ("传入查询类型不存在");
            }
        }
    }

}
