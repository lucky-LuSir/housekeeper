package com.kfwy.hkp.hos.house.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.common.dic.FireLevelType;
import com.kfwy.hkp.common.enums.DivideType;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerContactsEntity;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerEntity;
import com.kfwy.hkp.crm.houseowner.enums.HouseownerType;
import com.kfwy.hkp.hos.house.enums.*;
import com.kfwy.hkp.hrm.org.employee.entity.EmployeeEntity;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author baiye
 */
public class HouseEntity extends BaseEntity {

    /**
     * 未跟进天数
     */
    protected Long daysNotFollowup;
    /**
     * 权限检查code
     */
    protected String checkCode;

    /**
     * 房源编码
     */
    protected String houseCode;
    /**
     * 房源8位编码 当id大于等于8位时  该编码等于id
     */
    protected String houseNumber;
    /**
     * 房源标题
     */
    protected String houseName;

    /**
     * 房源描述
     */
    @NotNull
    protected String hosDescribe;
    /**
     * 是否整栋出租
     */
    @NotNull
    protected Boolean hasWholeRental;
    /**
     * 房源类型
     *
     * @see HouseType.PLATFORM 平台
     * @see HouseType.AGENT 经纪人
     */
    protected String houseType;
    protected String houseTypeName;
    /**
     * 房源来源
     */
    @NotNull(message = "houseFrom不能为空")
    protected String houseFrom;
    /**
     * 房源状态
     *
     * @see HouseStatus.hotRenting 热租中
     * @see HouseStatus.HasLease   已成交
     */
    protected String houseStatus;
    protected String houseStatusName;
    /**
     * 房源用途
     *
     * @see HousePurposeType.STORAGE 仓储
     * @see HousePurposeType.PRODUCT 生产
     * @see HousePurposeType.StorageProduction 仓储生产
     */
    @NotNull(message = "housePurpose不能为空")
    protected String housePurpose;
    protected String housePurposeName;
    /**
     * 房源风格
     *
     * @see HouseStyleType.PuTong 普通仓库厂房
     * @see HouseStyleType.LengLian 冷链仓库
     * @see HouseStyleType.Gaotai 高台仓库
     * @see HouseStyleType.Danger 危险品库
     */
    @NotNull(message = "houseStyle不能为空")
    protected String houseStyle;
    protected String houseStyleName;
    /**
     * 房源位置编码
     */
    protected String locationCode;

    /**
     * 房源业主电话,v2.8.0已经调整到ownerEntity中
     */
    protected String ownerPhone;
    /**
     * 房源业主类型
     *
     * @see HouseownerType.BIG_LANDLORD 大房东
     * @see HouseownerType.SECOND_LANDLORD 二房东
     * @see HouseownerType.SUBLET_THE_HOUSEHOLD 转租户
     */
    @NotNull
    protected String ownerType;
    protected String ownerTypeName;
    /**
     * 公司名
     */
    protected String companyName;


    /**
     * 房源所属经纪人
     */
    protected String empCode;
    /**
     * 房源所属经纪人名
     */
    protected String empName;
    /**
     * 经纪人照片
     */
    protected String empImg;
    /**
     * 经纪人部门
     */
    protected String deptCode;
    /**
     * 经纪人号码
     */
    protected String empPhone;
    /**
     * 是否独栋
     */
    @NotNull
    protected Boolean hasAloneBuilding;
    /**
     * 所在栋
     */
    @NotNull
    protected String whereBuilding;
    /**
     * 是否单层
     */
    protected Boolean hasMonolayer;
    /**
     * 共几层
     */
    @NotNull
    protected String severalLayers;
    /**
     * 所在层
     */
    @NotNull
    protected String whereLayer;
    /**
     * 是否需要环评
     */
    @NotNull
    protected Boolean hasEia;
    /**
     * 是否可分割
     */
    protected Boolean hasCut;
    /**
     * 是否有产证
     */
    @NotNull
    protected Boolean hasCertificate;
    /**
     * 是否可注册
     */
    @NotNull
    protected Boolean hasRegistry;
    /**
     * 是否可短租
     */
    protected Boolean hasShortLease;
    /**
     * 是否有停车位
     */
    @NotNull
    protected Boolean hasParking;
    /**
     * 是否有货台
     */
    protected Boolean hasPlatform;
    /**
     * 是否有货梯
     */
    @NotNull
    protected Boolean hasElevator;
    /**
     * 是否有行车
     */
    @NotNull
    protected Boolean hasInstallCrane;
    /**
     * 有无卫生间
     */
    protected Boolean hasBathroom;
    /**
     * 有无办公区域
     */
    @NotNull
    protected Boolean hasOfficeArea;
    /**
     * 有无排污证
     */
    protected Boolean hasDischargeSewage;

    /**
     * 是否公开
     */
    protected Boolean openFlag;

    /**
     *  房源参数
     * */

    /**
     * 可租面积
     */
    @NotNull
    protected BigDecimal area;
    /**
     * 层总面积
     */
    @NotNull
    protected BigDecimal layerArea;
    /**
     * 价格
     */
    @NotNull
    protected BigDecimal price;
    /**
     * 价格单位 / 天 月 年
     */
    @NotNull
    protected String unit;
    /**
     * 层高
     */
    @NotNull
    protected BigDecimal layerHeight;
    /**
     * 物业费
     */
    protected BigDecimal propertyFee;
    /**
     * 最短租期
     */
    @NotNull
    protected Integer lessLease;
    /**
     * 最长租期
     */
    @NotNull
    protected Integer moreLease;
    /**
     * 最大电量
     */
    @NotNull
    protected Integer maxElectric;
    /**
     * 入住时间
     */
    @NotNull
    protected Date enterTime;
    /**
     * 消防等级
     *
     * @see FireLevelType.JIA_CLASS 甲类
     * @see FireLevelType.YI_CLASS 乙类
     * @see FireLevelType.BING_CLASS 丙类
     * @see FireLevelType.DING_CLASS 丁类
     * @see FireLevelType.WU_CLASS 戊类
     */
    @NotNull
    protected String fireLevel;

    protected String fireLevelName;
    /**
     * 房屋结构
     *
     * @see HouseStructureType.STEEL_STRUCTURE 钢结构
     * @see HouseStructureType.FRAMEWORK 框架
     * @see HouseStructureType.STEEL_MIXED 钢混合
     * @see HouseStructureType.BRICK_MIXED 砖混合
     */
    @NotNull
    protected String houseStructure;

    protected String houseStructureName;

    /**
     * 行业性质
     */
    protected String industryRestriction;

    /**
     * 适合行业
     */
    protected String forInsdustry;
    /**
     * 房源特色
     */
    protected String features;

    /**
     * 省编码
     */
    protected String province;
    /**
     * 省名
     */
    protected String provinceName;
    /**
     * 城市编码
     */
    protected String city;
    /**
     * 城市名称
     */
    protected String cityName;
    /**
     * 区域编码
     */
    protected String region;
    /**
     * 区域名称
     */
    protected String regionName;
    /**
     * 街道编码
     */
    protected String street;
    /**
     * 街道名称
     */
    protected String streetName;
    /**
     * 设备编码
     */
    protected String community;
    /**
     * 社区名称
     */
    protected String communityName;

    /**
     * 我服务的客户数量
     */
    protected Integer cusCount;
    /**
     * 我服务的房源数量
     */
    protected Integer houseCount;
    /**
     * 是否收藏
     */
    protected Boolean hasCollect;

    /**
     * 跟进次数
     */
    protected Integer followupSum;

    /**
     * 房源文件
     */
    protected List<FileRelationEntity> fileList;


    /**
     * 房子外立面/园区道路/园区场地，仅内部可看，官网对外不展示（可上传3～5张）
     */
    @NotNull
    protected List<FileRelationEntity> houseYardImage;


    /**
     * 房源地址
     */
    protected HouseLocationEntity houseLocation;

    /**
     * 房源业主
     */
    @NotNull
    protected HouseownerEntity houseowner;

    /**
     * 多个联系人
     */
    protected List<HouseownerContactsEntity> houseownerContacts;

    /**
     * 查看号码的人
     */
    @Deprecated
    protected List<EmployeeEntity> applyEmpList;

    /**
     * 申请查看号码的人
     */
    protected List<UserEntity> applyUsers;

    /**
     * 最后跟进时间
     */
    protected Date lastFollowupTime;

    /**
     * 兼职新增字段
     */
    protected String ptCode;
    protected String ptName;

    /**
     * 分成类型
     *
     * @see DivideType.Ratio 分成比例
     * @see DivideType.Cash 分成现金
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
     * 是否能下架
     */
    protected Boolean hasCanDownFrame;

    /**
     * 下架原因
     */
    protected String downReason;

    /**
     * 是否是热门房源
     */
    protected Boolean isHot;
    /**
     * 装修类型
     *
     * @see HouseDecorationType.MAO 毛坯
     * @see HouseDecorationType.JIAN 简装
     * @see HouseDecorationType.JING 精装
     * @see HouseDecorationType.HAO 豪华
     */
    protected String decorationType;

    protected String decorationTypeName;

    /**
     * 房源协议支持多张图片
     */
    @NotNull
    protected List<FileRelationEntity> houseProtocol;
    /**
     * 房源添加协议信息图片资源
     */
    @Deprecated
    protected String authImage;
    /**
     * 房源协议签订时间
     */
    protected Date authTime;
    /**
     * 房源签协议人编码
     */
    protected String authCode;
    /**
     * 签委托人所在部门
     */
    protected String authDeptCode;
    /**
     * 物业公司
     */
    protected String propertyCompany;
    /**
     * 最大通行车辆
     */
    protected String maxPassCar;
    /**
     * 货梯规格
     */
    protected String elevatorStandard;
    /**
     * 货梯数量
     */
    protected Integer elevatorNumber;
    /**
     * 最小分割面积
     */
    @NotNull
    protected Double minAcreage;
    /**
     * 货梯开门
     */
    protected String elevatorDoor;
    /**
     * 状态  number  迁移数据用
     */
    protected Integer houseStatusNum;
    /**
     * 冷链仓库等类型  number  迁移数据用
     */
    protected Integer houseStyleNum;
    /**
     * 共几层  number  迁移数据用
     */
    protected Integer severalLayersNum;
    /**
     * 是否可以装行车
     */
    protected Boolean hasCrane;

    /**
     * 层数
     */
    protected Integer whereLayerNum;
    /**
     * 电压
     */
    protected String voltage;
    /**
     * 合作id
     */
    protected Integer bespeakId;
    /**
     * 委托id
     */
    protected Integer entrustId;
    /**
     * 推荐id
     */
    protected Integer recommendId;
    /**
     * 是否被收藏
     */
    protected Boolean hasFavorite;
    /**
     * 共享部门房源详情是否可见
     */
    protected Boolean shareOpenFlag;
    /**
     * 合作公开隐藏
     */
    protected Boolean cooOpenFlag;

    /**
     * 承重
     */
    protected Integer bearing;

    /**
     * 租期可协商
     */
    protected Boolean leaseNegotiable;

    /**
     * 起租面积
     */
    protected Integer ofTheArea;
    /**
     * 服务费
     */
    @NotNull
    protected BigDecimal commission;

    protected String houseOwnerName;
    /**
     * 导出用字段
     */

    private String ldetailAddress;
    //价格+单位
    private String priceStr;

    // 租赁到期时间
    protected Date leaseExpiration;

    //批次code(后端用,用于区分同一栋,或同一批次增加的房源)
    private String batchCode;

//    private Map<String, String> pramCheckName;
    //------------------------------------------分割线----------------------------------------------




    public Map<String, String> usePramCheckNameNoReturn() {
        Map<String, String> pramCheckName = new HashMap<String, String>();
        pramCheckName.put("hasAloneBuilding","是否独栋");
        pramCheckName.put("whereBuilding","所在栋");
        pramCheckName.put("severalLayers","共几层");

        pramCheckName.put("ownerType","业主类型");

        pramCheckName.put("houseFrom","房源来源");
        pramCheckName.put("commission","服务费");
        pramCheckName.put("houseStructure","房屋结构");
        pramCheckName.put("fireLevel","消防等级");

        pramCheckName.put("hasEia","是否环评");
        pramCheckName.put("hasRegistry","是否注册");
        //pramCheckName.put("hasCertificate","");  产证齐全对应 hasCertificate
        pramCheckName.put("hasParking","是否停车位");
        pramCheckName.put("hasElevator","是否有货梯");
        pramCheckName.put("hasPlatform","是否有货台");

        pramCheckName.put("hasInstallCrane","是否有行车");
        pramCheckName.put("hasOfficeArea","有无办公区域");

        pramCheckName.put("locationCode","房源位置编码");
        pramCheckName.put("hasDischargeSewage","是否产证齐全");  //对应前端产证齐全字段
        pramCheckName.put("industryRestriction","行业性质");

        //sublist校验字段
        pramCheckName.put("houseStyle","房源仓库类型");
        pramCheckName.put("housePurpose","房源用途");
        pramCheckName.put("whereLayer","所在层");

        pramCheckName.put("layerArea","层总面积");
        pramCheckName.put("area","可租面积");
        pramCheckName.put("minAcreage","最小分割面积");
        pramCheckName.put("price","价格");
        pramCheckName.put("unit","价格单位");

        pramCheckName.put("layerHeight","层高");
        pramCheckName.put("maxElectric","最大电量");
        pramCheckName.put("lessLease","最短租期");
        pramCheckName.put("moreLease","最长租期");
        pramCheckName.put("enterTime","入住时间");
        pramCheckName.put("bearing","每层层重");

        pramCheckName.put("hosDescribe","房源描述");
        pramCheckName.put("hasWholeRental","是否独栋出租");
        return pramCheckName;
    }

    public String getPriceStr() {
        return priceStr;
    }

    public void setPriceStr(String priceStr) {
        this.priceStr = priceStr;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Date getLeaseExpiration() {
        return leaseExpiration;
    }

    public void setLeaseExpiration(Date leaseExpiration) {
        this.leaseExpiration = leaseExpiration;
    }


    public String getLdetailAddress() {
        return ldetailAddress;
    }

    public void setLdetailAddress(String ldetailAddress) {
        this.ldetailAddress = ldetailAddress;
    }

    public String getHouseOwnerName() {
        return houseOwnerName;
    }

    public void setHouseOwnerName(String houseOwnerName) {
        this.houseOwnerName = houseOwnerName;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public String getDownReason() {
        return downReason;
    }

    public void setDownReason(String downReason) {
        this.downReason = downReason;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public Boolean getShareOpenFlag() {
        return shareOpenFlag;
    }

    public void setShareOpenFlag(Boolean shareOpenFlag) {
        this.shareOpenFlag = shareOpenFlag;
    }

    public Boolean getCooOpenFlag() {
        return cooOpenFlag;
    }

    public void setCooOpenFlag(Boolean cooOpenFlag) {
        this.cooOpenFlag = cooOpenFlag;
    }

    public Integer getHouseStyleNum() {
        return houseStyleNum;
    }

    public void setHouseStyleNum(Integer houseStyleNum) {
        this.houseStyleNum = houseStyleNum;
    }

    public Integer getSeveralLayersNum() {
        return severalLayersNum;
    }

    public void setSeveralLayersNum(Integer severalLayersNum) {
        this.severalLayersNum = severalLayersNum;
    }

    public Integer getWhereLayerNum() {
        return whereLayerNum;
    }

    public void setWhereLayerNum(Integer whereLayerNum) {
        this.whereLayerNum = whereLayerNum;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public Boolean getHasFavorite() {
        return hasFavorite;
    }

    public void setHasFavorite(Boolean hasFavorite) {
        this.hasFavorite = hasFavorite;
    }

    public Integer getHouseStatusNum() {
        return houseStatusNum;
    }

    public void setHouseStatusNum(Integer houseStatusNum) {
        this.houseStatusNum = houseStatusNum;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public Boolean getIsHot() {
        return isHot;
    }

    public void setIsHot(Boolean isHot) {
        this.isHot = isHot;
    }

    public String getDecorationType() {
        return decorationType;
    }

    public void setDecorationType(String decorationType) {
        this.decorationType = decorationType;
    }

    public String getDecorationTypeName() {
        if (this.decorationTypeName == null && null != this.decorationTypeName) {
            this.decorationTypeName = DictionaryStorage.get(HouseDecorationType.class.getName(), this.getDecorationType()).getName();
        }
        return decorationTypeName;
    }

    public void setDecorationTypeName(String decorationTypeName) {
        this.decorationTypeName = decorationTypeName;
    }

    public String getAuthImage() {
        return authImage;
    }

    public void setAuthImage(String authImage) {
        this.authImage = authImage;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getAuthDeptCode() {
        return authDeptCode;
    }

    public void setAuthDeptCode(String authDeptCode) {
        this.authDeptCode = authDeptCode;
    }

    public String getPropertyCompany() {
        return propertyCompany;
    }

    public void setPropertyCompany(String propertyCompany) {
        this.propertyCompany = propertyCompany;
    }

    public String getMaxPassCar() {
        return maxPassCar;
    }

    public void setMaxPassCar(String maxPassCar) {
        this.maxPassCar = maxPassCar;
    }

    public String getElevatorStandard() {
        return elevatorStandard;
    }

    public void setElevatorStandard(String elevatorStandard) {
        this.elevatorStandard = elevatorStandard;
    }

    public Integer getElevatorNumber() {
        return elevatorNumber;
    }

    public void setElevatorNumber(Integer elevatorNumber) {
        this.elevatorNumber = elevatorNumber;
    }

    public Double getMinAcreage() {
        return minAcreage;
    }

    public void setMinAcreage(Double minAcreage) {
        this.minAcreage = minAcreage;
    }

    public String getElevatorDoor() {
        return elevatorDoor;
    }

    public void setElevatorDoor(String elevatorDoor) {
        this.elevatorDoor = elevatorDoor;
    }

    public List<UserEntity> getApplyUsers() {
        return applyUsers;
    }

    public void setApplyUsers(List<UserEntity> applyUsers) {
        this.applyUsers = applyUsers;
    }

    public String getPtCode() {
        return ptCode;
    }

    public void setPtCode(String ptCode) {
        this.ptCode = ptCode;
    }

    public String getPtName() {
        return ptName;
    }

    public void setPtName(String ptName) {
        this.ptName = ptName;
    }

    public String getDivideType() {
        return divideType;
    }

    public void setDivideType(String divideType) {
        this.divideType = divideType;
    }

    public String getDivideTypeName() {
        if (this.divideTypeName == null && null != this.divideType) {
            this.divideTypeName = DictionaryStorage.get(DivideType.getKey(), this.getDivideType()).getName();
        }
        return divideTypeName;
    }

    public void setDivideTypeName(String divideTypeName) {
        this.divideTypeName = divideTypeName;
    }

    public BigDecimal getDivideRatio() {
        return divideRatio;
    }

    public void setDivideRatio(BigDecimal divideRatio) {
        this.divideRatio = divideRatio;
    }

    public BigDecimal getDivideCash() {
        return divideCash;
    }

    public void setDivideCash(BigDecimal divideCash) {
        this.divideCash = divideCash;
    }

    public Date getLastFollowupTime() {
        return lastFollowupTime;
    }

    public void setLastFollowupTime(Date lastFollowupTime) {
        this.lastFollowupTime = lastFollowupTime;
    }

    public List<EmployeeEntity> getApplyEmpList() {
        return applyEmpList;
    }

    public void setApplyEmpList(List<EmployeeEntity> applyEmpList) {
        this.applyEmpList = applyEmpList;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public HouseownerEntity getHouseowner() {
        return houseowner;
    }

    public void setHouseowner(HouseownerEntity houseowner) {
        this.houseowner = houseowner;
    }

    public List<HouseownerContactsEntity> getHouseownerContacts() {
        return houseownerContacts;
    }

    public void setHouseownerContacts(List<HouseownerContactsEntity> houseownerContacts) {
        this.houseownerContacts = houseownerContacts;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
        if (this.houseTypeName == null && null != this.houseType) {
            this.houseTypeName = DictionaryStorage.get(HouseType.class.getName(), this.getHouseType()).getName();
        }
    }

    public String getHouseTypeName() {
        if (this.houseTypeName == null && null != this.houseType) {
            this.houseTypeName = DictionaryStorage.get(HouseType.class.getName(), this.getHouseType()).getName();
        }
        return houseTypeName;
    }

    public void setHouseTypeName(String houseTypeName) {
        this.houseTypeName = houseTypeName;
    }

    public String getHouseFrom() {
        return houseFrom;
    }

    public void setHouseFrom(String houseFrom) {
        this.houseFrom = houseFrom;
    }

    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus;
        if (this.houseStatusName == null && null != this.houseStatus) {
            this.houseStatusName = DictionaryStorage.get(HouseStatus.class.getName(), this.getHouseStatus()).getName();
        }
    }

    public String getHouseStatusName() {
        if (this.houseStatusName == null && null != this.houseStatus) {
            this.houseStatusName = DictionaryStorage.get(HouseStatus.class.getName(), this.getHouseStatus()).getName();
        }
        return houseStatusName;
    }

    public void setHouseStatusName(String houseStatusName) {
        this.houseStatusName = houseStatusName;
    }

    public String getHousePurpose() {
        return housePurpose;
    }

    public void setHousePurpose(String housePurpose) {
        this.housePurpose = housePurpose;
        if (this.housePurposeName == null && null != this.housePurpose) {
            this.housePurposeName = DictionaryStorage.get(HousePurposeType.class.getName(), this.getHousePurpose()).getName();
        }
    }

    public String getHousePurposeName() {
        if (this.housePurposeName == null && null != this.housePurpose) {
            this.housePurposeName = DictionaryStorage.get(HousePurposeType.class.getName(), this.getHousePurpose()).getName();
        }
        return housePurposeName;
    }

    public void setHousePurposeName(String housePurposeName) {
        this.housePurposeName = housePurposeName;
    }

    public String getHouseStyle() {
        return houseStyle;
    }

    public void setHouseStyle(String houseStyle) {
        this.houseStyle = houseStyle;
        if (this.houseStyleName == null && null != this.houseStyle) {
            this.houseStyleName = DictionaryStorage.get(HouseStyleType.class.getName(), this.getHouseStyle()).getName();
        }
    }

    public String getHouseStyleName() {
        if (this.houseStyleName == null && null != this.houseStyle) {
            this.houseStyleName = DictionaryStorage.get(HouseStyleType.class.getName(), this.getHouseStyle()).getName();
        }
        return houseStyleName;
    }

    public void setHouseStyleName(String houseStyleName) {
        this.houseStyleName = houseStyleName;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public Boolean getHasAloneBuilding() {
        return hasAloneBuilding;
    }

    public void setHasAloneBuilding(Boolean hasAloneBuilding) {
        this.hasAloneBuilding = hasAloneBuilding;
    }

    public String getWhereBuilding() {
        return whereBuilding;
    }

    public void setWhereBuilding(String whereBuilding) {
        this.whereBuilding = whereBuilding;
    }

    public Boolean getHasMonolayer() {
        return hasMonolayer;
    }

    public void setHasMonolayer(Boolean hasMonolayer) {
        this.hasMonolayer = hasMonolayer;
    }

    public String getSeveralLayers() {
        return severalLayers;
    }

    public void setSeveralLayers(String severalLayers) {
        this.severalLayers = severalLayers;
    }

    public String getWhereLayer() {
        return whereLayer;
    }

    public void setWhereLayer(String whereLayer) {
        this.whereLayer = whereLayer;
    }

    public Boolean getHasEia() {
        return hasEia;
    }

    public void setHasEia(Boolean hasEia) {
        this.hasEia = hasEia;
    }

    public Boolean getHasCut() {
        return hasCut;
    }

    public void setHasCut(Boolean hasCut) {
        this.hasCut = hasCut;
    }

    public Boolean getHasCertificate() {
        return hasCertificate;
    }

    public void setHasCertificate(Boolean hasCertificate) {
        this.hasCertificate = hasCertificate;
    }

    public Boolean getHasRegistry() {
        return hasRegistry;
    }

    public void setHasRegistry(Boolean hasRegistry) {
        this.hasRegistry = hasRegistry;
    }

    public Boolean getHasShortLease() {
        return hasShortLease;
    }

    public void setHasShortLease(Boolean hasShortLease) {
        this.hasShortLease = hasShortLease;
    }

    public Boolean getHasParking() {
        return hasParking;
    }

    public void setHasParking(Boolean hasParking) {
        this.hasParking = hasParking;
    }

    public Boolean getHasPlatform() {
        return hasPlatform;
    }

    public void setHasPlatform(Boolean hasPlatform) {
        this.hasPlatform = hasPlatform;
    }

    public Boolean getHasElevator() {
        return hasElevator;
    }

    public void setHasElevator(Boolean hasElevator) {
        this.hasElevator = hasElevator;
    }

    public Boolean getHasInstallCrane() {
        return hasInstallCrane;
    }

    public void setHasInstallCrane(Boolean hasInstallCrane) {
        this.hasInstallCrane = hasInstallCrane;
    }

    public Boolean getHasBathroom() {
        return hasBathroom;
    }

    public void setHasBathroom(Boolean hasBathroom) {
        this.hasBathroom = hasBathroom;
    }

    public Boolean getHasOfficeArea() {
        return hasOfficeArea;
    }

    public void setHasOfficeArea(Boolean hasOfficeArea) {
        this.hasOfficeArea = hasOfficeArea;
    }

    public Boolean getHasDischargeSewage() {
        return hasDischargeSewage;
    }

    public void setHasDischargeSewage(Boolean hasDischargeSewage) {
        this.hasDischargeSewage = hasDischargeSewage;
    }

    public BigDecimal getLayerArea() {
        return layerArea;
    }

    public void setLayerArea(BigDecimal layerArea) {
        this.layerArea = layerArea;
    }

    public String getForInsdustry() {
        return forInsdustry;
    }

    public void setForInsdustry(String forInsdustry) {
        this.forInsdustry = forInsdustry;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getLayerHeight() {
        return layerHeight;
    }

    public void setLayerHeight(BigDecimal layerHeight) {
        this.layerHeight = layerHeight;
    }

    public BigDecimal getPropertyFee() {
        return propertyFee;
    }

    public void setPropertyFee(BigDecimal propertyFee) {
        this.propertyFee = propertyFee;
    }

    public Integer getLessLease() {
        return lessLease;
    }

    public void setLessLease(Integer lessLease) {
        this.lessLease = lessLease;
    }

    public Integer getMoreLease() {
        return moreLease;
    }

    public void setMoreLease(Integer moreLease) {
        this.moreLease = moreLease;
    }

    public Integer getMaxElectric() {
        return maxElectric;
    }

    public void setMaxElectric(Integer maxElectric) {
        this.maxElectric = maxElectric;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public String getFireLevel() {
        return fireLevel;
    }

    public void setFireLevel(String fireLevel) {
        this.fireLevel = fireLevel;
        if (this.fireLevelName == null && null != this.fireLevel) {
            this.fireLevelName = DictionaryStorage.get(FireLevelType.class.getName(), this.getFireLevel()).getName();
        }
    }

    public String getFireLevelName() {
        if (this.fireLevelName == null && null != this.fireLevel) {
            this.fireLevelName = DictionaryStorage.get(FireLevelType.class.getName(), this.getFireLevel()).getName();
        }
        return fireLevelName;
    }

    public void setFireLevelName(String fireLevelName) {
        this.fireLevelName = fireLevelName;
    }

    public String getHouseStructure() {
        return houseStructure;
    }

    public void setHouseStructure(String houseStructure) {
        this.houseStructure = houseStructure;
        if (this.houseStructureName == null && null != this.houseStructure) {
            this.houseStructureName = DictionaryStorage.get(HouseStructureType.class.getName(), this.getHouseStructure()).getName();
        }
    }

    public String getHouseStructureName() {
        if (this.houseStructureName == null && null != this.houseStructure) {
            this.houseStructureName = DictionaryStorage.get(HouseStructureType.class.getName(), this.getHouseStructure()).getName();
        }
        return houseStructureName;
    }

    public void setHouseStructureName(String houseStructureName) {
        this.houseStructureName = houseStructureName;
    }

    public String getIndustryRestriction() {
        return industryRestriction;
    }

    public void setIndustryRestriction(String industryRestriction) {
        this.industryRestriction = industryRestriction;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getEmpImg() {
        return empImg;
    }

    public void setEmpImg(String empImg) {
        this.empImg = empImg;
    }

    public Integer getCusCount() {
        return cusCount;
    }

    public void setCusCount(Integer cusCount) {
        this.cusCount = cusCount;
    }

    public Integer getHouseCount() {
        return houseCount;
    }

    public void setHouseCount(Integer houseCount) {
        this.houseCount = houseCount;
    }

    public Boolean getHasCollect() {
        return hasCollect;
    }

    public void setHasCollect(Boolean hasCollect) {
        this.hasCollect = hasCollect;
    }

    public Integer getFollowupSum() {
        return followupSum;
    }

    public void setFollowupSum(Integer followupSum) {
        this.followupSum = followupSum;
    }

    public List<FileRelationEntity> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileRelationEntity> fileList) {
        this.fileList = fileList;
    }

    public HouseLocationEntity getHouseLocation() {
        return houseLocation;
    }

    public void setHouseLocation(HouseLocationEntity houseLocation) {
        this.houseLocation = houseLocation;
    }

    public Boolean getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(Boolean openFlag) {
        this.openFlag = openFlag;
    }


    public Boolean getHasCanDownFrame() {
        return hasCanDownFrame;
    }

    public void setHasCanDownFrame(Boolean hasCanDownFrame) {
        this.hasCanDownFrame = hasCanDownFrame;
    }

    public Long getDaysNotFollowup() {
        return daysNotFollowup;
    }

    public void setDaysNotFollowup(Long daysNotFollowup) {
        this.daysNotFollowup = daysNotFollowup;
    }

    public Integer getBespeakId() {
        return bespeakId;
    }

    public void setBespeakId(Integer bespeakId) {
        this.bespeakId = bespeakId;
    }

    public Integer getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(Integer entrustId) {
        this.entrustId = entrustId;
    }

    public Integer getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(Integer recommendId) {
        this.recommendId = recommendId;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public Boolean getHot() {
        return isHot;
    }

    public void setHot(Boolean hot) {
        isHot = hot;
    }

    public Integer getBearing() {
        return bearing;
    }

    public void setBearing(Integer bearing) {
        this.bearing = bearing;
    }

    public Boolean getLeaseNegotiable() {
        return leaseNegotiable;
    }

    public void setLeaseNegotiable(Boolean leaseNegotiable) {
        this.leaseNegotiable = leaseNegotiable;
    }

    public Integer getOfTheArea() {
        return ofTheArea;
    }

    public void setOfTheArea(Integer ofTheArea) {
        this.ofTheArea = ofTheArea;
    }

    public Boolean getHasCrane() {
        return hasCrane;
    }

    public void setHasCrane(Boolean hasCrane) {
        this.hasCrane = hasCrane;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
        if (this.ownerTypeName == null && null != this.ownerType) {
            this.ownerTypeName = DictionaryStorage.get(HouseownerType.class.getName(), this.getOwnerType()).getName();
        }
    }

    public String getOwnerTypeName() {
        if (this.ownerTypeName == null && null != this.ownerType) {
            this.ownerTypeName = DictionaryStorage.get(HouseownerType.class.getName(), this.getOwnerType()).getName();
        }
        return ownerTypeName;
    }

    public void setOwnerTypeName(String ownerTypeName) {
        this.ownerTypeName = ownerTypeName;
    }


    public List<FileRelationEntity> getHouseYardImage() {
        return houseYardImage;
    }

    public void setHouseYardImage(List<FileRelationEntity> houseYardImage) {
        this.houseYardImage = houseYardImage;
    }

    public List<FileRelationEntity> getHouseProtocol() {
        return houseProtocol;
    }

    public void setHouseProtocol(List<FileRelationEntity> houseProtocol) {
        this.houseProtocol = houseProtocol;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getHosDescribe() {
        return hosDescribe;
    }

    public void setHosDescribe(String hosDescribe) {
        this.hosDescribe = hosDescribe;
    }

    public Boolean getHasWholeRental() {
        return hasWholeRental;
    }

    public void setHasWholeRental(Boolean hasWholeRental) {
        this.hasWholeRental = hasWholeRental;
    }
}
