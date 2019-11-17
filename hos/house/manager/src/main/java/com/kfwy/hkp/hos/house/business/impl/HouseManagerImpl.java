package com.kfwy.hkp.hos.house.business.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.exception.RestBusinessException;
import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.gniuu.framework.user.entity.BaseUserEntity;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.ListUtils;
import com.kfwy.hkp.common.utils.PhoneUtils;
import com.kfwy.hkp.cooperate.dao.ICooperateDbDao;
import com.kfwy.hkp.cooperate.entity.CooperateEntity;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.houseowner.business.IHouseownerContactsManager;
import com.kfwy.hkp.crm.houseowner.business.IHouseownerManager;
import com.kfwy.hkp.crm.houseowner.dao.IHouseownerDbDao;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerContactsEntity;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerEntity;
import com.kfwy.hkp.hos.house.api.BespeakApi.BespeakApiClient;
import com.kfwy.hkp.hos.house.api.EntrustApi.EntrustApiClient;
import com.kfwy.hkp.hos.house.api.RecommendApi.RecommendApiClient;
import com.kfwy.hkp.hos.house.business.*;
import com.kfwy.hkp.hos.house.dao.IHouseApplyDbDao;
import com.kfwy.hkp.hos.house.dao.IHouseDbDao;
import com.kfwy.hkp.hos.house.dao.IHouseExportLogDbDao;
import com.kfwy.hkp.hos.house.dao.ISharePoolDeptDbDao;
import com.kfwy.hkp.hos.house.entity.*;
import com.kfwy.hkp.hos.house.enums.*;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.file.business.IFileManager;
import com.kfwy.hkp.sys.file.business.IFileRelationManager;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;
import com.kfwy.hkp.sys.remind.business.IRemindDownManager;
import com.kfwy.hkp.sys.remind.entity.RemindDownEntity;
import com.kfwy.hkp.sys.remind.enums.DownType;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserLevel;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Create By hjh on 2018/7/24
 */
@Service
public class HouseManagerImpl extends AbstractManager<HouseEntity> implements IHouseManager {

    private static Logger logger = LoggerFactory.getLogger(HouseManagerImpl.class);

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private IHouseDbDao houseDbDao;

    @Autowired
    private IFileRelationManager fileRelationManager;

    @Autowired
    private IHouseownerDbDao houseownerDbDao;

    @Autowired
    private IHouseownerContactsManager houseownerContactsManager;

    @Autowired
    private IHouseownerManager houseownerManager;

    @Autowired
    private IHouseLocationManager houseLocationManager;

    @Autowired
    private IHouseFavoriteManager houseFavoriteManager;

    @Autowired
    private IHouseFollowupManager houseFollowupManager;

    @Autowired
    private IUserManager userManager;

    @Autowired
    private IFileManager fileManager;

    @Autowired
    private IHouseApplyDbDao houseApplyDbDao;

    @Autowired
    private ICooperateDbDao cooperateDbDao;

    @Autowired
    private ISharePoolDeptDbDao sharePoolDeptDbDao;

    @Autowired
    private ICustomerDbDao customerDbDao;

//    @Autowired
//    private ICustomerManager customerManager;

    @Autowired
    private IDeptManager deptManager;

    @Autowired
    private IRemindDownManager remindDownManager;

    @Autowired
    private IHouseExportLogDbDao houseExportLogDbDao;

    @Resource(name = "k0HouseCheckManager")
    private IHouseCheckManager k0HouseCheckManager;

    @Resource(name = "k1HouseCheckManager")
    private IHouseCheckManager k1HouseCheckManager;

    @Resource(name = "k2HouseCheckManager")
    private IHouseCheckManager k2HouseCheckManager;

    @Resource(name = "t0HouseCheckManager")
    private IHouseCheckManager t0HouseCheckManager;

    @Override
    protected IMyBatisBaseDao<HouseEntity, Long> getMyBatisDao() {
        return this.houseDbDao;
    }

    @Override
    public PageResult<HouseEntity> findByMap(Map<String, Object> param, int start, int pageSize) {
        param.put("isDeleted",Boolean.FALSE);
        String orderBy = "create_time";
        return houseDbDao.selectByMap(param,start,pageSize,orderBy,Boolean.FALSE);
    }

    @Override
    public HouseEntity findOne(Map<String,Object> param){

        // 查询房源
        HouseEntity houseEntity = houseDbDao.selectUniqueByMap(param);

        if(houseEntity != null){
            // 查询业主
            HouseownerEntity one = houseownerManager.findOne("houseownerCode",houseEntity.getOwnerCode());
            if(one != null){
                houseEntity.setHouseowner(one);
            }
            // 查询地址
            HouseLocationEntity locationEntity = houseLocationManager.findOne("locationCode", houseEntity.getLocationCode());
            if(locationEntity != null){
                houseEntity.setHouseLocation(locationEntity);
            }
            param.clear();
            //todo 房源所有人的键跟着房源编码的值
            param.put("ownerCode",houseEntity.getHouseCode());
            List<FileRelationEntity> fileLists = fileRelationManager.findByMap(param);
            if(CollectionUtil.isNotEmpty(fileLists)){
                // 删除房源委托协议
                Iterator<FileRelationEntity> it = fileLists.iterator();
                while (it.hasNext()){
                    FileRelationEntity fileRelationEntity = it.next();
                    String use = fileRelationEntity.getFileUse();
                    if(use != null && use.equals(HouseFileUse.Protocol)){
                        it.remove();
                    }
                }
                houseEntity.setFileList(fileLists);
            }
            return houseEntity;
        }
        return null;

    }

//    private void seeHouseOwnerOrAddressCheck(HouseEntity houseEntity,HouseownerEntity houseOwner,HouseLocationEntity houseLocation){
//        if (HouseType.PLATFORM.equals(houseEntity.getHouseType())){
//            seeHouseOwnerOrAddressApply(houseEntity,houseOwner,houseLocation);
//        }else{
//            UserEntity user=userManager.findUserByUserCode(CurrentContext.getUserCode());
//            UserEntity houseUser = userManager.findUserByUserCode(houseEntity.getEmpCode());
//            if (user.getUserCode().equals(houseEntity.getEmpCode())){
//                houseEntity.setHouseowner(houseOwner);
//                houseEntity.setHouseLocation(houseLocation);
//            }else if (StringUtils.isNotEmpty(user.getOwnerDeptCode()) &&
//                    user.getOwnerDeptCode().equals(houseUser.getOwnerDeptCode())){
//                //如果是部门内部房源，则可以查看业主及电话信息
//
//                houseEntity.setHouseowner(houseOwner);
//                houseEntity.setHouseLocation(houseLocation);
//            }else{
//                seeHouseOwnerOrAddressCooperate(houseEntity,houseOwner,houseLocation);
//            }
//            //如果业主信息和房源地址信息依然为空，且当前查看人的部门编码不为空，则进入共享池判断
//            if (ObjectUtils.isEmpty(houseEntity.getHouseowner()) && ObjectUtils.isEmpty(houseEntity.getHouseLocation()) && !StringUtils.isEmpty(user.getOwnerDeptCode())){
//                seeHouseOwnerOrAddressSharePool(houseEntity,houseOwner,houseLocation,user,houseUser);
//            }
//
//        }
//        //如果不是部门房源，并且是平台房源就看有没有申请，如果有申请则可以看
//    }
//    /**
//     * 通过共享池判断是否能够查看业主信息和房源地址
//     */
//    private void seeHouseOwnerOrAddressSharePool(HouseEntity houseEntity,HouseownerEntity houseOwner,HouseLocationEntity houseLocation,UserEntity user,UserEntity houseUser){
//        if (hasSharePool(user.getOwnerDeptCode(),houseUser.getOwnerDeptCode())){
//            houseEntity.setHouseowner(houseOwner);
//            houseEntity.setHouseLocation(houseLocation);
//        }
//    }

    private Boolean hasSharePool(String deptCode,String houseUserDeptCode){
        String preSql = "SELECT count(*) FROM t_hkp_share_pool_dept" +
                " WHERE share_dept_code=\'%s\'" +
                "AND share_type=\'hos\'"+
                "AND share_code IN (" +
                "SELECT share_code FROM t_hkp_share_pool_dept" +
                " WHERE share_dept_code = \'%s\'" +
                "AND share_type=\'hos\'"+
                "GROUP BY share_code)";
        String sql = String.format(preSql,deptCode,houseUserDeptCode);
        int count = sharePoolDeptDbDao.countByNativeSql(sql);
        if (count > 0){
            return true;
        }
        return false;
    }

//    /**
//     * 平台房源有申请记录，可以查看房源详情
//     * @param houseEntity
//     * @param houseOwner
//     * @param houseLocation
//     */
//    private void seeHouseOwnerOrAddressApply(HouseEntity houseEntity,HouseownerEntity houseOwner,HouseLocationEntity houseLocation){
//
//        if (hasApply(houseEntity.getHouseCode(),CurrentContext.getUserCode())){
//            houseEntity.setHouseowner(houseOwner);
//            houseEntity.setHouseLocation(houseLocation);
//        }
//    }
    //判断平台是否有申请
    public Boolean hasApply(String houseCode,String userCode){
        String preSql = "select count(*) from t_hkp_hos_apply where emp_code=\'%s\' and house_code=\'%s\'";
        String sql = String.format(preSql,userCode,houseCode);
        int count = houseApplyDbDao.countByNativeSql(sql);
        if (count > 0){
            return true;
        }
        return false;
    }

//    /**
//     * 经纪人有合作可以查看房源详情
//     * @param houseEntity
//     * @param houseOwner
//     * @param houseLocation
//     */
//    private void seeHouseOwnerOrAddressCooperate(HouseEntity houseEntity,HouseownerEntity houseOwner,HouseLocationEntity houseLocation){
//
////        String preSql = "select count(*) from t_hkp_union_cooperate " +
////                "where house_code=\'%s\' " +
////                "and coo_status in ('3','4','5','6') " +
////                "and (apply_code=\'%s\' or receive_code=\'%s\')";
////
////        String sql = String.format(preSql,houseEntity.getHouseCode(),CurrentContext.getUserCode(),CurrentContext.getUserCode());
////
////        int count = cooperateDbDao.countByNativeSql(sql);
//
//        if (hasCooperate(houseEntity.getHouseCode(),CurrentContext.getUserCode())){
//            houseEntity.setHouseowner(houseOwner);
//            houseEntity.setHouseLocation(houseLocation);
//        }
//
//    }
    //判断合作房源是否显示业主号码
    public Boolean hasCooperate(String houseCode,String userCode){
        String preSql = "select * from t_hkp_union_cooperate " +
                "where house_code=\'%s\' " +
                "and is_deleted=false " +
                "and coo_status in ('3','4','5') " +
                "and (apply_code=\'%s\' or receive_code=\'%s\')" +
                "order by create_time desc limit 1";
        String sql = String.format(preSql,houseCode,userCode,userCode);

        CooperateEntity cooperateEntity = cooperateDbDao.selectOneByNativeSql(sql);

        if (null!=cooperateEntity&&cooperateEntity.getCooOpenFlag()){
            return true;
        }
        return false;
    }

    /**
     * @param houseEntity
     */
    private void loadApplyInfo(HouseEntity houseEntity) {

        if (HouseType.PLATFORM.equals(houseEntity.getHouseType()) &&
                (houseEntity.getApplyEmpList()==null ||
                houseEntity.getApplyEmpList().size()<1)){
            Map<String, Object> param = new HashMap<>();
            param.put("houseCode", houseEntity.getHouseCode());
            PageResult<HouseApplyEntity> applies = houseApplyDbDao.selectByMap(param,
                    0, 10, "id", false);

            List<UserEntity> users = new ArrayList<>();
            if (applies.getTotalCount() > 0) {
                for (HouseApplyEntity ap : applies.getData()) {
                    UserEntity tmp = userManager.findUserByUserCode(ap.getEmpCode());
                    if (tmp != null) {
                        UserEntity user = new UserEntity();
                        user.setUserCode(tmp.getUserCode());
                        user.setUserName(tmp.getUserName());
                        user.setUserImg(tmp.getUserImg());
                        user.setUserPhone(tmp.getUserPhone());
                        users.add(user);
                    }
                }
            }
            houseEntity.setApplyUsers(users);
        }
    }

    private void loadHouseContacts(HouseEntity houseEntity){
        Map<String,Object> param = new HashMap<>();
        param.put("houseCode",houseEntity.getHouseCode());
        List<HouseownerContactsEntity> houseownerContactsEntities = houseownerContactsManager.findByMap(param);
        houseEntity.setHouseownerContacts(houseownerContactsEntities);
        param.clear();
    }

    private void loadHouseFavorite(HouseEntity houseEntity){
        Map<String,Object> param = new HashMap<>();
        param.put("empCode", CurrentContext.getUserCode());
        param.put("houseCode", houseEntity.getHouseCode());
        List<HouseFavoriteEntity> favoriteEntities = houseFavoriteManager.findByMap(param);
        if(ListUtils.isNotEmpty(favoriteEntities)){
            houseEntity.setHasCollect(Boolean.TRUE);
        }else{
            houseEntity.setHasCollect(Boolean.FALSE);
        }
    }

    private void loadHouseFollowupSum(HouseEntity houseEntity){
        Map<String,Object> param = new HashMap<>();
        param.put("houseCode", houseEntity.getHouseCode());
        int followupSum = houseDbDao.selectByFollowupSum(param);
        houseEntity.setFollowupSum(followupSum);
    }

    private void loadHouseFile(HouseEntity houseEntity){
        Map<String,Object> param = new HashMap<>();
        param.put("ownerCode",houseEntity.getHouseCode());
        List<FileRelationEntity> byMap = fileRelationManager.findByMap(param);

        List<FileRelationEntity> houseInner = new ArrayList<>();
        List<FileRelationEntity> houseYard = new ArrayList<>();
        List<FileRelationEntity> houseProtocol = new ArrayList<>();

        // 删除房源委托协议
        Iterator<FileRelationEntity> it = byMap.iterator();
        while (it.hasNext()){
            FileRelationEntity fileRelationEntity = it.next();
            String use = fileRelationEntity.getFileUse();
            if (StringUtils.isNotEmpty(use)){

                if (HouseFileUse.HouseInner.equals(use) ||HouseFileUse.HouseCover.equals(use) ){
                    houseInner.add(fileRelationEntity);
                }else if(HouseFileUse.HouseYard.equals(use)){
                    houseYard.add(fileRelationEntity);
                }else if (HouseFileUse.Protocol.equals(use)){
                    houseProtocol.add(fileRelationEntity);
                }
            }
        }

        houseEntity.setFileList(houseInner);
        houseEntity.setHouseYardImage(houseYard);
//        houseEntity.setHouseProtocol(houseProtocol);
    }

    @Override
    public HouseEntity detail(String houseCode) {

        // 查询房源
        HouseEntity house = houseDbDao.selectUniqueByProp("houseCode",houseCode);
        UserEntity cur=(UserEntity)CurrentContext.getUserInfo();
        // 查询业主
        Map<String,Object> param = new HashMap<>(2);
        param.put("houseownerCode",house.getOwnerCode());
        HouseownerEntity houseOwner = houseownerManager.details(param);
        param.clear();
        // 查询位置
        HouseLocationEntity locationEntity = houseLocationManager.findOne("locationCode", house.getLocationCode());
        //位置拼接栋(08月22不拼接栋)
        String whereBuilding = house.getWhereBuilding();
        String detailAddress = locationEntity.getDetailAddress();
//        detailAddress  += "--";
//        detailAddress = (new StringBuilder(String.valueOf(detailAddress))).append(whereBuilding).toString();
//        detailAddress = detailAddress+"栋";
        locationEntity.setDetailAddress(detailAddress);

        loadHouseFile(house);

        // 查询是否收藏
        loadHouseFavorite(house);

        // 查询带看次数
        loadHouseFollowupSum(house);

        //加载多个联系人
        loadHouseContacts(house);

        //设置申请信息
        loadApplyInfo(house);

        if (checkHosPermissions(HouseCheckType.CHECK_DETAILS,house,cur)){
            house.setHouseowner(houseOwner);
            house.setHouseLocation(locationEntity);
        }else{
            house.setHouseowner(null);
            house.setHouseLocation(null);
        }

        //设置查看人员

        //设置同部门的人有下架权限
        showDownFrameBtn(house);
        return house;
    }


    /**
     * v2.8.0版本不拼接栋
     * @param houseCode
     * @return
     */
    @Override
    public HouseEntity detailTwoVersion(String houseCode) {

        // 查询房源
        HouseEntity house = houseDbDao.selectUniqueByProp("houseCode",houseCode);
        UserEntity cur=(UserEntity)CurrentContext.getUserInfo();
        // 查询业主
        Map<String,Object> param = new HashMap<>(2);
        param.put("houseownerCode",house.getOwnerCode());
        HouseownerEntity houseOwner = houseownerManager.details(param);
        param.clear();
        // 查询位置
        HouseLocationEntity locationEntity = houseLocationManager.findOne("locationCode", house.getLocationCode());
        //位置拼接栋--不用

        loadHouseFile(house);

        // 查询是否收藏
        loadHouseFavorite(house);

        // 查询带看次数
        loadHouseFollowupSum(house);

        //加载多个联系人
        loadHouseContacts(house);

        //设置申请信息
        loadApplyInfo(house);

        if (checkHosPermissions(HouseCheckType.CHECK_DETAILS,house,cur)){
            house.setHouseowner(houseOwner);
            house.setHouseLocation(locationEntity);
        }else{
            house.setHouseowner(null);
            house.setHouseLocation(null);
        }

        //设置查看人员

        //设置同部门的人有下架权限
        showDownFrameBtn(house);
        return house;
    }

    public void showDownFrameBtn(HouseEntity houseEntity){
        //初始化为false,满足自己和同部门的人就为true
        houseEntity.setHasCanDownFrame(false);

        //判断同部门
        UserEntity user = (UserEntity) CurrentContext.getUserInfo();
        String userCode = houseEntity.getEmpCode();
        if (StringUtils.isNotEmpty(userCode)) {
            UserEntity emp = userManager.findUserByUserCode(userCode);
            if (emp != null && emp.getOwnerDeptCode() != null) {
                if (emp.getOwnerDeptCode().equals(user.getOwnerDeptCode())) {
                    houseEntity.setHasCanDownFrame(true);
                }
            }
        }

    }

    @Override
    public HouseEntity edit(Map<String, Object> param) {
        // 查询房源
        HouseEntity houseEntity = houseDbDao.selectUniqueByMap(param);
        param.clear();
        // 查询文件
        param.put("ownerCode",houseEntity.getHouseCode());
        List<FileRelationEntity> byMap = fileRelationManager.findByMap(param);
        param.clear();
        // 查询业主
        param.put("houseownerCode",houseEntity.getOwnerCode());
        HouseownerEntity one = houseownerManager.findOne(param);
        param.clear();
        // 查询多个联系人
        param.put("houseCode",houseEntity.getHouseCode());
        List<HouseownerContactsEntity> contactsManagerOne = houseownerContactsManager.findByMap(param);

        houseEntity.setFileList(byMap);
        houseEntity.setHouseowner(one);
        houseEntity.setHouseownerContacts(contactsManagerOne);
        return houseEntity;
    }


    /**
    * @Description 描述:查询编辑前的接口
    * @Auth xpp
    * @Date 2019/7/8 13:52
    * @Version 1.0
    * @Param [param]
    * @return com.kfwy.hkp.hos.house.entity.HouseEntity
    **/
    @Override
    public HouseEntity editTwoVersion(Map<String, Object> param) {
        // 查询房源
        HouseEntity houseEntity = houseDbDao.selectUniqueByMap(param);
        param.clear();
        // 查询文件
        param.put("ownerCode",houseEntity.getHouseCode());
        List<FileRelationEntity> byMap = fileRelationManager.findByMap(param);
        param.clear();
        // 查询业主
        param.put("houseownerCode",houseEntity.getOwnerCode());
        HouseownerEntity one = houseownerManager.findOne(param);
        param.clear();
        // 查询多个联系人
        param.put("houseCode",houseEntity.getHouseCode());
        List<HouseownerContactsEntity> contactsManagerOne = houseownerContactsManager.findByMap(param);

        houseEntity.setFileList(byMap);
        houseEntity.setHouseowner(one);
        houseEntity.setHouseownerContacts(contactsManagerOne);

        // 查询位置
        HouseLocationEntity locationEntity = houseLocationManager.findOne("locationCode", houseEntity.getLocationCode());
        houseEntity.setHouseLocation(locationEntity);
        return houseEntity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(HouseEntity houseEntity) {
        //获取前台传入的custype,后续经理可以录平台或我的
        String uiHousetype = houseEntity.getHouseType();

        houseEntity.setEmpCode(CurrentContext.getUserCode());
        OperateFillUtils.fill(houseEntity);
        //新增房源时，最新跟进时间等于房源创建时间
        houseEntity.setLastFollowupTime(houseEntity.getCreateTime());
        HouseLocationEntity location = houseLocationManager.findOne("locationCode", houseEntity.getLocationCode());
        houseEntity.setProvince(location.getProvince());
        houseEntity.setProvinceName(location.getProvinceName());
        houseEntity.setCity(location.getCity());
        houseEntity.setCityName(location.getCityName());
        houseEntity.setRegion(location.getRegion());
        houseEntity.setRegionName(location.getRegionName());
        houseEntity.setStreet(location.getStreet());
        houseEntity.setStreetName(location.getStreetName());

        BigDecimal price = houseEntity.getPrice();
        String unit = houseEntity.getUnit();
        //校验价格与单位是否合理
        checkPriceForUnit(unit,price);


        if (location.getCommunity()!=null &&location.getCommunity()!=""){
            houseEntity.setCommunity(location.getCommunity());
        }
        if (location.getCommunityName()!=null &&location.getCommunityName()!=""){
            houseEntity.setCommunityName(location.getCommunityName());
        }
        /**
         * 共享部门详情是否可见 暂定为可见，后续版本自定义
         */
        houseEntity.setShareOpenFlag(true);
        houseEntity.setCooOpenFlag(true);
        // 新增文件
        if(null != houseEntity.getFileList()){
            List<FileRelationEntity> list = new ArrayList<>();
            for (FileRelationEntity file :houseEntity.getFileList()){
                OperateFillUtils.fill(file);
                file.setOwnerCode(houseEntity.getHouseCode());
                list.add(file);
            }
            try {
                fileRelationManager.create(list);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        //新增房子外立面/园区道路/园区场地，仅内部可看，官网对外不展示
        if(null != houseEntity.getHouseYardImage()){
            List<FileRelationEntity> list = new ArrayList<>();
            for (FileRelationEntity file :houseEntity.getHouseYardImage()){
                OperateFillUtils.fill(file);
                file.setOwnerCode(houseEntity.getHouseCode());
                file.setFileUse(HouseFileUse.HouseYard);
                list.add(file);
            }
            try {
                fileRelationManager.create(list);
            } catch (IllegalAccessException e) {
                logger.error(e);
            }
        }

        // 新增联系人
        if(null != houseEntity.getHouseownerContacts()){
            for(HouseownerContactsEntity hoc: houseEntity.getHouseownerContacts()){
                hoc.setHouseCode(houseEntity.getHouseCode());
                houseownerContactsManager.create(hoc);
            }
        }

        //公开字段,默认公开
        houseEntity.setOpenFlag(true);
        //房源状态,默认热租中
        houseEntity.setHouseStatus(HouseStatus.hotRenting);
        //经理可以录平台或经纪人的房源类型1025
//        Map paramEmp = new HashMap();
//        paramEmp.put("empCode", CurrentContext.getUserCode());
//        EmployeeEntity nowEmp = employeeManager.findOne(paramEmp);
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();
        if(cur!=null){
            if(cur.getHasManager()){
                if(uiHousetype==null){throw new RemoveStackBusinessException ("您当前账号为经理账号,需要选择房源录入类型");}

                if(uiHousetype.equals("1")){houseEntity.setHouseType(uiHousetype);}
                else if(uiHousetype.equals("2")){houseEntity.setHouseType(uiHousetype);}
                else {throw new RemoveStackBusinessException("房源录入类型传字符串的1或2"+"当前housetype为:"+uiHousetype);}
            }
        }
//        int count = houseDbDao.insert(houseEntity);
        int count = this.createHouse(houseEntity);

        return count;
    }

    @Override
    @Transactional
    public int update(HouseEntity houseEntity) {
        if (houseEntity.getId() == null && houseEntity.getHouseCode() == null) {
            throw new RestBusinessException("更新房源时数据为空");
        }
        BigDecimal price = houseEntity.getPrice();
        String unit = houseEntity.getUnit();
        checkPriceForUnit(unit,price);
        HouseEntity oldHouseEntity=houseDbDao.selectUniqueByProp("houseCode",houseEntity.getHouseCode());
//        UserEntity userEntity=userManager.findUserByUserCode(CurrentContext.getUserCode());
//        UserEntity houseUserEntity=userManager.selectUniqueByProp(oldHouseEntity.getEmpCode());

        if(StringUtils.isNotEmpty(houseEntity.getLocationCode())){
            HouseLocationEntity location = houseLocationManager.findOne("locationCode", houseEntity.getLocationCode());
            houseEntity.setProvince(location.getProvince());
            houseEntity.setProvinceName(location.getProvinceName());
            houseEntity.setCity(location.getCity());
            houseEntity.setCityName(location.getCityName());
            houseEntity.setRegion(location.getRegion());
            houseEntity.setRegionName(location.getRegionName());
            houseEntity.setStreet(location.getStreet());
            houseEntity.setStreetName(location.getStreetName());
        }
        houseEntity.setLastUpdateCode(CurrentContext.getUserCode());
        houseEntity.setLastUpdateTime(new Date());
        List<FileRelationEntity> fileList = houseEntity.getFileList();
        List<FileRelationEntity> houseYardImage = houseEntity.getHouseYardImage();
        List<FileRelationEntity> houseProtocol = houseEntity.getHouseProtocol();
        if(fileList != null){
            for (FileRelationEntity entity : fileList) {
                entity.setFileUse(HouseFileUse.HouseInner);
            }
        }
        if(houseYardImage !=null){
            for (FileRelationEntity entity : houseYardImage) {
                entity.setFileUse(HouseFileUse.HouseYard);
                fileList.add(entity);
            }
        }
        if(houseProtocol != null){
            for (FileRelationEntity entity : houseProtocol) {
                entity.setFileUse(HouseFileUse.Protocol);
                fileList.add(entity);
            }
        }

        for (FileRelationEntity file :houseEntity.getFileList()){
            if(HouseFileUse.HouseInner.equals(file.getFileUse())){
                FileRelationEntity fileObjNewCopy = new FileRelationEntity();
                try {
                    BeanUtils.copyProperties(fileObjNewCopy,file);
                    OperateFillUtils.fill(fileObjNewCopy);
                    fileObjNewCopy.setFileUse("1");
                    fileObjNewCopy.setOwnerCode(houseEntity.getHouseCode());
                    fileList.add(fileObjNewCopy);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

                break;//转化一个内部推广图HouseInner为封面1后跳过for
            }
        }



        // 更新文件
        fileRelationManager.update(fileList,houseEntity.getHouseCode(),oldHouseEntity.getHouseName(),oldHouseEntity.getHouseNumber(),oldHouseEntity.getEmpCode());
        // 更新联系人
        houseownerContactsManager.update(houseEntity.getHouseownerContacts(),houseEntity.getHouseCode());
        // 增加跟进记录
        HouseFollowupEntity followupEntity = new HouseFollowupEntity();
        BaseUserEntity userInfo = CurrentContext.getUserInfo();
        followupEntity.setFollowupContext(userInfo.getOwnerDeptName()+"的"+userInfo.getUserName()+"更新了房源");
        followupEntity.setHouseCode(houseEntity.getHouseCode());
        /*********************重要****重要*****重要***************************/
        /**                                                                */
        /**         由于aop日志功能，该方法内只能调用一次houseDbDao.update();  */
        /**   修改信息造成的最后很近日期变更将直接在houseDbDao.update()中修改   */
        /*********************重要****重要*****重要***************************/

        houseFollowupManager.createHouseFollowupNotLastFollowupTime(followupEntity);
        Date date=new Date();
        houseEntity.setLastFollowupTime(date);
        houseEntity.setLastUpdateTime(date);
        houseEntity.setLastUpdateCode(userInfo.getUserCode());
        int i= houseDbDao.update(houseEntity);
        return i;
    }

    @Override
    @Transactional
    public int updateTwoVersion(HouseEntity houseEntity) {
        if (houseEntity.getId() == null && houseEntity.getHouseCode() == null) {
            throw new RestBusinessException("更新房源时数据为空");
        }
        HouseEntity oldHouseEntity=houseDbDao.selectUniqueByProp("houseCode",houseEntity.getHouseCode());
//        UserEntity userEntity=userManager.findUserByUserCode(CurrentContext.getUserCode());
//        UserEntity houseUserEntity=userManager.selectUniqueByProp(oldHouseEntity.getEmpCode());

        if(StringUtils.isNotEmpty(houseEntity.getLocationCode())){
            HouseLocationEntity location = houseLocationManager.findOne("locationCode", houseEntity.getLocationCode());
            houseEntity.setProvince(location.getProvince());
            houseEntity.setProvinceName(location.getProvinceName());
            houseEntity.setCity(location.getCity());
            houseEntity.setCityName(location.getCityName());
            houseEntity.setRegion(location.getRegion());
            houseEntity.setRegionName(location.getRegionName());
            houseEntity.setStreet(location.getStreet());
            houseEntity.setStreetName(location.getStreetName());
        }
        houseEntity.setLastUpdateCode(CurrentContext.getUserCode());
        houseEntity.setLastUpdateTime(new Date());
        // 更新文件
        fileRelationManager.update(houseEntity.getFileList(),houseEntity.getHouseCode(),oldHouseEntity.getHouseName(),oldHouseEntity.getHouseNumber(),oldHouseEntity.getEmpCode());
        // 更新联系人
        houseownerContactsManager.update(houseEntity.getHouseownerContacts(),houseEntity.getHouseCode());
        // 增加跟进记录
        HouseFollowupEntity followupEntity = new HouseFollowupEntity();
        BaseUserEntity userInfo = CurrentContext.getUserInfo();
        followupEntity.setFollowupContext(userInfo.getOwnerDeptName()+"的"+userInfo.getUserName()+"更新了房源");
        followupEntity.setHouseCode(houseEntity.getHouseCode());
        /*********************重要****updateTwoVersion*****重要***************************/
        /**         由于aop日志功能，该方法内只能调用一次houseDbDao.update();  */
        /**   修改信息造成的最后很近日期变更将直接在houseDbDao.update()中修改   */
        /*********************重要****重要*****重要***************************/

        houseFollowupManager.createHouseFollowupNotLastFollowupTime(followupEntity);
        Date date=new Date();
        houseEntity.setLastFollowupTime(date);
        houseEntity.setLastUpdateTime(date);
        houseEntity.setLastUpdateCode(userInfo.getUserCode());

        // 查询房源
//        HouseEntity houseDb = houseDbDao.selectUniqueByProp("houseCode",houseEntity.getHouseCode());
        //查询位置
        HouseLocationEntity location = houseLocationManager.findOne("locationCode", houseEntity.getLocationCode());
        houseEntity.setHouseLocation(location);
        if(location==null){
            throw new RemoveStackBusinessException("位置对象不存在!位置编码:"+houseEntity.getLocationCode());
        }
        houseEntity.setProvince(location.getProvince());
        houseEntity.setProvinceName(location.getProvinceName());
        houseEntity.setCity(location.getCity());
        houseEntity.setCityName(location.getCityName());
        houseEntity.setRegion(location.getRegion());
        houseEntity.setRegionName(location.getRegionName());
        houseEntity.setStreet(location.getStreet());
        houseEntity.setStreetName(location.getStreetName());
        if (location.getCommunity()!=null &&location.getCommunity()!=""){
            houseEntity.setCommunity(location.getCommunity());
        }
        if (location.getCommunityName()!=null &&location.getCommunityName()!=""){
            houseEntity.setCommunityName(location.getCommunityName());
        }
        //默认字段拼接start
        String nameTotalSplit = "";
        String name1 = location.getStreetName()+houseEntity.getArea()+"平";
        if (location.getCommunityName()!=null &&location.getCommunityName()!=""){
            name1 = location.getCommunityName()+houseEntity.getArea()+"平";
        }
        String name2 = "楼上";
        if(houseEntity.getWhereLayer().equals("1")){
            name2 = "底楼";
        }
        String name3 = DictionaryStorage.get(HouseStyleType.class.getName(), houseEntity.getHouseStyle()).getName(); //'房源类型'
        String name4 ="层高"+ houseEntity.getLayerHeight()+"米"; //'层高'
        String name5 = DictionaryStorage.get(HousePurposeType.class.getName(), houseEntity.getHousePurpose()).getName();; //'房源用途'
        String name6 = houseEntity.getMinAcreage()+"平米起租"; //'最小分割'
        if(houseEntity.getMinAcreage().equals("0")){
            name6 = "不可分割";
        }else if(houseEntity.getMinAcreage().equals(houseEntity.getLayerArea().toString())){
            name6 = "整租";
        }
        String name7 = houseEntity.getMaxElectric()+"kva供电"; //'最小分割'
        String name8 = "";
        if(houseEntity.getHasWholeRental()==true){
            name8=" "+"整栋出租";
        }

        nameTotalSplit = name1+name2+" "+name3+" "+name4+" "+name5+" "+name6+" "+name7+name8;
        houseEntity.setHouseName(nameTotalSplit);

        int i= houseDbDao.update(houseEntity);
        return i;
    }

    @Override
    @Transactional
    public int updateCut(HouseEntity houseEntity) {
        if (houseEntity.getId() == null && houseEntity.getHouseCode() == null) {
            throw new RestBusinessException("更新房源时数据为空");
        }
        HouseEntity oldHouseEntity=houseDbDao.selectUniqueByProp("houseCode",houseEntity.getHouseCode());
        UserEntity userEntity=userManager.findUserByUserCode(CurrentContext.getUserCode());
        UserEntity houseUserEntity=userManager.selectUniqueByProp(oldHouseEntity.getEmpCode());
        BigDecimal price = houseEntity.getPrice();
        String unit = houseEntity.getUnit();
        checkPriceForUnit(unit,price);
        if (!checkAuthOfFollowup(oldHouseEntity,userEntity,houseUserEntity)){
            throw new RemoveStackBusinessException("无权限修改");
        }

        houseEntity.setLastUpdateCode(userEntity.getUserCode());
        houseEntity.setLastUpdateTime(new Date());
        // 核对更改信息
        StringBuilder sb = new StringBuilder(userEntity.getOwnerDeptName()+"的"+userEntity.getUserName()+"修改了房源");
        if(null != houseEntity.getLayerArea()){
            sb.append("层总面积为:"+houseEntity.getLayerArea());
        }
        if(null != houseEntity.getArea()){
            sb.append(",可租面积为:"+houseEntity.getArea());
        }
        if(null != houseEntity.getPrice()){
            sb.append(",房源价格为:"+houseEntity.getPrice()+houseEntity.getUnit());
        }
        if(null != houseEntity.getEnterTime()){
            String format = DateUtil.format(houseEntity.getEnterTime(), "yyyy-MM-dd");
            sb.append(",可入住时间为:"+format);
        }
        // 更新联系人
        if(CollectionUtil.isNotEmpty(houseEntity.getHouseownerContacts())){
            sb.append(",添加了联系人");
            houseownerContactsManager.update(houseEntity.getHouseownerContacts(),houseEntity.getHouseCode());
        }
        sb.append(".");
        // 增加跟进记录
        HouseFollowupEntity followupEntity = new HouseFollowupEntity();
        followupEntity.setFollowupContext(sb.toString());
        followupEntity.setHouseCode(houseEntity.getHouseCode());
        houseFollowupManager.createHouseFollowupNotLastFollowupTime(followupEntity);
        Date date=new Date();
        houseEntity.setLastFollowupTime(date);
        houseEntity.setLastUpdateTime(date);
        houseEntity.setLastUpdateCode(userEntity.getUserCode());

        return houseDbDao.update(houseEntity);
    }

    @Override
    public PageResult<CustomerEntity> takeLookByCustomer(String houseCode, Integer start, Integer pageSize, String orderByValue, Boolean isAsc) {
//        HouseEntity oldHouseEntity=houseDbDao.selectUniqueByProp("houseCode",houseCode);
//        UserEntity userEntity=userManager.findUserByUserCode(CurrentContext.getUserCode());
//        UserEntity houseUserEntity=userManager.selectUniqueByProp(oldHouseEntity.getEmpCode());
        /*if (!this.checkAuthOfVisitCustomer(oldHouseEntity,userEntity,houseUserEntity)){
            throw new BusinessException("客户看房记录，只能查看自己的、部门共享公开的、达成合作并公开的房源！");
        }*/
        return customerDbDao.takeLookByCustomer(houseCode,start,pageSize,orderByValue,isAsc);
    }

    @Override
    public List<HouseEntity> selectCusSawHouses(Map<String,String> param) {

//        CustomerEntity customer  = customerManager.findOne("cusCode",param.get("cusCode"));

        return this.houseDbDao.selectCusSawHouses(param);
    }

    @Override
    public Boolean checkFollowupRecord(HouseEntity oldHouseEntity, UserEntity userEntity, UserEntity houseUserEntity) {
        if (userEntity.getEnterType().equals("1")){
            return true;
        }
        //判断是否是本人
        if (oldHouseEntity.getEmpCode()
                .equals(CurrentContext.getUserCode())){
            return true;
        }
        //同部门自定义
        if (
//                oldHouseEntity.getOpenFlag()&&
                houseUserEntity.getOwnerDeptCode().equals(CurrentContext.getUserInfo().getOwnerDeptCode())){
            //openFlag为true  同部门共享
            return true;
        }
        //共享部门共享房源
        if (oldHouseEntity.getShareOpenFlag()
                &&hasSharePool(userEntity.getOwnerDeptCode(),houseUserEntity.getOwnerDeptCode())){
            //shareOpenFlag为true  共享房源共享部门可进行修改
            return true;
        }
        //平台房源申请
        if (hasApply(oldHouseEntity.getHouseCode(),CurrentContext.getUserCode())){
            return true;
        }
        //合作中
        if(oldHouseEntity.getCooOpenFlag()
                &&hasCooperate(oldHouseEntity.getHouseCode(),CurrentContext.getUserCode())){
            return true;
        }
//        if (oldHouseEntity.getOpenFlag()){
//            return true;
//        }

        return false;

    }

    @Override
    public void up(Map<String, Object> param) {
        HouseEntity oldHouseEntity=houseDbDao.selectUniqueByProp("houseCode",param.get("houseCode"));
        UserEntity userEntity=userManager.findUserByUserCode(CurrentContext.getUserCode());
        UserEntity houseUserEntity=userManager.selectUniqueByProp(oldHouseEntity.getEmpCode());
        if (!this.checkAuthOfUp(oldHouseEntity,userEntity,houseUserEntity)){
            throw new RemoveStackBusinessException("无权限修改");
        }
        //执行后 房源权益归上架人
        param.put("empCode",CurrentContext.getUserCode());
        param.put("deptCode",userEntity.getOwnerDeptCode());
        param.put("isDeleted",Boolean.FALSE);
        param.put("houseStatus", HouseStatus.hotRenting);
        houseDbDao.upAndDownShelf(param);
        setUpAndDownLog(param);//上架记录
    }

    @Override
    @Transactional
    public String down(Map<String, Object> param) {
        HouseEntity he =houseDbDao.selectUniqueByProp("houseCode",param.get("houseCode"));
        //UserEntity userEntity=userManager.findUserByUserCode(CurrentContext.getUserCode());
        //UserEntity houseUserEntity=userManager.selectUniqueByProp(oldHouseEntity.getEmpCode());
        if (he.getEmpCode().equals(CurrentContext.getUserCode())){
            //throw new BusinessException("不是房源所属人无权限进行该操作");
            // 更改提醒下架
            param.put("ownerCode", he.getHouseCode());
            param.put("hasOperate", false);
            RemindDownEntity rd = remindDownManager.findOne(param);
            if(rd != null){
                rd.setHasOperate(true);
                rd.setLastUpdateCode(CurrentContext.getUserCode());
                rd.setLastUpdateTime(new Date());
                remindDownManager.update(rd);
            }
            //param.put("leaseExpiration",he.getLeaseExpiration());
            param.put("isDeleted",Boolean.FALSE);
            param.put("houseStatus", HouseStatus.HasLease);
            houseDbDao.upAndDownShelf(param);
            setUpAndDownLog(param);//下架记录

            return "下架成功";
        }else {
            // 发送下架提醒
            RemindDownEntity rd = new RemindDownEntity();
            rd.setUserCode(he.getEmpCode());
            rd.setOwnerCode(he.getHouseCode());
            rd.setOwnerName(he.getHouseName());
            rd.setDownCause(param.get("downReason").toString());
            if(param.get("leaseExpiration")!= null){
                String leaseExpiration = param.get("leaseExpiration").toString();
                DateTime date1 = DateUtil.date(new Date(leaseExpiration));
                rd.setLeaseExpiration(date1);
            }
            rd.setDownType(DownType.HOS);
            remindDownManager.create(rd);
            return "已经提醒持有者下架房源";
        }
    }

    /**
     * 实体类下面链接
     * @see com.kfwy.hkp.hos.house.entity.HouseUpdownEntity
     * 房源上下架记录
     */
    private void setUpAndDownLog(Map<String, Object> param){
        HouseUpdownEntity houseUpdownEntity = new HouseUpdownEntity();
        HouseEntity houseEntity = findOne("houseCode",param.get("houseCode"));
        houseUpdownEntity.setUserCode(houseEntity.getEmpCode());
        //判断上下架
        if(HouseStatus.hotRenting.equals(param.get("houseStatus"))){
            houseUpdownEntity.setPreStatus(HouseStatus.HasLease);
            houseUpdownEntity.setAftStatus(HouseStatus.hotRenting);
        } else {
            houseUpdownEntity.setPreStatus(HouseStatus.hotRenting);
            houseUpdownEntity.setAftStatus(HouseStatus.HasLease);
        }

        houseUpdownEntity.setHouseCode((String) param.get("houseCode"));
        houseUpdownEntity.setRemark((String) param.get("downReason"));//下架原因
        houseUpdownEntity.setCreateCode(CurrentContext.getUserCode());
        houseUpdownEntity.setCreateDeptCode(CurrentContext.getUserInfo().getOwnerDeptCode());
        houseUpdownEntity.setCreateTime(new Date());
        houseUpdownEntity.setLastUpdateCode(CurrentContext.getUserCode());
        houseUpdownEntity.setLastUpdateTime(new Date());
        houseUpdownEntity.setIsDeleted(Boolean.FALSE);
        houseDbDao.setUpAndDownLog(houseUpdownEntity);
    }

    @Override
    public void onOpenFlag(String houseCode) {
        HouseEntity oldHouseEntity=houseDbDao.selectUniqueByProp("houseCode",houseCode);
        UserEntity userEntity=userManager.findUserByUserCode(CurrentContext.getUserCode());
        UserEntity houseUserEntity=userManager.selectUniqueByProp(oldHouseEntity.getEmpCode());
        if (!this.checkAuthOfDown(oldHouseEntity,userEntity,houseUserEntity)){
            throw new RemoveStackBusinessException("不是房源所属人无权限进行该操作");
        }
        HouseEntity house = new HouseEntity();
        house.setHouseCode(houseCode);
        house.setOpenFlag(true);
        houseDbDao.onAndOffOpenFlag(house);
    }

    @Override
    public void offOpenFlag(String houseCode) {
        HouseEntity oldHouseEntity=houseDbDao.selectUniqueByProp("houseCode",houseCode);
        UserEntity userEntity=userManager.findUserByUserCode(CurrentContext.getUserCode());
        UserEntity houseUserEntity=userManager.findUserByUserCode(oldHouseEntity.getEmpCode());
        if (!this.checkAuthOfDown(oldHouseEntity,userEntity,houseUserEntity)){
            throw new RemoveStackBusinessException("不是房源所属人无权限进行该操作");
        }
        HouseEntity house = new HouseEntity();
        house.setHouseCode(houseCode);
        house.setOpenFlag(false);
        houseDbDao.onAndOffOpenFlag(house);
    }

    @Override
    public void onShareOpenFlag(String houseCode) {
        HouseEntity oldHouseEntity=houseDbDao.selectUniqueByProp("houseCode",houseCode);
        UserEntity userEntity=userManager.findUserByUserCode(CurrentContext.getUserCode());
        UserEntity houseUserEntity=userManager.selectUniqueByProp(oldHouseEntity.getEmpCode());
        if (!this.checkAuthOfDown(oldHouseEntity,userEntity,houseUserEntity)){
            throw new RemoveStackBusinessException("不是房源所属人无权限进行该操作");
        }
        HouseEntity house = new HouseEntity();
        house.setHouseCode(houseCode);
        house.setShareOpenFlag(true);
        houseDbDao.onAndOffShareOpenFlag(house);
    }

    @Override
    public void offShareOpenFlag(String houseCode) {
        HouseEntity oldHouseEntity=houseDbDao.selectUniqueByProp("houseCode",houseCode);
        UserEntity userEntity=userManager.findUserByUserCode(CurrentContext.getUserCode());
        UserEntity houseUserEntity=userManager.selectUniqueByProp(oldHouseEntity.getEmpCode());
        if (!this.checkAuthOfDown(oldHouseEntity,userEntity,houseUserEntity)){
            throw new RemoveStackBusinessException("不是房源所属人无权限进行该操作");
        }
        HouseEntity house = new HouseEntity();
        house.setHouseCode(houseCode);
        house.setShareOpenFlag(false);
        houseDbDao.onAndOffShareOpenFlag(house);
    }

    @Override
    public void onCooOpenFlag(String houseCode) {
        HouseEntity oldHouseEntity=houseDbDao.selectUniqueByProp("houseCode",houseCode);
        UserEntity userEntity=userManager.findUserByUserCode(CurrentContext.getUserCode());
        UserEntity houseUserEntity=userManager.selectUniqueByProp(oldHouseEntity.getEmpCode());
        if (!this.checkAuthOfDown(oldHouseEntity,userEntity,houseUserEntity)){
            throw new RemoveStackBusinessException("不是房源所属人无权限进行该操作");
        }
        HouseEntity house = new HouseEntity();
        house.setHouseCode(houseCode);
        house.setCooOpenFlag(true);
        houseDbDao.onAndOffCooOpenFlag(house);
    }

    @Override
    public void offCooOpenFlag(String houseCode) {
        HouseEntity oldHouseEntity=houseDbDao.selectUniqueByProp("houseCode",houseCode);
        UserEntity userEntity=userManager.findUserByUserCode(CurrentContext.getUserCode());
        UserEntity houseUserEntity=userManager.selectUniqueByProp(oldHouseEntity.getEmpCode());
        if (!this.checkAuthOfDown(oldHouseEntity,userEntity,houseUserEntity)){
            throw new RemoveStackBusinessException("不是房源所属人无权限进行该操作");
        }
        HouseEntity house = new HouseEntity();
        house.setHouseCode(houseCode);
        house.setCooOpenFlag(false);
        houseDbDao.onAndOffCooOpenFlag(house);
    }

    @Override
    public Boolean checkAuthOfDetails(HouseEntity oldHouseEntity,UserEntity userEntity,UserEntity houseUserEntity) {
        if (userEntity.getEnterType().equals("1")){
            return true;
        }
        if (HouseType.PLATFORM.equals(oldHouseEntity.getHouseType())){
            //平台房源申请
            if (hasApply(oldHouseEntity.getHouseCode(),CurrentContext.getUserCode())){
                return true;
            }
        }else {
            if (checkAgentTypeHosDetail(oldHouseEntity, userEntity, houseUserEntity))
                return true;

        }
        //如果仍然无权限修改
        return false;
    }

    /**
     * 经纪人类型的房源详情查看权限判断
     * @param oldHouseEntity
     * @param userEntity
     * @param houseUserEntity
     * @return
     */
    public boolean checkAgentTypeHosDetail(HouseEntity oldHouseEntity, UserEntity userEntity, UserEntity houseUserEntity) {
        //判断是否是本人
        if (oldHouseEntity.getEmpCode().equals(CurrentContext.getUserCode())){
            return true;
        }
        //同部门自定义
        if (
//                oldHouseEntity.getOpenFlag()&&
                houseUserEntity.getOwnerDeptCode().equals(CurrentContext.getUserInfo().getOwnerDeptCode())){
            //openFlag为true  同部门共享
            return true;
        }
        //共享部门共享房源自定义
        if (oldHouseEntity.getShareOpenFlag()&&hasSharePool(userEntity.getOwnerDeptCode(),houseUserEntity.getOwnerDeptCode())){
            //shareOpenFlag为true  共享房源共享部门可进行修改
            return true;
        }
        //合作中
        if(oldHouseEntity.getCooOpenFlag()&&hasCooperate(oldHouseEntity.getHouseCode(),CurrentContext.getUserCode())){
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkAuthOfFollowup(HouseEntity oldHouseEntity,UserEntity userEntity,UserEntity houseUserEntity) {
       /* if (userEntity.getEnterType().equals("1")){
            return true;
        }
        if (HouseType.PLATFORM.equals(oldHouseEntity.getHouseType())){
            //平台房源申请
            if (hasApply(oldHouseEntity.getHouseCode(),CurrentContext.getUserCode())){
                return true;
            }
        }else {
            //判断是否是本人
            if (checkAgentTypeHosDetail(oldHouseEntity, userEntity, houseUserEntity)) return true;
        }
        //如果仍然无权限修改
        return false;*/
       return true;
    }

    @Override
    public Boolean checkAuthOfUpdate(HouseEntity oldHouseEntity,UserEntity userEntity,UserEntity houseUserEntity) {
        if (userEntity.getEnterType().equals("1")){
            return true;
        }
        if (HouseType.PLATFORM.equals(oldHouseEntity.getHouseType())){
            //平台房源申请
            if (hasApply(oldHouseEntity.getHouseCode(),CurrentContext.getUserCode())){
                return true;
            }
        }else {
            //判断是否是本人
            if (checkAgentTypeHosDetail(oldHouseEntity, userEntity, houseUserEntity)) return true;
        }
        //如果仍然无权限修改
        return false;
    }

    @Override
    public Boolean checkAuthOfAddVisitCustomer(HouseEntity oldHouseEntity,UserEntity userEntity,UserEntity houseUserEntity) {
        if (userEntity.getEnterType().equals("1")){
            return true;
        }
        if (HouseType.PLATFORM.equals(oldHouseEntity.getHouseType())){
            //平台房源申请
            if (hasApply(oldHouseEntity.getHouseCode(),CurrentContext.getUserCode())){
                return true;
            }
        }else {
            //判断是否是本人
            if (checkAgentTypeHosDetail(oldHouseEntity, userEntity, houseUserEntity)) return true;
        }
        //暂定为都有权限
        return true;
    }

    @Override
    public Boolean checkAuthOfUp(HouseEntity oldHouseEntity,UserEntity userEntity,UserEntity houseUserEntity) {
        if (HouseType.PLATFORM.equals(oldHouseEntity.getHouseType())){
            //平台房源申请
            if (hasApply(oldHouseEntity.getHouseCode(),CurrentContext.getUserCode())){
                return true;
            }
        }else {
            //判断是否是本人
            if (oldHouseEntity.getEmpCode().equals(CurrentContext.getUserCode())){
                return true;
            }
            //同部门自定义
            if (
//                    oldHouseEntity.getOpenFlag()&&
                    houseUserEntity.getOwnerDeptCode().equals(CurrentContext.getUserInfo().getOwnerDeptCode())){
                //openFlag为true  同部门共享
                return true;
            }
            //共享部门共享房源
            if (oldHouseEntity.getShareOpenFlag()&&hasSharePool(userEntity.getOwnerDeptCode(),houseUserEntity.getOwnerDeptCode())){
                //shareOpenFlag为true  共享房源共享部门可进行修改
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean checkAuthOfDown(HouseEntity oldHouseEntity,UserEntity userEntity,UserEntity houseUserEntity) {
        if (userEntity.getEnterType().equals("1")){
            return true;
        }
        //判断是否是本人
        if (oldHouseEntity.getEmpCode().equals(CurrentContext.getUserCode())){
            return true;
        }
//        DeptEntity deptEntity=deptManager.getDeptEntity(userEntity.getOwnerDeptCode());
        DeptEntity houseDeptEntity=deptManager.getDeptEntity(houseUserEntity.getOwnerDeptCode());
        if(userEntity.getUserCode().equals(houseDeptEntity.getLeaderCode())){
            //判断是否是人员所属部门经理领导
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkAuthOfShow(HouseEntity oldHouseEntity,UserEntity userEntity,UserEntity houseUserEntity) {
        //判断是否是本人
        if (oldHouseEntity.getEmpCode().equals(CurrentContext.getUserCode())){
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkAuthOfHide(HouseEntity oldHouseEntity,UserEntity userEntity,UserEntity houseUserEntity) {
        //判断是否是本人
        if (oldHouseEntity.getEmpCode().equals(CurrentContext.getUserCode())){
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkAuthOfVisitCustomer(HouseEntity oldHouseEntity,UserEntity userEntity,UserEntity houseUserEntity) {
        if (userEntity.getEnterType().equals("1")){
            return true;
        }
        if (HouseType.PLATFORM.equals(oldHouseEntity.getHouseType())){
            //平台房源申请
            if (hasApply(oldHouseEntity.getHouseCode(),CurrentContext.getUserCode())){
                return true;
            }
        }else {
            //判断是否是本人
            if (checkAgentTypeHosDetail(oldHouseEntity, userEntity, houseUserEntity)) return true;
        }
        return false;
    }

    @Override
    public Boolean checkAuthOfAddVisitOwner(HouseEntity oldHouseEntity,UserEntity userEntity,UserEntity houseUserEntity) {
        if (userEntity.getEnterType().equals("1")){
            return true;
        }
        if (HouseType.PLATFORM.equals(oldHouseEntity.getHouseType())){
            //平台房源申请
            if (hasApply(oldHouseEntity.getHouseCode(),CurrentContext.getUserCode())){
                return true;
            }
        }else {
            //判断是否是本人
            if (checkAgentTypeHosDetail(oldHouseEntity, userEntity, houseUserEntity)) return true;
        }
        return false;
    }

    @Override
    public Boolean checkAuthOfVisitOwner(HouseEntity oldHouseEntity, UserEntity userEntity, UserEntity houseUserEntity) {
        if (userEntity.getEnterType().equals("1")){
            return true;
        }

        if (HouseType.PLATFORM.equals(oldHouseEntity.getHouseType())){
            //平台房源申请
            if (hasApply(oldHouseEntity.getHouseCode(),CurrentContext.getUserCode())){
                return true;
            }
        }else {
            //经纪人类型的房源详情查看权限判断
            if (checkAgentTypeHosDetail(oldHouseEntity, userEntity, houseUserEntity))
                return true;
        }
        return false;
    }

    @Override
    public String findEmpNameByHouseCode(String houseCode) {
        String preSql = "SELECT\n" +
                "us.user_name as emp_name\n" +
                "FROM\n" +
                "\tt_hkp_hos_house hos,\n" +
                "\tt_hkp_sys_user_user us\n" +
                "WHERE\n" +
                "\thos.emp_code = us.user_code\n" +
                "\tAND \n" +
                "\thos.house_code='%s'";

        HouseEntity ho = houseDbDao.selectOneByNativeSql(String.format(preSql,houseCode));

        return ho==null ? "" : ho.getEmpName();
    }

    @Override
    public PageResult<HouseUpdownEntity> upDownQuery(Map map,Integer start,Integer pageSize) {
        return houseDbDao.upDownQuery(map,start,pageSize);
    }

    @Override
    public HouseEntity editByWeb(Map<String, Object> param) {
        // 查询房源
        HouseEntity houseEntity = houseDbDao.selectUniqueByMap(param);
        param.clear();
        // 查询文件
        param.put("ownerCode",houseEntity.getHouseCode());
        List<FileRelationEntity> byMap = fileRelationManager.findByMap(param);
        param.clear();
        // 查询业主
        param.put("houseownerCode",houseEntity.getOwnerCode());
        HouseownerEntity one = houseownerManager.findOne(param);
        param.clear();
        // 查询多个联系人
        param.put("houseCode",houseEntity.getHouseCode());
        List<HouseownerContactsEntity> contactsManagerOne = houseownerContactsManager.findByMap(param);


        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "10");
        List<FileRelationEntity> fileList = new ArrayList<>();
        List<FileRelationEntity> houseYardImage = new ArrayList<>();
        List<FileRelationEntity> houseProtocol = new ArrayList<>();
        byMap.parallelStream().forEach(e -> {
            try {
                switch (e.getFileUse()){

                    case HouseFileUse.HouseInner:
                        fileList.add(e);
                        break;

                    case HouseFileUse.HouseYard:
                        houseYardImage.add(e);
                        break;

                    case HouseFileUse.Protocol:
                        houseProtocol.add(e);
                        break;

                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        });

        houseEntity.setFileList(fileList);
        houseEntity.setHouseYardImage(houseYardImage);
        houseEntity.setHouseProtocol(houseProtocol);
        houseEntity.setHouseowner(one);
        houseEntity.setHouseownerContacts(contactsManagerOne);
        return houseEntity;
    }

    @Override
    public Boolean checkHosPermissions(String checkCode, HouseEntity house, UserEntity cur) {
        Boolean checkPermission = false;

        String userLevel = cur.getUserLevel();

        UserEntity ownerUser = userManager.selectUniqueByProp(house.getEmpCode());
        Boolean isNoPass = true;
        if (cur.getEnterType().equals("1")) {
            isNoPass = false;
        }
        if (isNoPass) {
            //当前客户的所属人有部门，且有领导，则进入
            if (ownerUser.getOwnerDeptCode() != null
                    && ownerUser.getLeaderCode() != null
                    && cur.getOwnerDeptCode() != null) {

                UserEntity leader = userManager.selectUniqueByProp(deptManager.getDeptEntity(ownerUser.getOwnerDeptCode()).getLeaderCode());
                //当前客户所属人有领导
                if (leader != null) {
                    //当前操作人为领导或者客服则跳过判断
                    if (leader.getUserCode().equals(cur.getUserCode())) {
                        isNoPass = false;
                    } else {
                        //遍历当前操作人的所有子部门，如果子部门包含客户所属人的部门，则是大区总，大区总也可以做所有操作
                        List<String> deptCode = deptManager.getDownDeptCode(cur.getOwnerDeptCode());
                        if (deptCode != null
                                && deptCode.size() > 1
                                && deptCode.contains(ownerUser.getOwnerDeptCode())
                                && deptManager.getDeptEntity(cur.getOwnerDeptCode()).getLeaderCode().equals(cur.getUserCode())
                                ) {
                            isNoPass = false;
                        }
                    }
                }
            }
        }
        if (isNoPass){
            switch (userLevel) {

                case UserLevel.K0:
                    checkPermission = k0HouseCheckManager.checkHousePermissions(checkCode, house, cur, ownerUser);
                    break;

                case UserLevel.K1:
                    checkPermission = k1HouseCheckManager.checkHousePermissions(checkCode, house, cur, ownerUser);
                    break;

                case UserLevel.K2:
                    checkPermission = k2HouseCheckManager.checkHousePermissions(checkCode, house, cur, ownerUser);
                    break;

                case UserLevel.T0:
                    checkPermission = t0HouseCheckManager.checkHousePermissions(checkCode, house, cur, ownerUser);
                    break;
            }
        }else{
            checkPermission=true;
        }
        return checkPermission;
    }

    @Override
    public PageResult<HouseEntity> selectContractCensus(Map<String,Object> map, Integer start, Integer pageSize) {
        return this.houseDbDao.selectContractCensus(map,start,pageSize);
    }

    @Override
    public PageResult<HouseEntity> selectCusMatchingHos(Map<String, Object> map, Integer start, Integer pageSize, String orderBy, boolean isAse) {
        return this.houseDbDao.selectCusMatchingHos(map,start,pageSize,orderBy,isAse);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HouseEntity createTwoVersion(HouseEntity houseEntity) {
        //2,补接 manager中增加默认值字段
        //获取前台传入的custype,后续经理可以录平台或我的
        String uiHousetype = houseEntity.getHouseType();


        OperateFillUtils.fill(houseEntity);
        //新增房源时，最新跟进时间等于房源创建时间
        houseEntity.setLastFollowupTime(houseEntity.getCreateTime());
        HouseLocationEntity location = houseLocationManager.findOne("locationCode", houseEntity.getLocationCode());
        houseEntity.setHouseLocation(location);
        if(location==null){
            throw new RemoveStackBusinessException("位置对象不存在!位置编码:"+houseEntity.getLocationCode());
        }
        houseEntity.setProvince(location.getProvince());
        houseEntity.setProvinceName(location.getProvinceName());
        houseEntity.setCity(location.getCity());
        houseEntity.setCityName(location.getCityName());
        houseEntity.setRegion(location.getRegion());
        houseEntity.setRegionName(location.getRegionName());
        houseEntity.setStreet(location.getStreet());
        houseEntity.setStreetName(location.getStreetName());
        if (location.getCommunity()!=null &&location.getCommunity()!=""){
            houseEntity.setCommunity(location.getCommunity());
        }
        if (location.getCommunityName()!=null &&location.getCommunityName()!=""){
            houseEntity.setCommunityName(location.getCommunityName());
        }

        // 新增文件
        if(null != houseEntity.getFileList()){
            List<FileRelationEntity> list = new ArrayList<>();
            for (FileRelationEntity file :houseEntity.getFileList()){
                OperateFillUtils.fill(file);
                file.setOwnerCode(houseEntity.getHouseCode());
                list.add(file);
            }
            for (FileRelationEntity file :houseEntity.getFileList()){
                if(file.getFileUse().equals(HouseFileUse.HouseInner)){
                    FileRelationEntity fileObjNewCopy = new FileRelationEntity();
                    try {
                        BeanUtils.copyProperties(fileObjNewCopy,file);
                        OperateFillUtils.fill(fileObjNewCopy);
                        fileObjNewCopy.setFileUse("1");
                        fileObjNewCopy.setOwnerCode(houseEntity.getHouseCode());
                        list.add(fileObjNewCopy);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                    break;//转化一个外立面HouseYard为封面1后跳过for
                }
            }
            try {
                fileRelationManager.create(list);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        //新增房子外立面/园区道路/园区场地，仅内部可看，官网对外不展示
        if(null != houseEntity.getHouseYardImage()){
            List<FileRelationEntity> list = new ArrayList<>();
            for (FileRelationEntity file :houseEntity.getHouseYardImage()){
                OperateFillUtils.fill(file);
                file.setOwnerCode(houseEntity.getHouseCode());
                file.setFileUse(HouseFileUse.HouseYard);
                list.add(file);
            }
            try {
                fileRelationManager.create(list);
            } catch (IllegalAccessException e) {
                logger.error(e);
            }

        }

        //新增房子协议
        if(null != houseEntity.getHouseProtocol()){
            List<FileRelationEntity> list = new ArrayList<>();
            for (FileRelationEntity file :houseEntity.getHouseProtocol()){
                OperateFillUtils.fill(file);
                file.setOwnerCode(houseEntity.getHouseCode());
                file.setFileUse(HouseFileUse.Protocol);
                list.add(file);
            }
            try {
                fileRelationManager.create(list);
            } catch (IllegalAccessException e) {
                logger.error(e);
            }

        }

        // 新增联系人
        if(null != houseEntity.getHouseownerContacts()){
            for(HouseownerContactsEntity hoc: houseEntity.getHouseownerContacts()){
                hoc.setHouseCode(houseEntity.getHouseCode());
                houseownerContactsManager.create(hoc);
            }
        }

        //业主号码已存在直接获取,不新增
        // 手机号格式
        if (!PhoneUtils.isMobile(houseEntity.getHouseowner().getHouseownerPhone())) {
            throw new RemoveStackBusinessException("业主手机号码格式错误");
        }
        // 名下是否存在
        Map hwmap = new HashMap();
        hwmap.put("createCode", CurrentContext.getUserCode());
        hwmap.put("empCode", CurrentContext.getUserCode());
        hwmap.put("houseownerPhone",houseEntity.getHouseowner().getHouseownerPhone());
        List<HouseownerEntity> hwList = houseownerDbDao.selectByMap(hwmap);
        if(hwList!=null){
            if(hwList.size()>0){
                HouseownerEntity oldHwObj = hwList.get(0);
                houseEntity.setOwnerCode(oldHwObj.getHouseownerCode());
            }else{
                HouseownerEntity houseowner = houseEntity.getHouseowner();
                HouseownerEntity hwRetrunObj = houseownerManager.createAndReturn(houseowner);
                houseEntity.setOwnerCode(hwRetrunObj.getHouseownerCode());
            }
        }else if(hwList ==null){
            HouseownerEntity houseowner = houseEntity.getHouseowner();
            HouseownerEntity hwRetrunObj = houseownerManager.createAndReturn(houseowner);
            houseEntity.setOwnerCode(hwRetrunObj.getHouseownerCode());
        }
        // 新增业主0613
//        if(null != houseEntity.getHouseowner()){
//            HouseownerEntity houseowner = houseEntity.getHouseowner();
//            HouseownerEntity hwRetrunObj = houseownerManager.createAndReturn(houseowner);
//            houseEntity.setOwnerCode(hwRetrunObj.getOwnerCode());
//        }

        //公开字段,默认公开
        houseEntity.setOpenFlag(true);
        //房源状态,默认热租中
        houseEntity.setHouseStatus(HouseStatus.hotRenting);
        //经理可以录平台或经纪人的房源类型1025
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();
        if(cur!=null){
            if(cur.getHasManager()){
                if(uiHousetype==null){throw new RemoveStackBusinessException("您当前账号为经理账号,需要选择房源录入类型");}

                if(uiHousetype.equals("1")){houseEntity.setHouseType(uiHousetype);}
                else if(uiHousetype.equals("2")){houseEntity.setHouseType(uiHousetype);}
                else {throw new RemoveStackBusinessException("房源录入类型传字符串的1或2"+"当前housetype为:"+uiHousetype);}
            }
        }
        //原先逻辑结束,
        //默认字段拼接start
        String nameTotalSplit = "";
        String name1 = houseEntity.getStreetName()+houseEntity.getArea()+"平";
        if (location.getCommunityName()!=null &&location.getCommunityName()!=""){
            name1 = location.getCommunityName()+houseEntity.getArea()+"平";
        }
        String name2 = "楼上";
        if(houseEntity.getWhereLayer().equals("1")){
            name2 = "底楼";
        }
        String name3 = DictionaryStorage.get(HouseStyleType.class.getName(), houseEntity.getHouseStyle()).getName(); //'房源类型'
        String name4 ="层高"+ houseEntity.getLayerHeight()+"米"; //'层高'
        String name5 = DictionaryStorage.get(HousePurposeType.class.getName(), houseEntity.getHousePurpose()).getName();; //'房源用途'
        String name6 = houseEntity.getMinAcreage()+"平米起租"; //'最小分割'
        if(houseEntity.getMinAcreage().equals("0")){
            name6 = "不可分割";
        }else if(houseEntity.getMinAcreage().equals(houseEntity.getLayerArea().toString())){
            name6 = "整租";
        }
        String name7 = houseEntity.getMaxElectric()+"kva供电"; //'最小分割'

        String name8 = "";
        if(houseEntity.getHasWholeRental()==true){
            name8=" "+"整栋出租";
        }
        nameTotalSplit = name1+name2+" "+name3+" "+name4+" "+name5+" "+name6+" "+name7+name8;
        houseEntity.setHouseName(nameTotalSplit);
        houseEntity.setHouseStatus(HouseStatus.hotRenting);

        houseEntity.setEmpCode(CurrentContext.getUserCode());
        houseEntity.setEmpName(CurrentContext.getUserInfo().getUserName());
        houseEntity.setEmpImg(CurrentContext.getUserInfo().getUserImg());
        houseEntity.setDeptCode(CurrentContext.getUserInfo().getOwnerDeptCode());
        houseEntity.setEmpPhone(CurrentContext.getUserInfo().getUserPhone());
        houseEntity.setHasCut(false);
        BigDecimal MAcreage=new BigDecimal(houseEntity.getMinAcreage());
        //compareTo方法，结果是:   -1：小于；   0 ：等于；   1 ：大于；
         int ctResult = MAcreage.compareTo(houseEntity.getArea());
         if(ctResult==-1){
             houseEntity.setHasCut(true);
         }
        houseEntity.setHasShortLease(false);
         if(houseEntity.getLessLease()<30){houseEntity.setHasShortLease(true);}
        houseEntity.setHasBathroom(true);
        houseEntity.setOpenFlag(true);
        houseEntity.setPropertyFee(new BigDecimal("0"));
        houseEntity.setForInsdustry("无说明");
        houseEntity.setFeatures("房源特色后续拼接----");

        houseEntity.setHasCollect(false);
        houseEntity.setLastFollowupTime(new Date());
        /**
         * 共享部门详情是否可见 暂定为可见，后续版本自定义
         */
        houseEntity.setShareOpenFlag(true);
        houseEntity.setCooOpenFlag(true);


        //房源特色拼接
        String featureTotalSplit = "";
        String feature1Build ="非独栋";
        if(houseEntity.getHasAloneBuilding()){ feature1Build ="独栋";}
        String feature2 = " "+"多层";
        if(houseEntity.getHasMonolayer()){ feature2 =" "+"单层";}
        String feature3 = ""; //'产证齐全'
        if(houseEntity.getHasDischargeSewage()){feature3 =" "+"产证齐全";}
        String feature4 = ""; //'可环评'
        if(houseEntity.getHasEia()){feature4 =" "+"可环评";}
        String feature5 = ""; //'产证齐全'
        if(houseEntity.getHasRegistry()){feature5 =" "+"可注册";}
        String feature6 = ""; //'方便停车'
        if(houseEntity.getHasParking()){feature6 =" "+"方便停车";}
        String feature7 = ""; //'有行车'
        if(houseEntity.getHasInstallCrane()){feature7 =" "+"有行车";}
        String feature8 = ""; //'可环评'
        if(houseEntity.getHasPlatform()){feature8 =" "+"有卸货平台";}
        String feature9 = ""; //'办公配套'
        if(houseEntity.getHasOfficeArea()){feature9 =" "+"办公配套全";}

        featureTotalSplit = feature1Build+feature2+feature3+feature4+feature5+feature6+feature7+feature8+feature9;
        houseEntity.setFeatures(featureTotalSplit);
        houseEntity.setHasCertificate(houseEntity.getHasDischargeSewage());
        //默认字段拼接end
        //int icount = 666;
        int icount = this.createHouse(houseEntity);

        if (icount > 0 && houseEntity.getHouseFrom().equals("官网预约") && houseEntity.getBespeakId() != null) {
            BespeakApiClient.updateProcessed(houseEntity.getBespeakId());
        } else if (icount > 0 && houseEntity.getHouseFrom().equals("官网预约") && houseEntity.getEntrustId() != null) {
            EntrustApiClient.updateProcessed(houseEntity.getEntrustId());
        } else if (icount > 0 && houseEntity.getHouseFrom().equals("兼职推荐") && houseEntity.getRecommendId() != null) {
            RecommendApiClient.updateProcessed(houseEntity.getRecommendId(),houseEntity.getHouseCode());
        }
        return houseEntity;
    }

    @Override
    public List<HouseEntity> export(Map<String,Object> param) {

        UserEntity userEntity=userManager.findUserByUserCode(CurrentContext.getUserCode());
        List<String> deptCodes=deptManager.getDownDeptCode(userEntity.getOwnerDeptCode());
        deptCodes.add(userEntity.getOwnerDeptCode());
        param.put("deptCodes",deptCodes);
        Date startTime = (Date) param.get("startTime");
        Date endTime = (Date) param.get("endTime");
        List<HouseEntity> result= houseDbDao.export(param);
        if (null!=result&&result.size()>10000){
            throw new RemoveStackBusinessException("您要导出的房源数量过多，请缩小查询范围再进行导出");
        }
        if (result != null) {
            HouseExportLogEntity houseExportLogEntity=new HouseExportLogEntity();
            houseExportLogEntity.setUserCode(CurrentContext.getUserCode());
            houseExportLogEntity.setDeptCodes(deptCodes.toString());
            houseExportLogEntity.setStartTime(startTime);
            houseExportLogEntity.setEndTime(endTime);
            houseExportLogEntity.setCreateCode(CurrentContext.getUserCode());
            houseExportLogEntity.setCreateTime(new Date());
            houseExportLogEntity.setResultNumber(result.size());
            houseExportLogEntity.setRemark(param.toString());
            houseExportLogDbDao.insert(houseExportLogEntity);

        }

        return result;
    }

    private  int createHouse(HouseEntity house){
        int count =houseDbDao.insert(house);
        HouseEntity houseEntity = houseDbDao.selectUniqueByProp("houseCode",house.getHouseCode());
        String id = houseEntity.getId().toString();
        int lenth = id.length();
        String houseNumber="";
        if (lenth<8){
            long code = 100000000 + houseEntity.getId();
            houseNumber =String.valueOf(code).substring(1);

        }else {
            houseNumber =String.valueOf(houseEntity.getId());

        }
        if (StringUtils.isEmpty(houseNumber)){
            throw new RemoveStackBusinessException("发生未知错误，房源创建失败");
        }
        String preSql = "update  t_hkp_hos_house set house_number=\'%s\'" +
                " WHERE house_code=\'%s\' and id =\'%s\' ";
        String sql = String.format(preSql,houseNumber,houseEntity.getHouseCode(),houseEntity.getId());
        houseDbDao.updateByNativeSql(sql);
        fileManager.createFolderAndUpdateFile(house.getHouseName(),houseNumber,house.getFileList());
        return count;
    }

    private void checkPriceForUnit(String unit,BigDecimal price){
        if (null==price){
            return;
        }
        if (unit.equals("元/㎡/天")){
            if (price.compareTo(BigDecimal.valueOf(0.1))==-1){
                throw new RemoveStackBusinessException("价格过低，不在合理范围内，为了匹配精确度请输入准确价格");
            }
            if (price.compareTo(BigDecimal.valueOf(6))==1){
                throw new RemoveStackBusinessException("价格过高，不在合理范围内，为了匹配精确度请输入准确价格");
            }
        }else if (unit.equals("元/㎡/月")){
            if (price.compareTo(BigDecimal.valueOf(0.3))==-1){
                throw new RemoveStackBusinessException("价格过低，不在合理范围内，为了匹配精确度请输入准确价格");
            }
            if (price.compareTo(BigDecimal.valueOf(180))==1){
                throw new RemoveStackBusinessException("价格过高，不在合理范围内，为了匹配精确度请输入准确价格");
            }
        }else if (unit.equals("元/㎡/年")){
            if (price.compareTo(BigDecimal.valueOf(36.5))==-1){
                throw new RemoveStackBusinessException("价格过低，不在合理范围内，为了匹配精确度请输入准确价格");
            }
            if (price.compareTo(BigDecimal.valueOf(2190))==1){
                throw new RemoveStackBusinessException("价格过高，不在合理范围内，为了匹配精确度请输入准确价格");
            }
        }else {
            throw new RemoveStackBusinessException("未知价格单位");
        }
    }

}
