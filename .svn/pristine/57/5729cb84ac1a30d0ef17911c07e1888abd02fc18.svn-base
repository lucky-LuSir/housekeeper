package com.kfwy.hkp.crm.customer.business.impl;

import com.alibaba.fastjson.JSON;
import com.kfwy.hkp.common.annotion.HandlerType;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerType;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserLevel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @AUTHOR 李文思汗
 * @Description 类描述:
 */
@HandlerType(UserLevel.K2)
@Service(value = "k2CustomerCheckManager")
public class CustomerLevelK2CheckManagerImpl extends AbstractCustomerCheckManager {


    @Override
    public Boolean checkCusSawHouses(CustomerEntity customer) {
        Boolean canSeeHouses = false;

        if (checkIsMyCus(customer.getCusCode())) {
            canSeeHouses = true;
        } else {
            throw new RemoveStackBusinessException ("当前客户不是你的所属客户，不能对其进行客户看房记录操作");
        }

        return canSeeHouses;
    }

    @Override
    public Boolean checkCreateSelectAddressReport(CustomerEntity customer) {
        Boolean canCreateSelectAddress = false;
        UserEntity owner = userManager.findUserByUserCode(customer.getEmpCode());

        if (CustomerType.PLATFORM.equals(customer.getCusType())) {
            canCreateSelectAddress = true;
        } else {
            if (this.checkIsMyCus(customer.getCusCode())
                    || this.checkCusPush(customer)
                    || this.checkCooperate(customer)) {
                canCreateSelectAddress = true;
            } else {
                throw new RemoveStackBusinessException("当前客户是" + owner.getUserName() + "的客户，若要发送选址报告，需要与客户所属人建立合作关系");
            }
        }
        return canCreateSelectAddress;
    }


    @Override
    public Boolean checkUpShelfCustomer(CustomerEntity customer) {
        Boolean isUpShelf = false;

        if (this.checkIsMyCus(customer.getCusCode())) {
            isUpShelf = true;
        }

        return isUpShelf;
    }


    @Override
    public Boolean checkCreateCooperate(CustomerEntity customer) {
        throw new RemoveStackBusinessException("您当前暂时不能使用合作功能，若您需要使用合作，请联系工作人员开通认证付费权限！");
    }

    @Override
    public Boolean checkApplySeeCusPhone(CustomerEntity customer) {
        throw new RemoveStackBusinessException("平台获客只有库房无忧合作经纪人和K1认证付费用户才可以免费申请查看号码哦！");
    }

    @Override
    public Boolean checkCreatePullPrivate(CustomerEntity customer) {
        throw new RemoveStackBusinessException("平台拉私只有库房无忧合作经纪人和K1认证付费用户才可以使用哦！");
    }

    @Override
    public Boolean checkCustomerPhoneCanSee(CustomerEntity customer, Boolean checkApply) {
        Boolean checkCusPhoneCanSee = false;

        //判断是平台客户还是经纪人类型和平台拉私客户
        if (CustomerType.PLATFORM.equals(customer.getCusType())) {
            checkCusPhoneCanSee = false;
        } else {
            if (checkIsMyCus(customer.getCusCode())
                    || checkCusPush(customer)
                    || checkCooperate(customer)) {
                checkCusPhoneCanSee = true;
            }
        }

        return checkCusPhoneCanSee;
    }

    @Override
    public Boolean checkCreateCusFollowup(CustomerEntity customer, Boolean checkApply) {
        Boolean canFollowp = false;

        //判断是平台客户还是经纪人类型和平台拉私客户
        if (CustomerType.PLATFORM.equals(customer.getCusType())) {
            //如果要求判断是否进行了平台客户申请查看检查则checkApply为true
            if (checkApply) {
                if (this.checkPlatformCusApply(customer)) {
                    canFollowp = true;
                }
            } else {
                canFollowp = true;
            }
        } else {
            if (this.checkIsMyCus(customer.getCusCode())
                    || this.checkCusPush(customer)
                    || this.checkCooperate(customer)) {
                canFollowp = true;
            }
        }

        return canFollowp;
    }



    @Override
    protected boolean checkCustomerRelease(CustomerEntity customer, boolean b) {
        return false;
    }

    @Override
    protected void checkCusCallPhoneNum(CustomerEntity customer, UserEntity cur) {
        //k2默认最大值的键
        String maxKey = "ky:hkp:callCusPhone:k2max";
        String cusKey = "ky:hkp:callCusPhone:cus:"+cur.getUserCode();

        List<String> cusList;

        if(template.hasKey(cusKey)){
            cusList = JSON.parseArray(template.opsForValue().get(cusKey),String.class);
            //今天没有拨打电话的，要记录次数
            if(!cusList.contains(customer.getCusCode())){
                int max;
                if(template.hasKey(maxKey)){
                    max = Integer.parseInt(template.opsForValue().get(maxKey));
                }else {
                    max = 3;
                    template.opsForValue().set(maxKey,max+"");
                }
                if(cusList.size() >= max){
                    throw new RemoveStackBusinessException("今天拨打电话已超上限！");
                }else {
                    cusList.add(customer.getCusCode());
                    template.opsForValue().set(cusKey,JSON.toJSONString(cusList),getEndTime(), TimeUnit.MILLISECONDS);
                }
            }
        }else {
            cusList = new ArrayList<>();
            cusList.add(customer.getCusCode());
            template.opsForValue().set(cusKey, JSON.toJSONString(cusList),getEndTime(), TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public Boolean checkCreateShare(CustomerEntity customer) {
        return true;
    }

    @Override
    public Boolean checkCustomerInfoCanUpdate(CustomerEntity customer, Boolean checkApply) {
        Boolean canUpdate = false;

        //判断是平台客户还是经纪人类型和平台拉私客户
        if (CustomerType.PLATFORM.equals(customer.getCusType())) {
            throw new RemoveStackBusinessException("您没有权限修改平台客户的信息哦!");
        } else {
            if (checkIsMyCus(customer.getCusCode())) {
                canUpdate = true;
            } else {
                throw new RemoveStackBusinessException("当前客户不是你的所属客户，不能进行修改操作哦!");
            }
        }
        return canUpdate;
    }

    @Override
    public Boolean checkSeeCusFollowup(CustomerEntity customer, Boolean checkApply) {
        Boolean canSeeFollowp = false;

        //判断是平台客户还是经纪人类型和平台拉私客户
        if (CustomerType.PLATFORM.equals(customer.getCusType())) {
            throw new RemoveStackBusinessException("抱歉!您不能对平台客户资源进行跟进记录查询!");
        } else {

            if (this.checkIsMyCus(customer.getCusCode())
                    || this.checkCusPush(customer)
                    || this.checkCooperate(customer)) {
                canSeeFollowp = true;
            }

        }

        return canSeeFollowp;
    }

    @Override
    protected boolean checkCustomerCallPhoneTimeInterval (CustomerEntity customer, UserEntity cur) {
        return true;
    }

    @Override
    protected boolean checkCusToDayCallPhoneCount (CustomerEntity customer, UserEntity cur) {
        return true;
    }

    @Override
    public Boolean checkOffOpenFlag(CustomerEntity customer) {
        Boolean isOffOpenFlag = false;
        if (this.checkIsMyCus(customer.getCusCode())) {
            isOffOpenFlag = true;
        }
        return isOffOpenFlag;
    }

    @Override
    protected boolean checkCreateCus(CustomerEntity customer) {
        Boolean canCreate = true;
        if (checkCustomerPhoneCanSee(customer,true)){
            canCreate=false;
        }
        return canCreate;
    }


}
