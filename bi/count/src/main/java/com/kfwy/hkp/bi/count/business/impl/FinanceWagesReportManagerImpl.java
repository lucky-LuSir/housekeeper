package com.kfwy.hkp.bi.count.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.business.IFinanceWagesReportManager;
import com.kfwy.hkp.bi.count.dao.IFinanceWagesReportDbDao;
import com.kfwy.hkp.bi.count.dto.FinanceWagesReportDto;
import com.kfwy.hkp.hrm.org.dept.dao.IDeptDbDao;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.user.business.IUserDataManager;
import com.kfwy.hkp.sys.user.dao.IUserDbDao;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.trade.order.entity.OrderInvoiceEntity;
import com.kfwy.hkp.trade.order.entity.OrderPaybackEntity;
import com.kfwy.hkp.trade.order.entity.OrderPercentageEntity;
import com.kfwy.hkp.trade.order.enums.MoneyPercentType;
import com.kfwy.hkp.trade.order.enums.OrderPercentageType;
import com.kfwy.hkp.trade.order.enums.OrderStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FinanceWagesReportManagerImpl extends AbstractManager<FinanceWagesReportDto> implements IFinanceWagesReportManager {
    @Autowired
    IFinanceWagesReportDbDao financeWagesReportDbDao;
    @Autowired
    OrderStatus orderStatus;
    @Autowired
    IUserDataManager userDataManager;
    @Autowired
    private IDeptDbDao deptDbDao;
    @Autowired
    private IUserDbDao userDbDao;
    @Override
    protected IMyBatisBaseDao<FinanceWagesReportDto, Long> getMyBatisDao() {
        return this.financeWagesReportDbDao;
    }

    private Map<String,DeptEntity> getDeptHashMapByDeptList(){
        List<DeptEntity> DeptList = deptDbDao.selectAll();
        Map map  = new HashMap();
        for (DeptEntity deptEntity:
             DeptList) {
            map.put(deptEntity.getDeptCode(),deptEntity);
        }
        return map;
    }
    private Map<String,UserEntity> getInternalUserHashMapByInternalUserList(){
        Map<String,Object> param=new HashMap<>();
        List<UserEntity> result=userDbDao.selectUserBasisInfoByMap(param);
        Map map  = new HashMap();
        for (UserEntity userEntity:
                result) {
            map.put(userEntity.getUserCode(),userEntity);
        }
        return map;
    }
    @Override
    public PageResult<FinanceWagesReportDto> financeWagesReportSearch(Map<String, Object> map, Integer start, Integer pageSize) {

        map.put("self_page","");
        PageResult<FinanceWagesReportDto> pageResult = this.financeWagesReportDbDao.selectByMap(map,start, pageSize,null,false);
        List<FinanceWagesReportDto> data = pageResult.getData();
        if(data != null && data != null) {
            data=conduct(data);
            pageResult.setData(data);
        }
        return pageResult;
    }
    @Override
    public List<FinanceWagesReportDto> query(Map<String,Object> param){

        param.put("self_page","");
        param.put("isDeleted",false);
        List<FinanceWagesReportDto> data = this.financeWagesReportDbDao.selectByMap(param);
        if(data != null && data != null) {
            data=conduct(data);
        }
        return data;
    }

    public List<FinanceWagesReportDto> conduct(List<FinanceWagesReportDto> data){
        Map<String,DeptEntity> DeptHashMap = getDeptHashMapByDeptList();
        //待优化
        Map<String,UserEntity> UserHashMap = getInternalUserHashMapByInternalUserList();
        for (int i = 0; i < data.size(); i++) {


            //通过开单人员Code,设置开单人员名，工号，部门名，领导名，领导工号,上级部门名
            if (null != data.get(i).getClerkCode()) {
                //开单人员对象
                UserEntity userEntity = UserHashMap.get(data.get(i).getClerkCode());
                DeptEntity deptEntity = DeptHashMap.get(data.get(i).getcDeptCode());

                //部门领导对象
                UserEntity leaderEntity = null;
                if (!ObjectUtils.isEmpty(deptEntity) && StringUtils.isNotEmpty(deptEntity.getLeaderCode())) {
                    leaderEntity = UserHashMap.get(deptEntity.getLeaderCode());
                }

                //开单人员部门上级部门
                DeptEntity parentDeptEntity = DeptHashMap.get(deptEntity.getParentCode());
                //开单人员所属部门
                data.get(i).setClerkDeptName(DeptHashMap.get(userEntity.getOwnerDeptCode()).getDeptName());
                //开单人员名
                data.get(i).setClerkName(userEntity.getUserName());
                //开单人员工号
                data.get(i).setClerkNumber(userEntity.getWorkNumber());
                //设置分店经理名
                if (leaderEntity != null) {
                    data.get(i).setCLeaderName(leaderEntity.getUserName());
                }
                //设置所属分店
                data.get(i).setCDeptName(deptEntity.getDeptName());
                //设置分店经理工号
                if (leaderEntity != null) {
                    data.get(i).setCLeaderNumber(leaderEntity.getWorkNumber());
                }
                //设置分店上级分店
                data.get(i).setParentDeptName(parentDeptEntity.getDeptName());
            }



            if (data.get(i).getSumTax() == null) {
                data.get(i).setSumTax(BigDecimal.ZERO);
            }
            //回款信息集合
            List<OrderPaybackEntity> payback = data.get(i).getOrderPaybackList();
            StringBuffer paybackDescription = new StringBuffer();
            BigDecimal sumPayment = BigDecimal.ZERO;
            StringBuffer paybackTime = new StringBuffer();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            if (payback != null) {
                for (int j = 0; j < payback.size(); j++) {
                    if (payback.get(j).getDescription() != null) {
                        paybackDescription.append(payback.get(j).getDescription());
                        if(!("".equals(payback.get(j).getDescription())) || !(j==(payback.size()-1))){
                            paybackDescription.append(";");
                        }
                    }
                    if (payback.get(j).getPayment() != null) {
                        sumPayment = sumPayment.add(payback.get(j).getPayment());
                    }
                    if (payback.get(j).getBackTime() != null) {
                        paybackTime.append(sdf.format(payback.get(j).getBackTime()));
                        if (!(j==(payback.size()-1))){
                            paybackTime.append(" _ ");
                        }
                    }
                }

                if (paybackDescription != null) {
                    //回款说明
                    data.get(i).setPaybackDescription(paybackDescription.toString());
                } else {
                    data.get(i).setPaybackDescription(";   ");
                }

                if (sumPayment != null) {
                    //实际从客户处回款金额
                    data.get(i).setSumPayment(sumPayment);
                } else {
                    data.get(i).setSumPayment(BigDecimal.ZERO);
                }

                if (paybackTime != null) {
                    //回款时间
                    data.get(i).setPayBackTime(paybackTime.toString());
                } else {
                    data.get(i).setPayBackTime("_");
                }
            } else {
                data.get(i).setPaybackDescription("");
                data.get(i).setSumPayment(BigDecimal.ZERO);
                data.get(i).setPayBackTime("");
            }
            data.get(i).setOrderStatus(orderStatus.produce().get(Integer.parseInt(data.get(i).getOrderStatus())).getName());
            //设置返佣
            data.get(i).setBackMoney(data.get(i).getCusRebate().add(data.get(i).getOwnerRebate()));
            //实收佣金
            BigDecimal actCommission = data.get(i).getActCommission();
            //开票税金
            BigDecimal sumTax = data.get(i).getSumTax();
            if (null == sumTax){
                sumTax = BigDecimal.ZERO;
            }
            //剔除后实际业绩
            BigDecimal comeCommission =actCommission;
            data.get(i).setComeCommission(comeCommission);
            //成交支付协议服务费
            data.get(i).setReceivableCost(data.get(i).getReceivableCus().add(data.get(i).getReceivableHos()));
            //订单发票集合
//            List<OrderInvoiceEntity> orderInvoiceList = data.get(i).getOrderInvoiceList();
//            int invoiceCount = 1;
//            int invoiceNum = -1;
            //循环订单分成业绩的业务人员

            List<OrderPercentageEntity> percentage = data.get(i).getOrderPercentageList();
            int num = -1;
            BigDecimal serviceRatio = BigDecimal.ZERO;  //服务分成比例
            BigDecimal divideAmount = BigDecimal.ZERO; //业务分成金额


            List<OrderPercentageEntity> serPe = new ArrayList<>();
            for (OrderPercentageEntity orderPercentageEntity:percentage){
                if(null!=orderPercentageEntity.getPercentageType()&&orderPercentageEntity.getMoneyPercentType().equals(MoneyPercentType.SERVICE_PERCENT)){
                    serviceRatio=serviceRatio.add(orderPercentageEntity.getPercentage());
                    if (orderPercentageEntity.getPercentageType().equals("6")){
                        serPe.add(orderPercentageEntity);
                    }
                    //集中获客分成
                    if (orderPercentageEntity.getPercentageType().equals(OrderPercentageType.CPYDIVIDE)){
                        data.get(i).setFocusCusCode(orderPercentageEntity.getPeEmpCode());
                        data.get(i).setFocusCusName(UserHashMap.get(orderPercentageEntity.getPeEmpCode()).getUserName());
                        data.get(i).setFocusCusNumber(UserHashMap.get(orderPercentageEntity.getPeEmpCode()).getWorkNumber());
//                            data.get(i).setFocusCusPeDeptName(DeptHashMap.get(data.get(i).get));
                        data.get(i).setFocusCusPercentage(orderPercentageEntity.getPercentage());
                        data.get(i).setFocusCusPerformance(comeCommission.multiply(orderPercentageEntity.getPercentage().divide(BigDecimal.valueOf(100))));
                    }
                    //活动经费
                    if (orderPercentageEntity.getPercentageType().equals(OrderPercentageType.ACTDIVIDE)){
                        data.get(i).setActCode(orderPercentageEntity.getPeEmpCode());
                        data.get(i).setActName(UserHashMap.get(orderPercentageEntity.getPeEmpCode()).getUserName());
                        data.get(i).setActNumber(UserHashMap.get(orderPercentageEntity.getPeEmpCode()).getWorkNumber());
//                            data.get(i).setActPeDeptName(DeptHashMap.get(data.get(i).get));
                        data.get(i).setActPercentage(orderPercentageEntity.getPercentage());
                        data.get(i).setActPerformance(comeCommission.multiply(orderPercentageEntity.getPercentage().divide(BigDecimal.valueOf(100))));                        }

                }

            }

            if (null!=serPe&&serPe.size()>0){
                for (int m=0;m<serPe.size();m++){
                    //目前客服不存在两个以上
                    OrderPercentageEntity sPe = serPe.get(m);
                    if (m==0){
                        data.get(i).setCusSerNumber(UserHashMap.get(sPe.getPeEmpCode()).getWorkNumber());
                        data.get(i).setCusSerName(UserHashMap.get(sPe.getPeEmpCode()).getUserName());
                        data.get(i).setCusSerPeDeptName(DeptHashMap.get(sPe.getPeDeptCode()).getDeptName());
                        data.get(i).setCusSerDeptName(DeptHashMap.get(UserHashMap.get(sPe.getPeEmpCode()).getOwnerDeptCode()).getDeptName());
                        data.get(i).setCusSerPercentage(sPe.getPercentage());
                        data.get(i).setCusSerPerformance(comeCommission.multiply(sPe.getPercentage().divide(BigDecimal.valueOf(100))));
                    }
                    if (m==1){
                        data.get(i).setCusSerNumber1(UserHashMap.get(sPe.getPeEmpCode()).getWorkNumber());
                        data.get(i).setCusSerName1(UserHashMap.get(sPe.getPeEmpCode()).getUserName());
                        data.get(i).setCusSerPeDeptName1(DeptHashMap.get(sPe.getPeDeptCode()).getDeptName());
                        data.get(i).setCusSerDeptName1(DeptHashMap.get(UserHashMap.get(sPe.getPeEmpCode()).getOwnerDeptCode()).getDeptName());
                        data.get(i).setCusSerPercentage1(sPe.getPercentage());
                        data.get(i).setCusSerPerformance1(comeCommission.multiply(sPe.getPercentage().divide(BigDecimal.valueOf(100))));
                    }
                }
            }

            divideAmount=comeCommission.multiply(BigDecimal.valueOf(1).subtract(serviceRatio.divide(BigDecimal.valueOf(100))));



            int n = -1; //成交人分成序号
            int count =1;
            for (int j=0;j<percentage.size();j++){
                if (percentage.get(j).getPeEmpCode().equals(data.get(i).getClerkCode())&&!percentage.get(j).getPercentageType().equals(OrderPercentageType.CUSSERVICE)){
                    n =j;
                    data.get(i).setPerformance(divideAmount.multiply(percentage.get(j).getPercentage().divide(BigDecimal.valueOf(100))));
                    //分成
                    data.get(i).setPercentage(percentage.get(j).getPercentage());
                    data.get(i).setPeDeptName(DeptHashMap.get(percentage.get(j).getPeDeptCode()).getDeptName());
                    break;
                }
            }
            for (int k=0;k<percentage.size();k++){
                if(percentage.get(k).getMoneyPercentType().equals(MoneyPercentType.SERVICE_PERCENT)){
                    continue;
                }
                BigDecimal performance = BigDecimal.ZERO;
                BigDecimal proportion = BigDecimal.ZERO;
                String clerkName = UserHashMap.get(percentage.get(k).getPeEmpCode()).getUserName();
                String clerkDeptName = DeptHashMap.get(UserHashMap.get(percentage.get(k).getPeEmpCode()).getOwnerDeptCode()).getDeptName();
                String workNumber = UserHashMap.get(percentage.get(k).getPeEmpCode()).getWorkNumber();
                String peDeptName = DeptHashMap.get(percentage.get(k).getPeDeptCode()).getDeptName();
                if (percentage.get(k).getPercentage() != null) {
                    proportion = percentage.get(k).getPercentage();
                }

//
                performance = divideAmount.multiply(percentage.get(k).getPercentage().divide(BigDecimal.valueOf(100)));



                if (n != k && count == 1) {
//                            data.get(i).setClerkCode1(clerkCode);
                    data.get(i).setPercentage1(proportion);
                    data.get(i).setPerformance1(performance);
                    data.get(i).setClerkName1(clerkName);
                    data.get(i).setClerkDeptName1(clerkDeptName);
                    data.get(i).setClerkNumber1(workNumber);
                    data.get(i).setPeDeptName1(peDeptName);
//                            data.get(i).setPeDeptCode1(peDeptCode);
                    count++;
                    continue;
                } else if (n != k && count == 2) {
                    data.get(i).setPercentage2(proportion);
                    data.get(i).setPerformance2(performance);
                    data.get(i).setClerkName2(clerkName);
                    data.get(i).setClerkDeptName2(clerkDeptName);
                    data.get(i).setClerkNumber2(workNumber);
                    data.get(i).setPeDeptName2(peDeptName);
                    count++;
                    continue;
                } else if (n != k && count == 3) {
                    data.get(i).setPercentage3(proportion);
                    data.get(i).setPerformance3(performance);
                    data.get(i).setClerkName3(clerkName);
                    data.get(i).setClerkDeptName3(clerkDeptName);
                    data.get(i).setClerkNumber3(workNumber);
                    data.get(i).setPeDeptName3(peDeptName);
                    count++;
                    continue;
                } else if (n != k && count == 4) {
                    data.get(i).setPercentage4(proportion);
                    data.get(i).setPerformance4(performance);
                    data.get(i).setClerkName4(clerkName);
                    data.get(i).setClerkDeptName4(clerkDeptName);
                    data.get(i).setClerkNumber4(workNumber);
                    data.get(i).setPeDeptName4(peDeptName);
                    count++;
                    continue;
                } else if (n != k && count == 5) {
                    data.get(i).setPercentage5(proportion);
                    data.get(i).setPerformance5(performance);
                    data.get(i).setClerkName5(clerkName);
                    data.get(i).setClerkDeptName5(clerkDeptName);
                    data.get(i).setClerkNumber5(workNumber);
                    data.get(i).setPeDeptName5(peDeptName);
                    count++;
                    continue;
                }

            }
            /***********************************************************************************************/
        }
//        setName(data,UserHashMap,DeptHashMap);

        return data;
    }

    private void setName(List<FinanceWagesReportDto> financeWagesReportDtos,Map<String,UserEntity> userNameMap,Map<String,DeptEntity> deptNameMap){
        if(null!=financeWagesReportDtos){
            for (FinanceWagesReportDto financeWagesReportDto: financeWagesReportDtos){
                //开单人姓名 工号
                financeWagesReportDto.setClerkName(userNameMap.get(financeWagesReportDto.getClerkCode()).getUserName());
                financeWagesReportDto.setClerkNumber(userNameMap.get(financeWagesReportDto.getClerkCode()).getWorkNumber());

                //订单状态   成交分店  分店经理工号  分店经理名称 上级分店

                financeWagesReportDto.setOrderStatus(orderStatus.produce().get(Integer.parseInt(financeWagesReportDto.getOrderStatus())).getName());
                if(null!=financeWagesReportDto.getcDeptCode()){
                    financeWagesReportDto.setCDeptName(deptNameMap.get(financeWagesReportDto.getcDeptCode()).getDeptName());
                        financeWagesReportDto.setCLeaderName(StringUtils.isEmpty(deptNameMap.get(financeWagesReportDto.getcDeptCode()).getLeaderCode())?"":userNameMap.get(deptNameMap.get(financeWagesReportDto.getcDeptCode()).getLeaderCode()).getUserName());
                        financeWagesReportDto.setCLeaderNumber(StringUtils.isEmpty(deptNameMap.get(financeWagesReportDto.getcDeptCode()).getLeaderCode())?"":userNameMap.get(deptNameMap.get(financeWagesReportDto.getcDeptCode()).getLeaderCode()).getWorkNumber());
                        financeWagesReportDto.setParentDeptName(deptNameMap.get(deptNameMap.get(financeWagesReportDto.getcDeptCode()).getParentCode()).getDeptName());
                        financeWagesReportDto.setClerkDeptName(deptNameMap.get(userNameMap.get(financeWagesReportDto.getClerkCode()).getOwnerDeptCode()).getDeptName());
                   if (null!=financeWagesReportDto.getPeDeptCode()){
                       financeWagesReportDto.setPeDeptName(deptNameMap.get(financeWagesReportDto.getPeDeptCode()).getDeptName());
                   }
                }
                //分成人1姓名工号
                if(null!=financeWagesReportDto.getClerkCode1()&&!financeWagesReportDto.getClerkCode1().equals("")){
                    financeWagesReportDto.setClerkName1(userNameMap.get(financeWagesReportDto.getClerkCode1()).getUserName());
                    financeWagesReportDto.setClerkNumber1(userNameMap.get(financeWagesReportDto.getClerkCode1()).getWorkNumber());
                    financeWagesReportDto.setClerkDeptName1(deptNameMap.get(userNameMap.get(financeWagesReportDto.getClerkCode1()).getOwnerDeptCode()).getDeptName());
                    if (null!=financeWagesReportDto.getPeDeptCode1()){
                        financeWagesReportDto.setPeDeptName1(deptNameMap.get(financeWagesReportDto.getPeDeptCode1()).getDeptName());

                    }
                    if(null!=financeWagesReportDto.getClerkCode2()&&!financeWagesReportDto.getClerkCode2().equals("")){
                        financeWagesReportDto.setClerkName2(userNameMap.get(financeWagesReportDto.getClerkCode2()).getUserName());
                        financeWagesReportDto.setClerkNumber2(userNameMap.get(financeWagesReportDto.getClerkCode2()).getWorkNumber());
                        financeWagesReportDto.setClerkDeptName2(deptNameMap.get(userNameMap.get(financeWagesReportDto.getClerkCode2()).getOwnerDeptCode()).getDeptName());
                        if (null!=financeWagesReportDto.getPeDeptCode2()){
                            financeWagesReportDto.setPeDeptName2(deptNameMap.get(financeWagesReportDto.getPeDeptCode2()).getDeptName());

                        }
                        if(null!=financeWagesReportDto.getClerkCode3()&&!financeWagesReportDto.getClerkCode3().equals("")){
                            financeWagesReportDto.setClerkName3(userNameMap.get(financeWagesReportDto.getClerkCode3()).getUserName());
                            financeWagesReportDto.setClerkNumber3(userNameMap.get(financeWagesReportDto.getClerkCode3()).getWorkNumber());
                            financeWagesReportDto.setClerkDeptName3(deptNameMap.get(userNameMap.get(financeWagesReportDto.getClerkCode3()).getOwnerDeptCode()).getDeptName());
                            if (null!=financeWagesReportDto.getPeDeptCode3()){
                                financeWagesReportDto.setPeDeptName3(deptNameMap.get(financeWagesReportDto.getPeDeptCode3()).getDeptName());

                            }
                            if(null!=financeWagesReportDto.getClerkCode4()&&!financeWagesReportDto.getClerkCode4().equals("")){
                                financeWagesReportDto.setClerkName4(userNameMap.get(financeWagesReportDto.getClerkCode4()).getUserName());
                                financeWagesReportDto.setClerkNumber4(userNameMap.get(financeWagesReportDto.getClerkCode4()).getWorkNumber());
                                financeWagesReportDto.setClerkDeptName4(deptNameMap.get(userNameMap.get(financeWagesReportDto.getClerkCode4()).getOwnerDeptCode()).getDeptName());
                                if (null!=financeWagesReportDto.getPeDeptCode4()){
                                    financeWagesReportDto.setPeDeptName4(deptNameMap.get(financeWagesReportDto.getPeDeptCode4()).getDeptName());

                                }
                                if(null!=financeWagesReportDto.getClerkCode5()&&!financeWagesReportDto.getClerkCode5().equals("")){
                                    financeWagesReportDto.setClerkName5(userNameMap.get(financeWagesReportDto.getClerkCode5()).getUserName());
                                    financeWagesReportDto.setClerkNumber5(userNameMap.get(financeWagesReportDto.getClerkCode5()).getWorkNumber());
                                    financeWagesReportDto.setClerkDeptName5(deptNameMap.get(userNameMap.get(financeWagesReportDto.getClerkCode5()).getOwnerDeptCode()).getDeptName());
                                    if (null!=financeWagesReportDto.getPeDeptCode5()){
                                        financeWagesReportDto.setPeDeptName5(deptNameMap.get(financeWagesReportDto.getPeDeptCode5()).getDeptName());

                                    }
                                }
                            }
                        }
                    }

                }

            }
        }
    }
}
