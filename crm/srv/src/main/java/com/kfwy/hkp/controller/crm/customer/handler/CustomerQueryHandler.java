package com.kfwy.hkp.controller.crm.customer.handler;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.utils.BeanMapConvertUtils;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.controller.crm.customer.vo.CustomerVo;
import com.kfwy.hkp.crm.customer.enums.CusSortType;
import com.kfwy.hkp.crm.customer.enums.CustomerNotFollowupType;
import com.kfwy.hkp.crm.customer.enums.CustomerQueryType;
import com.kfwy.hkp.crm.customer.enums.CustomerType;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.dao.IDeptDbDao;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @AUTHOR 李文思汗
 * @Description 类描述:
 */
@Component
public class CustomerQueryHandler {
    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private IUserManager userManager;
    @Autowired
    private IDeptDbDao deptDbDao;

    public Map queryMap(CustomerVo customerVo) throws IllegalAccessException {
        Map param = new HashMap(16);
        if (customerVo == null) {
            customerVo = new CustomerVo();
        }
        param.putAll(BeanMapConvertUtils.convertExclude(customerVo));
        param.putAll(CusSortType.setCusSortType(customerVo.getCusSortType()));
        //客户管理搜索条件面板封装
        panelCondition(customerVo, param);
        //常用条件封装:包括未删除，自己分页等
        commonCondition(customerVo, param);


        return param;
    }

    /**
     * 客户查询条件面板封装
     *
     * @param customerVo
     * @param param
     */
    private void panelCondition(CustomerVo customerVo, Map param) {
        //客户查询类型条件
        queryTypeCondition(customerVo, param);
        // 客户手机号去除空格
        if (StringUtils.isNotEmpty(customerVo.getCusPhone())) {
            param.put("cusPhone", StringUtils.deleteWhitespace(customerVo.getCusPhone()));
        }
        //客户未跟进天数查询条件
        setCustomerNotFollowupCondition(customerVo.getCustomerNotFollowupType(), customerVo.getCustomerNotFollowupDay(), param);
        //需求楼层
        needLayerNum(customerVo, param);
    }

    /**
     * 客户查询类型条件封装
     *
     * @param customerVo
     * @param param
     */
    public void queryTypeCondition(CustomerVo customerVo, Map param) {
        if (customerVo.getQueryType() != null) {
            UserEntity cur = (UserEntity) CurrentContext.getUserInfo();
            switch (customerVo.getQueryType()) {
                //我的
                case CustomerQueryType.MINE:
                    param.put("empCode", CurrentContext.getUserCode());
                    break;
                //经纪人
                case CustomerQueryType.AGENT:
                    param.put("cusType", CustomerType.AGENT);
                    break;
                //查询平台类型客户
                case CustomerQueryType.PLATFORM:
                    param.put("cusType", CustomerType.PLATFORM);
                    break;
                // 收藏
                case CustomerQueryType.COLLECT:
                    param.put("hasCollect", true);
                    param.put("fEmpCode", CurrentContext.getUserCode());
                    break;
                //本部门
                case CustomerQueryType.DEPT:
                    queryDeptCus(customerVo, param);
                    break;
                //平台拉私
                case CustomerQueryType.PULL_PRIVATE:
                    param.put("empCode", CurrentContext.getUserCode());
                    param.put("cusType", CustomerType.PRIVATE);
                    break;
                //集中获客
                case CustomerQueryType.FOCUS_CUS:
                    param.put("fcEmpCode", CurrentContext.getUserCode());
                    param.put("cusType", CustomerType.FOCUS_CUS);
                    break;
            }
        }
    }

    private void queryDeptCus(CustomerVo customerVo, Map param) {
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();

        getLowerDeptCodes(param, cur);
        param.put("cusType", "4");
    }

    private void getLowerDeptCodes(Map param, UserEntity cur) {
        if (StringUtils.isNotEmpty(cur.getOwnerDeptCode())) {
            String leaderCode = deptManager.getDeptEntity(cur.getOwnerDeptCode()).getLeaderCode();

            if (leaderCode.equals(CurrentContext.getUserCode())) {

                List<String> deptCodes = deptManager.getDownDeptCode(cur.getOwnerDeptCode());

                if (deptCodes != null && deptCodes.size() >= 1) {
                    param.put("createDeptCodes", deptCodes);

                } else {
                    param.put("createDeptCode", CurrentContext.getUserInfo().getOwnerDeptCode());
                }
            } else {
                param.put("createDeptCode", CurrentContext.getUserInfo().getOwnerDeptCode());
            }
        }
    }

    /**
     * 客户未跟进天数查询条件
     *
     * @param param
     * @param
     * @param customerNotFollowupType
     */
    public void setCustomerNotFollowupCondition(Integer customerNotFollowupType, Integer customerNotFollowupDay, Map param) {
        Date date = new Date();
        if (customerNotFollowupType != null) {
            if (Objects.equals(customerNotFollowupType, CustomerNotFollowupType.ONEDAY)) {
                param.put("cusNotFolStart", DateFormatUtil.dayEnd(date));
                param.put("cusNotFolEnd", DateFormatUtil.dayBegin(date));
            } else if (Objects.equals(customerNotFollowupType, CustomerNotFollowupType.ONE_TO_THREE)) {
                param.put("cusNotFolStart", DateFormatUtil.dayEnd(DateFormatUtil.addDate(date, -2, "DD")));
                param.put("cusNotFolEnd", DateFormatUtil.dayBegin(DateFormatUtil.addDate(date, -4, "DD")));
            } else if (Objects.equals(customerNotFollowupType, CustomerNotFollowupType.THREE_TO_FIVE)) {
                param.put("cusNotFolStart", DateFormatUtil.dayEnd(DateFormatUtil.addDate(date, -4, "DD")));
                param.put("cusNotFolEnd", DateFormatUtil.dayBegin(DateFormatUtil.addDate(date, -6, "DD")));
            } else if (Objects.equals(customerNotFollowupType, CustomerNotFollowupType.FIVE_TO_TEN)) {
                param.put("cusNotFolStart", DateFormatUtil.dayEnd(DateFormatUtil.addDate(date, -6, "DD")));
                param.put("cusNotFolEnd", DateFormatUtil.dayBegin(DateFormatUtil.addDate(date, -11, "DD")));
            } else if (Objects.equals(customerNotFollowupType, CustomerNotFollowupType.MORE_THAN_TEN)) {
                param.put("cusNotFolStart", DateFormatUtil.dayEnd(DateFormatUtil.addDate(date, -11, "DD")));
            }
        } else if (customerNotFollowupDay != null) {
            param.put("cusNotFolStart", DateFormatUtil.dayEnd(DateFormatUtil.addDate(date, -(customerNotFollowupDay + 1), "DD")));
        }
    }

    /**
     * 需求楼层
     *
     * @param customerVo
     * @param param
     */
    private void needLayerNum(CustomerVo customerVo, Map param) {
        if (customerVo.getLayerNumName() != null) {
            if (StringUtils.isNotEmpty(customerVo.getLayerNumName())) {
                Integer layerNum = Integer.valueOf(customerVo.getLayerNum());
                if (layerNum > 1) {
                    param.put("layerNum", 2);
                } else {
                    param.put("layerNum", 1);
                }
            }
        }
    }

    /**
     * 常用条件
     *
     * @param customerVo
     * @param param
     */
    private void commonCondition(CustomerVo customerVo, Map param) {
        param.put("isDeleted", false);
        //自己控制分页
        param.put("self_page", true);
        param.put("userCode", CurrentContext.getUserCode());
        if (null != CurrentContext.getCpyCode() && "" != CurrentContext.getCpyCode()) {
            param.put("cpyCode", CurrentContext.getCpyCode());
        }

        if (customerVo.getCreateTimeStart() != null) {
            param.put("createTimeStart", DateFormatUtil.dayBegin(customerVo.getCreateTimeStart()));
        }

        if (customerVo.getCreateTimeEnd() != null) {
            param.put("createTimeEnd", DateFormatUtil.dayEnd(customerVo.getCreateTimeEnd()));
        }
    }

    /**
     * 未跟进天数查询条件
     *
     * @param param
     * @param customerVo
     */
    private void daysNotFollowupCondition(Map<String, Object> param, CustomerVo customerVo) {
        if (customerVo.getDaysNotFollowup() != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            Long daysNotFollowup = customerVo.getDaysNotFollowup();
            int Num = daysNotFollowup.intValue();
            int fuNum = 0 - Num;
            //时
            cal.set(Calendar.HOUR_OF_DAY, 00);
            //分
            cal.set(Calendar.MINUTE, 00);
            //秒
            cal.set(Calendar.SECOND, 00);
            cal.add(Calendar.DATE, fuNum);
            // 从一个 Calendar 对象中获取 Date 对象
            Date followupDate = cal.getTime();
            param.put("lastFollowupTime", followupDate);
        }
    }
}
