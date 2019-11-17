package com.kfwy.hkp.trade.order.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.hkp.common.utils.ListUtils;
import com.kfwy.hkp.crm.customer.business.ICustomerManager;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.houseowner.business.IHouseownerManager;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerEntity;
import com.kfwy.hkp.sys.file.business.IFileRelationManager;
import com.kfwy.hkp.trade.order.business.IOrderManager;
import com.kfwy.hkp.trade.order.business.IOrderPercentageApplyManager;
import com.kfwy.hkp.trade.order.business.IOrderUpdateApplyManager;
import com.kfwy.hkp.trade.order.dao.IOrderPercentageApplyDbDao;
import com.kfwy.hkp.trade.order.dao.IOrderUpdateApplyDao;
import com.kfwy.hkp.trade.order.entity.OrderEntity;
import com.kfwy.hkp.trade.order.entity.OrderPercentageApplyEntity;
import com.kfwy.hkp.trade.order.entity.OrderPercentageEntity;
import com.kfwy.hkp.trade.order.entity.OrderUpdateApplyEntity;
import com.kfwy.hkp.trade.order.enums.MoneyPercentType;
import com.kfwy.hkp.trade.order.enums.OrderUpdateApplyStatusType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 订单修改申请 manager实现
 */
@Service
public class OrderUpdateApplyManagerImpl extends AbstractManager<OrderUpdateApplyEntity> implements IOrderUpdateApplyManager {

    @Autowired
    private IOrderUpdateApplyDao orderUpdateApplyDao;
    @Autowired
    private ICustomerManager customerManager;
    @Autowired
    private IHouseownerManager houseownerManager;
    @Autowired
    private IOrderManager orderManager;
    @Autowired
    private IOrderPercentageApplyManager orderPercentageApplyManager;
    @Autowired
    private IFileRelationManager fileRelationManager;
    @Autowired
    private IOrderPercentageApplyDbDao orderPercentageApplyDbDao;


    @Override
    protected IMyBatisBaseDao<OrderUpdateApplyEntity, Long> getMyBatisDao() {
        return this.orderUpdateApplyDao;
    }

    @Override
    protected void beforeUpdate(OrderUpdateApplyEntity orderUpdateApplyEntity) {

    }

    @Override
    public int create(OrderUpdateApplyEntity orderUpdateApplyEntity) {


        //业务校验
        //校验0 是否提交申请 提交了 就不要再提交了哦
        Map<String, Object> map = new HashMap<>(2);
        map.put("orderCode",orderUpdateApplyEntity.getOrderCode());
        map.put("isDeleted",Boolean.FALSE);
        OrderUpdateApplyEntity history = orderUpdateApplyDao.selectUniqueByMap(map);
        if(history != null){
            throw new BusinessException("您提交的申请还在审核中，请不要重复提交！");
        }
        //校验1,校验分成人为空
        List<OrderPercentageApplyEntity> percentPersonSure = orderUpdateApplyEntity.getOrderPercentageApplyEntityList();
        if (percentPersonSure == null || percentPersonSure.size() == 0) {
            throw new BusinessException("分成人列表个数为0, 至少需要1个分成人!");
        }
        //校验2,校验分成人分成百分比和为100
        BigDecimal chenckTotalPercent = new BigDecimal("0.00");
        for (OrderPercentageApplyEntity orderPercentOne : percentPersonSure) {
            BigDecimal tempBigDecimal = orderPercentOne.getPercentage();
            chenckTotalPercent = chenckTotalPercent.add(tempBigDecimal);
        }
        BigDecimal hundredNum = new BigDecimal("100");
        boolean numFlag = false;
        //compareTo 如果指定的数与参数相等返回0 , 小于返回-1,大于返回1
        numFlag = (chenckTotalPercent.compareTo(hundredNum) == 0);
        if (numFlag == false) {
            throw new BusinessException("目前分成百分比之和为:" + chenckTotalPercent + ",等于100才能提交!");
        }
        // 客户手机号
        if (StringUtils.isEmpty(orderUpdateApplyEntity.getCusPhone())) {
            CustomerEntity customerEntity = customerManager.findOne("cusCode", orderUpdateApplyEntity.getCusCode());
            if (customerEntity == null) {
                throw new BusinessException("根据客户编码cusCode未查询到客户,传入有效客户编码!");
            }
            orderUpdateApplyEntity.setCusPhone(customerEntity.getCusPhone());
        }
        // 业主手机号
        if (StringUtils.isEmpty(orderUpdateApplyEntity.getHouseownerPhone())) {
            HouseownerEntity houseownerEntity = houseownerManager.queryHouseownerByHouseCode(orderUpdateApplyEntity.getHouseCode());
            if (houseownerEntity == null) {
                throw new BusinessException("根据房源编码houseCode未查询到业主,传入有效房源编码!");
            }
            orderUpdateApplyEntity.setHouseownerPhone(houseownerEntity.getHouseownerPhone());
        }
        //实际可分佣金总额,预备
        BigDecimal receivableHos = orderUpdateApplyEntity.getReceivableHos();
        BigDecimal receivableCus = orderUpdateApplyEntity.getReceivableCus();
        BigDecimal cusRebate = orderUpdateApplyEntity.getCusRebate();
        BigDecimal ownerRebate = orderUpdateApplyEntity.getOwnerRebate();
        BigDecimal cusParttimeMoney = orderUpdateApplyEntity.getCusParttimeMoney();
        BigDecimal ownerParttimeMoney = orderUpdateApplyEntity.getOwnerParttimeMoney();

        //总开单佣金总额(没有减去返佣)
        BigDecimal commision = new BigDecimal("0.00");
        commision = receivableHos.add(receivableCus);
        orderUpdateApplyEntity.setCommission(commision);

        //实际可分佣金总额计算
        BigDecimal actualCommision = new BigDecimal("0.00");
        actualCommision = receivableHos.add(receivableCus);
        actualCommision = actualCommision.subtract(cusRebate);
        actualCommision = actualCommision.subtract(ownerRebate);
        actualCommision = actualCommision.subtract(cusParttimeMoney);
        actualCommision = actualCommision.subtract(ownerParttimeMoney);

        orderUpdateApplyEntity.setActualCommision(actualCommision);

        //判断1,金额是否发生改变,如果改变就去变更子表分成人表,没有就不变更
        //判断1.1 查出老的订单,和新的订单比较6个金额字段
        String ordCode = orderUpdateApplyEntity.getOrderCode();
        OrderEntity oldOrdObj = this.orderManager.detail(ordCode);
        if (oldOrdObj == null) {
            throw new BusinessException("该订单不存在,核实编号:" + ordCode);
        }
        //老的6个金额字段
        BigDecimal oldReceivableHos = oldOrdObj.getReceivableHos();
        BigDecimal oldReceivableCus = oldOrdObj.getReceivableCus();
        BigDecimal oldCusRebate = oldOrdObj.getCusRebate();
        BigDecimal oldOwnerRebate = oldOrdObj.getOwnerRebate();
        BigDecimal oldCusParttimeMoney = oldOrdObj.getCusParttimeMoney();
        BigDecimal oldOwnerParttimeMoney = oldOrdObj.getOwnerParttimeMoney();
        boolean editSubFlag = false;
        if (receivableHos.compareTo(oldReceivableHos) != 0) {
            editSubFlag = true;
        }
        if (receivableCus.compareTo(oldReceivableCus) != 0) {
            editSubFlag = true;
        }
        if (cusRebate.compareTo(oldCusRebate) != 0) {
            editSubFlag = true;
        }
        if (ownerRebate.compareTo(oldOwnerRebate) != 0) {
            editSubFlag = true;
        }
        if (cusParttimeMoney.compareTo(oldCusParttimeMoney) != 0) {
            editSubFlag = true;
        }
        if (ownerParttimeMoney.compareTo(oldOwnerParttimeMoney) != 0) {
            editSubFlag = true;
        }
        orderUpdateApplyEntity.setApplyCode(CodeGenUtils.generate());
        //开启都修改分成人
        editSubFlag = true;
        if (editSubFlag) {
            try {
                checkAndEditSubList(oldOrdObj, orderUpdateApplyEntity);
            } catch (ParseException e) {
                throw new BusinessException("修改分成异常，修改失败");
            }
        }

        // 修改保存关联的文件,暂不修改
//        if (ListUtils.isNotEmpty(orderEntity.getFileRelationEntityList())) {
//            for (FileRelationEntity fileRelationEntity : orderEntity.getFileRelationEntityList()) {
//                fileRelationEntity.setOwnerCode(orderEntity.getOrderCode());
//                fileRelationManager.create(fileRelationEntity);
//            }
//        }
        if(orderUpdateApplyEntity.getUpdateFieldsList() != null && orderUpdateApplyEntity.getUpdateFieldsList().size()>0){
            fillUpdateFields(oldOrdObj,orderUpdateApplyEntity);
        }

        orderUpdateApplyEntity.setLastUpdateTime(new Date());
        orderUpdateApplyEntity.setLastUpdateCode(CurrentContext.getUserCode());

        orderUpdateApplyEntity.setApplyStatus(OrderUpdateApplyStatusType.MANAGERAUDIT);
        int result = orderUpdateApplyDao.insert(orderUpdateApplyEntity);
        return result;
    }

    private void fillUpdateFields(OrderEntity oldOrdObj, OrderUpdateApplyEntity orderUpdateApplyEntity) {

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer updateContent = new StringBuffer();
        //修改了哪些字段
        List<String> updateFielsList = orderUpdateApplyEntity.getUpdateFieldsList();
        updateFielsList = new ArrayList<>(new HashSet<>(updateFielsList));

        for(String uf:updateFielsList){
            if(uf.equals("empName")){
                updateContent.append("将开单人"+oldOrdObj.getEmpName()+"修改为"+orderUpdateApplyEntity.getEmpName()+",");
            }
            if(uf.equals("orderTime")){
                updateContent.append("将开单时间").append(fmt.format(oldOrdObj.getOrderTime())).append("修改为").append(fmt.format(orderUpdateApplyEntity.getOrderTime())).append(",");
            }
            if(uf.equals("contractStart")){
                String oldContractStart = oldOrdObj.getContractStart() == null ? "":fmt.format(oldOrdObj.getContractStart());
                String newContractStart = orderUpdateApplyEntity.getContractStart() == null ? "":fmt.format(orderUpdateApplyEntity.getContractStart());
                updateContent.append("将合同开始时间").append(oldContractStart).append("修改为").append(newContractStart).append(",");
            }
            if(uf.equals("contractEnd")){
                String oldContractEnd = oldOrdObj.getContractEnd() == null ? "":fmt.format(oldOrdObj.getContractEnd());
                String newContractEnd = orderUpdateApplyEntity.getContractEnd() == null ? "":fmt.format(orderUpdateApplyEntity.getContractEnd());
                updateContent.append("将合同结束时间").append(oldContractEnd).append("修改为").append(newContractEnd).append(",");
            }
            if(uf.equals("monthCount")){
                updateContent.append("将交月份数").append(oldOrdObj.getMonthCount()).append("修改为").append(orderUpdateApplyEntity.getMonthCount()).append(",");
            }
            if(uf.equals("deposit")){
                updateContent.append("将押金").append(oldOrdObj.getDeposit()).append("修改为").append(orderUpdateApplyEntity.getDeposit()).append(",");
            }
            if(uf.equals("acreage")){
                updateContent.append("将出租面积").append(oldOrdObj.getAcreage()).append("修改为").append(orderUpdateApplyEntity.getAcreage()).append(",");
            }
            if(uf.equals("price")){
                updateContent.append("将出租价格").append(oldOrdObj.getPrice()).append("修改为").append(orderUpdateApplyEntity.getPrice()).append(",");
            }
            if(uf.equals("unit")){
                updateContent.append("将价格单位").append(oldOrdObj.getUnit()).append("修改为").append(orderUpdateApplyEntity.getUnit()).append(",");
            }
            if(uf.equals("orderStatus")){
                updateContent.append("将订单状态").append(oldOrdObj.getOrderStatusName()).append("修改为").append(orderUpdateApplyEntity.getOrderStatusName()).append(",");
            }
            if(uf.equals("receivableHos")){
                updateContent.append("将业主佣金").append(oldOrdObj.getReceivableHos()).append("修改为").append(orderUpdateApplyEntity.getReceivableHos()).append(",");
            }
            if(uf.equals("ownerRebate")){
                updateContent.append("将业主返佣").append(oldOrdObj.getOwnerRebate()).append("修改为").append(orderUpdateApplyEntity.getOwnerRebate()).append(",");
            }
            if(uf.equals("ownerParttimeMoney")){
                updateContent.append("将业主兼职分钱").append(oldOrdObj.getOwnerParttimeMoney()).append("修改为").append(orderUpdateApplyEntity.getOwnerParttimeMoney()).append(",");
            }
            if(uf.equals("receivableCus")){
                updateContent.append("将客户佣金").append(oldOrdObj.getReceivableCus()).append("修改为").append(orderUpdateApplyEntity.getReceivableCus()).append(",");
            }
            if(uf.equals("cusRebate")){
                updateContent.append("将客户返佣").append(oldOrdObj.getCusRebate()).append("修改为").append(orderUpdateApplyEntity.getCusRebate()).append(",");
            }
            if(uf.equals("cusParttimeMoney")){
                updateContent.append("将客户兼职分钱").append(oldOrdObj.getCusParttimeMoney()).append("修改为").append(orderUpdateApplyEntity.getCusParttimeMoney()).append(",");
            }
            if(uf.equals("cusCommissionNum")){
                updateContent.append("将客户佣金确认书编号").append(oldOrdObj.getCusCommissionNum()==null?"":oldOrdObj.getCusCommissionNum()).append("修改为").append(orderUpdateApplyEntity.getCusCommissionNum()).append(",");
            }
            if(uf.equals("ownerCommissionNum")){
                updateContent.append("将业主佣金确认书编号").append(oldOrdObj.getOwnerCommissionNum()==null?"":oldOrdObj.getOwnerCommissionNum()).append("修改为").append(orderUpdateApplyEntity.getOwnerCommissionNum()).append(",");
            }
        }

        String oldCusImgName = oldOrdObj.getCusImageName() == null?"":oldOrdObj.getCusImageName();
        String newCusImgName = orderUpdateApplyEntity.getCusImageName() == null?"":orderUpdateApplyEntity.getCusImageName();
        if(!oldCusImgName.equals(newCusImgName)){
            updateFielsList.add("cusImageName");
            updateContent.append("客户佣金确认书修改了,");
        }
        String oldOwnImgName = oldOrdObj.getOwnerImageName() == null?"":oldOrdObj.getOwnerImageName();
        String newOwnImgName = orderUpdateApplyEntity.getOwnerImageName() == null?"":orderUpdateApplyEntity.getOwnerImageName();;
        if(!oldOwnImgName.equals(newOwnImgName)){
            updateFielsList.add("ownerImageName");
            updateContent.append("业主佣金确认书修改了,");
        }
        List<OrderPercentageEntity> oldPercentage = oldOrdObj.getOrderPercentageEntityList();
        List<OrderPercentageApplyEntity> newPercentage = orderUpdateApplyEntity.getOrderPercentageApplyEntityList();
        List<String> oldPeEmpCode = new ArrayList<>();
        List<String> newPeEmpCode = new ArrayList<>();
        for(int i=0;i<oldPercentage.size();i++){
            oldPeEmpCode.add(oldPercentage.get(i).getPeEmpCode());
        }
        for(int j=0;j<newPercentage.size();j++){
            newPeEmpCode.add(newPercentage.get(j).getPeEmpCode());
        }

        for(int i=0;i<oldPercentage.size();i++){
            if(!newPeEmpCode.contains(oldPercentage.get(i).getPeEmpCode())){
                updateContent.append("删除了分成人-,").append(oldPercentage.get(i).getPeDeptName()).append(oldPercentage.get(i).getPeEmpName()).append(",");
                updateFielsList.add("orderPercentageEntityList");
            }
        }
        for(int j=0;j<newPercentage.size();j++){
            if(!oldPeEmpCode.contains(newPercentage.get(j).getPeEmpCode())){
                updateContent.append("添加了分成人->,").append(newPercentage.get(j).getPeDeptName()).
                        append(newPercentage.get(j).getPeEmpName()).append(",");
                updateFielsList.add("orderPercentageEntityList");
            }
        }

        for(int i=0;i<oldPercentage.size();i++){
            for(int j=0;j<newPercentage.size();j++){
                if(oldPercentage.get(i).getPeEmpCode().equals(newPercentage.get(j).getPeEmpCode())){
                    if(!oldPercentage.get(i).getPercentageType().equals(newPercentage.get(i).getPercentageType())){
                        updateContent.append("修改了分成人的分成类型->").append(oldPercentage.get(j).getPeDeptName()).append(oldPercentage.get(j).getPeEmpName()).
                                append(oldPercentage.get(i).getPercentageName()).append("修改为").append(newPercentage.get(i).getPercentageName()).append(",");
                        updateFielsList.add("orderPercentageEntityList");
                    }
                    if(oldPercentage.get(i).getPercentage().compareTo(newPercentage.get(i).getPercentage()) != 0){
                        updateContent.append("修改了分成人的比例->").append(oldPercentage.get(j).getPeDeptName()).append(oldPercentage.get(j).getPeEmpName()).
                                append(oldPercentage.get(i).getPercentage()).append("修改为").append(newPercentage.get(i).getPercentage()).append(",");
                        updateFielsList.add("orderPercentageEntityList");
                    }
                }
            }
        }
        updateFielsList = new ArrayList<>(new HashSet<>(updateFielsList));

        orderUpdateApplyEntity.setUpdateContent(updateContent.toString());
        orderUpdateApplyEntity.setUpdateFields(StringUtils.join(updateFielsList,","));

    }

    @Override
    public OrderUpdateApplyEntity orderUpdateApplyDetail(String orderCode) {
        OrderUpdateApplyEntity orderUpdateApplyEntity = orderUpdateApplyDao.detail(orderCode);
        if (orderUpdateApplyEntity == null) {
            throw new BusinessException("查询不到订单编号:" + orderCode + ",的订单分成详情");
        }
        Map param = new HashMap<String, Object>(16);
        param.put("applyCode", orderUpdateApplyEntity.getApplyCode());
        List<OrderPercentageApplyEntity> percentListAll = orderPercentageApplyManager.findByMap(param);
        //客服分成列表
        List<OrderPercentageApplyEntity> percentListCusServ = new ArrayList<>();
        //分成人分成列表
        List<OrderPercentageApplyEntity> percentListDividePerson = new ArrayList<>();
        for (OrderPercentageApplyEntity orderPercentageOne : percentListAll) {
            String moneyPercentType = orderPercentageOne.getMoneyPercentType();
            if (moneyPercentType.equals(MoneyPercentType.SERVICE_PERCENT)) {
                percentListCusServ.add(orderPercentageOne);
            } else if (moneyPercentType.equals(MoneyPercentType.PERSON_PERCENT)) {
                percentListDividePerson.add(orderPercentageOne);
            }
        }

        orderUpdateApplyEntity.setCusServicePercentageApplyEntityList(percentListCusServ);
        orderUpdateApplyEntity.setOrderPercentageApplyEntityList(percentListDividePerson);
        //计算总的客服分成佣金
        orderUpdateApplyEntity.setActualCusServCommision(orderUpdateApplyEntity.getActualCommision().subtract(orderUpdateApplyEntity.getActualBranchCommision()));
        param.clear();
        param.put("ownerCode", orderUpdateApplyEntity.getOrderCode());
        orderUpdateApplyEntity.setFileRelationEntityList(fileRelationManager.findByMap(param));

        return orderUpdateApplyEntity;
    }

    @Transactional
    @Override
    public void auditFinish(OrderUpdateApplyEntity orderUpdateApplyEntity) {
        Date currentDate = new Date();
        orderUpdateApplyEntity.setLastUpdateTime(currentDate);
        orderUpdateApplyEntity.setLastUpdateCode(CurrentContext.getUserCode());

        orderUpdateApplyEntity.setIsDeleted(Boolean.TRUE);
        orderUpdateApplyDao.update(orderUpdateApplyEntity);

        // 订单分成表 删除
        Map<String, Object> map = new HashMap<>();
        map.put("orderCode",orderUpdateApplyEntity.getOrderCode());
        map.put("isDeleted",Boolean.FALSE);
        List<OrderPercentageApplyEntity> percentList = orderPercentageApplyDbDao.selectByMap(map);
        for(OrderPercentageApplyEntity p:percentList){
            p.setIsDeleted(Boolean.TRUE);
            p.setLastUpdateCode(CurrentContext.getUserCode());
            p.setLastUpdateTime(currentDate);
        }
        orderPercentageApplyDbDao.batchUpdate(percentList);
    }


    @Transactional(rollbackFor = Exception.class)
    public void checkAndEditSubList(OrderEntity oldEntity, OrderUpdateApplyEntity newEntity) throws ParseException {
        //增加实际分成佣金字段10-12
        List<OrderPercentageEntity> telephoneOrderPer = oldEntity.getCusServicePercentageEntityList();
        //校验3,校验前端客服数量和后端查询客服相同
        //校验3,校验前端客服数量和后端查询客服相同 end

        BigDecimal teleSumPercent = new BigDecimal("0.00");
        for (int i = 0; i < telephoneOrderPer.size(); i++) {
            BigDecimal shuOne = telephoneOrderPer.get(i).getPercentage();
            teleSumPercent = teleSumPercent.add(shuOne);
        }
        BigDecimal actualTele = new BigDecimal("0.00");
        BigDecimal actualCommision = newEntity.getActualCommision();
        actualTele = actualCommision.multiply(teleSumPercent);
        actualTele = actualTele.divide(new BigDecimal("100"));
        BigDecimal actualBranchCommision = new BigDecimal("0.00");
        actualBranchCommision = actualCommision.subtract(actualTele);

        newEntity.setActualBranchCommision(actualBranchCommision);

        // 预收款日期为订单合同开始时间后10天
        setTime(newEntity);

        List<OrderPercentageApplyEntity> percentPersonPart = newEntity.getOrderPercentageApplyEntityList();
        for (OrderPercentageApplyEntity orderPercentOne : percentPersonPart) {
            orderPercentOne.setMoneyPercentType("2");
            orderPercentOne.setMoneyPercentTypeName("分成人分成");
        }

        // 保存客服订单分成
        if (ListUtils.isNotEmpty(newEntity.getCusServicePercentageApplyEntityList())) {
            for(OrderPercentageApplyEntity orderPercentageEntity:newEntity.getCusServicePercentageApplyEntityList()){
                orderPercentageEntity.setOrderCode(newEntity.getOrderCode());
                orderPercentageEntity.setOrderTime(newEntity.getOrderTime());
                orderPercentageEntity.setApplyCode(newEntity.getApplyCode());
                // 设置分成业绩
                //原来没有减去返佣和兼职分钱orderPercentageEntity.setProfit(orderEntity.getCommission().multiply(orderPercentageEntity.getPercentage()).divide(new BigDecimal(100)));
                orderPercentageEntity.setProfit(newEntity.getActualCommision().multiply(orderPercentageEntity.getPercentage()).divide(new BigDecimal(100)));
                //客服分成 分成所属部门为订单所属部门
                orderPercentageApplyManager.createServicePe(orderPercentageEntity,newEntity.getEmpCode());
            }
        }
        // 保存订单分成
        if (ListUtils.isNotEmpty(percentPersonPart)) {
            for (OrderPercentageApplyEntity orderPercentageEntity : percentPersonPart) {
                orderPercentageEntity.setOrderCode(newEntity.getOrderCode());
                orderPercentageEntity.setOrderTime(newEntity.getOrderTime());
                orderPercentageEntity.setApplyCode(newEntity.getApplyCode());
                // 设置分成业绩
                //原来没有减去返佣和兼职分钱orderPercentageEntity.setProfit(orderEntity.getCommission().multiply(orderPercentageEntity.getPercentage()).divide(new BigDecimal(100)));
                orderPercentageEntity.setProfit(newEntity.getActualBranchCommision().multiply(orderPercentageEntity.getPercentage()).divide(new BigDecimal(100)));

                orderPercentageApplyManager.create(orderPercentageEntity);
            }

        }
    }

    public void setTime(OrderUpdateApplyEntity newEntity) {
        setExpectPamentTime(newEntity);
    }

    public void setExpectPamentTime(OrderUpdateApplyEntity orderUpdateApplyEntity) {
        if (orderUpdateApplyEntity.getContractStart() != null) {
            Calendar calender = Calendar.getInstance();
            calender.setTime(orderUpdateApplyEntity.getContractStart());
            calender.add(Calendar.DATE, 10);
            orderUpdateApplyEntity.setExpectPaymentTime(calender.getTime());
        }
    }


}

