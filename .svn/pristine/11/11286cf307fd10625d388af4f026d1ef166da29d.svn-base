package com.kfwy.hkp.cooperate.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.common.enums.ApplyType;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.ListUtils;
import com.kfwy.hkp.cooperate.business.ICooperateManager;
import com.kfwy.hkp.cooperate.dao.ApplyEvaluateDao;
import com.kfwy.hkp.cooperate.dao.ICooperateDbDao;
import com.kfwy.hkp.cooperate.entity.CooperateEntity;
import com.kfwy.hkp.cooperate.entity.EvaluateEntity;
import com.kfwy.hkp.cooperate.enums.CooStatus;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerFollowupDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerFollowupEntity;
import com.kfwy.hkp.hos.house.business.IHouseManager;
import com.kfwy.hkp.hos.house.dao.IHouseDbDao;
import com.kfwy.hkp.hos.house.dao.IHouseFollowupDbDao;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hos.house.entity.HouseFollowupEntity;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zjp on 2018/8/15.
 */
@Service
public class CooperateManagerImpl extends AbstractManager<CooperateEntity> implements ICooperateManager {

    @Autowired
    private ICooperateDbDao cooperateDbDao;
    @Autowired
    private ICustomerDbDao customerDbDao;
    @Autowired
    private IHouseDbDao houseDbDao;
    @Autowired
    private IHouseManager houseManager;
    @Autowired
    private IUserManager userManager;
    @Autowired
    private ApplyEvaluateDao applyEvaluateDao;
    @Autowired
    private ICustomerFollowupDbDao customerFollowupDbDao;
    @Autowired
    private IHouseFollowupDbDao houseFollowupDbDao;

    @Override
    protected IMyBatisBaseDao<CooperateEntity, Long> getMyBatisDao() {
        return this.cooperateDbDao;
    }

    @Override
    public int create(CooperateEntity cooperateEntity) {

        List<CooperateEntity> coos = new ArrayList<>();
        coos.add(cooperateEntity);

        return batchCreate(coos);
    }

    @Override
    @Transactional
    public int batchCreate(List<CooperateEntity> list) {

        CustomerEntity customerEntity = null;
        HouseEntity houseEntity = null;

//        List<CooperateNoticeDto> notices = new ArrayList<>();
        List<CooperateEntity> result = new ArrayList<>();
        StringBuilder errorMsg = new StringBuilder();

        int rs = 0 ;
        if (ListUtils.isNotEmpty(list)) {
            for (CooperateEntity coo : list) {

                if (StringUtils.isEmpty(coo.getCusCode()) ||
                        StringUtils.isEmpty(coo.getHouseCode())) {
                    errorMsg.append("申请合作，客户编码，房源编码必填");
                }

                //接下来的两个判断是为了避免一个客户对多个房源合作或者一个房源对多个客户合作的情况下多次查询一的一方
                houseEntity = findCooHouse(coo,houseEntity);
                customerEntity = findCooCustomer(coo,customerEntity);

                if (houseEntity == null || customerEntity == null) {
                    errorMsg.append("找不到指定合作的房源和客户");
                    continue;
                }
                if (isMyHouseAndCustomer(customerEntity, houseEntity)) {
                    errorMsg.append("自己的客户和自己的房源不能合作!");
                    continue;
                }


                //确定可以合作之后就填充实体信息
//                fillCooperateEntity(coo,customerEntity,houseEntity);

                //判断当前合作是否已经发生过
                if(hadCooperate(coo)){
                    errorMsg.append("已经发起过相应合作!");
                    continue;
                }

                //添加待批量新增
                result.add(coo);

            }

            if (result.size() < 1){
                throw new RemoveStackBusinessException (errorMsg.toString());
            }
            rs = cooperateDbDao.batchInsert(result);
        }
        return rs;
    }

    private CustomerEntity findCooCustomer(CooperateEntity coo,CustomerEntity customerEntity) {

        CustomerEntity tmp = customerEntity;
        if (tmp == null || !tmp.getCusCode().equals(coo.getCusCode())) {

            String cusSql = "select id,cus_code,emp_code from t_hkp_crm_customer " +
                    "where cus_code=\'" + coo.getCusCode() + "\'";

            List<CustomerEntity> customers = customerDbDao.selectByNativeSql(cusSql);

            if (ListUtils.isNotEmpty(customers)) {
                tmp = customers.get(0);
            }

        }
        return tmp;
    }

    private HouseEntity findCooHouse(CooperateEntity coo,HouseEntity houseEntity) {

        HouseEntity tmp = houseEntity;

        if (tmp == null || !houseEntity.getHouseCode().equals(coo.getHouseCode())) {

            String houseSql = "select id,house_code,emp_code from t_hkp_hos_house " +
                    "where house_code=\'" + coo.getHouseCode() + "\'";

            List<HouseEntity> houses = houseDbDao.selectByNativeSql(houseSql);
            if (houses == null || houses.size() < 1) {
                return null;
            }
            tmp = houses.get(0);
        }
        return tmp;
    }

    /**
     * 填充基本的合作信息
     *
     * @param coo
     * @param customer
     * @param house
     */
//    private void fillCooperateEntity(CooperateEntity coo, CustomerEntity customer, HouseEntity house) {
//
//        OperateFillUtils.fill(coo);
//
//        coo.setApplyCode(CurrentContext.getUserCode());
//        coo.setCooCode(CodeGenUtils.generate());
//        coo.setCooStatus(CooStatus.WAIT);
//
//        if (CurrentContext.getUserCode().equals(customer.getEmpCode())){
//            coo.setApplyType(ApplyType.CUSTOMER);
//            coo.setReceiveCode(house.getEmpCode());
//        }else if(CurrentContext.getUserCode().equals(house.getEmpCode())){
//            coo.setApplyType(ApplyType.HOUSE);
//            coo.setReceiveCode(customer.getEmpCode());
//        }
//    }

    /**
     * 判断是否是我的客户和我的房源进行了合作
     *
     * @param customerEntity
     * @param houseEntity
     * @return
     */
    private boolean isMyHouseAndCustomer(CustomerEntity customerEntity, HouseEntity houseEntity) {
        return customerEntity.getEmpCode().equals(houseEntity.getEmpCode());

    }

    /**
     * 判断是否已经有过相同的合作
     *
     * @param coo
     * @return
     */
    private boolean hadCooperate(CooperateEntity coo) {

        String preSql = "select count(*) from t_hkp_union_cooperate " +
                "where ( apply_code=\'%s\' or receive_code=\'%s\' )" +
                "and cus_code=\'%s\' and house_code=\'%s\'";

        String sql = String.format(preSql,CurrentContext.getUserCode(), CurrentContext.getUserCode(), coo.getCusCode(), coo.getHouseCode());

        int count = cooperateDbDao.countByNativeSql(sql);
        //如果大于零表示合作过

        return count > 0;
    }

    /**
     * 判断是否已经有过相同的合作
     *
     * @param coo
     * @return
     */
    private boolean hadCooperateCus(CooperateEntity coo) {

        String preSql = "select count(*) from t_hkp_union_cooperate " +
                "where ( apply_code=\'%s\' or receive_code=\'%s\' )" +
                "and cus_code=\'%s\'  and coo_status !=\'2\'";

        String sql = String.format(preSql,CurrentContext.getUserCode(), CurrentContext.getUserCode(), coo.getCusCode());

        int count = cooperateDbDao.countByNativeSql(sql);
        //如果大于零表示合作过

        return count > 0;
    }

    /**
     * 判断是否已经有过相同的合作
     *
     * @param coo
     * @return
     */
    private boolean hadCooperateHos(CooperateEntity coo) {

        String preSql = "select count(*) from t_hkp_union_cooperate " +
                "where ( apply_code=\'%s\' or receive_code=\'%s\' )" +
                "and house_code=\'%s\' and coo_status !=\'2\' ";

        String sql = String.format(preSql,CurrentContext.getUserCode(), CurrentContext.getUserCode(), coo.getHouseCode());

        int count = cooperateDbDao.countByNativeSql(sql);
        //如果大于零表示合作过

        return count > 0;
    }


    @Override
    public CooperateEntity detail(String cooCode) {
        CooperateEntity cooperateEntity = cooperateDbDao.detail(cooCode);


        UserEntity applyEntity = userManager.findUserByUserCode(cooperateEntity.getApplyCode());

        UserEntity receiveEntity = userManager.findUserByUserCode(cooperateEntity.getReceiveCode());
        Map<String, Object> map = new HashMap<>();
        map.put("houseCode",cooperateEntity.getHouseCode());
        if(cooperateEntity.getApplyType().equals(ApplyType.HOPEHOUSE)){
            HouseEntity houseEntity = houseManager.findOne(map);
            if(houseEntity != null){
//                cooperateEntity.setHouseEntity(houseEntity);
                cooperateEntity.setHouseName(houseEntity.getHouseName());
                cooperateEntity.setPrice(houseEntity.getPrice());
                cooperateEntity.setUnit(houseEntity.getUnit());
                cooperateEntity.setArea(houseEntity.getArea());
                cooperateEntity.setMaxElectric(houseEntity.getMaxElectric());
                cooperateEntity.setLayerHeight(houseEntity.getLayerHeight());
                cooperateEntity.setHasCut(houseEntity.getHasCut());
                cooperateEntity.setHasCertificate(houseEntity.getHasCertificate());
                cooperateEntity.setHasEia(houseEntity.getHasEia());
                cooperateEntity.setHouseownerName(houseEntity.getHouseowner().getHouseownerName());
                cooperateEntity.setHouseownerPhone(houseEntity.getHouseowner().getHouseownerPhone());
                cooperateEntity.setProvinceName(houseEntity.getProvinceName());
                cooperateEntity.setCityName(houseEntity.getCityName());
                cooperateEntity.setRegionName(houseEntity.getRegionName());
                cooperateEntity.setDetailAddress(houseEntity.getHouseLocation().getDetailAddress());
                cooperateEntity.setStreetName(houseEntity.getStreetName());
            }
        }

        if(cooperateEntity.getApplyType().equals(ApplyType.HOPECUSTOMER)){
            CustomerEntity customerEntity = customerDbDao.detailCut(cooperateEntity.getCusCode());
            if(customerEntity != null){
//                cooperateEntity.setCustomerEntity(customerEntity);
                cooperateEntity.setCusName(customerEntity.getCusName());
                cooperateEntity.setEnterTime(customerEntity.getEnterTime());
                cooperateEntity.setIndustry(customerEntity.getIndustry());
                cooperateEntity.setNeedAcreage(customerEntity.getNeedAcreage());
                cooperateEntity.setHouseType(customerEntity.getHouseType());
                cooperateEntity.setHouseTypeName(customerEntity.getHouseTypeName());
                cooperateEntity.setDescription(customerEntity.getDescription());
                cooperateEntity.setCustomerAreaEntityList(customerEntity.getCustomerAreaEntityList());
                cooperateEntity.setProducts(customerEntity.getProducts());
            }
        }


        if (cooperateEntity != null) {
            String applyCode = cooperateEntity.getApplyCode();
            String receiveCode = cooperateEntity.getReceiveCode();
            String userCode = CurrentContext.getUserCode();
            if(userCode.equals(applyCode)){
                cooperateEntity.setCooUser(receiveEntity);
            }
            if(userCode.equals(receiveCode)){
                cooperateEntity.setCooUser(applyEntity);
            }
        }
        return cooperateEntity;
    }

    @Override
    public List<CooperateEntity> querySimpleCooperateListByMap(Map param) {
        return cooperateDbDao.querySimpleCooperateListByMap(param);
    }

    @Override
    public int createcus(CooperateEntity entity) {
        List<CooperateEntity> coos = new ArrayList<>();
        coos.add(entity);

        return batchCreateCus(coos);
    }



    @Transactional
    public int batchCreateCus(List<CooperateEntity> list) {

        CustomerEntity customerEntity = null;

//        List<CooperateNoticeDto> notices = new ArrayList<>();
        List<CooperateEntity> result = new ArrayList<>();
        StringBuilder errorMsg = new StringBuilder();

        int rs = 0 ;
        if (ListUtils.isNotEmpty(list)) {
            for (CooperateEntity coo : list) {

                if (StringUtils.isEmpty(coo.getCusCode()) ) {
                    errorMsg.append("客户申请合作，客户编码必填");
                }

                //接下来的两个判断是为了避免一个客户对多个房源合作多次查询的方法
                customerEntity = findCooCustomer(coo,customerEntity);

                if ( customerEntity == null) {
                    errorMsg.append("找不到指定合作的客户");
                    continue;
                }
                boolean isMyCustomer = false;
                isMyCustomer = customerEntity.getEmpCode().equals(CurrentContext.getUserCode());
                if (isMyCustomer) {
                    errorMsg.append("这是您的客户不需要合作!");
                    continue;
                }


                //确定可以合作之后就填充实体信息 TODO  fill CooperateEntity
                OperateFillUtils.fill(coo);
                coo.setApplyCode(CurrentContext.getUserCode());
                coo.setCooCode(CodeGenUtils.generate());
                coo.setCooStatus(CooStatus.WAIT);
                coo.setApplyType(ApplyType.HOPECUSTOMER);
                coo.setReceiveCode(customerEntity.getEmpCode());

                //判断当前合作是否已经发生过
                if(hadCooperateCus(coo)){
                    errorMsg.append("已经发起过相应合作!");
                    continue;
                }

                //客户合作不需要房源
                coo.setHouseCode(null);
                //添加待批量新增
                result.add(coo);

                //添加待消息通知
//                notices.add(createCooNotice(coo));
            }

            if (result.size() < 1){
                throw new RemoveStackBusinessException(errorMsg.toString());
            }
            rs = cooperateDbDao.batchInsert(result);
//            for (CooperateNoticeDto dto : notices) {
//                CooperateNoticePool.create().push(dto);
//            }
        }
        return rs;
    }

    @Override
    public int createhos(CooperateEntity entity) {
        List<CooperateEntity> coos = new ArrayList<>();
        coos.add(entity);

        return batchCreateHos(coos);
    }



    private int batchCreateHos(List<CooperateEntity> list) {

        HouseEntity houseEntity = null;

//        List<CooperateNoticeDto> notices = new ArrayList<>();
        List<CooperateEntity> result = new ArrayList<>();
        StringBuilder errorMsg = new StringBuilder();

        int rs = 0 ;
        if (ListUtils.isNotEmpty(list)) {
            for (CooperateEntity coo : list) {

                if (StringUtils.isEmpty(coo.getHouseCode()) ) {
                    errorMsg.append("房源申请合作，房源编码必填");
                }

                //接下来的两个判断是为了避免一个客户对多个房源合作多次查询的方法
                houseEntity = findCooHouse(coo,houseEntity);

                if ( houseEntity == null) {
                    errorMsg.append("找不到指定合作的房源");
                    continue;
                }
                boolean isMyHos = false;
                isMyHos = houseEntity.getEmpCode().equals(CurrentContext.getUserCode());
                if (isMyHos) {
                    errorMsg.append("这是您的房源不需要合作!");
                    continue;
                }


                //确定可以合作之后就填充实体信息
                OperateFillUtils.fill(coo);
                coo.setApplyCode(CurrentContext.getUserCode());
                coo.setCooCode(CodeGenUtils.generate());
                coo.setCooStatus(CooStatus.WAIT);
                coo.setApplyType(ApplyType.HOPEHOUSE);
                coo.setReceiveCode(houseEntity.getEmpCode());

                //判断当前合作是否已经发生过
                if(hadCooperateHos(coo)){
                    errorMsg.append("已经发起过相应合作!");
                    continue;
                }

                //房源合作不需要客户
                coo.setCusCode(null);
                //添加待批量新增
                result.add(coo);

                //添加待消息通知
//                notices.add(createCooNotice(coo));
            }

            if (result.size() < 1){
                throw new RemoveStackBusinessException(errorMsg.toString());
            }
            rs = cooperateDbDao.batchInsert(result);
//            for (CooperateNoticeDto dto : notices) {
//                CooperateNoticePool.create().push(dto);
//            }
        }
        return rs;
    }

    @Override
    public PageResult<CooperateEntity> findByMapCoo(Map param, Integer start, Integer pageSize, String create_time, boolean b,String applyOrRecei) {

        PageResult<CooperateEntity> basicList =  this.getMyBatisDao().selectByMap(param, start, pageSize, create_time, b);
        List<CooperateEntity> dataList = basicList.getData();
        if(ListUtils.isNotEmpty(dataList)){
            for(CooperateEntity cooperateEntity:dataList){
                UserEntity apply = userManager.findUserByUserCode(cooperateEntity.getApplyCode());

                UserEntity receive = userManager.findUserByUserCode(cooperateEntity.getReceiveCode());

                if(applyOrRecei.equals("applyInit")){
                    cooperateEntity.setCooUser(receive);
                }
                if(applyOrRecei.equals("receiveInit")){
                    cooperateEntity.setCooUser(apply);
                }

                Map<String, Object> map = new HashMap<>();
                map.put("houseCode",cooperateEntity.getHouseCode());
                if(cooperateEntity.getApplyType().equals(ApplyType.HOPEHOUSE)){

                    HouseEntity houseEntity = houseManager.findOne(map);

                    if(houseEntity != null){
                        cooperateEntity.setArea(houseEntity.getArea());
                        cooperateEntity.setStreetName(houseEntity.getStreetName());
                    }
                }

                if(cooperateEntity.getApplyType().equals(ApplyType.HOPECUSTOMER)){
                    CustomerEntity customerEntity = customerDbDao.detailCut(cooperateEntity.getCusCode());
                    if(customerEntity != null){
                        cooperateEntity.setCusName(customerEntity.getCusName());
                        cooperateEntity.setIndustry(customerEntity.getIndustry());
                        cooperateEntity.setNeedAcreage(customerEntity.getNeedAcreage());
                        cooperateEntity.setProducts(customerEntity.getProducts());

                    }
                }




            }
            basicList.setData(dataList);
        }
        return basicList;
    }

    @Override
    public int evaluvteInsert(EvaluateEntity evaluateEntity) {
        if(StringUtils.isEmpty(evaluateEntity.getContent())){
            throw new RemoveStackBusinessException("评价内容为空");
        }
        if(StringUtils.isEmpty(evaluateEntity.getToUserCode())){
            throw new RemoveStackBusinessException("被评价者为空");
        }
        OperateFillUtils.fill(evaluateEntity);
        evaluateEntity.setIsDeleted(Boolean.FALSE);
        evaluateEntity.setFromUserCode(CurrentContext.getUserInfo().getUserCode());
        return applyEvaluateDao.evaluvteInsert(evaluateEntity);
    }

    @Override
    public PageResult<EvaluateEntity> evaluvteSelect(String userCode,Integer start,Integer pageSize) {
        if(StringUtils.isEmpty(userCode)){
            throw new RemoveStackBusinessException("用户为空");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("userCode",userCode);
        return this.applyEvaluateDao.selectByMap(map,start,pageSize,"create_time",false);
    }
    @Override
    public Boolean endCoo(CooperateEntity cooperateEntity, String userCode) {

        if (userCode.equals(cooperateEntity.getApplyCode())) {
            //申请人判断是否公开号码
            if (StringUtils.isNotEmpty(cooperateEntity.getCusCode()) && StringUtils.isEmpty(cooperateEntity.getHouseCode())) {
                //客户
                if (!hasCusCooperate(cooperateEntity.getCusCode(), userCode)) {
                    throw new RemoveStackBusinessException("对方向您公开了客户联系方式,您无法结束合作。");
                }

            } else if (StringUtils.isNotEmpty(cooperateEntity.getHouseCode()) && StringUtils.isEmpty(cooperateEntity.getCusCode())) {
                //房源
                if (!hasHosCooperate(cooperateEntity.getHouseCode(), userCode)) {
                    throw new RemoveStackBusinessException("对方向您公开了业主联系方式,您无法结束合作。");
                }

            }
        } else if (userCode.equals(cooperateEntity.getReceiveCode())) {
            //接收人判断是否有带看记录
            Map<String, Object> param = new HashMap<>();
            param.put("userCode", cooperateEntity.getApplyCode());
            param.put("isDeleted", false);
            //判断发起方是否有带看
            if (StringUtils.isNotEmpty(cooperateEntity.getCusCode()) && StringUtils.isEmpty(cooperateEntity.getHouseCode())) {
                //客户
                param.put("cusCode",cooperateEntity.getCusCode());
                List<CustomerFollowupEntity> customerFollowupEntities = customerFollowupDbDao.selectByMap(param);
                if (null != customerFollowupEntities && customerFollowupEntities.size() > 0) {
                    throw new RemoveStackBusinessException("申请人带看过，合作接收人无法结束合作。");
                }

            } else if (StringUtils.isNotEmpty(cooperateEntity.getHouseCode()) && StringUtils.isEmpty(cooperateEntity.getCusCode())) {
                //房源
                param.put("houseCode",cooperateEntity.getHouseCode());
                List<HouseFollowupEntity> houseFollowupEntities = houseFollowupDbDao.selectByMap(param);
                if (null != houseFollowupEntities && houseFollowupEntities.size() >0) {
                    throw new RemoveStackBusinessException("申请人带看过该房源，合作接收人无法结束合作。");
                }

            }
        }else {
            return false;
        }
        return true;
    }

    //判断合作房源是否显示业主号码
    public Boolean hasHosCooperate(String houseCode, String userCode) {
        String preSql = "select * from t_hkp_union_cooperate " +
                "where house_code=\'%s\' " +
                "and coo_status in ('3','4','5') " +
                "and apply_code=\'%s\' " +
                "order by create_time desc limit 1";
        String sql = String.format(preSql, houseCode, userCode);

        CooperateEntity cooperateEntity = cooperateDbDao.selectOneByNativeSql(sql);

        if (null != cooperateEntity && !cooperateEntity.getCooOpenFlag()) {
            return true;
        }
        return false;
    }

    //判断合作房源是否显示业主号码
    public Boolean hasCusCooperate(String cusCode, String userCode) {
        String preSql = "select * from t_hkp_union_cooperate " +
                "where cus_code=\'%s\' " +
                "and coo_status in ('3','4','5') " +
                "and apply_code=\'%s\' " +
                "order by create_time desc limit 1";
        String sql = String.format(preSql, cusCode, userCode, userCode);

        CooperateEntity cooperateEntity = cooperateDbDao.selectOneByNativeSql(sql);

        if (null != cooperateEntity && !cooperateEntity.getCooOpenFlag()) {
            return true;
        }
        return false;
    }

}
