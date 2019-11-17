package com.kfwy.hkp.trade.order.business.impl;


import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.AbstractServiceResponse;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.trade.order.business.IOrderManager;
import com.kfwy.hkp.trade.order.business.IOrderPaybackManager;
import com.kfwy.hkp.trade.order.dao.IOrderDbDao;
import com.kfwy.hkp.trade.order.dao.IOrderPaybackDbDao;
import com.kfwy.hkp.trade.order.entity.OrderEntity;
import com.kfwy.hkp.trade.order.entity.OrderPaybackEntity;
import com.kfwy.hkp.trade.order.entity.OrderPercentageEntity;
import com.kfwy.hkp.trade.order.enums.OrderStatus;
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
 * @Author:ChenJie Created by kfwy_it_009 on 2018/11/9.
 */
@Service
public class OrderPaybackManagerImpl extends AbstractManager<OrderPaybackEntity> implements IOrderPaybackManager {
    @Autowired
    private IOrderPaybackDbDao oderPaybackDbDao;
    @Autowired
    private IOrderDbDao orderDbDao;
    @Autowired
    private IOrderManager orderManager;
    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private ICommissionRatioDbDao commissionRatioDbDao;
    @Autowired
    private ICommissionWageDbDao commissionWageDbDao;


    @Override
    protected IMyBatisBaseDao<OrderPaybackEntity, Long> getMyBatisDao() {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(List<OrderPaybackEntity> pe, AbstractServiceResponse response) throws Exception {
        List<String> deptCodes=deptManager.getWageAllDeptCodes();

        for (OrderPaybackEntity p : pe) {
            //查询订单，判断订单是否存在，是否为已回款订单
            Map<String, Object> map = new HashMap<String, Object>(1);
            map.put("orderCode", p.getOrderCode());
            OrderEntity temp = orderManager.selectUniqueByMap(map);
            if (temp == null) {
//                response.setMessage("" + p.getOrderCode() + "订单不存在");
//                throw new Exception();
                throw new BusinessException("" + p.getOrderCode() + "订单不存在");

            } else if (temp.getOrderStatus() == OrderStatus.BackPayment) {
//                response.setMessage("" + p.getOrderCode() + "订单已经完成了全部回款，无法多次录入回款");
//                throw new Exception();
                throw new BusinessException("" + p.getOrderCode() + "订单已经完成了全部回款，无法多次录入回款");

            }
            p.setPayBackOrderCode(CodeGenUtils.generate("PB"));
            p.setCreateTime(DateFormatUtil.getCurrentDateTime());
            p.setCreateCode(CurrentContext.getUserInfo().getUserCode());
            p.setCreateName(CurrentContext.getUserInfo().getUserName());
            p.setLastUpdateTime(DateFormatUtil.getCurrentDateTime());
            p.setIsDeleted(Boolean.FALSE);

            this.createCommissionWage(p,deptCodes);

            //inset回款记录
            oderPaybackDbDao.insert(p);
            //比较回款金额  修改订单状态
            statisticsOrderPaybackPrice(p.getOrderCode(),response);
        }

//        if (oderPaybackDbDao.batchInsert(pe) > 0) {
//            return updateOrderStatus(pe, response);
//        }
        return 1;
    }


    @Override
    @Transactional
    public int create(OrderPaybackEntity p) {
        //查询订单，判断订单是否存在，是否为已回款订单
        int i = 0;
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("orderCode", p.getOrderCode());
        OrderEntity temp = orderManager.selectUniqueByMap(map);
        if (temp == null) {
            throw new BusinessException("" + p.getOrderCode() + "订单不存在");
        } else if (temp.getOrderStatus().equals(OrderStatus.BackPayment)) {
            throw new BusinessException("" + p.getOrderCode() + "订单已经完成了全部回款，无法多次录入回款");
        }
        OperateFillUtils.fill(p);
        p.setPayBackOrderCode(CodeGenUtils.generate("PB"));
        List<String> deptCodes=deptManager.getWageAllDeptCodes();
        this.createCommissionWage(p,deptCodes);
        int j= oderPaybackDbDao.insert(p);
        if (j > 0) {

            try {
                i = this.statisticsOrderPaybackPrice(p.getOrderCode(), null);
            } catch (Exception e) {
                throw new BusinessException("回款金额总额超过订单合同金额，无法录入回款");
            }

        }
        return i;
    }

    @Override
    public List<OrderPaybackEntity> selectByMap(Map<String, Object> map) {
        map.put("is_deleted", Boolean.FALSE);
        return oderPaybackDbDao.selectByMap(map);
    }

    @Override
    public PageResult<OrderPaybackEntity> selectByMap(Map<String, Object> map, int start, int pageSize) {
        return oderPaybackDbDao.selectByMap(map, start, pageSize, "create_time", false);
    }

    @Override
    public int createWage() throws ParseException {

        //查出3月后的订单
        List<String> deptCodes=deptManager.getWageAllDeptCodes();

        String strDate="2019-03-01";
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        Date orderTimeStart=sdf.parse(strDate);

        Map<String,Object> param=new HashMap<>();
        param.put("orderTimeStart",orderTimeStart);
        param.put("isDeleted",false);
        List<OrderEntity> orderEntities=orderManager.findByMap(param);
        //循环订单查询回款   逐条计算提成
        for (OrderEntity orderEntity : orderEntities) {
            if (deptCodes.contains(orderEntity.getCreateDeptCode())){
                OrderEntity orderEntityDetail=orderDbDao.detail(orderEntity.getOrderCode());
                Map<String,Object> map=new HashMap<>();
                map.put("orderCode",orderEntity.getOrderCode());
                List<OrderPaybackEntity> paybackEntities = this.selectByMap(map);

                BigDecimal price = new BigDecimal(0);
                BigDecimal backCommission =orderEntityDetail.getCommission().subtract(orderEntityDetail.getActualCommision());

                for (OrderPaybackEntity paybackEntity : paybackEntities) {
                    BigDecimal commissionWage = new BigDecimal(0);
                    if (price.compareTo(backCommission)==1){
                        //全部提成
                        commissionWage=paybackEntity.getPayment();
                    }else{
                        price=price.add(paybackEntity.getPayment());
                        if (price.compareTo(backCommission)==1){
                            commissionWage=price.subtract(backCommission);
                        }
                    }
                    if (commissionWage.compareTo(BigDecimal.valueOf(0))==1){
                        getCommissionWage(paybackEntity.getPayBackOrderCode(),paybackEntity.getOrderCode(),paybackEntity.getBackTime(),commissionWage,paybackEntity.getPayment());
                    }else {
                        getCommissionWage(paybackEntity.getPayBackOrderCode(),paybackEntity.getOrderCode(),paybackEntity.getBackTime(),BigDecimal.ZERO,paybackEntity.getPayment());

                    }
                }
            }
        }

        return 0;
    }


    @Override
    public int update(OrderPaybackEntity pay) {
        int i = 0;
        if (StringUtils.isEmpty(pay.getPayBackOrderCode())){
            throw new BusinessException("回款编码为空，修改失败");
        }
        pay.setLastUpdateCode(CurrentContext.getUserInfo().getUserCode());
        pay.setLastUpdateName(CurrentContext.getUserInfo().getUserName());
        int j = oderPaybackDbDao.update(pay);
        pay.setCreateTime(DateFormatUtil.getCurrentDateTime());
        pay.setCreateCode(CurrentContext.getUserInfo().getUserCode());
        pay.setCreateName(CurrentContext.getUserInfo().getUserName());
        pay.setLastUpdateTime(DateFormatUtil.getCurrentDateTime());
        pay.setIsDeleted(Boolean.FALSE);
        List<String> deptCodes=deptManager.getWageAllDeptCodes();
        //逻辑删除修改前回款提成
        CommissionWageEntity commissionWageEntity = new CommissionWageEntity();
        commissionWageEntity.setPayBackOrderCode(pay.getPayBackOrderCode());
        commissionWageEntity.setIsDeleted(false);
        String preSql = "update  t_hkp_trade_order_commission_wage set is_deleted=true " +
                " WHERE pay_back_order_code=\'%s\'";
        String sql = String.format(preSql,pay.getPayBackOrderCode());
        commissionWageDbDao.updateByNativeSql(sql);
        this.createCommissionWage(pay,deptCodes);
        if (j > 0) {
            try {
                i = this.statisticsOrderPaybackPrice(pay.getOrderCode(), null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    //根据回款金额修改订单状态
    protected int updateOrderStatus(List<OrderPaybackEntity> pe, AbstractServiceResponse response) throws Exception {
        int num = 0;
        for (OrderPaybackEntity p : pe) {
            num = this.statisticsOrderPaybackPrice(p.getOrderCode(), response);
        }
        return num;
    }

    //根据回款金额修改订单状态
    protected int statisticsOrderPaybackPrice(String orderCode, AbstractServiceResponse response) throws Exception {
        int num = 0;
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("orderCode", orderCode);
        List<OrderPaybackEntity> pay = this.selectByMap(map);
        BigDecimal price = new BigDecimal(0);
        //统计回款总金额
        for (int i = 0; i < pay.size(); i++) {
            price = price.add(pay.get(i).getPayment());
        }
        //查询订单
        OrderEntity orderEntity = orderManager.detail(orderCode);
        //判断回款是否完成
        if (orderEntity.getCommission().compareTo(price) == 1 && price.compareTo(BigDecimal.ZERO) == 1) {
            orderEntity.setOrderStatus(OrderStatus.BackPaymenting);
            num = orderManager.udpateOrderStatus(orderEntity);
        } else if (orderEntity.getCommission().compareTo(price) == 0) {
            orderEntity.setOrderStatus(OrderStatus.BackPayment);
            num = orderManager.udpateOrderStatus(orderEntity);
        } else if (price.compareTo(BigDecimal.ZERO) == 0) {
            orderEntity.setOrderStatus(OrderStatus.HasDeal);
            num = orderManager.udpateOrderStatus(orderEntity);
        } else if (orderEntity.getCommission().compareTo(price) == -1) {
//            response.setMessage("" + orderCode + "订单回款金额超过应回款金额，请核实");
            throw new BusinessException("" + orderCode + "订单回款金额超过应回款金额，请核实");

        }
        return num;
    }

    @Override
    public OrderPaybackEntity selectPayBackSum(Map<String, Object> param) {
        OrderPaybackEntity  orderPaybackEntity = oderPaybackDbDao.selectPayBackPayment(param);
        if (null!=orderPaybackEntity){
            return orderPaybackEntity;
        }
        OrderPaybackEntity orderPaybackEntity1=new OrderPaybackEntity();
        orderPaybackEntity1.setPayment(BigDecimal.ZERO);
        return orderPaybackEntity1;
    }

    public int createCommissionWage(OrderPaybackEntity orderPaybackEntity,List<String> deptCodes){

        OrderEntity orderEntityDetail=orderDbDao.detail(orderPaybackEntity.getOrderCode());

        //仅计算招商选址部门内业务部门订单提成
        if (deptCodes.contains(orderEntityDetail.getCreateDeptCode())){
            BigDecimal commissionWage = new BigDecimal(0);
            //判断回款金额中提成金额
            Map<String,Object> map=new HashMap<>();
            map.put("orderCode",orderPaybackEntity.getOrderCode());
            List<OrderPaybackEntity> pay = this.selectByMap(map);
            BigDecimal price = new BigDecimal(0);
            //统计回款总金额
            for (int i = 0; i < pay.size(); i++) {
                price = price.add(pay.get(i).getPayment());
            }
            //返佣总计
            BigDecimal backCommission =orderEntityDetail.getCommission().subtract(orderEntityDetail.getActualCommision());
            if (price.add(orderPaybackEntity.getPayment()).compareTo(backCommission)==1){
                //有提成
                if (price.compareTo(backCommission)!=-1){
                    commissionWage=orderPaybackEntity.getPayment();
                }else {
                    commissionWage=orderPaybackEntity.getPayment().add(price).subtract(backCommission);
                }
                //生成提成明细
                getCommissionWage(orderPaybackEntity.getPayBackOrderCode(),orderPaybackEntity.getOrderCode(),orderPaybackEntity.getBackTime(),commissionWage,orderPaybackEntity.getPayment());
            }else {
                getCommissionWage(orderPaybackEntity.getPayBackOrderCode(),orderPaybackEntity.getOrderCode(),orderPaybackEntity.getBackTime(),BigDecimal.ZERO,orderPaybackEntity.getPayment());
            }

        }
        return 0;
    }

    //根据回款生成提成
    public List<CommissionWageEntity> getCommissionWage(String payBackCode, String orderCode, Date payBackTime, BigDecimal paybackPrice,BigDecimal payBackAmount){
        Map<String,Object> param=new HashMap<>();
        param.put("orderCode",orderCode);
        param.put("isDeleted",false);
        OrderEntity orderEntity=orderManager.detail(orderCode);
//        int serviceNum=0;
//        if (null!=orderEntity.getCusServicePercentageEntityList()&&orderEntity.getCusServicePercentageEntityList().size()>0){
//            serviceNum=orderEntity.getCusServicePercentageEntityList().size();
//        }
        BigDecimal serviceRatio = BigDecimal.ZERO;  //服务分成比例
        BigDecimal commissionWage = BigDecimal.ZERO; //业务分成金额
        if (null!=orderEntity.getCusServicePercentageEntityList()&&orderEntity.getCusServicePercentageEntityList().size()>0){
            List<OrderPercentageEntity>  opl = orderEntity.getCusServicePercentageEntityList();
            for (OrderPercentageEntity ope : opl) {
                serviceRatio = ope.getPercentage().add(serviceRatio);
            }

        }

        commissionWage = paybackPrice.multiply(BigDecimal.valueOf(1).subtract(serviceRatio.divide(BigDecimal.valueOf(100))));

        List<CommissionRatioEntity> commissionRatioEntities=commissionRatioDbDao.selectByMap(param);
        List<CommissionWageEntity> commissionWageEntities=new ArrayList<>();
        Date date=new Date();
        for (CommissionRatioEntity commissionRatioEntity : commissionRatioEntities) {
            List<CommissionWageEntity> commissionWageEntityList =createWageEntity(commissionRatioEntity,commissionWage);
            for (CommissionWageEntity commissionWageEntity:commissionWageEntityList){
                commissionWageEntity.setCommissionWageCode(CodeGenUtils.generate("CW"));
                commissionWageEntity.setPayBackAchievements(paybackPrice);
                commissionWageEntity.setPayBackAmount(payBackAmount);
                commissionWageEntity.setOrderCode(commissionRatioEntity.getOrderCode());
                commissionWageEntity.setCommissionRatioCode(commissionRatioEntity.getCommissionRatioCode());
                commissionWageEntity.setPeOrderCode(commissionRatioEntity.getPeOrderCode());
                commissionWageEntity.setPercentage(commissionRatioEntity.getPercentage());
                commissionWageEntity.setPayBackOrderCode(payBackCode);
                commissionWageEntity.setPayBackTime(payBackTime);
                commissionWageEntity.setCreateTime(date);
                commissionWageEntity.setWageType("1");//回款
                commissionWageEntity.setCreateCode(CurrentContext.getUserCode());
                commissionWageEntity.setHasChanged(commissionRatioEntity.getHasChanged());
            }
            commissionWageEntities.addAll(commissionWageEntityList);
        }
        commissionWageDbDao.batchInsert(commissionWageEntities);
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
        commissionWageEntity.setCommissionWage(wage);
        commissionWageEntity.setCommissionRatio(commissionRatioEntity.getCommissionRatio());
        commissionWageEntities.add(commissionWageEntity);
        //组
        if (StringUtils.isNotEmpty(commissionRatioEntity.getGroupUserCode())){
            CommissionWageEntity group=new CommissionWageEntity();
            group.setUserCode(commissionRatioEntity.getGroupUserCode());
            BigDecimal groupWage=commissionWage.multiply((commissionRatioEntity.getPercentage()).divide(BigDecimal.valueOf(100)))
                    .multiply(commissionRatioEntity.getGroupPercentage().divide(BigDecimal.valueOf(100)));
            group.setCommissionWage(groupWage);
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
            leader.setCommissionWage(leaderWage);
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
            manager.setCommissionWage(managerWage);
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
            recommend.setCommissionWage(recommendWage);
            recommend.setCommissionType(CommissionType.RECOMMEND);
            recommend.setCommissionRatio(commissionRatioEntity.getRecommendPercentage());
            commissionWageEntities.add(recommend);
        }

        return commissionWageEntities;
    }

}
