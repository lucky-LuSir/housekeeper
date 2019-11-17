package com.kfwy.hkp.bi.count.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.business.IHrWageReportManager;
import com.kfwy.hkp.bi.count.dao.IHrWageReportDbDao;
import com.kfwy.hkp.bi.count.dto.HrWageReportDto;
import com.kfwy.hkp.hrm.org.dept.dao.IDeptDbDao;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.user.business.IUserDataManager;
import com.kfwy.hkp.sys.user.dao.IUserDbDao;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.trade.order.entity.OrderInvoiceEntity;
import com.kfwy.hkp.trade.order.entity.OrderPercentageEntity;
import com.kfwy.hkp.trade.order.enums.MoneyPercentType;
import com.kfwy.hkp.trade.order.enums.OrderPercentageType;
import com.kfwy.hkp.trade.order.enums.OrderStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author lzp
 */
@Service
public class HrReportManagerImpl extends AbstractManager<HrWageReportDto> implements IHrWageReportManager {
    @Autowired
    IHrWageReportDbDao hrWageReportDbDao;
    @Autowired
    OrderStatus orderStatus;
    @Autowired
    IUserDataManager userDataManager;
    @Autowired
    private IDeptDbDao deptDbDao;
    @Autowired
    private IUserDbDao userDbDao;

    @Override
    protected IMyBatisBaseDao<HrWageReportDto, Long> getMyBatisDao() {
        return this.hrWageReportDbDao;
    }

    private Map<String, DeptEntity> getDeptHashMapByDeptList() {
        List<DeptEntity> DeptList = deptDbDao.selectAll();
        Map map = new HashMap();
        for (DeptEntity deptEntity :
                DeptList) {
            map.put(deptEntity.getDeptCode(), deptEntity);
        }
        return map;
    }

    private Map<String, UserEntity> getInternalUserHashMapByInternalUserList() {
        Map<String, Object> param = new HashMap<>();
        List<UserEntity> result = userDbDao.selectUserBasisInfoByMap(param);
        Map map = new HashMap();
        for (UserEntity userEntity :
                result) {
            map.put(userEntity.getUserCode(), userEntity);
        }
        return map;
    }

    @Override
    public PageResult<HrWageReportDto> hrWageReportSearch(Map<String, Object> map, Integer start, Integer pageSize) {
        Map<String, DeptEntity> DeptHashMap = getDeptHashMapByDeptList();
        Map<String, UserEntity> UserHashMap = getInternalUserHashMapByInternalUserList();
        map.put("self_page", true);
        PageResult<HrWageReportDto> pageResult = this.getMyBatisDao().selectByStatement("selectInvoiceByMap", map, start, pageSize);
        List<HrWageReportDto> data = pageResult.getData();
        if (data != null && data != null) {
            for (int i = 0; i < data.size(); i++) {
                //通过开单人员Code,设置开单人员名，工号，部门名，领导名，领导工号,上级部门名
                if (null != data.get(i).getClerkCode()) {
                    //开单人员对象
                    UserEntity userEntity = UserHashMap.get(data.get(i).getClerkCode());
                    DeptEntity deptEntity = DeptHashMap.get(data.get(i).getDealDeptCode());

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
                        data.get(i).setDealLeaderName(leaderEntity.getUserName());
                    }
                    //设置所属分店
                    data.get(i).setDealDeptName(deptEntity.getDeptName());
                    //设置分店经理工号
                    if (leaderEntity != null) {
                        data.get(i).setDealLeaderNumber(leaderEntity.getWorkNumber());
                    }
                    //设置分店上级分店
                    data.get(i).setParentDeptName(parentDeptEntity.getDeptName());
                }
                //设置返佣
                data.get(i).setBackMoney(data.get(i).getCusRebate().add(data.get(i).getOwnerRebate()));
                //实收佣金
//                BigDecimal actCommission = data.get(i).getActCommission();
                //开票税金
                BigDecimal taxDeductions = data.get(i).getTaxDeductions();
                if (null == taxDeductions) {
                    taxDeductions = BigDecimal.ZERO;
                }
                BigDecimal commissionDeductions =data.get(i).getCommissionDeductions();
                if (null==commissionDeductions){
                    commissionDeductions = BigDecimal.ZERO;
                }

                BigDecimal payPayment = data.get(i).getPayPayment();
                BigDecimal comeCommission = BigDecimal.ZERO;
                if (null!=payPayment&&payPayment.compareTo(BigDecimal.ZERO)!=0){
                    //剔除后实际业绩
                    comeCommission = data.get(i).getPayPayment().subtract(taxDeductions.add(commissionDeductions));
                }
                data.get(i).setComeCommission(comeCommission);
                //成交支付协议服务费
                data.get(i).setReceivableCost(data.get(i).getReceivableCus().add(data.get(i).getReceivableHos()));
                if (data.get(i).getPayPayment() == null) {
                    data.get(i).setPayPayment(BigDecimal.ZERO);
                }
                data.get(i).setOrderStatus(orderStatus.produce().get(Integer.parseInt(data.get(i).getOrderStatus())).getName());
                //订单发票集合
//                List<OrderInvoiceEntity> orderInvoiceList = data.get(i).getOrderInvoiceList();
//                int invoiceCount = 1;
//                int invoiceNum = -1;
//                //循环订单发票集合，判断有几个需要分成业绩的业务人员
//                for (int z = 0; z < orderInvoiceList.size(); z++) {
//                    //发票税金
//                    String invoiceCode = "";
//                    BigDecimal invoiceUnitAmount = BigDecimal.ZERO;
//                    BigDecimal invoiceTax = BigDecimal.ZERO;
//                    BigDecimal invoiceTotalPrice = BigDecimal.ZERO;
//                    Integer invoiceTaxRate = 0;
//                    Date invoiceTime = null;
//                    if (StringUtils.isNotEmpty(orderInvoiceList.get(z).getInvoiceCode())) {
//                        //发票号
//                        invoiceCode = orderInvoiceList.get(z).getInvoiceCode();
//                    }
//                    if (orderInvoiceList.get(z).getInvoiceTax() != null) {
//                        //税额
//                        invoiceTax = orderInvoiceList.get(z).getInvoiceTax();
//                    }
//                    if (orderInvoiceList.get(z).getInvoiceTaxRate() != null) {
//                        //税率
//                        invoiceTaxRate = orderInvoiceList.get(z).getInvoiceTaxRate();
//                    }
//                    if (orderInvoiceList.get(z).getInvoiceUnitAmount() != null) {
//                        //发票金额
//                        invoiceUnitAmount = orderInvoiceList.get(z).getInvoiceUnitAmount();
//                    }
//                    if (orderInvoiceList.get(z).getInvoiceTotalPrice() != null) {
//                        //发票总额（发票金额+税额）
//                        invoiceTotalPrice = orderInvoiceList.get(z).getInvoiceTotalPrice();
//                    }
//                    if (orderInvoiceList.get(z).getInvoiceTime() != null) {
//                        //开票时间
//                        invoiceTime = orderInvoiceList.get(z).getInvoiceTime();
//                    }
//
//                    if (invoiceNum == -1 && invoiceCount == 1) {
//                        data.get(i).setInvoiceCode(invoiceCode);
//                        data.get(i).setInvoiceTaxRate(invoiceTaxRate);
//                        data.get(i).setInvoiceTax(invoiceTax);
//                        data.get(i).setInvoiceUnitAmount(invoiceUnitAmount);
//                        data.get(i).setInvoiceTotalPrice(invoiceTotalPrice);
//                        data.get(i).setInvoiceTime(invoiceTime);
//                        invoiceNum = z;
//                        continue;
//                    } else if (invoiceNum != z && invoiceCount == 1) {
//                        data.get(i).setInvoiceCode1(invoiceCode);
//                        data.get(i).setInvoiceTaxRate1(invoiceTaxRate);
//                        data.get(i).setInvoiceTax1(invoiceTax);
//                        data.get(i).setInvoiceUnitAmount1(invoiceUnitAmount);
//                        data.get(i).setInvoiceTotalPrice1(invoiceTotalPrice);
//                        data.get(i).setInvoiceTime1(invoiceTime);
//                        invoiceCount++;
//                        continue;
//                    } else if (invoiceNum != z && invoiceCount == 2) {
//                        data.get(i).setInvoiceCode2(invoiceCode);
//                        data.get(i).setInvoiceTaxRate2(invoiceTaxRate);
//                        data.get(i).setInvoiceTax2(invoiceTax);
//                        data.get(i).setInvoiceUnitAmount2(invoiceUnitAmount);
//                        data.get(i).setInvoiceTotalPrice2(invoiceTotalPrice);
//                        data.get(i).setInvoiceTime2(invoiceTime);
//                        invoiceCount++;
//                        continue;
//                    } else if (invoiceNum != z && invoiceCount == 3) {
//                        data.get(i).setInvoiceCode3(invoiceCode);
//                        data.get(i).setInvoiceTaxRate3(invoiceTaxRate);
//                        data.get(i).setInvoiceTax3(invoiceTax);
//                        data.get(i).setInvoiceUnitAmount3(invoiceUnitAmount);
//                        data.get(i).setInvoiceTotalPrice3(invoiceTotalPrice);
//                        data.get(i).setInvoiceTime3(invoiceTime);
//                        invoiceCount++;
//                        continue;
//                    } else if (invoiceNum != z && invoiceCount == 4) {
//                        data.get(i).setInvoiceCode4(invoiceCode);
//                        data.get(i).setInvoiceTaxRate4(invoiceTaxRate);
//                        data.get(i).setInvoiceTax4(invoiceTax);
//                        data.get(i).setInvoiceUnitAmount4(invoiceUnitAmount);
//                        data.get(i).setInvoiceTotalPrice4(invoiceTotalPrice);
//                        data.get(i).setInvoiceTime4(invoiceTime);
//                        invoiceCount++;
//                        continue;
//                    } else if (invoiceNum != z && invoiceCount == 5) {
//                        data.get(i).setInvoiceCode5(invoiceCode);
//                        data.get(i).setInvoiceTaxRate5(invoiceTaxRate);
//                        data.get(i).setInvoiceTax5(invoiceTax);
//                        data.get(i).setInvoiceUnitAmount5(invoiceUnitAmount);
//                        data.get(i).setInvoiceTotalPrice5(invoiceTotalPrice);
//                        data.get(i).setInvoiceTime5(invoiceTime);
//                        invoiceCount++;
//                        continue;
//                    } else if (invoiceNum != z && invoiceCount == 6) {
//                        data.get(i).setInvoiceCode6(invoiceCode);
//                        data.get(i).setInvoiceTaxRate6(invoiceTaxRate);
//                        data.get(i).setInvoiceTax6(invoiceTax);
//                        data.get(i).setInvoiceUnitAmount6(invoiceUnitAmount);
//                        data.get(i).setInvoiceTotalPrice6(invoiceTotalPrice);
//                        data.get(i).setInvoiceTime6(invoiceTime);
//                        invoiceCount++;
//                        continue;
//                    } else if (invoiceNum != z && invoiceCount == 7) {
//                        data.get(i).setInvoiceCode7(invoiceCode);
//                        data.get(i).setInvoiceTaxRate7(invoiceTaxRate);
//                        data.get(i).setInvoiceTax7(invoiceTax);
//                        data.get(i).setInvoiceUnitAmount7(invoiceUnitAmount);
//                        data.get(i).setInvoiceTotalPrice7(invoiceTotalPrice);
//                        data.get(i).setInvoiceTime7(invoiceTime);
//                        invoiceCount++;
//                        continue;
//                    }
//                }
                List<OrderPercentageEntity> percentage = data.get(i).getOrderPercentageList();

                BigDecimal serviceRatio = BigDecimal.ZERO;  //服务分成比例
                BigDecimal divideAmount = BigDecimal.ZERO; //业务分成金额


                /**
                 * 08.22计算公式
                 * 服务分成为  公司获客10%  客服1% 大区活动经费1%
                 * 业务分成合计100%
                 * 剔除后实际业绩-服务分成比例*剔除后实际业绩=业务分成业绩
                 */

                //客服数量
//                int kfNum=0;
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
                //客服分成


                    int n = -1; //成交人分成序号
                    int count =1;
                    for (int j=0;j<percentage.size();j++){
                        if (percentage.get(j).getPeEmpCode().equals(data.get(i).getClerkCode())&&!percentage.get(j).getPercentageType().equals(OrderPercentageType.CUSSERVICE)){
                            n =j;
                            data.get(i).setPerformance(divideAmount.multiply(percentage.get(j).getPercentage().divide(BigDecimal.valueOf(100))));
                            //分成
                            data.get(i).setPercentage(percentage.get(j).getPercentage());
                            data.get(i).setClerkPeDeptName(DeptHashMap.get(percentage.get(j).getPeDeptCode()).getDeptName());
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
                            data.get(i).setClerkPeDeptName1(peDeptName);
//                            data.get(i).setPeDeptCode1(peDeptCode);
                            count++;
                            continue;
                        } else if (n != k && count == 2) {
                            data.get(i).setPercentage2(proportion);
                            data.get(i).setPerformance2(performance);
                            data.get(i).setClerkName2(clerkName);
                            data.get(i).setClerkDeptName2(clerkDeptName);
                            data.get(i).setClerkNumber2(workNumber);
                            data.get(i).setClerkPeDeptName2(peDeptName);
                            count++;
                            continue;
                        } else if (n != k && count == 3) {
                            data.get(i).setPercentage3(proportion);
                            data.get(i).setPerformance3(performance);
                            data.get(i).setClerkName3(clerkName);
                            data.get(i).setClerkDeptName3(clerkDeptName);
                            data.get(i).setClerkNumber3(workNumber);
                            data.get(i).setClerkPeDeptName3(peDeptName);
                            count++;
                            continue;
                        } else if (n != k && count == 4) {
                            data.get(i).setPercentage4(proportion);
                            data.get(i).setPerformance4(performance);
                            data.get(i).setClerkName4(clerkName);
                            data.get(i).setClerkDeptName4(clerkDeptName);
                            data.get(i).setClerkNumber4(workNumber);
                            data.get(i).setClerkPeDeptName4(peDeptName);
                            count++;
                            continue;
                        } else if (n != k && count == 5) {
                            data.get(i).setPercentage5(proportion);
                            data.get(i).setPerformance5(performance);
                            data.get(i).setClerkName5(clerkName);
                            data.get(i).setClerkDeptName5(clerkDeptName);
                            data.get(i).setClerkNumber5(workNumber);
                            data.get(i).setClerkPeDeptName5(peDeptName);
                            count++;
                            continue;
                        }

                    }

//                }



//                int count = 1;
//                int num = -1;
//                for (int j = 0; j < percentage.size(); j++) {
//                    /**
//                     * 业绩 = （提成金额 - 税金） *  分成比例 /  100
//                     * 提成金额 percentage.get(j).getProfit()
//                     * 税金 data.get(i).getSumTax()
//                     * 分成比例 percentage.get(j).getPercentage()
//                     */
//                    BigDecimal performance = BigDecimal.ZERO;
//                    BigDecimal proportion = BigDecimal.ZERO;
//                    BigDecimal sumInvoiceTax = BigDecimal.ZERO;
//                    if (percentage.get(j).getPercentage() != null) {
//                        proportion = percentage.get(j).getPercentage();
//                    }
//
//                    if (data.get(i).getSumTax() != null) {
//                        sumInvoiceTax = data.get(i).getComeCommission();
//                        performance = sumInvoiceTax.multiply(proportion.divide(BigDecimal.valueOf(100)));
//                    } else {
//                        performance = percentage.get(j).getProfit();
//                    }
//
//                    //可分成业务员名
//                    String clerkName = "";
//                    //可分成业务员工号
//                    String clerkNumber = "";
//                    String clerkPeDeptName = "";
//                    //分成业务员所属部门
//                    String clerkDeptName = "";
//                    if (StringUtils.isNotEmpty(percentage.get(j).getPeEmpCode())) {
//                        clerkNumber = UserHashMap.get(percentage.get(j).getPeEmpCode()).getWorkNumber();
//                        clerkName = UserHashMap.get(percentage.get(j).getPeEmpCode()).getUserName();
//                        clerkDeptName = DeptHashMap.get(UserHashMap.get(percentage.get(j).getPeEmpCode()).getOwnerDeptCode()).getDeptName();
//                        clerkPeDeptName = DeptHashMap.get(percentage.get(j).getPeDeptCode()).getDeptName();
//                    }
//                    if (clerkName.equals(data.get(i).getClerkName())) {
//                        //开单人员业绩
//                        data.get(i).setPerformance(performance);
//                        //分成
//                        data.get(i).setPercentage(proportion);
//                        data.get(i).setClerkPeDeptName(clerkPeDeptName);
//                        num = j;
//                        continue;
//                    } else if (num != j && count == 1) {
//                        data.get(i).setClerkName1(clerkName);
//                        data.get(i).setClerkNumber1(clerkNumber);
//                        data.get(i).setPercentage1(proportion);
//                        data.get(i).setClerkDeptName1(clerkDeptName);
//                        data.get(i).setPerformance1(performance);
//                        data.get(i).setClerkPeDeptName1(clerkPeDeptName);
//                        count++;
//                        continue;
//                    } else if (num != j && count == 2) {
//                        data.get(i).setClerkName2(clerkName);
//                        data.get(i).setClerkNumber2(clerkNumber);
//                        data.get(i).setPercentage2(proportion);
//                        data.get(i).setClerkDeptName2(clerkDeptName);
//                        data.get(i).setPerformance2(performance);
//                        data.get(i).setClerkPeDeptName2(clerkPeDeptName);
//                        count++;
//                        continue;
//                    } else if (num != j && count == 3) {
//                        data.get(i).setClerkName3(clerkName);
//                        data.get(i).setClerkNumber3(clerkNumber);
//                        data.get(i).setPercentage3(proportion);
//                        data.get(i).setClerkDeptName3(clerkDeptName);
//                        data.get(i).setPerformance3(performance);
//                        data.get(i).setClerkPeDeptName3(clerkPeDeptName);
//                        count++;
//                        continue;
//                    } else if (num != j && count == 4) {
//                        data.get(i).setClerkName4(clerkName);
//                        data.get(i).setClerkNumber4(clerkNumber);
//                        data.get(i).setPercentage4(proportion);
//                        data.get(i).setClerkDeptName4(clerkDeptName);
//                        data.get(i).setClerkPeDeptName4(clerkPeDeptName);
//                        count++;
//                        continue;
//                    } else if (num != j && count == 5) {
//                        data.get(i).setClerkName5(clerkName);
//                        data.get(i).setClerkNumber5(clerkNumber);
//                        data.get(i).setPercentage5(proportion);
//                        data.get(i).setClerkDeptName5(clerkDeptName);
//                        data.get(i).setPerformance5(performance);
//                        data.get(i).setClerkPeDeptName5(clerkPeDeptName);
//                        count++;
//                        continue;
//                    }
//                }
            }
            pageResult.setData(dealData(data));
        }
        return pageResult;
    }

    /**
     * 处理数据
     *
     * @param data
     * @return
     */
    public List<HrWageReportDto> dealData(List<HrWageReportDto> data) {
        if (null != data && data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (i == 0) {
                    continue;
                } else {
//                    if (!data.get(i).getOrderCode().equals(data.get(i - 1).getOrderCode())) {
//                        data.get(i).setComeCommission(
//                                (null != data.get(i).getPayPayment() ? data.get(i).getPayPayment() : BigDecimal.ZERO).subtract(
//                                        null != data.get(i).getBackMoney() ? data.get(i).getBackMoney() : BigDecimal.ZERO).subtract(
//                                        null != data.get(i).getCusRebate() ? data.get(i).getCusRebate() : BigDecimal.ZERO).subtract(
//                                        null != data.get(i).getOwnerRebate() ? data.get(i).getOwnerRebate() : BigDecimal.ZERO));
//                    } else {
//                        data.get(i).setComeCommission(null != data.get(i).getPayPayment() ? data.get(i).getPayPayment() : BigDecimal.ZERO);
//                        data.get(i).setBackMoney(BigDecimal.ZERO);
//                        data.get(i).setCusRebate(BigDecimal.ZERO);
//                        data.get(i).setOwnerRebate(BigDecimal.ZERO);
//                    }


                    if (data.get(i).getOrderCode().equals(data.get(i-1).getOrderCode())){
                        data.get(i).setBackMoney(BigDecimal.ZERO);
                        data.get(i).setCusRebate(BigDecimal.ZERO);
                        data.get(i).setOwnerRebate(BigDecimal.ZERO);
                    }
                }
            }
        }
        return data;
    }
}
