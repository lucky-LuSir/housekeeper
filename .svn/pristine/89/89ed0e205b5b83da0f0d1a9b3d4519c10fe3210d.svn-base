package com.kfwy.hkp.trade.order.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.AbstractServiceResponse;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.trade.order.business.IOrderInvoiceManager;
import com.kfwy.hkp.trade.order.business.IOrderManager;
import com.kfwy.hkp.trade.order.dao.IOrderInvoiceDbDao;
import com.kfwy.hkp.trade.order.dao.IOrderPaybackDbDao;
import com.kfwy.hkp.trade.order.entity.OrderEntity;
import com.kfwy.hkp.trade.order.entity.OrderInvoiceEntity;
import com.kfwy.hkp.trade.order.entity.OrderPaybackEntity;
import com.kfwy.hkp.trade.order.entity.OrderPercentageEntity;
import com.kfwy.hkp.trade.wage.dao.ICommissionRatioDbDao;
import com.kfwy.hkp.trade.wage.dao.ICommissionWageDbDao;
import com.kfwy.hkp.trade.wage.entity.CommissionRatioEntity;
import com.kfwy.hkp.trade.wage.entity.CommissionWageEntity;
import com.kfwy.hkp.trade.wage.enums.CommissionType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/11/11.
 */
@Service
public class OrderInvoiceManagerImpl extends AbstractManager<OrderInvoiceEntity> implements IOrderInvoiceManager {
    @Override
    protected IMyBatisBaseDao<OrderInvoiceEntity, Long> getMyBatisDao() {
        return this.orderInvoiceDbDao;
    }
    @Autowired
    private IOrderInvoiceDbDao orderInvoiceDbDao;
    @Autowired
    private IOrderManager orderManager;
    @Autowired
    private ICommissionRatioDbDao commissionRatioDbDao;
    @Autowired
    private ICommissionWageDbDao commissionWageDbDao;
    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private IOrderPaybackDbDao orderPaybackDbDao;

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public int insert(List<OrderInvoiceEntity> orderInvoiceEntity, AbstractServiceResponse response){
        List<OrderInvoiceEntity> orderInvoiceList=new ArrayList<OrderInvoiceEntity>();
//        List<CommissionWageEntity> commissionWageEntities =new ArrayList<>();
        for(OrderInvoiceEntity order:orderInvoiceEntity){
            OrderEntity orderEntity=orderManager.detail(order.getOrderCode());
            if(orderEntity==null){
                throw new BusinessException("订单"+order.getOrderCode()+"不存在");
            }
            order.setOrderInvoiceCode(CodeGenUtils.generate("PI"));
            orderInvoiceList.add(order);
        }
        List<CommissionWageEntity> commissionWageEntities = createInvoiceWage(orderInvoiceEntity);
        if (null!=commissionWageEntities&&commissionWageEntities.size()>0){
            commissionWageDbDao.batchInsert(commissionWageEntities);
        }
        return orderInvoiceDbDao.batchInsert(orderInvoiceList);
    }

    @Override
    public int update(OrderInvoiceEntity pay){
        if (StringUtils.isEmpty(pay.getInvoiceCode())){
            throw new BusinessException("发票号为空，修改失败");
        }


        Map<String,Object> param=new HashMap<>();
        param.put("orderCode",pay.getOrderCode());
        param.put("orderInvoiceCode",pay.getOrderInvoiceCode());
        OrderInvoiceEntity orderInvoiceEntity =orderInvoiceDbDao.selectOne(param);

        if (orderInvoiceEntity.getHasDeductions()&&orderInvoiceEntity.getInvoiceTax().compareTo(pay.getInvoiceTax())!=0){
            throw new BusinessException("该开票已统计扣款，无法修改金额，详情请联系管理员");
        }

        String preSql = "update  t_hkp_trade_order_commission_wage set is_deleted=true" +
                " WHERE pay_back_order_code=\'%s\'";
        String sql = String.format(preSql,pay.getOrderInvoiceCode());
        commissionWageDbDao.updateByNativeSql(sql);
        List<OrderInvoiceEntity> orderInvoiceEntities=new ArrayList<>();
        orderInvoiceEntities.add(pay);
        List<CommissionWageEntity> commissionWageEntities = createInvoiceWage(orderInvoiceEntities);
        if (null!=commissionWageEntities&&commissionWageEntities.size()>0){
            commissionWageDbDao.batchInsert(commissionWageEntities);
        }
        if (orderInvoiceEntity.getHasDeductions()){
            /**
             * 每个月初2号 4号会刷新回款数据  将相应的开票扣款扣除在有回款记录中的taxDeductions中
             *
             *
             * 若修改时发现修改的开票已被扣除过，则从开票日期所在月第一天后的回款中必定扣除了该回款
             *          如若查询不到回款记录则数据出现异常，请排查数据
             *
             * 当hasDeductions为false时说明该开票未扣除，将会在下次回款中扣除
             * 在commissionWage功能中 每月4号会查询所有为未进行扣款的开票后续是否有进行回款，有回款则修改为已扣除
             */

            //如果被统计需要修改被扣回款
            Date taxTime = orderInvoiceEntity.getInvoiceTime();
            Date startTime = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(taxTime);
            cal.set(Calendar.DAY_OF_MONTH,1);
            //获取所在月第一天
            startTime =DateFormatUtil.dayBegin(cal.getTime());
            param.put("startTime",startTime);
            OrderPaybackEntity orderPaybackEntity = orderPaybackDbDao.selectDeductionsForInvoice(param);
            if (null==orderPaybackEntity){
                throw new BusinessException("该税款已在相应业绩中扣除,但未找到扣除记录,修改失败请联系管理员");
            }
            BigDecimal taxDeductions =orderPaybackEntity.getTaxDeductions().subtract(orderInvoiceEntity.getInvoiceTax()).add(pay.getInvoiceTax());
            Map<String,Object> payBackMap = new HashMap<>();
            payBackMap.put("payBackCode",orderPaybackEntity.getPayBackOrderCode());
            payBackMap.put("taxDeductions",taxDeductions);
            orderPaybackDbDao.updateDeductions(payBackMap);
        }

        return orderInvoiceDbDao.update(pay);
    }

    @Override
    public PageResult<OrderInvoiceEntity> selectByMap(Map<String, Object> map, int start, int pageSize){
        return orderInvoiceDbDao.selectByMap(map, start, pageSize, "invoice_time", false);
    }

    @Override
    public int dealshui() throws ParseException {

        String strDate="2019-03-01";
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        Date orderTimeStart=sdf.parse(strDate);

        Map<String,Object> param=new HashMap<>();
        param.put("invoiceTimeStart",orderTimeStart);
        param.put("isDeleted",false);
        List<OrderInvoiceEntity> orderInvoiceEntities=orderInvoiceDbDao.selectByMap(param);
        List<CommissionWageEntity> commissionWageEntities=createInvoiceWage(orderInvoiceEntities);
        commissionWageDbDao.batchInsert(commissionWageEntities);

        return 0;
    }

    public List<CommissionWageEntity> createInvoiceWage(List<OrderInvoiceEntity> orderInvoiceEntities){
        List<CommissionWageEntity> commissionWageEntities=new ArrayList<>();
        List<String> deptCodes = deptManager.getWageDeptCodes();
        for (OrderInvoiceEntity orderInvoiceEntity : orderInvoiceEntities){
                commissionWageEntities.addAll(createInvoiceWage(orderInvoiceEntity,deptCodes));
        }
        return commissionWageEntities;
    }

    public List<CommissionWageEntity> createInvoiceWage(OrderInvoiceEntity orderInvoiceEntity,List<String> deptCodes){
        List<CommissionWageEntity> commissionWageEntities=new ArrayList<>();
        Map<String,Object> param=new HashMap<>();
        param.put("orderCode",orderInvoiceEntity.getOrderCode());
        param.put("isDeleted",false);
        OrderEntity orderEntity=orderManager.detail(orderInvoiceEntity.getOrderCode());

        if (!deptCodes.contains(orderEntity.getCreateDeptCode())){
            //仅计算招商选址部门内业务部门订单
            return commissionWageEntities;
        }

        BigDecimal serviceRatio = BigDecimal.ZERO;  //服务分成比例
        BigDecimal commissionWage = BigDecimal.ZERO; //业务分成金额
        if (null!=orderEntity.getCusServicePercentageEntityList()&&orderEntity.getCusServicePercentageEntityList().size()>0){
            List<OrderPercentageEntity>  opl = orderEntity.getCusServicePercentageEntityList();
            for (OrderPercentageEntity ope : opl) {
                serviceRatio = ope.getPercentage().add(serviceRatio);
            }

        }
        commissionWage = orderInvoiceEntity.getInvoiceTax().multiply(BigDecimal.valueOf(1).subtract(serviceRatio.divide(BigDecimal.valueOf(100))));

        List<CommissionRatioEntity> commissionRatioEntities=commissionRatioDbDao.selectByMap(param);

        Date date=new Date();
        for (CommissionRatioEntity commissionRatioEntity : commissionRatioEntities) {
            List<CommissionWageEntity> commissionWageEntityList =createWageEntity(commissionRatioEntity,commissionWage);
            for (CommissionWageEntity commissionWageEntity:commissionWageEntityList){
                commissionWageEntity.setCommissionWageCode(CodeGenUtils.generate("CI"));
                commissionWageEntity.setPayBackAchievements(orderInvoiceEntity.getInvoiceTax());
                commissionWageEntity.setPayBackAmount(orderInvoiceEntity.getInvoiceTax());
                commissionWageEntity.setOrderCode(commissionRatioEntity.getOrderCode());
                commissionWageEntity.setCommissionRatioCode(commissionRatioEntity.getCommissionRatioCode());
                commissionWageEntity.setPeOrderCode(commissionRatioEntity.getPeOrderCode());
                commissionWageEntity.setPercentage(commissionRatioEntity.getPercentage());
                commissionWageEntity.setPayBackOrderCode(orderInvoiceEntity.getOrderInvoiceCode());
                commissionWageEntity.setPayBackTime(orderInvoiceEntity.getInvoiceTime());
                commissionWageEntity.setCreateTime(date);
                commissionWageEntity.setWageType("2");//开票扣款
                commissionWageEntity.setCreateCode(CurrentContext.getUserCode());
                commissionWageEntity.setHasChanged(commissionRatioEntity.getHasChanged());
            }
            commissionWageEntities.addAll(commissionWageEntityList);
        }
        return commissionWageEntities;
    }



    public List<CommissionWageEntity> createWageEntity(CommissionRatioEntity commissionRatioEntity,BigDecimal commissionWage){

        List<CommissionWageEntity> commissionWageEntities=new ArrayList<>();

        //个人
        CommissionWageEntity commissionWageEntity=new CommissionWageEntity();
        commissionWageEntity.setUserCode(commissionRatioEntity.getUserCode());
        if (commissionRatioEntity.getSalaryType().equals("1")){
            commissionWageEntity.setCommissionType(CommissionType.FIXED);
        }else {
            commissionWageEntity.setCommissionType(CommissionType.PERSONAL);
        }

        BigDecimal wage=commissionWage.multiply((commissionRatioEntity.getPercentage()).divide(BigDecimal.valueOf(100)))
                .multiply(commissionRatioEntity.getCommissionRatio().divide(BigDecimal.valueOf(100)));
        commissionWageEntity.setCommissionWage(BigDecimal.ZERO.subtract(wage));
        commissionWageEntity.setCommissionRatio(commissionRatioEntity.getCommissionRatio());
        commissionWageEntities.add(commissionWageEntity);
        //组
        if (StringUtils.isNotEmpty(commissionRatioEntity.getGroupUserCode())){
            CommissionWageEntity group=new CommissionWageEntity();
            group.setUserCode(commissionRatioEntity.getGroupUserCode());
            BigDecimal groupWage=commissionWage.multiply((commissionRatioEntity.getPercentage()).divide(BigDecimal.valueOf(100)))
                    .multiply(commissionRatioEntity.getGroupPercentage().divide(BigDecimal.valueOf(100)));
            group.setCommissionWage(BigDecimal.ZERO.subtract(groupWage));
            group.setCommissionType(CommissionType.GROUPMANAGER);
            group.setCommissionRatio(commissionRatioEntity.getGroupPercentage());
            commissionWageEntities.add(group);
        }
        //经理
        if (StringUtils.isNotEmpty(commissionRatioEntity.getLeaderCode())){
            CommissionWageEntity leader=new CommissionWageEntity();
            leader.setUserCode(commissionRatioEntity.getLeaderCode());
            BigDecimal leaderWage=commissionWage.multiply((commissionRatioEntity.getPercentage()).divide(BigDecimal.valueOf(100)))
                    .multiply(commissionRatioEntity.getLeaderPercentage().divide(BigDecimal.valueOf(100)));
            leader.setCommissionWage(BigDecimal.ZERO.subtract(leaderWage));
            leader.setCommissionType(CommissionType.LEADERMANAGER);
            leader.setCommissionRatio(commissionRatioEntity.getLeaderPercentage());
            commissionWageEntities.add(leader);
        }
        //资深经理
        if (StringUtils.isNotEmpty(commissionRatioEntity.getManagerUserCode())){
            CommissionWageEntity manager=new CommissionWageEntity();
            manager.setUserCode(commissionRatioEntity.getManagerUserCode());
            BigDecimal managerWage=commissionWage.multiply((commissionRatioEntity.getPercentage()).divide(BigDecimal.valueOf(100)))
                    .multiply(commissionRatioEntity.getManagerPercentage().divide(BigDecimal.valueOf(100)));
            manager.setCommissionWage(BigDecimal.ZERO.subtract(managerWage));
            manager.setCommissionType(CommissionType.SENIORMANAGER);
            manager.setCommissionRatio(commissionRatioEntity.getManagerPercentage());
            commissionWageEntities.add(manager);
        }
        //推荐人
        if (StringUtils.isNotEmpty(commissionRatioEntity.getRecommendCode())){
            CommissionWageEntity recommend=new CommissionWageEntity();
            recommend.setUserCode(commissionRatioEntity.getRecommendCode());
            BigDecimal recommendWage=commissionWage.multiply((commissionRatioEntity.getPercentage()).divide(BigDecimal.valueOf(100)))
                    .multiply(commissionRatioEntity.getRecommendPercentage().divide(BigDecimal.valueOf(100)));
            recommend.setCommissionWage(BigDecimal.ZERO.subtract(recommendWage));
            recommend.setCommissionType(CommissionType.RECOMMEND);
            recommend.setCommissionRatio(commissionRatioEntity.getRecommendPercentage());
            commissionWageEntities.add(recommend);
        }

        return commissionWageEntities;
    }

}
