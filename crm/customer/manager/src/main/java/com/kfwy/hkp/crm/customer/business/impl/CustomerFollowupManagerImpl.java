package com.kfwy.hkp.crm.customer.business.impl;

import cn.hutool.core.map.MapUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.common.sms.SmsEntity;
import com.kfwy.hkp.common.sms.send.ISmsTemplate;
import com.kfwy.hkp.common.utils.ListUtils;
import com.kfwy.hkp.crm.customer.business.ICustomerFollowupManager;
import com.kfwy.hkp.crm.customer.business.ICustomerHouseManager;
import com.kfwy.hkp.crm.customer.business.ICustomerManager;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerFollowupDbDao;
import com.kfwy.hkp.crm.customer.dao.IFollowupHouseDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerFollowupEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerHouseEntity;
import com.kfwy.hkp.crm.customer.entity.FollowupHouseEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerFollowupFileUse;
import com.kfwy.hkp.crm.customer.enums.CustomerFollowupType;
import com.kfwy.hkp.crm.houseowner.dao.IHouseownerDbDao;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerEntity;
import com.kfwy.hkp.hos.house.dao.IHouseDbDao;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.log.manager.IOperationManager;
import com.kfwy.hkp.sys.file.business.IFileManager;
import com.kfwy.hkp.sys.file.business.IFileRelationManager;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserLevel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/8/23
 */
@Service
@Transactional
public class CustomerFollowupManagerImpl extends AbstractManager<CustomerFollowupEntity> implements ICustomerFollowupManager {

    @Autowired
    private ICustomerFollowupDbDao customerFollowupDbDao;
    @Autowired
    private IFileRelationManager fileRelationManager;
    @Autowired
    private IFollowupHouseDbDao followupHouseDbDao;
    @Autowired
    private ICustomerDbDao customerDbDao;
    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private ICustomerManager customerManager;
    @Autowired
    private ISmsTemplate smsTemplate;
    @Autowired
    private IHouseownerDbDao houseownerDbDao;
    @Autowired
    private ICustomerHouseManager customerHouseManager;
    @Autowired
    private IFileManager fileManager;
    @Autowired
    private IHouseDbDao houseDbDao;
    @Autowired
    private IOperationManager operationManager;

    @Override
    protected IMyBatisBaseDao<CustomerFollowupEntity, Long> getMyBatisDao () {
        return this.customerFollowupDbDao;
    }

    @Override
    public void afterCreate (CustomerFollowupEntity customerFollowupEntity) {
        //更新关联客户电话拨打的处理状态
        Map map = MapUtil.newHashMap ();
        map.put ("userCode", CurrentContext.getUserCode ());
        map.put ("cusCode", customerFollowupEntity.getCusCode ());
        operationManager.updateCallPhoneHandle (map);
    }

    @Override
    public int create (CustomerFollowupEntity customerFollowupEntity) {
        CustomerEntity customer = customerManager.findOne ("cusCode", customerFollowupEntity.getCusCode ());

        OperateFillUtils.fill (customerFollowupEntity);
        customerFollowupEntity.setFollowupCode (CodeGenUtils.generate ());
        customerFollowupEntity.setEmpCode (CurrentContext.getUserCode ());
        //客户带看信息处理
        fillSeeHouseInfo (customerFollowupEntity, customer);
        // 设置经理意向客户
        setIntendedCus (customerFollowupEntity, customer.getCusCode ());

        int result = customerFollowupDbDao.insert (customerFollowupEntity);

        //修改客户的最后跟进时间
        customer.setCusCode (customerFollowupEntity.getCusCode ());
        customer.setLastFollowupTime (customerFollowupEntity.getCreateTime ());
        customerDbDao.updateLastFollowupTime (customer);
        //修改强制跟进记录
        afterCreate (customerFollowupEntity);
        return result;
    }

    private void fillSeeHouseInfo (CustomerFollowupEntity customerFollowupEntity, CustomerEntity customer) {
        if (CustomerFollowupType.SeeHouse.equals (customerFollowupEntity.getFollowupType ())) {

            String currentUserName = setCurrentUserName ();

            // 保存关联的房源
            if (ListUtils.isNotEmpty (customerFollowupEntity.getFollowupHouseList ())) {

                for (FollowupHouseEntity followupHouse : customerFollowupEntity.getFollowupHouseList ()) {
                    OperateFillUtils.fill (followupHouse);
                    followupHouse.setFollowupCode (customerFollowupEntity.getFollowupCode ());
                    followupHouse.setFollowupHouseCode (CodeGenUtils.generate ());
                    followupHouseDbDao.insert (followupHouse);

                    // 保存关联的文件
                    saveFolloupImg (followupHouse);

                    // 更改合作状态
                    changeCooStatus (customerFollowupEntity, followupHouse);

                    //给业主发送短信
                    if (chechCusFollowupCreateSend ()) {
                        sendFollowupToMsgToHouseOwner (followupHouse.getHouseCode (), currentUserName);
                    }
                }
            }

            //环评广告
            if (chechCusFollowupCreateSend ()) {
                smsTemplate.asyncSend (customer.getCusPhone (), SmsEntity.customerFollowupEia, null);
            }
            //给客户发
            if (chechCusFollowupCreateSend ()) {
                smsTemplate.asyncSend (customer.getCusPhone (), SmsEntity.followupCustomerTemplate, new String[]{currentUserName, "400-820-2058"});
            }

        }
    }

    public Boolean chechCusFollowupCreateSend () {
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
        if (cur.getUserLevel ().equals (UserLevel.K0)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 核对熊万顺逻辑 查询是否被带看过,是的话再查询是否领导添加的跟进,
     * 符合全部条件即为经理意向客户.
     */
    private void setIntendedCus (CustomerFollowupEntity cf, String cusCode) {
        Map<String, Object> map = new HashMap<> ();
        map.put ("cusCode", cusCode);
        map.put ("followupType", CustomerFollowupType.SeeHouse);
        map.put ("isDeleted", false);
        int i = customerFollowupDbDao.countByMap (map);
        if (i > 0) {
            // 是否经理
            map.put ("isDeleted", false);
            map.put ("leaderCode", CurrentContext.getUserCode ());
            PageResult<DeptEntity> byMap = deptManager.findByMap (map, 0, 1);
            if (byMap.getTotalCount () != 0) {
                cf.setManagerFollowup (true);
            }
        }
    }

    public String setCurrentUserName () {
        String currentUserName = "";
        if (! ObjectUtils.isEmpty (CurrentContext.getUserInfo ()) && StringUtils.isNotEmpty (CurrentContext.getUserInfo ().getUserName ())) {
            currentUserName = CurrentContext.getUserInfo ().getUserName ();
        }
        return currentUserName;
    }

    public void saveFolloupImg (FollowupHouseEntity followupHouse) {
        if (! ListUtils.isEmpty (followupHouse.getFileList ())) {
            for (FileRelationEntity fileRelationEntity : followupHouse.getFileList ()) {
                fileRelationEntity.setFileUse (CustomerFollowupFileUse.CREDENTIALS);
                fileRelationEntity.setOwnerCode (followupHouse.getFollowupHouseCode ());
                fileRelationManager.create (fileRelationEntity);
            }
        }
        String preSql = "select * from  t_hkp_hos_house WHERE house_code=\'%s\'";
        String sql = String.format (preSql, followupHouse.getHouseCode ());
        HouseEntity house = houseDbDao.selectOneByNativeSql (sql);
        fileManager.createFolderAndUpdateFile (house.getHouseName (), house.getHouseNumber (), followupHouse.getFileList ());
    }

    public void changeCooStatus (CustomerFollowupEntity customerFollowupEntity, FollowupHouseEntity followupHouse) {
        Map<String, Object> param = new HashMap<> ();
        param.put ("cooStatus", "4");
        param.put ("houseCode", followupHouse.getHouseCode ());
        param.put ("cusCode", customerFollowupEntity.getCusCode ());
        customerFollowupDbDao.updateCoo (param);
    }

    private void sendFollowupToMsgToHouseOwner (String houseCode, String currentUserName) {

        String preSql = "select o.* from t_hkp_hos_house h " + "left join t_hkp_hos_houseowner o " + "on o.houseowner_code = h.owner_code " + "where h.house_code ='%s'";

        HouseownerEntity ho = houseownerDbDao.selectOneByNativeSql (String.format (preSql, houseCode));

        if (! ho.getHouseownerPhone ().equals ("13373648470") && ! ho.getHouseownerPhone ().equals ("13812688784") && ! ho.getHouseownerPhone ().equals ("18556846270")) {
            smsTemplate.asyncSend (ho.getHouseownerPhone (), SmsEntity.followupOwnerTemplate, new String[]{currentUserName, "400-820-2058"});
        }
    }

    @Override
    public PageResult<CustomerFollowupEntity> findByMap (Map<String, Object> param, int start, int pageSize) {
        param.put ("isDelete", Boolean.FALSE);
        String orderBy = "create_time";
        return customerFollowupDbDao.selectByMap (param, start, pageSize, orderBy, Boolean.FALSE);
    }

    @Override
    public CustomerFollowupEntity findOne (Map<String, Object> param) {
        param.put ("isDelete", Boolean.FALSE);

        CustomerFollowupEntity followupEntity = null;
        if (CustomerFollowupType.SeeHouse.equals (param.get ("followupType"))) {
            // 查询房源
            followupEntity = customerFollowupDbDao.detailSee (param);
            // 查询文件
            param.clear ();
            for (int i = 0; i < followupEntity.getFollowupHouseList ().size (); i++) {
                if (StringUtils.isNotEmpty (followupEntity.getFollowupHouseList ().get (i).getFollowupHouseCode ())) {
                    param.put ("ownerCode", followupEntity.getFollowupHouseList ().get (i).getFollowupHouseCode ());
                    List<FileRelationEntity> fileList = fileRelationManager.findByMap (param);
                    followupEntity.getFollowupHouseList ().get (i).setFileList (fileList);
                }
            }
        } else {
            followupEntity = customerFollowupDbDao.selectUniqueByMap (param);
        }
        return followupEntity;

    }

    @Override
    public PageResult<CustomerFollowupEntity> selectByMap (Map<String, Object> param, Integer start, Integer pageSize) {
        PageResult<CustomerFollowupEntity> byMap = this.findByMap (param, start, pageSize, "create_time", false);
        if (null != byMap.getData ()) {
            for (int i = 0; i < byMap.getData ().size (); i++) {
                List<CustomerHouseEntity> customerHouseList = customerHouseManager.getCustomerHouseEntity (byMap.getData ().get (i).getFollowupCode ());
                StringBuilder jointRemark = new StringBuilder ();
                if (byMap.getData ().get (i).getFollowupContent () != null && byMap.getData ().get (i).getFollowupType ().equals (CustomerFollowupType.SeeHouse)) {
                    jointRemark.append ("我说:" + byMap.getData ().get (i).getFollowupContent () + ";");
                }
                for (int j = 0; j < customerHouseList.size (); j++) {
                    jointRemark.append (("客户说房源" + (j + 1) + ":" + customerHouseList.get (j).getRemark () + ";"));
                }
                if (StringUtils.isBlank (jointRemark.toString ())) {
                    byMap.getData ().get (i).setJointRemark (byMap.getData ().get (i).getFollowupContent ());
                } else {
                    byMap.getData ().get (i).setJointRemark (jointRemark.toString ());
                }

            }
        }
        return byMap;
    }

    @Override
    public PageResult<FollowupHouseEntity> selectCusSeeHouse (Map param, Integer start, Integer pageSize) {

        return followupHouseDbDao.selectCusSeeHouse (param, start, pageSize);

    }

    @Override
    public PageResult<CustomerFollowupEntity> takeLookByCusEvaluate (Map param, Integer start, Integer pageSize) {
        return customerFollowupDbDao.takeLookByCusEvaluate (param, start, pageSize);
    }
}
