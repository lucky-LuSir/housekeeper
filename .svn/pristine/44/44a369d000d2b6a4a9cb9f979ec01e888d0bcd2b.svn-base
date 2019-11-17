package com.kfwy.hkp.crm.customer.business;

import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;

import java.util.Map;

/**
 * 客户操作检查管理接口
 * @AUTHOR 李文思汗
 * @Description 接口描述:客户操作检查管理接口
 */
public interface ICustomerCheckManager {

    /**
     * 检查是否是自己的客户
     *
     * @param cusCode
     * @return
     */
    Boolean checkIsMyCus(String cusCode);


    /**
     * 检查是否可以申请客户拉私
     * @param customer
     * @return
     */
    Boolean checkCustomerApplyPrivate(CustomerEntity customer);

    /**
     * 检查是否可以申请客户隐藏
     * @param customer
     * @return
     */
    Boolean checkCustomerApplyHidden(CustomerEntity customer);
    /**
     * 检查是否部门内共享
     * @param cur 当前操作人
     * @param ownerUser 客户所属者
     * @param customerEntity 当前被操作客户
     * @param checkOpenFlag 是否检查客户是否公开条件 true则要检查，false则不用检查
     * @return
     */
    Boolean checkSameDeptShare(UserEntity cur, UserEntity ownerUser, CustomerEntity customerEntity, Boolean checkOpenFlag);

    /**
     * 检查客户信息是否可以修改
     * @param customer
     * @param checkApply
     * @return
     */
    Boolean checkCustomerInfoCanUpdate(CustomerEntity customer, Boolean checkApply);

    /**
     * 检查是否部门间共享
     *
     * @param ownerUser
     * @param customer
     * @return
     */
    Boolean checkCusSharePool(UserEntity ownerUser, CustomerEntity customer, Boolean checkOpenFlag);

    /**
     * 针对部门间共享池拨打电话，排除48小时内创建新客户的检查限制
     *
     * @param ownerUser
     * @param customer
     * @return
     */
    Boolean checkCusSharePoolForCallPhone(UserEntity ownerUser, CustomerEntity customer);
    /**
     * 针对部门内共享拨打电话，排除48小时内创建新客户的检查限制
     *
     * @param ownerUser
     * @param customer
     * @return
     */
    Boolean checkCusSameDeptForCallPhone(UserEntity ownerUser,CustomerEntity customer);

    /**
     * 检查是否申请查看了平台客户
     *
     * @param customer
     * @return
     */
    Boolean checkPlatformCusApply(CustomerEntity customer);

    /**
     * 检查客户是否推送
     *
     * @param customer
     * @return
     */
    Boolean checkCusPush(CustomerEntity customer);

    /**
     * 检查客户是否合作
     *
     * @param customer
     * @return
     */
    Boolean checkCooperate(CustomerEntity customer);



    /**
     * 检查是否能创建客户跟进
     *
     * @param customer
     * @param checkApply 是否进行平台客户申请查看检查
     * @return
     */
    Boolean checkCreateCusFollowup(CustomerEntity customer, Boolean checkApply);

    /**
     * 检查是否能够查看客户跟进详情信息
     *
     * @param customer
     * @return
     */
    Boolean checkCusFollowupDetail(CustomerEntity customer);

    /**
     * 检查是否能够查看客户看房记录
     *
     * @param customer
     * @return
     */
    Boolean checkCusSawHouses(CustomerEntity customer);

    /**
     * 检查是否可以创建选址报告
     *
     * @param customer
     * @return
     */
    Boolean checkCreateSelectAddressReport(CustomerEntity customer);

    /**
     * 检查是否能够上架客户
     *
     * @param customer
     * @return
     */
    Boolean checkUpShelfCustomer(CustomerEntity customer);

    /**
     * 检查是否能够进行客户收藏
     *
     * @param customer
     */
    Boolean checkCreateFavorite(CustomerEntity customer);

    /**
     * 检查是否能够进行客户分享
     * @param customer
     * @return
     */
    Boolean checkCreateShare(CustomerEntity customer);

    /**
     * 检查是否能够进行客户跟进查看
     * @param customer
     * @param checkApply 是否需要进行平台申请查看客户的检查
     * @return
     */
    Boolean checkSeeCusFollowup(CustomerEntity customer, Boolean checkApply);

    /**
     * 检查是否能够下架客户
     * @param customer
     * @return
     */
    Boolean checkDownCus(CustomerEntity customer);

    /**
     * 检查隐藏客户
     * @param customer
     * @return
     */
    Boolean checkOffOpenFlag(CustomerEntity customer);

    /**
     * 检查公开客户
     * @param customer
     * @return
     */
    Boolean checkOnOpenFlag(CustomerEntity customer);

    /**
     * 检查是否可以进行客户推送操作
     * @param customer
     * @return
     */
    Boolean checkCreateCusPush(CustomerEntity customer);

    /**
     * 检查是否可以进行客户智能匹配房源操作
     * @param customer
     * @return
     */
    Boolean checkMatchHos(CustomerEntity customer);

    /**
     * 检查创建合作
     * @param customer
     * @return
     */
    Boolean checkCreateCooperate(CustomerEntity customer);

    /**
     * 检查是否申请查看客户手机号
     * @param customer
     * @return
     */
    Boolean checkApplySeeCusPhone(CustomerEntity customer);

    /**
     * 检查是否可以创建拉私
     * @param customer
     * @return
     */
    Boolean checkCreatePullPrivate(CustomerEntity customer);

    /**
     * 查看客户详情，能否查看电话号码
     * @param customer
     * @return
     */
    Boolean checkCustomerPhoneCanSee(CustomerEntity customer,Boolean checkApply );

    /**
     * 客户号码申请查看是否可以操作
     * @param customer
     * @param cusPhone
     */
    void customerPhoneApplyCanRead(CustomerEntity customer, String cusPhone);

    /**
     * 客户操作权限检查
     * @return 是否有权限操作
     * @param checkCode
     * @param customer
     * @param cur
     */
    Boolean checkCusPermissions(String checkCode, CustomerEntity customer, UserEntity cur);

    /**
     * 检查是否可以隐藏客户
     */
    Boolean checkCanOffOpenFlagCus();

    Boolean checkPlatFormCusDeptAreaMatch(CustomerEntity customer, UserEntity cur);

}
