package com.kfwy.hkp.crm.customer.entity;

import com.gniuu.framework.common.enums.SexType;
import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.common.dic.FireLevelType;
import com.kfwy.hkp.common.enums.DivideType;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.crm.customer.enums.*;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 客户实体类
 *
 * @author liwensihan
 */
public class CustomerEntity extends BaseEntity {

    /**
     * 客户编号
     */
    protected String cusCode;
    /**
     * 所属者编号
     */
    protected String empCode;
    /**
     * 客户状态
     */
    protected String cusStatus;
    /**
     * 客户状态名字
     */
    protected String cusStatusName;
    /**
     * 客户类型
     *
     * @see CustomerType.PLATFORM 平台
     * @see CustomerType.AGENT 经纪人自己的
     * @see CustomerType.PRIVATE 平台拉私
     */
    protected Integer cusType;
    /**
     * 客户类型名字
     */
    protected String cusTypeName;
    /**
     * 客户来源
     */
    protected String cusFrom;
    /**
     * 客户姓名
     */
    protected String cusName;
    /**
     * 客户手机号
     */
    protected String cusPhone;
    /**
     * 客户性别
     */
    protected String cusSex;
    /**
     * 客户性别名
     */
    protected String cusSexName;
    /**
     * 公司名字
     */
    protected String companyName;
    /**
     * 行业性质
     */
    protected String industry;
    /**
     * 产品信息
     */
    protected String products;
    /**
     * 需求面积
     */
    protected BigDecimal needAcreage;
    /**
     * 要求价格
     */
    protected BigDecimal needPrice;
    /**
     * 价格单位
     */
    protected String priceUnit;
    /**
     * 楼层类型
     *
     * @see LayerNum.BUTTOM 底楼
     * @see LayerNum.FLOORUP 楼上
     * @see LayerNum.ALL 全部
     */
    protected Integer layerNum;

    /**
     * 楼层类型名字 {@link com.kfwy.hkp.crm.customer.enums.LayerNum}
     */
    protected String layerNumName;
    /**
     * 楼层高
     */
    protected BigDecimal layerHeight;
    /**
     * 要求电量
     */
    protected BigDecimal needVoltage;
    /**
     * 入住时间
     */
    protected Date enterTime;
    /**
     * 期望租期（月）
     */
    protected String expectTerm;
    /**
     * 消防等级
     *
     * @see FireLevelType.JIA_CLASS 甲类
     * @see FireLevelType.YI_CLASS 乙类
     * @see FireLevelType.BING_CLASS 丙类
     * @see FireLevelType.DING_CLASS 丁类
     * @see FireLevelType.WU_CLASS 戊类
     */
    protected String fireLevel;
    /**
     * 消防等级名{@link com.kfwy.hkp.common.dic.FireLevelType}
     */
    protected String fireLevelName;
    /**
     * 是否需要环评
     */
    protected Boolean needEia;
    /**
     * 是否需要注册
     */
    protected Boolean needRegister;
    /**
     * 是否需要产证
     */
    protected Boolean needCertificate;
    /**
     * 是否需要单栋
     */
    protected Boolean needSingleBuilding;
    /**
     * 有无办公区域
     */
    protected Boolean hasOfficeArea;
    /**
     * 是否公开
     */
    protected Boolean openFlag;
    /**
     * 需求类型(找房用途)
     *
     * @see CustomerHouseType.STORAGE 仓储
     * @see CustomerHouseType.PRODUCT 生产
     * @see CustomerHouseType.StorageProduction 仓储生产
     */
    protected String houseType;
    /**
     * 需求类型名
     */
    protected String houseTypeName;
    /**
     * 需求描述
     */
    protected String description;
    /**
     * 更多联系人
     */
    protected List<CustomerContactsEntity> customerContactsEntityList;
    /**
     * 需求区域
     */
    protected List<CustomerAreaEntity> customerAreaEntityList;
    /**
     * 关联的文件
     */
    protected List<FileRelationEntity> fileRelationEntityList;
    /**
     * 经纪人名字
     */
    protected String empName;
    /**
     * 经纪人手机号
     */
    protected String empPhone;
    /**
     * 经纪人头像
     */
    protected String empImg;
    /**
     * 是否收藏
     */
    protected Boolean hasCollect;
    /**
     * 是否能跟进
     */
    protected Boolean hasCanFollowup;
    /**
     * 申请查看的人员记录
     */
    protected List<UserEntity> applyUsers;
    /**
     * 最后跟进时间
     */
    protected Date lastFollowupTime;
    /**
     * 未跟进天数
     */
    protected Long daysNotFollowup;

    /**
     * 兼职新增字段
     */
    protected String ptCode;
    /**
     * 兼职人名
     */
    protected String ptName;
    /**
     * 分成类型
     *
     * @see DivideType.Ratio 分成比例
     * @see DivideType.Cash  分成现金
     */
    protected String divideType;

    protected String divideTypeName;
    /**
     * 比例
     */
    protected BigDecimal divideRatio;
    /**
     * 现金
     */
    protected BigDecimal divideCash;
    /**
     * 客户等级
     */
    protected int level;
    /**
     * 客户属性, 1, 普通客户 2,房源字段客户
     */
    protected int cusProp;
    /**
     * 合同开始时间
     */
    protected Date contractStartTime;
    /**
     * 合同结束时间
     */
    protected Date contractEndTime;
    /**
     * 周边及配套要求
     */
    protected String needNearbyFacilities;
    /**
     * 库房类型分类：普通仓库厂房，冷链仓库、高台仓库、危险品仓库
     *
     * @see com.kfwy.hkp.hos.house.enums.HouseStyleType.PuTong 普通仓库厂房
     * @see com.kfwy.hkp.hos.house.enums.HouseStyleType.LengLian 冷链仓库
     * @see com.kfwy.hkp.hos.house.enums.HouseStyleType.Gaotai 高台仓库
     * @see com.kfwy.hkp.hos.house.enums.HouseStyleType.Danger 危险品仓库
     */
    protected String category;

    /**
     * 客户选址报告url
     */
    protected String url;

    /**
     * 获客类型
     *
     * @see CustomerGainType.COMPANY_GAIN 公司获客
     * @see CustomerGainType.PERSONAL_GAIN 个人获客
     */
    protected String gainType;

    /**
     * 是否被收藏
     */
    protected Boolean hasFavorite;

    /**
     * 最新上架时间
     */
    protected Date lastUpshelfTime;

    /**
     * 最新下架时间
     */
    protected Date lastDownshelfTime;

    /**
     * 下架原因
     */
    protected String downShelfReason;


    /**
     * 承重
     */
    protected String bearing;

    protected String cusUpUser;

    protected String cusDownUser;

    protected String cusUpUserName;

    protected String cusDownUserName;
    /**
     * 集中获客指定推送部门
     */
    protected List<String> noticeDepts;
    /**
     * 集中获客推送指定员工
     */
    protected List<String> noticeUsers;

    protected Boolean specialPush;

    public Boolean getSpecialPush () {
        return specialPush;
    }

    public void setSpecialPush (Boolean specialPush) {
        this.specialPush = specialPush;
    }

    public List<String> getNoticeUsers () {
        return noticeUsers;
    }

    public void setNoticeUsers (List<String> noticeUsers) {
        this.noticeUsers = noticeUsers;
    }

    public List<String> getNoticeDepts () {
        return noticeDepts;
    }

    public void setNoticeDepts (List<String> noticeDepts) {
        this.noticeDepts = noticeDepts;
    }

    public String getBearing () {
        return bearing;
    }

    public void setBearing (String bearing) {
        this.bearing = bearing;
    }

    public String getDownShelfReason () {
        return downShelfReason;
    }

    public void setDownShelfReason (String downShelfReason) {
        this.downShelfReason = downShelfReason;
    }

    public Date getLastDownshelfTime () {
        return lastDownshelfTime;
    }

    public void setLastDownshelfTime (Date lastDownshelfTime) {
        this.lastDownshelfTime = lastDownshelfTime;
    }

    public Date getLastUpshelfTime () {
        return lastUpshelfTime;
    }

    public void setLastUpshelfTime (Date lastUpshelfTime) {
        this.lastUpshelfTime = lastUpshelfTime;
    }

    public Boolean getHasFavorite () {
        return hasFavorite;
    }

    public void setHasFavorite (Boolean hasFavorite) {
        this.hasFavorite = hasFavorite;
    }

    public int getLevel () {
        return level;
    }

    public void setLevel (int level) {
        this.level = level;
    }

    public String getNeedNearbyFacilities () {
        return needNearbyFacilities;
    }

    public void setNeedNearbyFacilities (String needNearbyFacilities) {
        this.needNearbyFacilities = needNearbyFacilities;
    }

    public String getCategory () {
        return category;
    }

    public void setCategory (String category) {
        this.category = category;
    }

    public String getGainType () {
        return gainType;
    }

    public void setGainType (String gainType) {
        this.gainType = gainType;
    }

    public int getCusProp () {
        return cusProp;
    }

    public void setCusProp (int cusProp) {
        this.cusProp = cusProp;
    }

    public Date getContractStartTime () {
        return contractStartTime;
    }

    public void setContractStartTime (Date contractStartTime) {
        this.contractStartTime = contractStartTime;
    }

    public Date getContractEndTime () {
        return contractEndTime;
    }

    public void setContractEndTime (Date contractEndTime) {
        this.contractEndTime = contractEndTime;
    }

    public String getPtCode () {
        return ptCode;
    }

    public void setPtCode (String ptCode) {
        this.ptCode = ptCode;
    }

    public String getPtName () {
        return ptName;
    }

    public void setPtName (String ptName) {
        this.ptName = ptName;
    }

    public String getDivideType () {
        return divideType;
    }

    public void setDivideType (String divideType) {
        this.divideType = divideType;
    }

    public String getDivideTypeName () {

        if (this.divideTypeName == null && null != this.divideType) {
            this.divideTypeName = DictionaryStorage.get (DivideType.getKey (), this.getDivideType ()).getName ();
        }

        return divideTypeName;
    }

    public void setDivideTypeName (String divideTypeName) {
        this.divideTypeName = divideTypeName;
    }

    public BigDecimal getDivideRatio () {
        return divideRatio;
    }

    public void setDivideRatio (BigDecimal divideRatio) {
        this.divideRatio = divideRatio;
    }

    public BigDecimal getDivideCash () {
        return divideCash;
    }

    public void setDivideCash (BigDecimal divideCash) {
        this.divideCash = divideCash;
    }

    public Date getLastFollowupTime () {
        return lastFollowupTime;
    }

    public void setLastFollowupTime (Date lastFollowupTime) {
        this.lastFollowupTime = lastFollowupTime;
    }

    public String getCusCode () {
        return cusCode;
    }

    public void setCusCode (String cusCode) {
        this.cusCode = cusCode;
    }

    public String getEmpCode () {
        return empCode;
    }

    public void setEmpCode (String empCode) {
        this.empCode = empCode;
    }

    public String getCusStatus () {
        return cusStatus;
    }

    public void setCusStatus (String cusStatus) {
        this.cusStatus = cusStatus;
    }

    public String getCusStatusName () {

        if (this.cusStatusName == null && null != this.cusStatus) {
            this.cusStatusName = DictionaryStorage.get (CustomerStatus.class.getName (), this.getCusStatus ()).getName ();
        }

        return cusStatusName;
    }

    public void setCusStatusName (String cusStatusName) {
        this.cusStatusName = cusStatusName;
    }

    public Integer getCusType () {
        return cusType;
    }

    public void setCusType (Integer cusType) {
        this.cusType = cusType;
    }

    public String getCusTypeName () {

        if (this.cusTypeName == null && null != this.cusType) {
            this.cusTypeName = DictionaryStorage.get (CustomerType.class.getName (), String.valueOf (this.getCusType ())).getName ();
        }

        return cusTypeName;
    }

    public void setCusTypeName (String cusTypeName) {
        this.cusTypeName = cusTypeName;
    }

    public String getCusFrom () {
        return cusFrom;
    }

    public void setCusFrom (String cusFrom) {
        this.cusFrom = cusFrom;
    }

    public String getCusName () {
        return cusName;
    }

    public void setCusName (String cusName) {
        this.cusName = cusName;
    }

    public String getCusPhone () {
        return cusPhone;
    }

    public void setCusPhone (String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusSex () {
        return cusSex;
    }

    public void setCusSex (String cusSex) {
        this.cusSex = cusSex;
    }

    public String getCusSexName () {

        if (cusSexName == null && cusSex != null) {
            cusSexName = DictionaryStorage.get (SexType.getKey (), cusSex).getName ();
        }

        return cusSexName;
    }

    public void setCusSexName (String cusSexName) {
        this.cusSexName = cusSexName;
    }

    public String getCompanyName () {
        return companyName;
    }

    public void setCompanyName (String companyName) {
        this.companyName = companyName;
    }

    public String getIndustry () {
        return industry;
    }

    public void setIndustry (String industry) {
        this.industry = industry;
    }

    public String getProducts () {
        return products;
    }

    public void setProducts (String products) {
        this.products = products;
    }

    public BigDecimal getNeedAcreage () {
        return needAcreage;
    }

    public void setNeedAcreage (BigDecimal needAcreage) {
        this.needAcreage = needAcreage;
    }

    public BigDecimal getNeedPrice () {
        return needPrice;
    }

    public void setNeedPrice (BigDecimal needPrice) {
        this.needPrice = needPrice;
    }

    public String getPriceUnit () {
        return priceUnit;
    }

    public void setPriceUnit (String priceUnit) {
        this.priceUnit = priceUnit;
    }


    public BigDecimal getLayerHeight () {
        return layerHeight;
    }

    public void setLayerHeight (BigDecimal layerHeight) {
        this.layerHeight = layerHeight;
    }

    public BigDecimal getNeedVoltage () {
        return needVoltage;
    }

    public void setNeedVoltage (BigDecimal needVoltage) {
        this.needVoltage = needVoltage;
    }

    public Date getEnterTime () {
        return enterTime;
    }

    public void setEnterTime (Date enterTime) {
        this.enterTime = enterTime;
    }

    public String getExpectTerm () {
        return expectTerm;
    }

    public void setExpectTerm (String expectTerm) {
        this.expectTerm = expectTerm;
    }

    public String getFireLevel () {
        return fireLevel;
    }

    public void setFireLevel (String fireLevel) {
        this.fireLevel = fireLevel;
    }

    public String getFireLevelName () {

        if (fireLevelName == null && fireLevel != null) {
            fireLevelName = DictionaryStorage.get (FireLevelType.class.getName (), fireLevel).getName ();
        }

        return fireLevelName;
    }

    public void setFireLevelName (String fireLevelName) {
        this.fireLevelName = fireLevelName;
    }

    public Boolean getNeedEia () {
        return needEia;
    }

    public void setNeedEia (Boolean needEia) {
        this.needEia = needEia;
    }

    public Boolean getNeedRegister () {
        return needRegister;
    }

    public void setNeedRegister (Boolean needRegister) {
        this.needRegister = needRegister;
    }

    public Boolean getNeedCertificate () {
        return needCertificate;
    }

    public void setNeedCertificate (Boolean needCertificate) {
        this.needCertificate = needCertificate;
    }

    public Boolean getNeedSingleBuilding () {
        return needSingleBuilding;
    }

    public void setNeedSingleBuilding (Boolean needSingleBuilding) {
        this.needSingleBuilding = needSingleBuilding;
    }

    public String getHouseType () {
        return houseType;
    }

    public void setHouseType (String houseType) {
        this.houseType = houseType;
    }

    public String getHouseTypeName () {

        if (houseTypeName == null && houseType != null) {
            houseTypeName = DictionaryStorage.get (CustomerHouseType.class.getName (), houseType).getName ();
        }

        return houseTypeName;
    }

    public void setHouseTypeName (String houseTypeName) {
        this.houseTypeName = houseTypeName;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public List<CustomerContactsEntity> getCustomerContactsEntityList () {
        return customerContactsEntityList;
    }

    public void setCustomerContactsEntityList (List<CustomerContactsEntity> customerContactsEntityList) {
        this.customerContactsEntityList = customerContactsEntityList;
    }

    public List<CustomerAreaEntity> getCustomerAreaEntityList () {
        return customerAreaEntityList;
    }

    public void setCustomerAreaEntityList (List<CustomerAreaEntity> customerAreaEntityList) {
        this.customerAreaEntityList = customerAreaEntityList;
    }

    public List<FileRelationEntity> getFileRelationEntityList () {
        return fileRelationEntityList;
    }

    public void setFileRelationEntityList (List<FileRelationEntity> fileRelationEntityList) {
        this.fileRelationEntityList = fileRelationEntityList;
    }

    public String getEmpName () {
        return empName;
    }

    public void setEmpName (String empName) {
        this.empName = empName;
    }

    public String getEmpPhone () {
        return empPhone;
    }

    public void setEmpPhone (String empPhone) {
        this.empPhone = empPhone;
    }

    public String getEmpImg () {
        return empImg;
    }

    public void setEmpImg (String empImg) {
        this.empImg = empImg;
    }

    public Boolean getHasCollect () {
        return hasCollect;
    }

    public void setHasCollect (Boolean hasCollect) {
        this.hasCollect = hasCollect;
    }

    public Boolean getHasCanFollowup () {
        return hasCanFollowup;
    }

    public void setHasCanFollowup (Boolean hasCanFollowup) {
        this.hasCanFollowup = hasCanFollowup;
    }

    public List<UserEntity> getApplyUsers () {
        return applyUsers;
    }

    public void setApplyUsers (List<UserEntity> applyUsers) {
        this.applyUsers = applyUsers;
    }

    public Boolean getHasOfficeArea () {
        return hasOfficeArea;
    }

    public void setHasOfficeArea (Boolean hasOfficeArea) {
        this.hasOfficeArea = hasOfficeArea;
    }

    public Boolean getOpenFlag () {
        return openFlag;
    }

    public void setOpenFlag (Boolean openFlag) {
        this.openFlag = openFlag;
    }

    public Integer getLayerNum () {
        return layerNum;
    }

    public void setLayerNum (Integer layerNum) {
        this.layerNum = layerNum;
    }

    public String getLayerNumName () {
        if (this.layerNumName == null && null != this.layerNum) {
            this.layerNumName = DictionaryStorage.get (LayerNum.class.getName (), String.valueOf (this.getLayerNum ())).getName ();
        }

        return layerNumName;
    }

    public void setLayerNumName (String layerNumName) {
        this.layerNumName = layerNumName;
    }

    public Long getDaysNotFollowup () {

        return daysNotFollowup;
    }

    public void setDaysNotFollowup (Long daysNotFollowup) {
        this.daysNotFollowup = daysNotFollowup;
    }

    public String getUrl () {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public String getCusUpUser () {
        return cusUpUser;
    }

    public void setCusUpUser (String cusUpUser) {
        this.cusUpUser = cusUpUser;
    }

    public String getCusDownUser () {
        return cusDownUser;
    }

    public void setCusDownUser (String cusDownUser) {
        this.cusDownUser = cusDownUser;
    }

    public String getCusUpUserName () {
        return cusUpUserName;
    }

    public void setCusUpUserName (String cusUpUserName) {
        this.cusUpUserName = cusUpUserName;
    }

    public String getCusDownUserName () {
        return cusDownUserName;
    }

    public void setCusDownUserName (String cusDownUserName) {
        this.cusDownUserName = cusDownUserName;
    }
}
