package com.kfwy.hkp.crm.customer.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.user.entity.BaseUserEntity;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.crm.customer.business.ICustomerManager;
import com.kfwy.hkp.crm.customer.business.ISelectAddressManager;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.dao.ISelectAddressDbDao;
import com.kfwy.hkp.crm.customer.dao.ISelectHouseDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.entity.SelectAddressEntity;
import com.kfwy.hkp.crm.customer.entity.SelectHouseEntity;
import com.kfwy.hkp.hos.house.dao.IHouseDbDao;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hos.house.enums.HouseFileUse;
import com.kfwy.hkp.sys.file.dao.IFileRelationDbDao;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;
import com.kfwy.hkp.sys.user.business.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by zjp on 2018/7/16.
 */
@Service
public class SelectAddressManagerImpl extends AbstractManager<SelectAddressEntity> implements ISelectAddressManager {

    @Autowired
    private ISelectAddressDbDao selectAddressDbDao;

    @Autowired
    private ISelectHouseDbDao selectHouseDbDao;

    @Autowired
    private IHouseDbDao houseDbDao;

    @Autowired
    private ICustomerDbDao customerDbDao;

    @Autowired
    private ICustomerManager customerManager;
    @Autowired
    private IUserManager userManager;
    @Autowired
    private IFileRelationDbDao fileRelationDbDao;


    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.selectAddressDbDao;
    }


    @Override
    public SelectAddressEntity createAddress(SelectAddressEntity saEntity) {
        //CustomerEntity customer = customerManager.findOne("cusCode", saEntity.getCusCode());

        String genCode = CodeGenUtils.generate();
        //加入生成编码
        saEntity.setSelectAddressCode(genCode);
        //加入创建人修改人
        OperateFillUtils.fill(saEntity);

        saEntity.setAddressUrl("http://m.kufangwuyou.com/select/" + genCode + ".html");

        //加入所属人的基本信息电话
        BaseUserEntity baseUser = CurrentContext.getUserInfo();
        if (baseUser != null) {
            saEntity.setOwnerUserPhone(baseUser.getUserPhone());
            saEntity.setOwnerCode(baseUser.getUserCode());
            saEntity.setOwnerName(baseUser.getUserName());
            saEntity.setCreateDeptCode(baseUser.getOwnerDeptCode());
            saEntity.setUserImg(baseUser.getUserImg());
        }

        //新增选址房源子表
        List<SelectHouseEntity> selectHouseList = new ArrayList<SelectHouseEntity>();
        selectHouseList = saEntity.getSelectHouseList();
        if (selectHouseList.isEmpty()) {
            throw new RemoveStackBusinessException ("请选择房源.(当前房源为空)" + selectHouseList.size());
        }
        selectAddressDbDao.insert(saEntity);
//        CustomerEntity customerEntity = new CustomerEntity();
//        customerEntity.setCusCode(saEntity.getCusCode());
//        customerEntity.setUrl(saEntity.getAddressUrl());
//        customerDbDao.update(customerEntity);
        List<SelectHouseEntity> shListNoDupAndSort = removeDuplicateUser(selectHouseList);
        for (SelectHouseEntity oneEntity : shListNoDupAndSort) {
            oneEntity.setSelectAddressCode(genCode);
            //加入生成编码
            oneEntity.setSelectHouseCode(CodeGenUtils.generate());
            String houseCode = oneEntity.getHouseCode();
            //加入创建人修改人
            OperateFillUtils.fill(oneEntity);
            oneEntity.setHouseUrl("http://m.kufangwuyou.com/detail/" + houseCode + ".html");
        }

        selectHouseDbDao.batchInsert(shListNoDupAndSort);

        saEntity.setSelectHouseList(shListNoDupAndSort);
        return saEntity;
    }


    @Override
    public SelectAddressEntity createNewAddress(SelectAddressEntity saEntity) {
        String genCode = CodeGenUtils.generate();
        //加入生成编码
        saEntity.setSelectAddressCode(genCode);
        //加入创建人修改人
        OperateFillUtils.fill(saEntity);

        saEntity.setAddressUrl("http://m.kufangwuyou.com/selectNew/" + genCode + ".html");

        //加入所属人的基本信息电话
        BaseUserEntity baseUser = CurrentContext.getUserInfo();
        if (baseUser != null) {
            saEntity.setOwnerUserPhone(baseUser.getUserPhone());
            saEntity.setUserImg(baseUser.getUserImg());
        }

        //新增选址房源子表
        List<SelectHouseEntity> selectHouseList = new ArrayList<SelectHouseEntity>();
        selectHouseList = saEntity.getSelectHouseList();
        if (selectHouseList.isEmpty()) {
            throw new RemoveStackBusinessException("请选择房源.(当前房源为空)" + selectHouseList.size());
        }
        selectAddressDbDao.insert(saEntity);
        List<SelectHouseEntity> shListNoDupAndSort = removeDuplicateUser(selectHouseList);
        for (SelectHouseEntity oneEntity : shListNoDupAndSort) {
            oneEntity.setSelectAddressCode(genCode);
            //加入生成编码
            oneEntity.setSelectHouseCode(CodeGenUtils.generate());
            String houseCode = oneEntity.getHouseCode();
            //加入创建人修改人
            OperateFillUtils.fill(oneEntity);
            oneEntity.setHouseUrl("http://m.kufangwuyou.com/detail/" + houseCode + ".html");
        }

        selectHouseDbDao.batchInsert(shListNoDupAndSort);

        saEntity.setSelectHouseList(shListNoDupAndSort);
        return saEntity;
    }

    @Override
    public SelectAddressEntity selectHos(SelectAddressEntity entity) {
        String selectAddrCode = entity.getSelectAddressCode();
        SelectAddressEntity saddrEntity = new SelectAddressEntity();
        saddrEntity = selectAddressDbDao.selectUniqueByProp("selectAddressCode", selectAddrCode);
        List<SelectHouseEntity> shosList = new ArrayList<SelectHouseEntity>();

        Map<String, Object> queryparam = new HashMap<String, Object>();
        queryparam.put("selectAddressCode", selectAddrCode);
        shosList = selectHouseDbDao.selectByMap(queryparam);
        queryparam.clear();
        this.getHouseEntity(shosList);
        saddrEntity.setSelectHouseList(shosList);
        return saddrEntity;
    }

    private void getHouseEntity(List<SelectHouseEntity> shosList) {
        Map<String, Object> param = new HashMap<>();
        List<String> fileUses = new ArrayList<>();
        fileUses.add(HouseFileUse.HouseCover);
        fileUses.add(HouseFileUse.HouseInner);
        param.put("fileUses", fileUses);
        for (SelectHouseEntity one : shosList) {
            // 查询房源
            HouseEntity houseEntity = houseDbDao.selectUniqueByProp("houseCode", one.getHouseCode());
            if (houseEntity != null) {
                // 房源图片
                param.put("ownerCode", houseEntity.getHouseCode());
                List<FileRelationEntity> files = fileRelationDbDao.selectByMap(param);
                one.setFileList(files);
                // 房源基本信息
                one.setPrice(houseEntity.getPrice());
                one.setUnit(houseEntity.getUnit());
                one.setArea(houseEntity.getArea());
                one.setCityName(houseEntity.getCityName());
                one.setProvinceName(houseEntity.getProvinceName());
                one.setRegionName(houseEntity.getRegionName());
                one.setStreetName(houseEntity.getStreetName());
                one.setDetailAddress(houseEntity.getHouseLocation().getDetailAddress());
                one.setDoorNumber(houseEntity.getHouseLocation().getDoorNumber());

                one.setFeatures(houseEntity.getFeatures());
                one.setHasEia(houseEntity.getHasEia());
                one.setHasParking(houseEntity.getHasParking());
                one.setHasInstallCrane(houseEntity.getHasInstallCrane());
                one.setHasOfficeArea(houseEntity.getHasOfficeArea());
                one.setHasBathroom(houseEntity.getHasBathroom());
                one.setHasPlatform(houseEntity.getHasPlatform());
                one.setHasElevator(houseEntity.getHasElevator());
                one.setHasCut(houseEntity.getHasCut());
                one.setHasDischargeSewage(houseEntity.getHasDischargeSewage());
                one.setHasCertificate(houseEntity.getHasCertificate());
                one.setHasRegistry(houseEntity.getHasRegistry());
            }
        }
    }

    @Override
    public SelectAddressEntity mbwebquery(SelectAddressEntity entity) {
        String selectAddrCode = entity.getSelectAddressCode();
        SelectAddressEntity saddrEntity = new SelectAddressEntity();
        saddrEntity = selectAddressDbDao.selectUniqueByProp("selectAddressCode", selectAddrCode);
        List<SelectHouseEntity> shosList = new ArrayList<SelectHouseEntity>();

        Map<String, Object> queryparam = new HashMap<String, Object>();
        queryparam.put("selectAddressCode", selectAddrCode);
        shosList = selectHouseDbDao.selectByMap(queryparam);

        for (SelectHouseEntity one : shosList) {
            // 查询房源
            HouseEntity houseEntity = houseDbDao.selectUniqueByProp("houseCode", one.getHouseCode());
            if (houseEntity != null) {
                one.setPrice(houseEntity.getPrice());
                one.setUnit(houseEntity.getUnit());
                one.setArea(houseEntity.getArea());
                one.setCityName(houseEntity.getCityName());
                one.setProvinceName(houseEntity.getProvinceName());
                one.setRegionName(houseEntity.getRegionName());
                one.setStreetName(houseEntity.getStreetName());
                one.setDetailAddress(houseEntity.getHouseLocation().getDetailAddress());
                one.setDoorNumber(houseEntity.getHouseLocation().getDoorNumber());
            }

        }

        saddrEntity.setSelectHouseList(shosList);

        return saddrEntity;
    }

    @Override
    public List<SelectAddressEntity> query(String cusCode) {
        return this.selectAddressDbDao.query(cusCode);
    }

    //自身list对象去重
    private static ArrayList<SelectHouseEntity> removeDuplicateUser(List<SelectHouseEntity> shList) {
        Set<SelectHouseEntity> set = new TreeSet<SelectHouseEntity>(new Comparator<SelectHouseEntity>() {
            @Override
            public int compare(SelectHouseEntity o1, SelectHouseEntity o2) {
                //字符串,则按照asicc码升序排列
                return o1.getHouseCode().compareTo(o2.getHouseCode());
            }
        });
        set.addAll(shList);
        ArrayList<SelectHouseEntity> resultList = new ArrayList<SelectHouseEntity>(set);
        return resultList;
    }


}
