package com.kfwy.hkp.trade.order.business.impl;

import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.hkp.common.utils.ListUtils;
import com.kfwy.hkp.cooperate.dao.ICooperateDbDao;
import com.kfwy.hkp.cooperate.entity.CooperateEntity;
import com.kfwy.hkp.cooperate.enums.CooStatus;
import com.kfwy.hkp.crm.customer.business.ICustomerManager;
import com.kfwy.hkp.crm.customer.business.ICustomerPushManager;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerFollowupDbDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerPushDbDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerUpdownDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerFollowupEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerPushEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerUpdownEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerFollowupType;
import com.kfwy.hkp.crm.customer.enums.CustomerStatus;
import com.kfwy.hkp.crm.customer.enums.CustomerType;
import com.kfwy.hkp.crm.houseowner.business.IHouseownerManager;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerEntity;
import com.kfwy.hkp.hos.house.dao.IHouseDbDao;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hos.house.enums.HouseFileUse;
import com.kfwy.hkp.hos.house.enums.HouseStatus;
import com.kfwy.hkp.hrm.org.dept.IDeptProfitShareManager;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.dao.ICusServiceDbDao;
import com.kfwy.hkp.hrm.org.dept.entity.CusServiceEntity;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.hrm.org.dept.entity.DeptProfitShareEntity;
import com.kfwy.hkp.hrm.org.dept.enums.ProfitShareType;
import com.kfwy.hkp.sys.file.business.IFileManager;
import com.kfwy.hkp.sys.file.business.IFileRelationManager;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;
import com.kfwy.hkp.sys.user.business.IUserDataManager;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.dao.IUserRelationDbDao;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.entity.UserRelationEntity;
import com.kfwy.hkp.trade.order.business.IOrderManager;
import com.kfwy.hkp.trade.order.business.IOrderPercentageManager;
import com.kfwy.hkp.trade.order.dao.*;
import com.kfwy.hkp.trade.order.entity.*;
import com.kfwy.hkp.trade.order.enums.MoneyPercentType;
import com.kfwy.hkp.trade.order.enums.OrderPercentageType;
import com.kfwy.hkp.trade.order.enums.OrderStatus;
import com.kfwy.hkp.trade.wage.dao.ICommissionRatioDbDao;
import com.kfwy.hkp.trade.wage.entity.CommissionRatioEntity;
import com.kfwy.hkp.trade.wage.enums.CommissionType;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author zjp
 * @date 2018/8/15
 */
@Service
public class OrderManagerImpl extends AbstractManager<OrderEntity> implements IOrderManager {

    @Autowired
    private IOrderDbDao orderDbDao;
    @Autowired
    private IOrderPercentageDbDao orderPercentageDbDao;
    @Autowired
    private ICustomerManager customerManager;
    @Autowired
    private ICustomerDbDao customerDbDao;
    @Autowired
    private IHouseDbDao houseDbDao;
    @Autowired
    private IHouseownerManager houseownerManager;
    @Autowired
    private ICooperateDbDao cooperateDbDao;
    @Autowired
    private IOrderPercentageManager orderPercentageManager;
    @Autowired
    private IFileRelationManager fileRelationManager;
    @Autowired
    private IUserManager userManager;
    @Autowired
    private IUserDataManager userDataManager;
    @Autowired
    private ICusServiceDbDao cusServiceDbDao;
    @Autowired
    private ICommissionRatioDbDao commissionRatioDbDao;
    @Autowired
    private IUserRelationDbDao userRelationDbDao;
    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private IFileManager fileManager;
    @Autowired
    private IOrderBackDeductionsDbDao orderBackDeductionsDbDao;
    @Autowired
    private IOrderPaybackDbDao orderPaybackDbDao;
    @Autowired
    private IOrderInvoiceDbDao orderInvoiceDbDao;
    @Autowired
    private IDeptProfitShareManager profitShareManager;
    @Autowired
    private ICustomerPushDbDao customerPushDbDao;
    @Autowired
    private ICustomerFollowupDbDao customerFollowupDbDao;
    @Autowired
    private ICustomerUpdownDbDao customerUpdownDbDao;

    @Override
    protected IMyBatisBaseDao<OrderEntity, Long> getMyBatisDao() {
        return this.orderDbDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(OrderEntity orderEntity) {
        //只能创建本月订单校验
        String thisMonth = DateUtils.formatDate(new Date(), "yyyy-MM");
        String orderMonth=DateUtils.formatDate(orderEntity.getOrderTime(), "yyyy-MM");
        if (!thisMonth.equals(orderMonth)){
            throw new BusinessException("业绩时间只能录入本月，如又问题请联系财务");
        }

        orderEntity.setOrderCode(CodeGenUtils.generate());
        //设置成交部门
        UserEntity userEntity = userManager.selectUniqueByProp(orderEntity.getEmpCode());
        orderEntity.setDeptCode(userEntity.getOwnerDeptCode());
        //业务校验
        //region 校验1,校验分成人为空
        List<OrderPercentageEntity> percentPersonSure = orderEntity.getOrderPercentageEntityList();

        if (ListUtils.isEmpty(percentPersonSure)) {
            throw new BusinessException("分成人列表个数为0, 至少需要1个分成人!");
        }
        //endregion

        //region 校验2,校验分成人分成百分比和为100
        BigDecimal chenckTotalPercent = new BigDecimal("0.00");
        for (OrderPercentageEntity orderPercentOne : percentPersonSure) {
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
        //endregion

        //region 客户手机号
        setCusPhone(orderEntity);
        //endregion

        //region 业主手机号
        setHosOwnerPhone(orderEntity);
        //endregion

        //region 实际可分佣金总额,预备
        BigDecimal receivableHos = orderEntity.getReceivableHos();
        BigDecimal receivableCus = orderEntity.getReceivableCus();
        BigDecimal cusRebate = orderEntity.getCusRebate();
        BigDecimal ownerRebate = orderEntity.getOwnerRebate();
        BigDecimal cusParttimeMoney = orderEntity.getCusParttimeMoney();
        BigDecimal ownerParttimeMoney = orderEntity.getOwnerParttimeMoney();
        //endregion

        //region 总开单佣金总额(业主佣金加上客户佣金，没有减去返佣)
        BigDecimal commision = new BigDecimal("0.00");
        commision = receivableHos.add(receivableCus);
        orderEntity.setCommission(commision);
        //endregion

        //region 实际可分佣金总额actualCommision计算(业主佣金+客户佣金-客户返佣-业主返佣-客户兼职分钱-业主兼职分钱)
        BigDecimal actualCommision = new BigDecimal("0.00");
        actualCommision = receivableHos.add(receivableCus);
        actualCommision = actualCommision.subtract(cusRebate);
        actualCommision = actualCommision.subtract(ownerRebate);
        actualCommision = actualCommision.subtract(cusParttimeMoney);
        actualCommision = actualCommision.subtract(ownerParttimeMoney);
        orderEntity.setActualCommision(actualCommision);
        //endregion

        //增加实际分成佣金字段
        OrderEntity telephoneCus = new OrderEntity();
        String empcode = orderEntity.getEmpCode();
        telephoneCus = this.querycusservice(empcode,orderEntity.getCusCode());
        //客服分成明细
        List<OrderPercentageEntity> telephoneOrderPer = telephoneCus.getCusServicePercentageEntityList();

        //region 校验3,校验前端客服数量和后端查询客服相同
        int appSize = 0;
        if (ListUtils.isNotEmpty(orderEntity.getCusServicePercentageEntityList())) {
            appSize = orderEntity.getCusServicePercentageEntityList().size();
        }

        int dbSize = 0;
        if (ListUtils.isNotEmpty(telephoneOrderPer)) {
            dbSize = telephoneOrderPer.size();
        }
        if (appSize != dbSize) {
            throw new BusinessException("前台页面分成客服数为:" + appSize + ",后台分成客服数为:" + dbSize + ",相等才能提交,请点击预览!");
        }
        //endregion


        BigDecimal teleSumPercent = new BigDecimal("0.00");
        for (int i = 0; i < telephoneOrderPer.size(); i++) {
            BigDecimal shuOne = telephoneOrderPer.get(i).getPercentage();
            teleSumPercent = teleSumPercent.add(shuOne);
        }
        BigDecimal actualTele = new BigDecimal("0.00");
        actualTele = actualCommision.multiply(teleSumPercent);
        actualTele = actualTele.divide(new BigDecimal("100"));
        BigDecimal actualBranchCommision = new BigDecimal("0.00");
        actualBranchCommision = actualCommision.subtract(actualTele);

        orderEntity.setActualBranchCommision(actualBranchCommision);


        //List<OrderPercentageEntity> percentListAll = new ArrayList<OrderPercentageEntity>();
        //暂时不用,加客服固定分成//percentListAll.addAll(telephoneOrderPer);

        // 预收款日期为订单合同开始时间后10天
        setExpectPamentTime(orderEntity);

        List<OrderPercentageEntity> percentPersonPart = orderEntity.getOrderPercentageEntityList();
        for (OrderPercentageEntity orderPercentOne : percentPersonPart) {
            orderPercentOne.setMoneyPercentType("2");
            orderPercentOne.setMoneyPercentTypeName("分成人分成");
        }

        // 保存客服订单分成
        if (ListUtils.isNotEmpty(telephoneOrderPer)) {


            for (OrderPercentageEntity orderPercentageEntity : telephoneOrderPer) {
                orderPercentageEntity.setOrderCode(orderEntity.getOrderCode());
                orderPercentageEntity.setOrderTime(orderEntity.getOrderTime());
                // 设置分成业绩
                if (orderPercentageEntity.getProfit() == null || orderPercentageEntity.getProfit().equals(0)) {
                    //原来没有减去返佣和兼职分钱orderPercentageEntity.setProfit(orderEntity.getCommission().multiply(orderPercentageEntity.getPercentage()).divide(new BigDecimal(100)));
                    orderPercentageEntity.setProfit(orderEntity.getActualCommision().multiply(orderPercentageEntity.getPercentage()).divide(new BigDecimal(100)));
                }

                String percentageType = orderPercentageEntity.getPercentageType();
                if(OrderPercentageType.CUSSERVICE.equals(percentageType)
                        ||OrderPercentageType.CPYDIVIDE.equals(percentageType)
                        ||OrderPercentageType.ACTDIVIDE.equals(percentageType)){
                    orderPercentageManager.create(orderPercentageEntity);
                }

            }
        }

        // 保存订单分成
        if (ListUtils.isNotEmpty(percentPersonPart)) {

            List<OrderPercentageEntity> orderPercentageEntityList =new ArrayList<>();
            for (OrderPercentageEntity orderPercentageEntity : percentPersonPart) {
                orderPercentageEntity.setOrderCode(orderEntity.getOrderCode());
                orderPercentageEntity.setOrderTime(orderEntity.getOrderTime());
                // 设置分成业绩
                if (orderPercentageEntity.getProfit() == null || orderPercentageEntity.getProfit().equals(0)) {
                    //原来没有减去返佣和兼职分钱orderPercentageEntity.setProfit(orderEntity.getCommission().multiply(orderPercentageEntity.getPercentage()).divide(new BigDecimal(100)));
                    orderPercentageEntity.setProfit(orderEntity.getActualBranchCommision().multiply(orderPercentageEntity.getPercentage()).divide(new BigDecimal(100)));
                }

                orderPercentageManager.create(orderPercentageEntity);
                orderPercentageEntityList.add(orderPercentageEntity);
            }
            try {
                createOrderCommission(orderEntity.getOrderCode(),orderPercentageEntityList);
            } catch (ParseException e) {
                throw  new BusinessException("生成订单提成有误");
            }
        }
        // 保存关联的文件
        if (ListUtils.isNotEmpty(orderEntity.getFileRelationEntityList())) {
            for (FileRelationEntity fileRelationEntity : orderEntity.getFileRelationEntityList()) {
                fileRelationEntity.setOwnerCode(orderEntity.getOrderCode());
                fileRelationManager.create(fileRelationEntity);
            }
            fileManager.createFolderAndUpdateFile("订单编号:"+orderEntity.getOrderCode(),orderEntity.getOrderCode(),orderEntity.getFileRelationEntityList());
        }
        OperateFillUtils.fill(orderEntity);

        // 更新客户的状态
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCusCode(orderEntity.getCusCode());
        customerEntity.setCusStatus(CustomerStatus.HASDEAL);
        customerEntity.setLastUpdateCode(CurrentContext.getUserCode());
        customerEntity.setLastUpdateTime(new Date());

        customerDbDao.updateForOrder(customerEntity);

        // 更新房源的状态
        HouseEntity houseEntity = new HouseEntity();
        houseEntity.setHouseCode(orderEntity.getHouseCode());
        //houseEntity.setHouseStatus(HouseStatus.HasLease);
        houseEntity.setLastUpdateCode(CurrentContext.getUserCode());
        houseEntity.setLastUpdateTime(new Date());

        //判断订单面积大于房源area可租面积才下架
        Map hosParam = new HashMap<String, Object>();
        hosParam.put("houseCode", orderEntity.getHouseCode());
        HouseEntity dbHosObj = houseDbDao.selectUniqueByMap(hosParam);
        if(dbHosObj !=null){
          BigDecimal  hosArea = dbHosObj.getArea();
          BigDecimal  ordArea = orderEntity.getAcreage();
            if(ordArea.compareTo(hosArea)==1){
                houseEntity.setHouseStatus(HouseStatus.HasLease);
            }
        }

        houseDbDao.updateForOrder(houseEntity);

        // 更新合作的状态
        Map param = new HashMap<String, Object>();
        param.put("cusCode", orderEntity.getCusCode());
        param.put("houseCode", orderEntity.getHouseCode());
        List<CooperateEntity> cooperateEntityList = cooperateDbDao
                .querySimpleCooperateListByMap(param);

        if (ListUtils.isNotEmpty(cooperateEntityList)) {
            for (CooperateEntity cooperateEntity : cooperateEntityList) {
                cooperateEntity.setCooStatus(CooStatus.CONTRACT);
                cooperateEntity.setLastUpdateCode(CurrentContext.getUserCode());
                cooperateEntity.setLastUpdateTime(new Date());
            }
            cooperateDbDao.batchUpdate(cooperateEntityList);
        }


        return orderDbDao.insert(orderEntity);
    }

    /**
     * 获取分成明细
     * @param orderCode,flag
     */
    public List<OrderPercentageEntity> getPeDetail(String orderCode,String flag){
        Map<String,Object> param=new HashMap<>();
        List<OrderPercentageEntity> percentListAll = new ArrayList<OrderPercentageEntity>();
        param.put("orderCode",orderCode);
        param.put("isDeleted",false);
        percentListAll = orderPercentageManager.findByMap(param);
        List<OrderPercentageEntity> percentageEntities=new ArrayList<>();

        for (OrderPercentageEntity orderPercentageOne : percentListAll) {
            String moneyPercentType = orderPercentageOne.getMoneyPercentType();
            if (moneyPercentType.equals(flag)) {
                percentageEntities.add(orderPercentageOne);
            }
        }
        return percentageEntities;
    }

    public void setExpectPamentTime(OrderEntity orderEntity) {
        if (orderEntity.getContractStart() != null) {
            Calendar calender = Calendar.getInstance();
            calender.setTime(orderEntity.getContractStart());
            calender.add(Calendar.DATE, 10);
            orderEntity.setExpectPaymentTime(calender.getTime());
        }
    }

    public void setHosOwnerPhone(OrderEntity orderEntity) {
        if (StringUtils.isEmpty(orderEntity.getHouseownerPhone())) {
            HouseownerEntity houseownerEntity = houseownerManager.queryHouseownerByHouseCode(orderEntity.getHouseCode());
            if (houseownerEntity == null) {
                throw new BusinessException("根据房源编码houseCode未查询到业主,传入有效房源编码!");
            }
            orderEntity.setHouseownerPhone(houseownerEntity.getHouseownerPhone());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(OrderEntity orderEntity) {

        //业务校验
        //校验1,校验分成人为空
        List<OrderPercentageEntity> percentPersonSure = orderEntity.getOrderPercentageEntityList();
        if (percentPersonSure == null || percentPersonSure.size() == 0) {
            throw new BusinessException("分成人列表个数为0, 至少需要1个分成人!");
        }
        //校验2,校验分成人分成百分比和为100
        BigDecimal chenckTotalPercent = new BigDecimal("0.00");
        for (OrderPercentageEntity orderPercentOne : percentPersonSure) {
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
        setCusPhone(orderEntity);
        // 业主手机号
        setHosOwnerPhone(orderEntity);
        //实际可分佣金总额,预备
        BigDecimal receivableHos = orderEntity.getReceivableHos();
        BigDecimal receivableCus = orderEntity.getReceivableCus();
        BigDecimal cusRebate = orderEntity.getCusRebate();
        BigDecimal ownerRebate = orderEntity.getOwnerRebate();
        BigDecimal cusParttimeMoney = orderEntity.getCusParttimeMoney();
        BigDecimal ownerParttimeMoney = orderEntity.getOwnerParttimeMoney();

        //总开单佣金总额(没有减去返佣)
        BigDecimal commision = new BigDecimal("0.00");
        commision = receivableHos.add(receivableCus);
        orderEntity.setCommission(commision);

        //实际可分佣金总额计算
        BigDecimal actualCommision = new BigDecimal("0.00");
        actualCommision = receivableHos.add(receivableCus);
        actualCommision = actualCommision.subtract(cusRebate);
        actualCommision = actualCommision.subtract(ownerRebate);
        actualCommision = actualCommision.subtract(cusParttimeMoney);
        actualCommision = actualCommision.subtract(ownerParttimeMoney);

        orderEntity.setActualCommision(actualCommision);

        //判断1,金额是否发生改变,如果改变就去变更子表分成人表,没有就不变更
        //判断1.1 查出老的订单,和新的订单比较6个金额字段
        String ordCode = orderEntity.getOrderCode();
        OrderEntity oldOrdObj = this.detail(ordCode);
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

        //开启都修改分成人
        editSubFlag = true;
        if (editSubFlag == true) {
            try {
                checkAndEditSubList(oldOrdObj, orderEntity);
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

        orderEntity.setLastUpdateTime(new Date());
        orderEntity.setLastUpdateCode(CurrentContext.getUserCode());

        orderEntity.setLastUpdateCode(CurrentContext.getUserCode());
        orderEntity.setLastUpdateTime(new Date());
        int result = orderDbDao.update(orderEntity);
        return result;
    }

    public void setCusPhone(OrderEntity orderEntity) {
        if (StringUtils.isEmpty(orderEntity.getCusPhone())) {
            CustomerEntity customerEntity = customerManager.findOne("cusCode", orderEntity.getCusCode());
            if (customerEntity == null) {
                throw new BusinessException("根据客户编码cusCode未查询到客户,传入有效客户编码!");
            }
            orderEntity.setCusPhone(customerEntity.getCusPhone());
        }
    }


    @Transactional(rollbackFor = Exception.class)
    public void checkAndEditSubList(OrderEntity oldEntity, OrderEntity newEntity) throws ParseException {
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

        List<OrderPercentageEntity> percentPersonPart = newEntity.getOrderPercentageEntityList();
        for (OrderPercentageEntity orderPercentOne : percentPersonPart) {
            orderPercentOne.setMoneyPercentType("2");
            orderPercentOne.setMoneyPercentTypeName("分成人分成");
        }

        //先删除原来的订单分成isdelete改为false,写入日志
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("orderCode", newEntity.getOrderCode());
        param.put("isDeleted",false);
        //查询old分成比
        List<OrderPercentageEntity> oldPercentage=new ArrayList<>();
        List<OrderPercentageEntity> oldPe=orderPercentageDbDao.selectByMap(param);
        //保留非客服分成
        for (OrderPercentageEntity orderPercentageEntity:oldPe){
            String moneyPercentType = orderPercentageEntity.getMoneyPercentType();
            if (moneyPercentType.equals(MoneyPercentType.PERSON_PERCENT)) {
                oldPercentage.add(orderPercentageEntity);
            }
        }

        orderPercentageDbDao.updateIsDeleteByOrderCode(param);
        // 保存客服订单分成
        if (ListUtils.isNotEmpty(telephoneOrderPer)) {


            for (OrderPercentageEntity orderPercentageEntity : telephoneOrderPer) {
                orderPercentageEntity.setOrderCode(newEntity.getOrderCode());
                orderPercentageEntity.setOrderTime(newEntity.getOrderTime());
                // 设置分成业绩
                //原来没有减去返佣和兼职分钱orderPercentageEntity.setProfit(orderEntity.getCommission().multiply(orderPercentageEntity.getPercentage()).divide(new BigDecimal(100)));
                orderPercentageEntity.setProfit(newEntity.getActualCommision().multiply(orderPercentageEntity.getPercentage()).divide(new BigDecimal(100)));
                //客服分成 分成所属部门为订单所属部门
                orderPercentageManager.create(orderPercentageEntity);
            }
        }

        // 保存订单分成
        if (ListUtils.isNotEmpty(percentPersonPart)) {
            List<OrderPercentageEntity> orderPercentageEntityList=new ArrayList<>();
            for (OrderPercentageEntity orderPercentageEntity : percentPersonPart) {
                orderPercentageEntity.setOrderCode(newEntity.getOrderCode());
                orderPercentageEntity.setOrderTime(newEntity.getOrderTime());
                // 设置分成业绩
                //原来没有减去返佣和兼职分钱orderPercentageEntity.setProfit(orderEntity.getCommission().multiply(orderPercentageEntity.getPercentage()).divide(new BigDecimal(100)));
                orderPercentageEntity.setProfit(newEntity.getActualBranchCommision().multiply(orderPercentageEntity.getPercentage()).divide(new BigDecimal(100)));

                orderPercentageManager.create(orderPercentageEntity);
                orderPercentageEntityList.add(orderPercentageEntity);
            }

        }
        updateOrderCommission(newEntity.getOrderCode(),oldPercentage);


    }



    public void setTime(OrderEntity newEntity) {
        setExpectPamentTime(newEntity);
    }

    @Override
    public OrderEntity detail(String orderCode) {
        OrderEntity orderEntity = orderDbDao.detail(orderCode);
        if (orderEntity == null) {
            throw new BusinessException("查询不到订单编号:" + orderCode + ",的订单分成详情");
        }
        Map param = new HashMap<String, Object>(16);
        param.put("orderCode", orderEntity.getOrderCode());
        param.put("isDeleted", false);
        List<OrderPercentageEntity> percentListAll = new ArrayList<OrderPercentageEntity>();
        percentListAll = orderPercentageManager.findByMap(param);
        //客服分成列表
        List<OrderPercentageEntity> percentListCusServ = new ArrayList<OrderPercentageEntity>();
        //分成人分成列表
        List<OrderPercentageEntity> percentListDividePerson = new ArrayList<OrderPercentageEntity>();
        for (OrderPercentageEntity orderPercentageOne : percentListAll) {
            String moneyPercentType = orderPercentageOne.getMoneyPercentType();
            if (moneyPercentType.equals(MoneyPercentType.SERVICE_PERCENT)) {
                percentListCusServ.add(orderPercentageOne);
            } else if (moneyPercentType.equals(MoneyPercentType.PERSON_PERCENT)) {
                percentListDividePerson.add(orderPercentageOne);
            }
        }

        orderEntity.setCusServicePercentageEntityList(percentListCusServ);
        orderEntity.setOrderPercentageEntityList(percentListDividePerson);
        //计算总的客服分成佣金
        orderEntity.setActualCusServCommision(orderEntity.getActualCommision().subtract(orderEntity.getActualBranchCommision()));
        param.clear();
        param.put("ownerCode", orderEntity.getOrderCode());
        orderEntity.setFileRelationEntityList(fileRelationManager.findByMap(param));
        return orderEntity;
    }

    @Override
    public int udpateOrderStatus(OrderEntity orderEntity) {
        if (orderEntity != null) {
                OrderEntity o = new OrderEntity();
                o.setOrderCode(orderEntity.getOrderCode());
                o.setOrderStatus(orderEntity.getOrderStatus());
                o.setLastUpdateTime(new Date());
                o.setLastUpdateCode(CurrentContext.getUserCode());
                return orderDbDao.update(o);
        }
        return 0;
    }

    @Override
    public int appUdpateOrderStatus(OrderEntity orderEntity) {
        if (orderEntity != null) {
            if(this.updateOrderStatusCheck(orderEntity.getOrderCode())) {
                OrderEntity o = new OrderEntity();
                o.setOrderCode(orderEntity.getOrderCode());
                o.setOrderStatus(orderEntity.getOrderStatus());
                o.setLastUpdateTime(new Date());
                o.setLastUpdateCode(CurrentContext.getUserCode());
                return orderDbDao.update(o);
            }else{
                throw new BusinessException("温馨提示：该订单已签租赁合同，无需再次修改！");
            }
        }
        return 0;
    }

    @Override
    public int hasCommissionConfirm(OrderEntity orderEntity) {
        if (orderEntity != null) {
            OrderEntity o = new OrderEntity();
            o.setOrderCode(orderEntity.getOrderCode());
            o.setHasCommissionConfirm(Boolean.TRUE);
            return orderDbDao.update(o);
        }
        return 0;
    }

    @Override
    public OrderEntity querycusservice(String empCode,String cusCode) {

        //region 设置分成部门
        OrderEntity orderEntity = new OrderEntity();
        //查分成人
        UserEntity userEntity = userManager.findUserByUserCode(empCode);
        if (userEntity == null) {
            throw new BusinessException("该分成人不存在!");
        }

        //查客户
        CustomerEntity customerEntity = customerManager.findOne("cusCode", cusCode);
        if (customerEntity == null) {
            throw new BusinessException("根据客户编码cusCode未查询到客户,传入有效客户编码!");
        }
        //endregion

        //region 注释掉的代码
        // UserApiVo userVo = new UserApiVo();
        //岗位是客服编码
        //userVo.setEmpPostCode("PT201712210005ce7f");
        //userVo.setOwnerDeptCode(userEntity.getOwnerDeptCode());
        //List<UserEntity> users = UserApiClient.findUserList(userVo);
        //部门不存在无客服分成
        //华南地区的分成加上客服,其他地区的分成不加客服
        //endregion

        List<OrderPercentageEntity> orderptList = new ArrayList<>();
        String deptCode = userEntity.getOwnerDeptCode();

        //region 设置客服订单分成
        if (StringUtils.isNotEmpty(userEntity.getOwnerDeptCode())) {
            //region 根据部门code查询出客服表里客服表实体, 根据客服表实体里code查出对应的客户信息，如果当前客服表里的客服已经离职，则跳过他的分成
            List<UserEntity> users = new ArrayList<UserEntity>();
            Map param = new HashMap(16);
            param.put("isDeleted", Boolean.FALSE);
            param.put("deptCode", deptCode);
            List<CusServiceEntity> cusservList = cusServiceDbDao.selectByMap(param);
            if (cusservList != null && cusservList.size() > 0) {
                for (int i = 0; i < cusservList.size(); i++) {
                    String userCode = cusservList.get(i).getUserCode();
                    Map map = new HashMap(16);
                    map.put("isDeleted",Boolean.FALSE);
                    map.put("userCode",userCode);
                    UserEntity user =  userDataManager.findOne(map);
                    if (user!=null){
                        users.add(user);
                    }
                }
            }
            //endregion
            //region 设置客服订单分成信息
            if (users != null && users.size() > 0) {
                for (UserEntity ue : users) {
                    OrderPercentageEntity oneOrder = new OrderPercentageEntity();
                    oneOrder.setPeEmpCode(ue.getUserCode());
                    oneOrder.setPeEmpName(ue.getUserName());
                    oneOrder.setPercentageType("6");
                    oneOrder.setPercentageName("客服分成");
                    oneOrder.setMoneyPercentType("1");
                    oneOrder.setMoneyPercentTypeName("客服固定分成");
                    oneOrder.setPercentage(new BigDecimal("1"));
                    orderptList.add(oneOrder);

                }
            }
            //活动经费  所有订单皆抽取1%活动经费 0822

            OrderPercentageEntity actPe = new OrderPercentageApplyEntity();
            actPe.setPeEmpCode("E2019082200045146");
            actPe.setPeEmpName("大区活动经费");
            actPe.setPercentageType(OrderPercentageType.ACTDIVIDE);
            actPe.setPercentageName("大区活动经费");
            actPe.setMoneyPercentType("1");
            actPe.setMoneyPercentTypeName("大区活动经费");
            actPe.setPercentage(new BigDecimal(1));
            orderptList.add(actPe);

            //查部门
            Map<String,Object> deptPara=new HashMap<>();
            deptPara.put("deptCode",deptCode);
            List<DeptEntity> deptList = deptManager.findByMap(deptPara);
            DeptEntity deptEntity = deptList.get(0);
            String bigRegionCode = deptEntity.getParentCode();

            Integer cusType = customerEntity.getCusType();
            if (!cusType.equals(CustomerType.AGENT)){
                if("DP201607040001".equals(bigRegionCode)
                        ||"DP201606120001".equals(bigRegionCode)
                        ||"DP201703160001".equals(bigRegionCode)
                        ||"DP2018011200011267".equals(bigRegionCode)
                        ||"DP201607040007".equals(bigRegionCode)

                        ){
                    //客户类型为公司(非个人),增加公司固定分百分10,0806上海大区启用 08月14号浙江大区启用
                    //08月29日开启中西部,华北,江苏 3个大区
                    OrderPercentageEntity aOrder = new OrderPercentageEntity();
                    //调整为公司创建的400客服下的用户
                    aOrder.setPeEmpCode("E201908070001567d");
                    aOrder.setPeEmpName("公司获客分成");
                    aOrder.setPercentageType(OrderPercentageType.CPYDIVIDE);
                    aOrder.setPercentageName("公司获客分成");
                    aOrder.setMoneyPercentType("1");
                    aOrder.setMoneyPercentTypeName("客服固定分成");
                    aOrder.setPercentage(new BigDecimal("10"));
                    orderptList.add(aOrder);
                    //客户类型为公司(非个人),增加公司固定分百分10,0806上海大区启用end
                }

            }


            //endregion
        }
        //endregion


        orderEntity.setCusServicePercentageEntityList(orderptList);
        return orderEntity;
    }

    @Override
    public OrderEntity selectUniqueByMap(Map<String, Object> map) {
        map.put("isDeleted", Boolean.FALSE);
        return orderDbDao.selectUniqueByMap(map);
    }

    @Override
    public List<OrderEntity> orderStatistics(Map<String, Object> map) {
        map.put("isDeleted", Boolean.FALSE);
        return orderDbDao.orderStatistics(map);
    }

    @Override
    public List<OrderEntity> orderDivideInto(Map<String, Object> map) {
        map.put("isDeleted", Boolean.FALSE);
        return orderDbDao.orderDivideInto(map);
    }

    @Override
    public List<OrderEntity> orderTransDept(Map<String, Object> map) {
        map.put("isDeleted", Boolean.FALSE);
        return orderDbDao.orderTransDept(map);
    }

    @Override
    public List<OrderEntity> selectOrderHistory(Map<String,Object> map){
        List<OrderEntity> orderList=orderDbDao.selectOrderHistory(map);
        for(int i=0;i<orderList.size();i++){
            List<OrderPercentageEntity> list =orderList.get(i).getOrderPercentageEntityList();
            int count = 1;
            int num = -1;
            for(int j=0;j<list.size();j++){
                //分成比例
                BigDecimal percentage = BigDecimal.ZERO;
                //分成金额
                BigDecimal profit = BigDecimal.ZERO;

                if (list.get(j).getPercentage() != null) {
                    percentage = list.get(j).getPercentage();
                }
                if (list.get(j).getProfit() != null) {
                    profit = list.get(j).getProfit();
                }
                //可分成业务员名
                String perEmpName = "";
                String peDeptName="";
                String workNumber="";
                //可分成业务员工号
                if (StringUtils.isNotEmpty(list.get(j).getPeEmpName())) {
                    perEmpName = list.get(j).getPeEmpName();
                    peDeptName = list.get(j).getPeDeptName();
                    workNumber = list.get(j).getWorkNumber();
                }

                if (list.get(j).getPeEmpCode().equals(orderList.get(i).getEmpCode())) {
                    orderList.get(i).setPerEmpName(perEmpName);
                    //开单人员业绩
                    orderList.get(i).setPerPercentage(percentage);
                    orderList.get(i).setPerProfit(profit);
                    orderList.get(i).setPerWorkNumner(workNumber);
                    //分成
                    //orderList.get(i).setPerWorkNumner(proportion);
                    orderList.get(i).setPerDeptName(peDeptName);
                    num = j;
                    continue;
                } else if (num != j && count == 1) {
                    orderList.get(i).setPerEmpName1(perEmpName);
                    orderList.get(i).setPerProfit1(profit);
                    orderList.get(i).setPerDeptName1(peDeptName);
                    orderList.get(i).setPerPercentage1(percentage);
                    orderList.get(i).setPerWorkNumner1(workNumber);
                   /* orderList.get(i).setPerformance1(performance);
                    orderList.get(i).setPeDeptCode1(peDeptCode);*/
                    count++;
                    continue;
                } else if (num != j && count == 2) {
                    orderList.get(i).setPerEmpName2(perEmpName);
                    orderList.get(i).setPerProfit2(profit);
                    orderList.get(i).setPerDeptName2(peDeptName);
                    orderList.get(i).setPerPercentage2(percentage);
                    orderList.get(i).setPerWorkNumner2(workNumber);
                    count++;
                    continue;
                } else if (num != j && count == 3) {
                    orderList.get(i).setPerEmpName3(perEmpName);
                    orderList.get(i).setPerProfit3(profit);
                    orderList.get(i).setPerDeptName3(peDeptName);
                    orderList.get(i).setPerPercentage3(percentage);
                    orderList.get(i).setPerWorkNumner3(workNumber);
                    count++;
                    continue;
                } else if (num != j && count == 4) {
                    orderList.get(i).setPerEmpName4(perEmpName);
                    orderList.get(i).setPerProfit4(profit);
                    orderList.get(i).setPerDeptName4(peDeptName);
                    orderList.get(i).setPerPercentage4(percentage);
                    orderList.get(i).setPerWorkNumner4(workNumber);
                    count++;
                    continue;
                } else if (num != j && count == 5) {
                    //分成人
                    orderList.get(i).setPerEmpName5(perEmpName);
                    //分成人业绩
                    orderList.get(i).setPerProfit5(profit);
                    //分成人部门
                    orderList.get(i).setPerDeptName5(peDeptName);
                    //比例
                    orderList.get(i).setPerPercentage5(percentage);
                    orderList.get(i).setPerWorkNumner5(workNumber);
                    count++;
                    continue;
                }
            }
        }
        return orderList;
    }

    /**
     * 批量处理历史数据接口
     * @return
     * @throws ParseException
     */
    @Override
    public int dealRatio() throws ParseException{
        //目前只计算非华南地区招商选址事业部业务部门的订单
        List<String> deptCodes=deptManager.getWageAllDeptCodes();
        String strDate="2019-03-01";
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        Date orderTimeStart=sdf.parse(strDate);

        Map<String,Object> param=new HashMap<>();
        param.put("orderTimeStart",orderTimeStart);
        param.put("isDeleted",false);
        List<OrderEntity> orderEntities=this.findByMap(param);
        for (OrderEntity orderEntity : orderEntities) {
            test(orderEntity.getOrderCode(),deptCodes);
        }


        return 0;
    }

    /**
     * 批量处理历史职位变动接口
     * @param userPostChangeEntities
     * @return
     */
    @Override
    public int dealPost(List<UserPostChangeEntity> userPostChangeEntities) {

        List<String> needDealPosts=new ArrayList<>();

        needDealPosts.add("PT20190315000194c5");//组长
        needDealPosts.add("PT201603310001");    //分部经理
        needDealPosts.add("PT201712210004d713");    //高级选址顾问
        needDealPosts.add("PT201601310001");    //选址专员
        needDealPosts.add("PT201903250001feae");    //资深经理
        List<String> deptCodes=deptManager.getWageAllDeptCodes();

        for (UserPostChangeEntity userPostChangeEntity:userPostChangeEntities){
            if (null==userPostChangeEntity.getEndTime()){
             //无结束时间只需处理3月到变动时间的订单  处理变动前职位
                //变动前不是以上职位无需修改
                //变动前不是以上部门无需修改
                if ((needDealPosts.contains(userPostChangeEntity.getBeforePost())||needDealPosts.contains(userPostChangeEntity.getAfterPost()))&&
                        (deptCodes.contains(userPostChangeEntity.getBeforeDept())||deptCodes.contains(userPostChangeEntity.getAfterDept()))){



                }
            }
        }
        return 0;
    }

    @Override
    public int dealPostGroup(List<UserPostChangeEntity> userPostChangeEntities) {

        List<String> deptCodes=deptManager.getWageAllDeptCodes();

        for (UserPostChangeEntity userPostChangeEntity:userPostChangeEntities){
            if (deptCodes.contains(userPostChangeEntity.getBeforeDept())){
                Map<String,Object> param=new HashMap<>();
                param.put("groupCode",userPostChangeEntity.getUserCode());
                param.put("orderTime",userPostChangeEntity.getStartTime());
                commissionRatioDbDao.updateGroup(param);
            }
        }

        //查找离职的组长


        //3.21-离职日期内部门中所在组的组长分成归该人

        return 0;
    }

    @Override
    public int dealPostLeader(List<DeptPostChangeEntity> deptPostChangeEntities) {
        List<String> deptCodes=deptManager.getWageAllDeptCodes();

        for (DeptPostChangeEntity deptPostChangeEntity:deptPostChangeEntities){
            if (deptCodes.contains(deptPostChangeEntity.getDeptCode())){
                Map<String,Object> param=new HashMap<>();
                param.put("deptCode",deptPostChangeEntity.getDeptCode());
                param.put("userCode",deptPostChangeEntity.getUserCode());
                param.put("beginTime",deptPostChangeEntity.getChangeTime());
                param.put("endTime",deptPostChangeEntity.getChangeTimeEnd());
                commissionRatioDbDao.updateLeader(param);

            }
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void dealPayBackHistory() throws ParseException {
        String startTimeStr = "2018-10-01";
        Date endTime = new Date();
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        Date startTime=sdf.parse(startTimeStr);
        for (int i=0;i<9;i++){
            Calendar cal = Calendar.getInstance();
            cal.setTime(startTime);
            cal.add(Calendar.MONTH,1);
            cal.set(Calendar.DAY_OF_MONTH,1);
            endTime = cal.getTime();
            System.out.println(sdf.format(startTime)+"======"+sdf.format(endTime));
            dealPayBack(startTime,endTime,"1");
            //处理下个月
            startTime = endTime;
        }
    }

    /**
    * @Description 描述:(个人获客)由规则配置code和name
    * @Auth xpp
    * @Date 2019/8/7 19:24
    * @Version 1.0
    * @Param [namePerson]
    * @return void
     * (个人获客)
    **/
    public void flushPersonRule(OrderNamePerson pNamePerson,OrderEntity orderEntity){
        //a查分成人
        UserEntity userEntity = userManager.findUserByUserCode(orderEntity.getEmpCode());
        if (userEntity == null) {
            throw new BusinessException("该分成人不存在!");
        }
        //b查客户
        CustomerEntity customerEntity = customerManager.findOne("cusCode", orderEntity.getCusCode());
        if (customerEntity == null) {
            throw new BusinessException("根据客户编码cusCode未查询到客户,传入有效客户编码!");
        }
        //c查客户所有人
        UserEntity cusOwnerUser = userManager.findUserByUserCode(customerEntity.getEmpCode());
        if (cusOwnerUser == null) {
            throw new BusinessException("该客户所有人不存在!");
        }

        //个人1客户推送
        //推送至不同部门成交后归属推送人,推送人离职后归成交人,无推送人归属于客户所有者
        Map cusPushMap = new HashMap<String, Object>();
        cusPushMap.put("cusCode", orderEntity.getCusCode());
        List<CustomerPushEntity> cusPushList = customerPushDbDao.selectByMap(cusPushMap,"id",false);
        if(cusPushList == null){
            pNamePerson.setCusPushCode(customerEntity.getEmpCode());
            pNamePerson.setCusPushName(cusOwnerUser.getUserName());

        }
        if(cusPushList.size()==0){
            pNamePerson.setCusPushCode(customerEntity.getEmpCode());
            pNamePerson.setCusPushName(cusOwnerUser.getUserName());
        }
        if(cusPushList.size()>0){
            //推送人部门
            CustomerPushEntity customerPushEntity = cusPushList.get(0);
            //推送人自己的部门
            String selfcreateDeptCode = customerPushEntity.getCreateDeptCode();
            //查推送人
            UserEntity pushUser = userManager.findUserByUserCode(customerPushEntity.getPushCode());
            if (pushUser == null) {
                throw new BusinessException("该接收人不存在!");
            }
            if(customerPushEntity.getPushType().equals("Personal")){
                //查接收人
                UserEntity receiveUser = userManager.findUserByUserCode(customerPushEntity.getReceiveCode());
                if (receiveUser == null) {
                    throw new BusinessException("该接收人不存在!");
                }
                if(selfcreateDeptCode != receiveUser.getOwnerDeptCode()){
                    if(pushUser.getIsDeleted().equals(true) || pushUser.getEmpPostCode().equals("PT201712210005ce7f") || pushUser.getEmpPostCode().equals("PT201712080001d5ba")){
                        pNamePerson.setCusPushCode(orderEntity.getEmpCode());
                        pNamePerson.setCusPushName(userEntity.getUserName());
                    }else if(pushUser.getIsDeleted().equals(false)){
                        pNamePerson.setCusPushCode(customerPushEntity.getPushCode());
                        pNamePerson.setCusPushName(customerPushEntity.getPushName());
                    }

                }

            }else if(customerPushEntity.getPushType().equals("Department")){

                if(selfcreateDeptCode != customerPushEntity.getReceiveDeptCode()){
                    if(pushUser.getIsDeleted().equals(true) || pushUser.getEmpPostCode().equals("PT201712210005ce7f") || pushUser.getEmpPostCode().equals("PT201712080001d5ba")){
                        pNamePerson.setCusPushCode(orderEntity.getEmpCode());
                        pNamePerson.setCusPushName(userEntity.getUserName());
                    }else if(pushUser.getIsDeleted().equals(false)){
                        pNamePerson.setCusPushCode(customerPushEntity.getPushCode());
                        pNamePerson.setCusPushName(customerPushEntity.getPushName());
                    }

                }
            }else{
                throw new BusinessException("查客户的推送类型错误(pushType为):"+customerPushEntity.getPushType());
            }

        }//数量大于0
        //个人2获客分成
        //说明:客户所属人分成,客户所属人离职,归属成交人
        pNamePerson.setAcquireCusCode(cusOwnerUser.getUserCode());
        pNamePerson.setAcquireCusName(cusOwnerUser.getUserName());
        if(cusOwnerUser.getIsDeleted().equals(true)){
            pNamePerson.setAcquireCusCode(orderEntity.getEmpCode());
            pNamePerson.setAcquireCusName(userEntity.getUserName());
        }
        //个人3带看分成
        //说明:成交前最后一次带看,带看人分5%,无带看记录则归成交人
        Map cusFollowMap = new HashMap<String, Object>();
        cusFollowMap.put("cusCode", orderEntity.getCusCode());
        cusFollowMap.put("followupType", CustomerFollowupType.SeeHouse);
        //Map<String, Object> queryParam, String orderBy, boolean isAsc
        List<CustomerFollowupEntity> cusFollowList = customerFollowupDbDao.selectByMap(cusFollowMap,"id",false);
        //for (int i = 0; i < cusFollowList.size(); i++) {
        //    if(cusFollowList.get(i).getEmpCode().equals(orderEntity.getEmpCode())){
        //        cusFollowList.remove(cusFollowList.get(i));
        //        i=-1;
        //    }
        //}

        if(cusFollowList.size()==0){
            pNamePerson.setFollowupSeeCode(orderEntity.getEmpCode());
            pNamePerson.setFollowupSeeName(userEntity.getUserName());
        }
        if(cusFollowList.size()>0){

            //最后一次带看人
            UserEntity lastFollowUser = userManager.findUserByUserCode(cusFollowList.get(0).getEmpCode());


            if (lastFollowUser == null) {
                //throw new BusinessException("该最后一次带看人不存在123!");
                pNamePerson.setFollowupSeeCode(orderEntity.getEmpCode());
                pNamePerson.setFollowupSeeName(userEntity.getUserName());
            }
            //大于等于2个带看人,1个带看人(0个带看人上面已处理)
            if(cusFollowList.size()==1){
                pNamePerson.setFollowupSeeCode(cusFollowList.get(0).getEmpCode());
                pNamePerson.setFollowupSeeName(lastFollowUser.getUserName());
            }
            if(cusFollowList.size()>=2){
                String emp2Code = cusFollowList.get(1).getEmpCode();
                UserEntity lastNextTwoUser = userManager.findUserByUserCode(emp2Code);
                boolean flag = cusFollowList.get(0).getEmpCode().equals(orderEntity.getEmpCode());
                if(flag){
                    pNamePerson.setFollowupSeeCode(lastNextTwoUser.getUserCode());
                    pNamePerson.setFollowupSeeName(lastNextTwoUser.getUserName());
                }else {
                    pNamePerson.setFollowupSeeCode(cusFollowList.get(0).getEmpCode());
                    pNamePerson.setFollowupSeeName(lastFollowUser.getUserName());
                }
            }



        }
        //个人4成交分成
        //说明:最终成交人
        pNamePerson.setOrderDealCode(orderEntity.getEmpCode());
        pNamePerson.setOrderDealName(userEntity.getUserName());
        //个人5房源开发
        //说明:a,接收房源的同时享有. b,接手房源的同时在接手后若7天未跟进,则不在享有分成,归成交人
        //查房源
        Map hosParam = new HashMap<String, Object>();
        hosParam.put("houseCode", orderEntity.getHouseCode());
        HouseEntity dbHosObj = houseDbDao.selectUniqueByMap(hosParam);
        if (dbHosObj == null) {
            throw new BusinessException("根据房源编码hosCode未查询到房源,传入有效房源编码!");
        }
        //查房源所有人
        UserEntity hosEmpUser = userManager.findUserByUserCode(dbHosObj.getEmpCode());

        if (hosEmpUser == null) {
            throw new BusinessException("该房源所有人不存在!");
        }
        pNamePerson.setHouseDevelopCode(dbHosObj.getEmpCode());
        pNamePerson.setHouseDevelopName(hosEmpUser.getUserName());
        //房源所有人离职处理
        if(hosEmpUser.getIsDeleted().equals(true)){
            pNamePerson.setHouseDevelopCode(orderEntity.getEmpCode());
            pNamePerson.setHouseDevelopName(userEntity.getUserName());
        }
        //房源empCode和createCode相同则跳过超7天处理,归房源所有人empCode
        //房源所有人不跟进超过7天处理
        boolean equalFlag = false;
        if(dbHosObj.getCreateCode().equals(dbHosObj.getEmpCode())){
            equalFlag = true;
        }
        if(!equalFlag){
            Date lastFollupDate = dbHosObj.getLastFollowupTime();
            long indays = 1;
            if (lastFollupDate != null) {
                Date nowDate = new Date();
                indays = (nowDate.getTime() - lastFollupDate.getTime()) / (24 * 60 * 60 * 1000);
                dbHosObj.setDaysNotFollowup(indays);
                if(indays > 7){
                    pNamePerson.setHouseDevelopCode(orderEntity.getEmpCode());
                    pNamePerson.setHouseDevelopName(userEntity.getUserName());
                }
            }
        }

        //个人6房源委托分成
        //说明:a,签订符合公司要求的房源委托书且上传到后台. b,若该同事离职则分成归成交人 c,未签委托,此项归属成交人
        // 查询文件
        Map fileParam = new HashMap<String, Object>();
        fileParam.put("ownerCode",dbHosObj.getHouseCode());
        fileParam.put("fileUse", HouseFileUse.Protocol);
        List<FileRelationEntity> hosFileList = fileRelationManager.findByMap(fileParam,"id",false);
        if(hosFileList == null){
            pNamePerson.setHouseEntrustCode(orderEntity.getEmpCode());
            pNamePerson.setHouseEntrustName(userEntity.getUserName());
        }
        if(hosFileList.size()==0){
            pNamePerson.setHouseEntrustCode(orderEntity.getEmpCode());
            pNamePerson.setHouseEntrustName(userEntity.getUserName());
        }
        if(hosFileList.size()>0){
            hosFileList.get(0);
            //查房源上传委托人
            UserEntity hosProtoUser = userManager.findUserByUserCode(hosFileList.get(0).getCreateCode());
            if (hosProtoUser == null) {
                throw new BusinessException("该房源上传委托人不存在!");
            }
            pNamePerson.setHouseEntrustCode(hosFileList.get(0).getCreateCode());
            pNamePerson.setHouseEntrustName(hosProtoUser.getUserName());
        }



    }

    /**
    * @Description 描述:(公司获客)由规则配置code和name
    * @Auth xpp
    * @Date 2019/8/7 19:27
    * @Version 1.0
    * @Param [namePerson]
    * @return void
    **/
    public void flushCpyRule(OrderNamePerson cNamePerson,OrderEntity orderEntity){

        //a查分成人
        UserEntity userEntity = userManager.findUserByUserCode(orderEntity.getEmpCode());
        if (userEntity == null) {
            throw new BusinessException("该分成人不存在!");
        }
        //b查客户
        CustomerEntity customerEntity = customerManager.findOne("cusCode", orderEntity.getCusCode());
        if (customerEntity == null) {
            throw new BusinessException("根据客户编码cusCode未查询到客户,传入有效客户编码!");
        }
        //c查客户所有人
        UserEntity cusOwnerUser = userManager.findUserByUserCode(customerEntity.getEmpCode());
        if (cusOwnerUser == null) {
            throw new BusinessException("该客户所有人不存在!");
        }

        //公司1客户推送
        //推送至不同部门成交后归属推送人,推送人离职后归成交人,无推送人归属于(成交人)
        Map cusPushMap = new HashMap<String, Object>();
        cusPushMap.put("cusCode", orderEntity.getCusCode());
        List<CustomerPushEntity> cusPushList = customerPushDbDao.selectByMap(cusPushMap,"id",false);
        if(cusPushList == null){
            cNamePerson.setCusPushCode(orderEntity.getEmpCode());
            cNamePerson.setCusPushName(userEntity.getUserName());

        }
        if(cusPushList.size()==0){
            cNamePerson.setCusPushCode(orderEntity.getEmpCode());
            cNamePerson.setCusPushName(userEntity.getUserName());
        }
        if(cusPushList.size()>0){
            //推送人部门
            CustomerPushEntity customerPushEntity = cusPushList.get(0);
            //推送人自己的部门
            String selfcreateDeptCode = customerPushEntity.getCreateDeptCode();
            //查推送人
            UserEntity pushUser = userManager.findUserByUserCode(customerPushEntity.getPushCode());
            if (pushUser == null) {
                throw new BusinessException("该接收人不存在!");
            }
            if(customerPushEntity.getPushType().equals("Personal")){
                //查接收人
                UserEntity receiveUser = userManager.findUserByUserCode(customerPushEntity.getReceiveCode());
                if (receiveUser == null) {
                    throw new BusinessException("该接收人不存在!");
                }
                if(selfcreateDeptCode != receiveUser.getOwnerDeptCode()){
                    if(pushUser.getIsDeleted().equals(true) || pushUser.getEmpPostCode().equals("PT201712210005ce7f") || pushUser.getEmpPostCode().equals("PT201712080001d5ba")){
                        cNamePerson.setCusPushCode(orderEntity.getEmpCode());
                        cNamePerson.setCusPushName(userEntity.getUserName());
                    }else if(pushUser.getIsDeleted().equals(false)){
                        cNamePerson.setCusPushCode(customerPushEntity.getPushCode());
                        cNamePerson.setCusPushName(customerPushEntity.getPushName());
                    }

                }

            }else if(customerPushEntity.getPushType().equals("Department")){

                if(selfcreateDeptCode != customerPushEntity.getReceiveDeptCode()){
                    if(pushUser.getIsDeleted().equals(true) || pushUser.getEmpPostCode().equals("PT201712210005ce7f") || pushUser.getEmpPostCode().equals("PT201712080001d5ba")){
                        cNamePerson.setCusPushCode(orderEntity.getEmpCode());
                        cNamePerson.setCusPushName(userEntity.getUserName());
                    }else if(pushUser.getIsDeleted().equals(false)){
                        cNamePerson.setCusPushCode(customerPushEntity.getPushCode());
                        cNamePerson.setCusPushName(customerPushEntity.getPushName());
                    }

                }
            }else{
                throw new BusinessException("查客户的推送类型错误(pushType为):"+customerPushEntity.getPushType());
            }

        }//数量大于0
        //公司2获客分成,调整为上架分成
        //说明:客户所属人分成,客户所属人离职,归属成交人
        //说明:上架人离职归成交人,客服上架不参与分成,归成交人,其余都参与上架分成
        Map cusUpMap = new HashMap<String, Object>();
        cusUpMap.put("cusCode", orderEntity.getCusCode());
        cusUpMap.put("cusDownType", "上架");
        List<CustomerUpdownEntity> cusUpList = customerUpdownDbDao.selectByMap(cusUpMap, "id", false);
        if (cusUpList == null) {
            cNamePerson.setAcquireCusCode(orderEntity.getEmpCode());
            cNamePerson.setAcquireCusName(userEntity.getUserName());
        }
        if(cusUpList!=null && cusUpList.size()==0){
            cNamePerson.setAcquireCusCode(orderEntity.getEmpCode());
            cNamePerson.setAcquireCusName(userEntity.getUserName());
        }
        if(cusUpList!=null && cusUpList.size()>0){
            CustomerUpdownEntity cusUpEntity = cusUpList.get(0);
            //查上架人
            UserEntity cusUpUser = userManager.findUserByUserCode(cusUpEntity.getCreateCode());
            if (cusUpUser == null) {
                throw new BusinessException("该上架人不存在!");
            }
            if(cusUpUser.getIsDeleted().equals(true)){
                cNamePerson.setAcquireCusCode(orderEntity.getEmpCode());
                cNamePerson.setAcquireCusName(userEntity.getUserName());
            }else{
                cNamePerson.setAcquireCusCode(cusUpUser.getUserCode());
                cNamePerson.setAcquireCusName(cusUpUser.getUserName());
            }

        }




        //公司3带看分成
        //说明:成交前最后一次带看,带看人分5%,无带看记录则归成交人
        Map cusFollowMap = new HashMap<String, Object>();
        cusFollowMap.put("cusCode", orderEntity.getCusCode());
        cusFollowMap.put("followupType", CustomerFollowupType.SeeHouse);
        //Map<String, Object> queryParam, String orderBy, boolean isAsc
        List<CustomerFollowupEntity> cusFollowList = customerFollowupDbDao.selectByMap(cusFollowMap,"id",false);
        //for (int i = 0; i < cusFollowList.size(); i++) {
        //    if(cusFollowList.get(i).getEmpCode().equals(orderEntity.getEmpCode())){
        //        cusFollowList.remove(cusFollowList.get(i));
        //        i=-1;
        //    }
        //}

        if(cusFollowList.size()==0){
            cNamePerson.setFollowupSeeCode(orderEntity.getEmpCode());
            cNamePerson.setFollowupSeeName(userEntity.getUserName());
        }
        if(cusFollowList.size()>0){

            //最后一次带看人
            UserEntity lastFollowUser = userManager.findUserByUserCode(cusFollowList.get(0).getEmpCode());


            if (lastFollowUser == null) {
                //throw new BusinessException("该最后一次带看人不存在123!");
                cNamePerson.setFollowupSeeCode(orderEntity.getEmpCode());
                cNamePerson.setFollowupSeeName(userEntity.getUserName());
            }
            //大于等于2个带看人,1个带看人(0个带看人上面已处理)
            if(cusFollowList.size()==1){
                cNamePerson.setFollowupSeeCode(cusFollowList.get(0).getEmpCode());
                cNamePerson.setFollowupSeeName(lastFollowUser.getUserName());
            }
            if(cusFollowList.size()>=2){
                String emp2Code = cusFollowList.get(1).getEmpCode();
                UserEntity lastNextTwoUser = userManager.findUserByUserCode(emp2Code);
                boolean flag = cusFollowList.get(0).getEmpCode().equals(orderEntity.getEmpCode());
                if(flag){
                    cNamePerson.setFollowupSeeCode(lastNextTwoUser.getUserCode());
                    cNamePerson.setFollowupSeeName(lastNextTwoUser.getUserName());
                }else {
                    cNamePerson.setFollowupSeeCode(cusFollowList.get(0).getEmpCode());
                    cNamePerson.setFollowupSeeName(lastFollowUser.getUserName());
                }
            }


        }
        //公司4成交分成
        //说明:最终成交人
        cNamePerson.setOrderDealCode(orderEntity.getEmpCode());
        cNamePerson.setOrderDealName(userEntity.getUserName());
        //公司5房源开发
        //说明:a,接收房源的同时享有. b,接手房源的同时在接手后若7天未跟进,则不在享有分成,归成交人
        //查房源
        Map hosParam = new HashMap<String, Object>();
        hosParam.put("houseCode", orderEntity.getHouseCode());
        HouseEntity dbHosObj = houseDbDao.selectUniqueByMap(hosParam);
        if (dbHosObj == null) {
            throw new BusinessException("根据房源编码hosCode未查询到房源,传入有效房源编码!");
        }
        //查房源所有人
        UserEntity hosEmpUser = userManager.findUserByUserCode(dbHosObj.getEmpCode());
        if (hosEmpUser == null) {
            throw new BusinessException("该房源所有人不存在!");
        }
        cNamePerson.setHouseDevelopCode(dbHosObj.getEmpCode());
        cNamePerson.setHouseDevelopName(hosEmpUser.getUserName());
        //房源所有人离职处理
        if(hosEmpUser.getIsDeleted().equals(true)){
            cNamePerson.setHouseDevelopCode(orderEntity.getEmpCode());
            cNamePerson.setHouseDevelopName(userEntity.getUserName());
        }
        //房源empCode和createCode相同则跳过超7天处理,归房源所有人empCode
        //房源所有人不跟进超过7天处理
        boolean equalFlag = false;
        if(dbHosObj.getCreateCode().equals(dbHosObj.getEmpCode())){
            equalFlag = true;
        }
        if(!equalFlag){
            Date lastFollupDate = dbHosObj.getLastFollowupTime();
            long indays = 1;
            if (lastFollupDate != null) {
                Date nowDate = new Date();
                indays = (nowDate.getTime() - lastFollupDate.getTime()) / (24 * 60 * 60 * 1000);
                dbHosObj.setDaysNotFollowup(indays);
                if(indays > 7){
                    cNamePerson.setHouseDevelopCode(orderEntity.getEmpCode());
                    cNamePerson.setHouseDevelopName(userEntity.getUserName());
                }
            }
        }

        //公司6房源委托分成
        //说明:a,签订符合公司要求的房源委托书且上传到后台. b,若该同事离职则分成归成交人 c,未签委托,此项归属成交人
        // 查询文件
        Map fileParam = new HashMap<String, Object>();
        fileParam.put("ownerCode",dbHosObj.getHouseCode());
        fileParam.put("fileUse", HouseFileUse.Protocol);
        List<FileRelationEntity> hosFileList = fileRelationManager.findByMap(fileParam,"id",false);
        if(hosFileList == null){
            cNamePerson.setHouseEntrustCode(orderEntity.getEmpCode());
            cNamePerson.setHouseEntrustName(userEntity.getUserName());
        }
        if(hosFileList.size()==0){
            cNamePerson.setHouseEntrustCode(orderEntity.getEmpCode());
            cNamePerson.setHouseEntrustName(userEntity.getUserName());
        }
        if(hosFileList.size()>0){
            hosFileList.get(0);
            //查房源上传委托人
            UserEntity hosProtoUser = userManager.findUserByUserCode(hosFileList.get(0).getCreateCode());
            if (hosProtoUser == null) {
                throw new BusinessException("该房源上传委托人不存在!");
            }
            cNamePerson.setHouseEntrustCode(hosFileList.get(0).getCreateCode());
            cNamePerson.setHouseEntrustName(hosProtoUser.getUserName());
        }


    }
    @Override
    public OrderEntity querySysAutoDivide(OrderEntity orderVEntity) {

        OrderEntity orderObj = new OrderEntity();
        //查分成人
        UserEntity userEntity = userManager.findUserByUserCode(orderVEntity.getEmpCode());
        if (userEntity == null) {
            throw new BusinessException("该分成人不存在!");
        }
        //查客户
        CustomerEntity customerEntity = customerManager.findOne("cusCode", orderVEntity.getCusCode());
        if (customerEntity == null) {
            throw new BusinessException("根据客户编码cusCode未查询到客户,传入有效客户编码!");
        }

        //查房源
        Map hosParam = new HashMap<String, Object>();
        hosParam.put("houseCode", orderVEntity.getHouseCode());
        HouseEntity dbHosObj = houseDbDao.selectUniqueByMap(hosParam);
        if (dbHosObj == null) {
            throw new BusinessException("根据房源编码hosCode未查询到房源,传入有效房源编码!");
        }

        //查配置
        String deptCode = userEntity.getOwnerDeptCode();
        Map pfsParam = new HashMap<String, Object>();
        pfsParam.put("deptCode",deptCode);
        List<DeptProfitShareEntity> deptProfitList = profitShareManager.findByMap(pfsParam);
        int fitsize = 0;
         fitsize = deptProfitList.size();
        if(fitsize>=0 && fitsize!=2){
            throw new BusinessException(userEntity.getOwnerDeptName()+"配置分成条数为("+deptProfitList.size()+")条,配置为2条才正确,请联系运营部");
        }
//        if(fitsize==0){
//            Map fitmap = new HashMap<String, Object>();
//            fitmap.put("deptCode","baseCode");
//            deptProfitList = profitShareManager.findByMap(fitmap);
//        }
//        int sysFitsize = 0;
//        sysFitsize = deptProfitList.size();
//        if(sysFitsize==0){
//            //1条个人客户分成方案和1条公司客户分成方案
//            throw new BusinessException("请联系运营部,配置2条分成方案数据.");
//        }
//        if(sysFitsize>=0 && sysFitsize!=2){
//            throw new BusinessException("已有分客配置条数为("+sysFitsize+")条,配置为2条才正确(客户方案公司方案各1条)");
//        }
        for (DeptProfitShareEntity shareEntity : deptProfitList) {
            Integer sum = shareEntity.getSum();
            if(!sum.equals(100)){
                throw new BusinessException("分成配置总和不为100,("+shareEntity.getDeptName()+shareEntity.getDeptCode()+")"+"("+shareEntity.getProfitShareType()+")");
            }
        }
        DeptProfitShareEntity cpyPlatForm = new DeptProfitShareEntity();
        DeptProfitShareEntity personForm = new DeptProfitShareEntity();
        for (DeptProfitShareEntity shareEntity : deptProfitList) {
            if(shareEntity.getProfitShareType().equals(ProfitShareType.PLATFORM)){
                cpyPlatForm = shareEntity;
            }

            if(shareEntity.getProfitShareType().equals(ProfitShareType.PERSONAL)){
                personForm = shareEntity;
            }

        }



        Integer cusType = customerEntity.getCusType();
        if (cusType == null) {
            throw new BusinessException("客户类型为空,custype!");
        }

        //由规则配置对应名字
        OrderNamePerson personNameObj = new OrderNamePerson();
        OrderNamePerson cpyNameObj = new OrderNamePerson();
        flushPersonRule(personNameObj,orderVEntity);
        flushCpyRule(cpyNameObj,orderVEntity);
        //由配置设置分成人百分比
        List<OrderPercentageEntity> orderptList = new ArrayList<>();
        //1,个人获客
        if (cusType.equals(CustomerType.AGENT)){

//            OrderPercentageEntity oneOrder = new OrderPercentageEntity();
//            oneOrder.setPeEmpCode("ue001");
//            oneOrder.setPeEmpName("赵一");
//            oneOrder.setPercentageType(OrderPercentageType.CUSPERSONFEN);
//            oneOrder.setPercentageName("个人获客补贴");
//            oneOrder.setMoneyPercentType("2");
//            oneOrder.setMoneyPercentTypeName("分成人分成");
//            oneOrder.setPercentage(new BigDecimal(personForm.getAcquireCusReward()));
//            oneOrder.setRemark("(个人获客)默认方案");
//            orderptList.add(oneOrder);

            OrderPercentageEntity twoOrder = new OrderPercentageEntity();
            twoOrder.setPeEmpCode(personNameObj.getCusPushCode());
            twoOrder.setPeEmpName(personNameObj.getCusPushName());
            twoOrder.setPercentageType(OrderPercentageType.CUSPUSHFEN);
            twoOrder.setPercentageName("客户推送");
            twoOrder.setMoneyPercentType("2");
            twoOrder.setMoneyPercentTypeName("分成人分成");
            twoOrder.setPercentage(new BigDecimal(personForm.getCusPush()));
            //加分成人所在部门
            UserEntity twoOrderUser = userManager.findUserByUserCode(twoOrder.getPeEmpCode());
            twoOrder.setPeDeptCode(twoOrderUser.getOwnerDeptCode());
            twoOrder.setPeDeptName(twoOrderUser.getOwnerDeptName());
            orderptList.add(twoOrder);

            OrderPercentageEntity threeOrder = new OrderPercentageEntity();
            threeOrder.setPeEmpCode(personNameObj.getAcquireCusCode());
            threeOrder.setPeEmpName(personNameObj.getAcquireCusName());
            threeOrder.setPercentageType(OrderPercentageType.CUSTOMER);
            threeOrder.setPercentageName("获客分成");
            threeOrder.setMoneyPercentType("2");
            threeOrder.setMoneyPercentTypeName("分成人分成");
            threeOrder.setPercentage(new BigDecimal(personForm.getAcquireCus()));
            //加分成人所在部门
            UserEntity threeOrderUser = userManager.findUserByUserCode(threeOrder.getPeEmpCode());
            threeOrder.setPeDeptCode(threeOrderUser.getOwnerDeptCode());
            threeOrder.setPeDeptName(threeOrderUser.getOwnerDeptName());
            orderptList.add(threeOrder);

            OrderPercentageEntity fourOrder = new OrderPercentageEntity();
            fourOrder.setPeEmpCode(personNameObj.getFollowupSeeCode());
            fourOrder.setPeEmpName(personNameObj.getFollowupSeeName());
            fourOrder.setPercentageType(OrderPercentageType.ASSIST_FOLLOW);
            fourOrder.setPercentageName("带看");
            fourOrder.setMoneyPercentType("2");
            fourOrder.setMoneyPercentTypeName("分成人分成");
            fourOrder.setPercentage(new BigDecimal(personForm.getFollowupSee()));
            //加分成人所在部门
            UserEntity fourOrderUser = userManager.findUserByUserCode(fourOrder.getPeEmpCode());
            fourOrder.setPeDeptCode(fourOrderUser.getOwnerDeptCode());
            fourOrder.setPeDeptName(fourOrderUser.getOwnerDeptName());
            orderptList.add(fourOrder);

            OrderPercentageEntity fiveOrder = new OrderPercentageEntity();
            fiveOrder.setPeEmpCode(personNameObj.getOrderDealCode());
            fiveOrder.setPeEmpName(personNameObj.getOrderDealName());
            fiveOrder.setPercentageType(OrderPercentageType.DEAL);
            fiveOrder.setPercentageName("成交");
            fiveOrder.setMoneyPercentType("2");
            fiveOrder.setMoneyPercentTypeName("分成人分成");
            fiveOrder.setPercentage(new BigDecimal(personForm.getOrderDeal()));
            //加分成人所在部门
            UserEntity fiveOrderUser = userManager.findUserByUserCode(fiveOrder.getPeEmpCode());
            fiveOrder.setPeDeptCode(fiveOrderUser.getOwnerDeptCode());
            fiveOrder.setPeDeptName(fiveOrderUser.getOwnerDeptName());
            orderptList.add(fiveOrder);

            OrderPercentageEntity sixOrder = new OrderPercentageEntity();
            sixOrder.setPeEmpCode(personNameObj.getHouseDevelopCode());
            sixOrder.setPeEmpName(personNameObj.getHouseDevelopName());
            sixOrder.setPercentageType(OrderPercentageType.HOUSE);
            sixOrder.setPercentageName("房源开发");
            sixOrder.setMoneyPercentType("2");
            sixOrder.setMoneyPercentTypeName("分成人分成");
            sixOrder.setPercentage(new BigDecimal(personForm.getHouseDevelop()));
            //加分成人所在部门
            UserEntity sixOrderUser = userManager.findUserByUserCode(sixOrder.getPeEmpCode());
            sixOrder.setPeDeptCode(sixOrderUser.getOwnerDeptCode());
            sixOrder.setPeDeptName(sixOrderUser.getOwnerDeptName());
            orderptList.add(sixOrder);

            OrderPercentageEntity sevenOrder = new OrderPercentageEntity();
            sevenOrder.setPeEmpCode(personNameObj.getHouseEntrustCode());
            sevenOrder.setPeEmpName(personNameObj.getHouseEntrustName());
            sevenOrder.setPercentageType(OrderPercentageType.PROXY);
            sevenOrder.setPercentageName("房源委托");
            sevenOrder.setMoneyPercentType("2");
            sevenOrder.setMoneyPercentTypeName("分成人分成");
            sevenOrder.setPercentage(new BigDecimal(personForm.getHouseEntrust()));
            //加分成人所在部门
            UserEntity sevenOrderUser = userManager.findUserByUserCode(sevenOrder.getPeEmpCode());
            sevenOrder.setPeDeptCode(sevenOrderUser.getOwnerDeptCode());
            sevenOrder.setPeDeptName(sevenOrderUser.getOwnerDeptName());
            orderptList.add(sevenOrder);


        }
        //2,公司获客
        else {


//            OrderPercentageEntity oneOrder = new OrderPercentageEntity();
//            oneOrder.setPeEmpCode("ue001");
//            oneOrder.setPeEmpName("公司赵一");
//            oneOrder.setPercentageType(OrderPercentageType.CUSPERSONFEN);
//            oneOrder.setPercentageName("个人获客补贴");
//            oneOrder.setMoneyPercentType("2");
//            oneOrder.setMoneyPercentTypeName("分成人分成");
//            oneOrder.setPercentage(new BigDecimal(cpyPlatForm.getAcquireCusReward()));
//            oneOrder.setRemark("(公司获客)默认方案");
//            orderptList.add(oneOrder);

            OrderPercentageEntity twoOrder = new OrderPercentageEntity();
            twoOrder.setPeEmpCode(cpyNameObj.getCusPushCode());
            twoOrder.setPeEmpName(cpyNameObj.getCusPushName());
            twoOrder.setPercentageType(OrderPercentageType.CUSPUSHFEN);
            twoOrder.setPercentageName("客户推送");
            twoOrder.setMoneyPercentType("2");
            twoOrder.setMoneyPercentTypeName("分成人分成");
            twoOrder.setPercentage(new BigDecimal(cpyPlatForm.getCusPush()));
            //加分成人所在部门
            UserEntity twoOrderUser = userManager.findUserByUserCode(twoOrder.getPeEmpCode());
            twoOrder.setPeDeptCode(twoOrderUser.getOwnerDeptCode());
            twoOrder.setPeDeptName(twoOrderUser.getOwnerDeptName());
            orderptList.add(twoOrder);

            OrderPercentageEntity threeOrder = new OrderPercentageEntity();
            threeOrder.setPeEmpCode(cpyNameObj.getAcquireCusCode());
            threeOrder.setPeEmpName(cpyNameObj.getAcquireCusName());
            threeOrder.setPercentageType(OrderPercentageType.CUSTOMER);
            threeOrder.setPercentageName("上架获客分成");
            threeOrder.setMoneyPercentType("2");
            threeOrder.setMoneyPercentTypeName("分成人分成");
            threeOrder.setPercentage(new BigDecimal(cpyPlatForm.getAcquireCus()));
            //加分成人所在部门
            UserEntity threeOrderUser = userManager.findUserByUserCode(threeOrder.getPeEmpCode());
            threeOrder.setPeDeptCode(threeOrderUser.getOwnerDeptCode());
            threeOrder.setPeDeptName(threeOrderUser.getOwnerDeptName());
            orderptList.add(threeOrder);

            OrderPercentageEntity fourOrder = new OrderPercentageEntity();
            fourOrder.setPeEmpCode(cpyNameObj.getFollowupSeeCode());
            fourOrder.setPeEmpName(cpyNameObj.getFollowupSeeName());
            fourOrder.setPercentageType(OrderPercentageType.ASSIST_FOLLOW);
            fourOrder.setPercentageName("带看");
            fourOrder.setMoneyPercentType("2");
            fourOrder.setMoneyPercentTypeName("分成人分成");
            fourOrder.setPercentage(new BigDecimal(cpyPlatForm.getFollowupSee()));
            //加分成人所在部门
            UserEntity fourOrderUser = userManager.findUserByUserCode(fourOrder.getPeEmpCode());
            fourOrder.setPeDeptCode(fourOrderUser.getOwnerDeptCode());
            fourOrder.setPeDeptName(fourOrderUser.getOwnerDeptName());
            orderptList.add(fourOrder);

            OrderPercentageEntity fiveOrder = new OrderPercentageEntity();
            fiveOrder.setPeEmpCode(cpyNameObj.getOrderDealCode());
            fiveOrder.setPeEmpName(cpyNameObj.getOrderDealName());
            fiveOrder.setPercentageType(OrderPercentageType.DEAL);
            fiveOrder.setPercentageName("成交");
            fiveOrder.setMoneyPercentType("2");
            fiveOrder.setMoneyPercentTypeName("分成人分成");
            fiveOrder.setPercentage(new BigDecimal(cpyPlatForm.getOrderDeal()));
            //加分成人所在部门
            UserEntity fiveOrderUser = userManager.findUserByUserCode(fiveOrder.getPeEmpCode());
            fiveOrder.setPeDeptCode(fiveOrderUser.getOwnerDeptCode());
            fiveOrder.setPeDeptName(fiveOrderUser.getOwnerDeptName());
            orderptList.add(fiveOrder);

            OrderPercentageEntity sixOrder = new OrderPercentageEntity();
            sixOrder.setPeEmpCode(cpyNameObj.getHouseDevelopCode());
            sixOrder.setPeEmpName(cpyNameObj.getHouseDevelopName());
            sixOrder.setPercentageType(OrderPercentageType.HOUSE);
            sixOrder.setPercentageName("房源开发");
            sixOrder.setMoneyPercentType("2");
            sixOrder.setMoneyPercentTypeName("分成人分成");
            sixOrder.setPercentage(new BigDecimal(cpyPlatForm.getHouseDevelop()));
            //加分成人所在部门
            UserEntity sixOrderUser = userManager.findUserByUserCode(sixOrder.getPeEmpCode());
            sixOrder.setPeDeptCode(sixOrderUser.getOwnerDeptCode());
            sixOrder.setPeDeptName(sixOrderUser.getOwnerDeptName());
            orderptList.add(sixOrder);

            OrderPercentageEntity sevenOrder = new OrderPercentageEntity();
            sevenOrder.setPeEmpCode(cpyNameObj.getHouseEntrustCode());
            sevenOrder.setPeEmpName(cpyNameObj.getHouseEntrustName());
            sevenOrder.setPercentageType(OrderPercentageType.PROXY);
            sevenOrder.setPercentageName("房源委托");
            sevenOrder.setMoneyPercentType("2");
            sevenOrder.setMoneyPercentTypeName("分成人分成");
            sevenOrder.setPercentage(new BigDecimal(cpyPlatForm.getHouseEntrust()));
            //加分成人所在部门
            UserEntity sevenOrderUser = userManager.findUserByUserCode(sevenOrder.getPeEmpCode());
            sevenOrder.setPeDeptCode(sevenOrderUser.getOwnerDeptCode());
            sevenOrder.setPeDeptName(sevenOrderUser.getOwnerDeptName());
            orderptList.add(sevenOrder);

        }

        //添加协助分成人
        Boolean flag = orderVEntity.getAssistFlag();
        if(flag){
            OrderPercentageEntity oneOrder = new OrderPercentageEntity();
            List<OrderPercentageEntity> subAssistList = orderVEntity.getAssistEntityList();
            OrderPercentageEntity ptEntity = subAssistList.get(0);

            oneOrder.setPeEmpCode(ptEntity.getPeEmpCode());
            oneOrder.setPeEmpName(ptEntity.getPeEmpName());
            oneOrder.setPercentageType(OrderPercentageType.ASSIST_DEAL);
            oneOrder.setPercentageName("协助成交分成");
            oneOrder.setMoneyPercentType("2");
            oneOrder.setMoneyPercentTypeName("分成人分成");
            oneOrder.setPercentage(ptEntity.getPercentage());
            //加分成人所在部门
            UserEntity oneOrderUser = userManager.findUserByUserCode(oneOrder.getPeEmpCode());
            oneOrder.setPeDeptCode(oneOrderUser.getOwnerDeptCode());
            oneOrder.setPeDeptName(oneOrderUser.getOwnerDeptName());
            orderptList.add(oneOrder);

            OrderPercentageEntity hObjNewCopy = new OrderPercentageEntity();
            for (OrderPercentageEntity sObja : orderptList) {
                if(OrderPercentageType.DEAL.equals(sObja.getPercentageType())){


                    hObjNewCopy.setPeEmpCode(sObja.getPeEmpCode());
                    hObjNewCopy.setPeEmpName(sObja.getPeEmpName());
                    hObjNewCopy.setPercentageType(sObja.getPercentageType());
                    hObjNewCopy.setPercentageName(sObja.getPercentageName());
                    hObjNewCopy.setMoneyPercentType("2");
                    hObjNewCopy.setMoneyPercentTypeName("分成人分成");
                    hObjNewCopy.setPercentage(sObja.getPercentage());
                    hObjNewCopy.setPeDeptCode(sObja.getPeDeptCode());
                    hObjNewCopy.setPeDeptName(sObja.getPeDeptName());

                    BigDecimal oldDealNum = sObja.getPercentage();
                    BigDecimal newDealNum = new BigDecimal("0.00");
                    if(ptEntity.getPercentage().compareTo(oldDealNum) == 1){
                        throw new BusinessException("协助分成的比例"+ptEntity.getPercentage()+"不能大于成交分成的比例"+oldDealNum);
                    }
                    newDealNum = oldDealNum.subtract(ptEntity.getPercentage());
                    hObjNewCopy.setPercentage(newDealNum);

                    orderptList.add(hObjNewCopy);
                    orderptList.remove(sObja);

                    break;
                }
            }

        }

        orderObj.setOrderPercentageEntityList(orderptList);
        flushDividePersonMoney(orderptList,orderVEntity);
        return orderObj;
    }

    public void flushDividePersonMoney(List<OrderPercentageEntity> percentPersonPart,OrderEntity orderEntity) {
        //region 实际可分佣金总额,预备
        BigDecimal receivableHos = orderEntity.getReceivableHos();
        BigDecimal receivableCus = orderEntity.getReceivableCus();
        BigDecimal cusRebate = orderEntity.getCusRebate();
        BigDecimal ownerRebate = orderEntity.getOwnerRebate();
        BigDecimal cusParttimeMoney = orderEntity.getCusParttimeMoney();
        BigDecimal ownerParttimeMoney = orderEntity.getOwnerParttimeMoney();
        //endregion

        //region 总开单佣金总额(业主佣金加上客户佣金，没有减去返佣)
        BigDecimal commision = new BigDecimal("0.00");
        commision = receivableHos.add(receivableCus);
        orderEntity.setCommission(commision);
        //endregion

        //region 实际可分佣金总额actualCommision计算(业主佣金+客户佣金-客户返佣-业主返佣-客户兼职分钱-业主兼职分钱)
        BigDecimal actualCommision = new BigDecimal("0.00");
        actualCommision = receivableHos.add(receivableCus);
        actualCommision = actualCommision.subtract(cusRebate);
        actualCommision = actualCommision.subtract(ownerRebate);
        actualCommision = actualCommision.subtract(cusParttimeMoney);
        actualCommision = actualCommision.subtract(ownerParttimeMoney);
        orderEntity.setActualCommision(actualCommision);
        //endregion

        //增加实际分成佣金字段
        OrderEntity telephoneCus = new OrderEntity();
        String empcode = orderEntity.getEmpCode();
        telephoneCus = this.querycusservice(empcode,orderEntity.getCusCode());

        //客服分成明细
        List<OrderPercentageEntity> telephoneOrderPer = telephoneCus.getCusServicePercentageEntityList();

        BigDecimal teleSumPercent = new BigDecimal("0.00");
        for (int i = 0; i < telephoneOrderPer.size(); i++) {
            BigDecimal shuOne = telephoneOrderPer.get(i).getPercentage();
            teleSumPercent = teleSumPercent.add(shuOne);
        }
        BigDecimal actualTele = new BigDecimal("0.00");
        actualTele = actualCommision.multiply(teleSumPercent);
        actualTele = actualTele.divide(new BigDecimal("100"));
        BigDecimal actualBranchCommision = new BigDecimal("0.00");
        actualBranchCommision = actualCommision.subtract(actualTele);

        orderEntity.setActualBranchCommision(actualBranchCommision);

        // 保存订单分成(返回给前端)
        if (ListUtils.isNotEmpty(percentPersonPart)) {

            for (OrderPercentageEntity orderPercentageEntity : percentPersonPart) {
                orderPercentageEntity.setOrderCode(orderEntity.getOrderCode());
                orderPercentageEntity.setOrderTime(orderEntity.getOrderTime());
                // 设置分成业绩
                if (orderPercentageEntity.getProfit() == null || orderPercentageEntity.getProfit().equals(0)) {
                    //原来没有减去返佣和兼职分钱orderPercentageEntity.setProfit(orderEntity.getCommission().multiply(orderPercentageEntity.getPercentage()).divide(new BigDecimal(100)));
                    orderPercentageEntity.setProfit(orderEntity.getActualBranchCommision().multiply(orderPercentageEntity.getPercentage()).divide(new BigDecimal(100)));
                }

            }

        }




    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void dealPayBack(Date startTime,Date endTime,String flag){

        Map<String,Object> param = new HashMap<>();
        param.put("startTime",startTime);
        param.put("endTime",endTime);
        if (flag.equals("2")){
            orderPaybackDbDao.updateDeductionsSecond(param);
        }
        Map<String,Object> inMap = new HashMap<>();
        inMap.put("endTime",endTime);

        //查询所有有回款订单 以及返佣总金额  历史扣除返佣总额 本月应扣返佣金额
        List<OrderEntity> orderEntityList = orderDbDao.selectOrderByPayBack(param);
        for (OrderEntity orderEntity:orderEntityList){
            param.put("orderCode",orderEntity.getOrderCode());
            inMap.put("orderCode",orderEntity.getOrderCode());
            OrderBackDeductionsEntity orderBackDeductionsEntity = orderBackDeductionsDbDao.selectOrderDeductions(param);
            if (null==orderBackDeductionsEntity){
                continue;
            }
            BigDecimal deductMoney = orderBackDeductionsEntity.getDeductMoney();
            if (null==deductMoney){
                deductMoney = BigDecimal.ZERO;
            }
            BigDecimal backMoney = orderBackDeductionsEntity.getBackMoney();
            if (null==backMoney){
                backMoney = BigDecimal.ZERO;
            }
            BigDecimal unInvoiceMoney = orderBackDeductionsEntity.getUnInvoiceMoney();
            if (null==unInvoiceMoney){
                unInvoiceMoney = BigDecimal.ZERO;
            }
            if (backMoney.compareTo(BigDecimal.ZERO)==0&&unInvoiceMoney.compareTo(BigDecimal.ZERO)==0){
                continue;
            }
            BigDecimal needDeductMoney =backMoney.subtract(deductMoney);

            List<OrderPaybackEntity> orderPaybackEntities = orderPaybackDbDao.selectPayBack(param);
            List<OrderInvoiceEntity> orderInvoiceEntities = orderInvoiceDbDao.selectInvoiceWithoutDeductions(inMap);
            //需要再扣
            for (OrderPaybackEntity orderPaybackEntity : orderPaybackEntities) {
                Map<String,Object> payMap = new HashMap<>();

                if(needDeductMoney.compareTo(BigDecimal.ZERO)!=0){

                    if (needDeductMoney.compareTo(orderPaybackEntity.getPayment())==-1){
                        payMap=new HashMap<>();
                        payMap.put("orderCode",orderEntity.getOrderCode());
                        payMap.put("payBackCode",orderPaybackEntity.getPayBackOrderCode());
                        unInvoiceMoney = orderPaybackEntity.getTaxDeductions().add(unInvoiceMoney);
                        payMap.put("taxDeductions",unInvoiceMoney);
                        payMap.put("commissionDeductions",needDeductMoney);
                        orderPaybackDbDao.updateDeductions(payMap);
                        //下笔不在需要再扣needDeductMoney
                        //扣税
                        if (unInvoiceMoney.compareTo(BigDecimal.ZERO)!=0){
                            for (OrderInvoiceEntity orderInvoiceEntity : orderInvoiceEntities) {
                                Map<String,Object> orderInvoiceMap = new HashMap<>();
                                orderInvoiceMap.put("invoiceCode",orderInvoiceEntity.getInvoiceCode());
                                orderInvoiceMap.put("orderCode",orderEntity.getOrderCode());
                                orderInvoiceMap.put("orderInvoiceCode",orderInvoiceEntity.getOrderInvoiceCode());
                                orderInvoiceDbDao.updateDeductions(orderInvoiceMap);
                            }
                        }
                        //修改 unInvoiceMoney
                        break;
                    }else if(needDeductMoney.compareTo(orderPaybackEntity.getPayment())==0){
                        //下笔不需要再扣  扣钱为回款金额
                        payMap=new HashMap<>();
                        payMap.put("orderCode",orderEntity.getOrderCode());
                        payMap.put("payBackCode",orderPaybackEntity.getPayBackOrderCode());
                        payMap.put("commissionDeductions",needDeductMoney);
                        orderPaybackDbDao.updateDeductions(payMap);
                        if (unInvoiceMoney.compareTo(BigDecimal.ZERO)!=0){
                            needDeductMoney=BigDecimal.ZERO;
                            continue;
                        }else {
                            break;
                        }
                    }else {
                        //下笔回款需要再扣
                        payMap=new HashMap<>();
                        payMap.put("orderCode",orderEntity.getOrderCode());
                        payMap.put("payBackCode",orderPaybackEntity.getPayBackOrderCode());
                        payMap.put("commissionDeductions",orderPaybackEntity.getPayment());
                        orderPaybackDbDao.updateDeductions(payMap);
                        needDeductMoney=needDeductMoney.subtract(orderPaybackEntity.getPayment());
                        continue;
                    }
                }else {
                    //不需要再扣
                    //处理开票
                    payMap=new HashMap<>();
                    payMap.put("orderCode",orderEntity.getOrderCode());
                    payMap.put("payBackCode",orderPaybackEntity.getPayBackOrderCode());
                    unInvoiceMoney = orderPaybackEntity.getTaxDeductions().add(unInvoiceMoney);
                    payMap.put("taxDeductions",unInvoiceMoney);
                    orderPaybackDbDao.updateDeductions(payMap);
                    if (unInvoiceMoney.compareTo(BigDecimal.ZERO)!=0){
                        for (OrderInvoiceEntity orderInvoiceEntity : orderInvoiceEntities) {
                            Map<String,Object> orderInvoiceMap = new HashMap<>();
                            orderInvoiceMap.put("orderCode",orderEntity.getOrderCode());
                            orderInvoiceMap.put("invoiceCode",orderInvoiceEntity.getInvoiceCode());
                            orderInvoiceMap.put("orderInvoiceCode",orderInvoiceEntity.getOrderInvoiceCode());

                            orderInvoiceDbDao.updateDeductions(orderInvoiceMap);
                        }
                        unInvoiceMoney=BigDecimal.ZERO;
                    }
                }
            }
        }

    }

    /**
     * 历史数据批量处理
     * @param orderCode
     * @param deptCodes
     * @return
     */
    public List<CommissionRatioEntity> test(String orderCode,List<String> deptCodes) {

        //判断是否存在提成比例
        Map<String,Object> param=new HashMap<>();
        param.put("orderCode", orderCode);
        param.put("isDeleted", false);
        OrderEntity orderEntity=orderDbDao.detail(orderCode);
        if (!deptCodes.contains(orderEntity.getCreateDeptCode())){
            return null;
        }
        List<CommissionRatioEntity> commissionRatioEntities1=commissionRatioDbDao.selectByMap(param);
        if (null!=commissionRatioEntities1||commissionRatioEntities1.size()>0){
            Map<String,Object> map=new HashMap<>();
            map.put("orderCode", orderCode);
            commissionRatioDbDao.updateIsDeleteByOrderCode(map);
        }

        List<OrderPercentageEntity> percentListAll = new ArrayList<OrderPercentageEntity>();
        percentListAll = orderPercentageManager.findByMap(param);


        List<OrderPercentageEntity> percentListCusServ = new ArrayList<OrderPercentageEntity>();
        //分成人分成列表
        List<OrderPercentageEntity> percentListDividePerson = new ArrayList<OrderPercentageEntity>();
        for (OrderPercentageEntity orderPercentageOne : percentListAll) {
            String moneyPercentType = orderPercentageOne.getMoneyPercentType();
            if (moneyPercentType.equals(MoneyPercentType.SERVICE_PERCENT)) {
                //客服分成 暂不处理
                percentListCusServ.add(orderPercentageOne);
            } else if (moneyPercentType.equals(MoneyPercentType.PERSON_PERCENT)) {
                //个人分成
                percentListDividePerson.add(orderPercentageOne);
            }
        }
        int serviceNum=0;
        if (null!=percentListCusServ&&percentListCusServ.size()>0){
            serviceNum=percentListCusServ.size();
        }
        List<CommissionRatioEntity> commissionRatioEntities= null;
        try {
            commissionRatioEntities = createCommissionRatio(percentListDividePerson);

            commissionRatioDbDao.batchInsert(commissionRatioEntities);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return commissionRatioEntities;
    }

    /**
     * 创建订单生成提成比
     * @param orderCode
     * @param orderPercentageEntities
     * @return
     * @throws ParseException
     */
    public int createOrderCommission(String orderCode,List<OrderPercentageEntity> orderPercentageEntities) throws ParseException {
        UserEntity userEntity=userManager.findUserByUserCode(CurrentContext.getUserCode());
        orderCommission(orderCode,userEntity.getOwnerDeptCode(),orderPercentageEntities);

        return 1;
    }

    public int updateOrderCommission(String orderCode,List<OrderPercentageEntity> old) throws ParseException {
        List<String> deptCodes=deptManager.getWageAllDeptCodes();
        Map<String,Object> param=new HashMap<>();
        param.put("orderCode", orderCode);
        param.put("isDeleted", false);
        OrderEntity orderEntity=orderDbDao.detail(orderCode);
        String strDate="2019-03-01";
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        Date date=sdf.parse(strDate);
        if (date.getTime()>orderEntity.getOrderTime().getTime()){
            //3月1号之前的订单修改不进行处理
            return 1;
        }
        List<OrderPercentageEntity> newPercentageEntities =getPeDetail(orderCode,MoneyPercentType.PERSON_PERCENT);
        if (deptCodes.contains(orderEntity.getCreateDeptCode())){

            List<CommissionRatioEntity> commissionRatioEntityList = updateCommissionRatioEntity(old,newPercentageEntities);
            String preSql = "update  t_hkp_trade_order_commission_ratio set is_deleted=true " +
                    " WHERE order_code=\'%s\'";
            String sql = String.format(preSql,orderCode);
            commissionRatioDbDao.updateByNativeSql(sql);
            commissionRatioDbDao.batchInsert(commissionRatioEntityList);
        }


        return 1;
    }
    //修改分成造成的修改提成比例
    public List<CommissionRatioEntity> updateCommissionRatioEntity(List<OrderPercentageEntity> oldPercentageEntities,
                                                                   List<OrderPercentageEntity> newOrderPercentageEntities) throws ParseException {
        List<CommissionRatioEntity> commissionRatioEntityList=new ArrayList<>();



        commissionRatioEntityList.addAll(updatepe(oldPercentageEntities,newOrderPercentageEntities));

        return commissionRatioEntityList;
    }

    //生成新的分成比例
    public List<CommissionRatioEntity> updatepe(List<OrderPercentageEntity>  oldPercentageEntities,List<OrderPercentageEntity> newPercentageEntities) throws ParseException {
        List<CommissionRatioEntity> commissionRatioEntityList=new ArrayList<>();
        for (OrderPercentageEntity n:newPercentageEntities){
            CommissionRatioEntity commissionRatioEntity=new CommissionRatioEntity();
            for (OrderPercentageEntity o:oldPercentageEntities){
                if (n.getPeEmpCode().equals(o.getPeEmpCode())){
                    Map<String,Object> param =new HashMap<>();
                    param.put("peOrderCode",o.getPercentageCode());
                    param.put("orderCode",o.getOrderCode());
                    param.put("isDeleted",false);
                    commissionRatioEntity=commissionRatioDbDao.selectUniqueByMap(param);
                    commissionRatioEntity.setPeOrderCode(n.getPercentageCode());
                    commissionRatioEntity.setCommissionRatioCode(CodeGenUtils.generate("CR"));
                    commissionRatioEntity.setLastUpdateCode(CurrentContext.getUserCode());
                    commissionRatioEntity.setLastUpdateTime(new Date());
                    commissionRatioEntity.setPercentage(n.getPercentage());
                }
            }
            if (StringUtils.isNotEmpty(commissionRatioEntity.getCommissionRatioCode())){
                //原有分成人
                commissionRatioEntityList.add(commissionRatioEntity);
            }else {
                //新添加分成人
                List<OrderPercentageEntity> p=new ArrayList<>();
                p.add(n);
                List<CommissionRatioEntity> pc=createCommissionRatio(p);
                for (CommissionRatioEntity c:pc){
                    c.setHasChanged(true);
                }
                commissionRatioEntityList.addAll(pc);
            }
        }
        return commissionRatioEntityList;
    }
    //添加分成人



    //检查订单所属部门是否需要生成提成以及是否存在提成而需要重新生成
    public int orderCommission(String orderCode,String deptCode, List<OrderPercentageEntity> orderPercentageEntityList) throws ParseException {
        List<String> deptCodes=deptManager.getWageAllDeptCodes();
        if (deptCodes.contains(deptCode)){
            Map<String,Object> param=new HashMap<>();
            param.put("orderCode", orderCode);
            param.put("isDeleted", false);
            List<CommissionRatioEntity> commissionRatioEntityList=createCommissionRatio(orderPercentageEntityList);
            List<CommissionRatioEntity> commissionRatioEntities=commissionRatioDbDao.selectByMap(param);
            //判断是否修改过
            if (null!=commissionRatioEntities||commissionRatioEntities.size()>0){
                Map<String,Object> map=new HashMap<>();
                map.put("orderCode", orderCode);
                commissionRatioDbDao.updateIsDeleteByOrderCode(map);
                //修改过需要添加标识
                for (CommissionRatioEntity commissionRatioEntity : commissionRatioEntityList){
                    commissionRatioEntity.setHasChanged(true);
                }
            }

            commissionRatioDbDao.batchInsert(commissionRatioEntityList);
        }
        return 1;
    }


    /**
     * 生成分成比例
     * @param orderPercentageEntities
     * @return
     */
    public List<CommissionRatioEntity> createCommissionRatio(List<OrderPercentageEntity> orderPercentageEntities) throws ParseException {
        if (orderPercentageEntities != null) {
            List<CommissionRatioEntity> commissionRatioEntities=new ArrayList<>();
            for (OrderPercentageEntity orderPercentageEntity : orderPercentageEntities) {
                CommissionRatioEntity commissionRatioEntity=new CommissionRatioEntity();
                UserEntity userEntity=userManager.selectUniqueByProp(orderPercentageEntity.getPeEmpCode());
                int days =enterDays(userEntity.getEntryTime(),orderPercentageEntity.getOrderTime());
                //大于等于30都为第31天
                if (days>=30){
                    //是否是资深经理
                    UserRelationEntity userRelationEntity=userRelationDbDao.getUserRelationEntity(orderPercentageEntity.getPeEmpCode());
                    if (!userEntity.getEmpPostCode().equals("PT201903250001feae")){
                        if (userEntity.getEmpPostCode().equals("PT201603310001")){
                            //经理个人提成
                            createCommission(commissionRatioEntity,orderPercentageEntity,userEntity);
                            //自身经历提成
                            if (StringUtils.isNotEmpty(userRelationEntity.getManagerCode())){
                                createManagerCommission(commissionRatioEntity,userRelationEntity.getManagerCode());
                            }
                        }else if (null!=userRelationEntity&&orderPercentageEntity.getPeEmpCode().equals(userRelationEntity.getUserGroupCode())
                                &&userEntity.getEmpPostCode().equals("PT20190315000194c5")
                                &&orderPercentageEntity.getPeEmpCode().equals(userRelationEntity.getUserGroupCode())){
                            //组长个人提成
                            createCommission(commissionRatioEntity,orderPercentageEntity,userEntity);
                            //经理提成
                            createLeaderCommission(commissionRatioEntity,userRelationEntity.getLeaderCode());
                            //资深经理提成
                            if (StringUtils.isNotEmpty(userRelationEntity.getManagerCode())){
                                createManagerCommission(commissionRatioEntity,userRelationEntity.getManagerCode());
                            }
                        }else{
                            //组员个人提成
                            createCommission(commissionRatioEntity,orderPercentageEntity,userEntity);
                            //组长提成
                            if(StringUtils.isNotEmpty(userRelationEntity.getUserGroupCode())){
                                createGroupCommission(commissionRatioEntity,userRelationEntity.getUserGroupCode());
                            }
                            //经理提成
                            createLeaderCommission(commissionRatioEntity,userRelationEntity.getLeaderCode());

                            //资深经理提成
                            if (StringUtils.isNotEmpty(userRelationEntity.getManagerCode())){
                                createManagerCommission(commissionRatioEntity,userRelationEntity.getManagerCode());
                            }
                        }
                    }else {
                        //资深经理自身提成
                        createCommission(commissionRatioEntity,orderPercentageEntity,userEntity);
                    }

                    //若存在推荐人
                    if (StringUtils.isNotEmpty(userRelationEntity.getRecommendCode())){
                        createRecommendCommission(commissionRatioEntity,userRelationEntity.getRecommendCode());
                    }

                }else {
                    //固定底薪
                    commissionRatioEntity.setCommissionRatio(BigDecimal.valueOf(50));
                    commissionRatioEntity.setUserCode(orderPercentageEntity.getPeEmpCode());
                    commissionRatioEntity.setSalaryType(CommissionType.FIXED);
                    commissionRatioEntity.setOrderCode(orderPercentageEntity.getOrderCode());
                    commissionRatioEntity.setPeOrderCode(orderPercentageEntity.getPercentageCode());
                    commissionRatioEntity.setPercentage(orderPercentageEntity.getPercentage());
                }
                commissionRatioEntity.setOrderTime(orderPercentageEntity.getOrderTime());
                commissionRatioEntity.setDeptCode(orderPercentageEntity.getPeDeptCode());
                commissionRatioEntities.add(commissionRatioEntity);

            }

            if (null!=commissionRatioEntities&&commissionRatioEntities.size()>0){
                for (CommissionRatioEntity commissionRatioEntity : commissionRatioEntities) {
                    Date date= new Date();
                    commissionRatioEntity.setCommissionRatioCode(CodeGenUtils.generate("CR"));
                    commissionRatioEntity.setCreateCode(CurrentContext.getUserCode());
                    commissionRatioEntity.setCreateTime(date);
                }
            }else {
                throw new BusinessException("提成数据计算异常");
            }
            return commissionRatioEntities;
        }else {
            throw new BusinessException("提成数据计算异常");
        }
    }


    //资深经理提成
    public CommissionRatioEntity createManagerCommission(CommissionRatioEntity commissionRatioEntity,String managerCode){

        commissionRatioEntity.setManagerPercentage(BigDecimal.valueOf(6));
        commissionRatioEntity.setManagerUserCode(managerCode);
        return commissionRatioEntity;
    }
    //经理提成
    public CommissionRatioEntity createLeaderCommission(CommissionRatioEntity commissionRatioEntity,String leaderCode){

        commissionRatioEntity.setLeaderPercentage(BigDecimal.valueOf(12));
        commissionRatioEntity.setLeaderCode(leaderCode);
        return commissionRatioEntity;
    }
    //组长提成
    public CommissionRatioEntity createGroupCommission(CommissionRatioEntity commissionRatioEntity,String groupLeaderCode){

        commissionRatioEntity.setGroupPercentage(BigDecimal.valueOf(2));
        commissionRatioEntity.setGroupUserCode(groupLeaderCode);
        return commissionRatioEntity;
    }
    //自身提成
    public CommissionRatioEntity createCommission(CommissionRatioEntity commissionRatioEntity,OrderPercentageEntity orderPercentageEntity,UserEntity userEntity) throws ParseException {
        if (userEntity.getEmpPostCode().equals("PT20190315000194c5")||userEntity.getEmpPostCode().equals("PT201712210004d713")
                ||userEntity.getEmpPostCode().equals("PT201601310001")||userEntity.getEmpPostCode().equals("PT201805030001be8b")){
            //根据工龄计算提成比例
            int years=getYearInterval(userEntity.getEntryTime(),orderPercentageEntity.getOrderTime());
            if (years>=4){
                //入职时间4年以上
                commissionRatioEntity.setCommissionRatio(BigDecimal.valueOf(75));
            }else if (years>=2){
                //入职时间[2,4)
                commissionRatioEntity.setCommissionRatio(BigDecimal.valueOf(70));
            }else if (years>=1){
                //入职时间[1,2)
                commissionRatioEntity.setCommissionRatio(BigDecimal.valueOf(65));
            }else {
                commissionRatioEntity.setCommissionRatio(BigDecimal.valueOf(60));
            }
        }else {
            //其它为60
            commissionRatioEntity.setCommissionRatio(BigDecimal.valueOf(60));
        }
        commissionRatioEntity.setUserCode(orderPercentageEntity.getPeEmpCode());
        commissionRatioEntity.setSalaryType(CommissionType.PERSONAL);
        commissionRatioEntity.setOrderCode(orderPercentageEntity.getOrderCode());
        commissionRatioEntity.setPeOrderCode(orderPercentageEntity.getPercentageCode());
        commissionRatioEntity.setPercentage(orderPercentageEntity.getPercentage());
        return commissionRatioEntity;
    }
    //推荐提成
    public CommissionRatioEntity createRecommendCommission(CommissionRatioEntity commissionRatioEntity,String recommendCode){

        commissionRatioEntity.setRecommendPercentage(BigDecimal.valueOf(3));
        commissionRatioEntity.setRecommendCode(recommendCode);
        return commissionRatioEntity;
    }






    //入职天数计算
    /**
     * 因存在隔天录入订单的情况，不能以薪资类型来判段开单时应使用的薪资类型
     */
    public int enterDays(Date enterTime,Date orderTime){
        //创建时间
        try {
            enterTime=DateUtil.beginOfDay(enterTime);
        } catch (Exception e) {
        }

        try {
            orderTime=DateUtil.beginOfDay(orderTime);
        } catch (Exception e) {
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(enterTime);
        long time1 = cal.getTimeInMillis();
        cal.setTime(orderTime);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }
    //获取入职年限
    public int getYearInterval(Date start,Date end) throws ParseException {
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        start =sdf.parse(sdf.format(start));
        end =sdf.parse(sdf.format(end));

        Instant startInstant = start.toInstant();
        Instant endInstant = end.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        LocalDate localStartDate = startInstant.atZone(zoneId).toLocalDate();
        LocalDate localEndDate = endInstant.atZone(zoneId).toLocalDate();
        Period p = Period.between(localStartDate, localEndDate);
        return p.getYears();
    }

    private Boolean updateOrderStatusCheck(String orderCode){
        Boolean check=false;
        OrderEntity oldOrder =orderDbDao.selectUniqueByProp("orderCode",orderCode);
        DeptEntity de=deptManager.getDeptEntity(oldOrder.getDeptCode());
        if(de.getLeaderCode().equals(CurrentContext.getUserCode())&&oldOrder.getOrderStatus().equals(OrderStatus.NoSign)) {
            check=true;
        }
        return check;
    }
}
