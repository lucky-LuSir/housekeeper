package com.kfwy.hkp.sys.area.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.hkp.sys.area.business.IBaseAreaManager;
import com.kfwy.hkp.sys.area.business.ICompanyAreaManager;
import com.kfwy.hkp.sys.area.dao.ICompanyAreaDbDao;
import com.kfwy.hkp.sys.area.dictionary.AreaStatus;
import com.kfwy.hkp.sys.area.entity.AreaAllEntity;
import com.kfwy.hkp.sys.area.entity.BaseAreaEntity;
import com.kfwy.hkp.sys.area.entity.CompanyAreaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/7/3 09:32
 */
@Service
public class CompanyAreaManagerImpl extends AbstractManager<CompanyAreaEntity> implements ICompanyAreaManager {


    @Autowired
    private ICompanyAreaDbDao companyAreaDbDao;

    @Autowired
    private IBaseAreaManager baseAreaManager;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.companyAreaDbDao;
    }

    @Override
    public CompanyAreaEntity findAreaByCode(String areaCode) {
        return companyAreaDbDao.selectUniqueByProp("areaCode",areaCode);
    }

    /**
     * 激活指定省市区街道社区
     * @param enableFlag 开启级别
     * @param provinceObj 省对象
     * @param cityObj 市对象
     * @param regionObj 区域对象
     * @param readyInsertList 准备新增的集合
     */
    @Override
    public void enableArea(int enableFlag, BaseAreaEntity provinceObj, BaseAreaEntity cityObj, BaseAreaEntity regionObj, List<BaseAreaEntity> readyInsertList) {
        switch (enableFlag){
            //1(激活省及下地区)
            case 1:
                enableFlagIsOne(provinceObj, readyInsertList);
                break;
            //2(激活市及下地区)
            case 2:
                enableFlagIsTwo(provinceObj, cityObj, readyInsertList);
                break;
            //3(激活县及下地区)
            case 3:
                enableFlagIsThree(provinceObj, cityObj, regionObj, readyInsertList);
                break;
            //4(其他情况)
            default:
                throw new BusinessException("启用等级enableFlag为:" + enableFlag + ",不符合激活等级,激活等级为(1,2,3)");
        }
    }
    @Override
    @Transactional
    public void enableFlagIsOne(BaseAreaEntity provinceObj, List<BaseAreaEntity> readyInsertList) {
        readyInsertList.clear();

        //A查出省及管辖地区
        BaseAreaEntity provOne = baseAreaManager.findAreaByCode(provinceObj.getAreaCode());
        readyInsertList.add(provOne);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("parentCode", provinceObj.getAreaCode());
        List<BaseAreaEntity> cityAllList = baseAreaManager.findByMap(param, "sort", true);
        readyInsertList.addAll(cityAllList);
        getRegionAndStreetAndCommunityAllList(readyInsertList, param, cityAllList);
        //B新增到cpyArea表中
        //B.1判断cpyArea表中是否已经存在
        for (int i = 0; i < readyInsertList.size(); i++) {
            CompanyAreaEntity selectOne = this.findAreaByCode(readyInsertList.get(i).getAreaCode());
            if (selectOne == null) {
                CompanyAreaEntity cpyAreaEntity = baseArea2CpyArea(readyInsertList.get(i));
                this.create(cpyAreaEntity);
            } else {
                //已存在,不做新增操作,继续下次循环
                System.out.println("已存在,不做新增操作,继续下次循环,BaseAreaService" + i);
            }
        }
        //B.1新增结束
    }

    public void getRegionAndStreetAndCommunityAllList(List<BaseAreaEntity> readyInsertList, Map<String, Object> param, List<BaseAreaEntity> cityAllList) {
        for (int i = 0; i < cityAllList.size(); i++) {
            param.clear();
            param.put("parentCode", cityAllList.get(i).getAreaCode());
            List<BaseAreaEntity> regionAllList = baseAreaManager.findByMap(param, "sort", true);
            readyInsertList.addAll(regionAllList);
            getStreetAndCommunityAllList(readyInsertList, param, regionAllList);
        }
    }

    @Override
    @Transactional
    public void enableFlagIsTwo(BaseAreaEntity provinceObj, BaseAreaEntity cityObj, List<BaseAreaEntity> readyInsertList) {
        readyInsertList.clear();

        //A查出省及管辖地区
        BaseAreaEntity provOne = baseAreaManager.findAreaByCode(provinceObj.getAreaCode());
        readyInsertList.add(provOne);
        BaseAreaEntity cityOne = baseAreaManager.findAreaByCode(cityObj.getAreaCode());
        readyInsertList.add(cityOne);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("parentCode", cityObj.getAreaCode());
        List<BaseAreaEntity> regionAllList = baseAreaManager.findByMap(param, "sort", true);
        readyInsertList.addAll(regionAllList);
        getStreetAndCommunityAllList(readyInsertList, param, regionAllList);

        //B新增到cpyArea表中
        //B.1判断cpyArea表中是否已经存在
        createCompanyArea(readyInsertList);
        //B.1新增结束
    }

    public void getStreetAndCommunityAllList(List<BaseAreaEntity> readyInsertList, Map<String, Object> param, List<BaseAreaEntity> regionAllList) {
        for (int j = 0; j < regionAllList.size(); j++) {
            param.clear();
            String regionAreaCode = regionAllList.get(j).getAreaCode();
            param.put("parentCode", regionAreaCode);
            List<BaseAreaEntity> streetAllList = baseAreaManager.findByMap(param, "sort", true);
            readyInsertList.addAll(streetAllList);
            getCommunityAllList(readyInsertList, param, streetAllList);
        }
    }

    @Override
    @Transactional
    public void enableFlagIsThree(BaseAreaEntity provinceObj, BaseAreaEntity cityObj, BaseAreaEntity regionObj, List<BaseAreaEntity> readyInsertList) {
        readyInsertList.clear();

        //A查出省及管辖地区
        BaseAreaEntity provOne = baseAreaManager.findAreaByCode(provinceObj.getAreaCode());
        readyInsertList.add(provOne);
        BaseAreaEntity cityOne = baseAreaManager.findAreaByCode(cityObj.getAreaCode());
        readyInsertList.add(cityOne);
        BaseAreaEntity regionOne = baseAreaManager.findAreaByCode(regionObj.getAreaCode());
        readyInsertList.add(regionOne);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("parentCode", regionObj.getAreaCode());
        List<BaseAreaEntity> streetAllList = baseAreaManager.findByMap(param, "sort", true);
        readyInsertList.addAll(streetAllList);
        getCommunityAllList(readyInsertList, param, streetAllList);
        //B新增到cpyArea表中
        //B.1判断cpyArea表中是否已经存在
        createCompanyArea(readyInsertList);
        //B.1新增结束
    }

    public void getCommunityAllList(List<BaseAreaEntity> readyInsertList, Map<String, Object> param, List<BaseAreaEntity> streetAllList) {
        for (int z = 0; z < streetAllList.size(); z++) {
            param.clear();
            param.put("parentCode", streetAllList.get(z).getAreaCode());
            List<BaseAreaEntity> communityAllList = baseAreaManager.findByMap(param, "sort", true);
            readyInsertList.addAll(communityAllList);
        }
    }

    @Override
    public void createCompanyArea(List<BaseAreaEntity> readyInsertList) {
        for (int i = 0; i < readyInsertList.size(); i++) {
            CompanyAreaEntity selectOne = this.findAreaByCode(readyInsertList.get(i).getAreaCode());
            if (selectOne == null) {
                BaseAreaEntity baseArea = readyInsertList.get(i);
                CompanyAreaEntity cpyAreaEntity = baseArea2CpyArea(baseArea);
                this.create(cpyAreaEntity);
            } else {
                //已存在,不做新增操作,继续下次循环
                System.out.println("已存在,不做新增操作,继续下次循环,BaseAreaService" + i);
            }
        }
    }
    @Override
    public CompanyAreaEntity baseArea2CpyArea(BaseAreaEntity baseArea) {
        CompanyAreaEntity cpyAreaObj = new CompanyAreaEntity();
        cpyAreaObj.setAreaCode(baseArea.getAreaCode());
        cpyAreaObj.setParentCode(baseArea.getParentCode());
        cpyAreaObj.setName(baseArea.getName());
        cpyAreaObj.setShortName(baseArea.getShortName());
        cpyAreaObj.setLevel(baseArea.getLevel());
        cpyAreaObj.setSort(baseArea.getSort());
        cpyAreaObj.setStatus(baseArea.getStatus());
        cpyAreaObj.setLongitude(baseArea.getLongitude());
        cpyAreaObj.setLatitude(baseArea.getLatitude());
        //基本数据
        cpyAreaObj.setCreateTime(new Date());
        cpyAreaObj.setLastUpdateTime(new Date());
        cpyAreaObj.setIsDeleted(false);
        return cpyAreaObj;
    }

    @Cacheable(value = "redisCache",key = "'ky:hkp:areaCache'")
    public List<AreaAllEntity> getCascaderArea() {
        List<AreaAllEntity> areaList = getAreaAllEntity("0");
        getChildArea(areaList);
        return areaList;
    }

    private void getChildArea(List<AreaAllEntity> areaList) {
        if (areaList!=null && areaList.size()>0){
            for(AreaAllEntity areaAllEntity2 : areaList){
                List<AreaAllEntity> areaList3 = getAreaAllEntity(getAreaCode(areaAllEntity2.getAreaCode()));
                if (areaList3!=null && areaList3.size()>0){
                    areaAllEntity2.setCities(areaList3);
                    getChildArea(areaList3);
                }
            }
        }
    }

    /**
     * 主要是根据parentCode获取子节点  eg:根据省areaCode获取其省的市信息
     * @param parentCode
     * @return
     */
    private List<AreaAllEntity> getAreaAllEntity(String parentCode){

        Map<String,Object> param = new HashMap<String,Object>(16);
        param.put("parentCode",parentCode);
        param.put("status", AreaStatus.ENABLE);
        param.put("cpyCode", CurrentContext.getCpyCode());
        List<CompanyAreaEntity> list = this.findByMap(param,"sort",true);
        List<AreaAllEntity> areaList = new ArrayList<>();

        for (CompanyAreaEntity a : list){
            AreaAllEntity areaAllEntity = new AreaAllEntity();
            //将省市区name与code都放入code里 便于在前端传输给后台
            areaAllEntity.setAreaCode(a.getAreaCode()+","+a.getName());
            areaAllEntity.setName(a.getName());
            areaList.add(areaAllEntity);
        }

        return areaList;
    }
    /**
     * 获取省市区areaCode 一开始areaCode是code+name 这里主要是是分开
     * @param areaCode
     * @return
     */
    private String getAreaCode(String areaCode){

        //将name与code分开
        String [] areaCodeName = areaCode.split(",");
        return  areaCodeName[0];
    }


}
