package com.kfwy.hkp.crm.customer.business.impl;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.gniuu.framework.common.context.CurrentContext;
import com.kfwy.hkp.base.CusCallPhoneRuleConfig;
import com.kfwy.hkp.base.SystemConfig;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerStatus;
import com.kfwy.hkp.crm.customer.enums.CustomerType;
import com.kfwy.hkp.hrm.org.dept.dto.CusServiceDto;
import com.kfwy.hkp.log.entity.OperationEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @AUTHOR 李文思汗
 * @Description 类描述:
 */
@Service(value = "k0CustomerCheckManager")
public class CustomerLevelK0CheckManagerImpl extends AbstractCustomerCheckManager {


    @Override
    public Boolean checkCusSawHouses(CustomerEntity customer) {
        Boolean canSeeHouses = false;
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();
        UserEntity ownerUser = userManager.selectUniqueByProp(customer.getEmpCode());

        //判断是平台客户还是经纪人类型和平台拉私客户
        if (CustomerType.PLATFORM.equals(customer.getCusType())) {
            if (this.checkPlatformCusApply(customer)) {
                canSeeHouses = true;
            }
        } else {
            if (this.checkIsMyCus(customer.getCusCode())
                    || this.checkSameDeptShare(cur, ownerUser, customer, true)
                    || this.checkCusSharePool(ownerUser, customer, true)
                    || this.checkCusPush(customer)
                    || this.checkCooperate(customer)) {
                canSeeHouses = true;
            }
        }
        return canSeeHouses;
    }

    @Override
    public Boolean checkCreateSelectAddressReport(CustomerEntity customer) {
        Boolean canCreateSelectAddress = false;
        UserEntity cur = userManager.selectUniqueByProp(CurrentContext.getUserCode());
        UserEntity owner = userManager.selectUniqueByProp(customer.getEmpCode());

        if (CustomerType.PLATFORM.equals(customer.getCusType())
                || CustomerType.FOCUS_CUS.equals (customer.getCusType ())) {
            canCreateSelectAddress = true;
        } else {
            if (this.checkIsMyCus(customer.getCusCode())
                    || this.checkSameDeptShare(cur, owner, customer, false)
                    || this.checkCusSharePool(owner, customer, false)
                    || this.checkCusPush(customer)
                    || this.checkCooperate(customer)) {
                canCreateSelectAddress = true;
            } else {
                throw new RemoveStackBusinessException ("当前客户是" + owner.getUserName() + "的客户，若要发送选址报告，需要与客户所属人建立合作关系");
            }
        }
        return canCreateSelectAddress;
    }

    @Override
    public Boolean checkCreateCusPush(CustomerEntity customer) {
        Boolean checkCreateCusPush = false;
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
        UserEntity owner = userManager.selectUniqueByProp(customer.getEmpCode());
        //当前人的部门需求区域满足客户的需求则可以推送
        if(CustomerType.PLATFORM.equals(customer.getCusType())){
            if(customer.getCustomerAreaEntityList() == null || customer.getCustomerAreaEntityList().size()==0){
                Map<String,Object> map = new HashMap<>(2);
                map.put("cusCode",customer.getCusCode());
                map.put("isDeleted",Boolean.FALSE);
                customer = customerDbDao.selectByMap(map).get(0);
            }
            checkCreateCusPush =  checkPlatFormCusDeptAreaMatch(customer,cur);
            if(!checkCreateCusPush){
                throw new RemoveStackBusinessException ("平台客户推送只允许负责区域的部门人员进行推送!");
            }
            return checkCreateCusPush;
        }else {

            if (checkIsMyCus (customer.getCusCode ()) ||
                checkSameDeptShare (cur,owner,customer,true) ||
                checkCusSharePool (owner,customer,true)){
                checkCreateCusPush = true;
            }
        }
        return checkCreateCusPush;
    }

    @Override
    public Boolean checkUpShelfCustomer(CustomerEntity customer) {
        Boolean isUpShelf = false;
        UserEntity cur = userManager.selectUniqueByProp(CurrentContext.getUserCode());
        UserEntity owner = userManager.selectUniqueByProp(customer.getEmpCode());


        if (CustomerType.PLATFORM.equals(customer.getCusType())) {
            isUpShelf = true;
        } else {
            if (this.checkIsMyCus(customer.getCusCode())
                    || this.checkSameDeptShare(cur, owner, customer, false)
                    || this.checkCusSharePool(owner, customer, false)
                    ) {
                isUpShelf = true;
            }
        }

        return isUpShelf;
    }

    @Override
    public Boolean checkCreateCooperate(CustomerEntity customer) {
        Boolean isCreateCooperate = false;
        if (!checkIsMyCus(customer.getCusCode())) {
            isCreateCooperate = true;
        }
        return isCreateCooperate;
    }

    @Override
    public Boolean checkApplySeeCusPhone(CustomerEntity customer) {
        Boolean isCheckApplySeeCusPhone = false;
        //如果是平台客户才能申请
        if (CustomerType.PLATFORM.equals(customer.getCusType())) {
            isCheckApplySeeCusPhone = true;
        }
        return isCheckApplySeeCusPhone;
    }

    @Override
    public Boolean checkCreatePullPrivate(CustomerEntity customer) {
        Boolean isCheckCreatePullPrivate = false;
        if (cusServiceCheckPull()){
            return true;
        }

        //如果是平台客户且申请查看过号码，且跟进过客户，才能对当前客户进行拉私
        if ((CustomerType.PLATFORM.equals(customer.getCusType()) || CustomerType.FOCUS_CUS.equals(customer.getCusType()))
                && checkPlatformCusApply(customer)) {
            checkPullPrivateByFollowup(customer.getCusCode());
        }
        isCheckCreatePullPrivate=this.checkCustomerApplyPrivate(customer);

        checkPullPrivateByHadPullPrivateCount(customer.getCusCode());

        return isCheckCreatePullPrivate;
    }
    public boolean cusServiceCheckPull() {
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();
        //配置的部门内的员工跳过拉私判断
        CusServiceDto cusServiceConfig = SystemConfig.create().getObject("cus_service_config", CusServiceDto.class);
        List<String> cusDeptCodes = cusServiceConfig.getDeptCodes();
        if (cusDeptCodes.contains(cur.getOwnerDeptCode())){
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkCustomerPhoneCanSee(CustomerEntity customer, Boolean checkApply) {
        Boolean checkCusPhoneCanSee = false;
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();
        UserEntity ownerUser = userManager.selectUniqueByProp(customer.getEmpCode());
        Boolean isNoPass = true;

        if (cur.getEnterType().equals("1")) {
            isNoPass = false;
            checkCusPhoneCanSee=true;
        }

        if (isNoPass) {
            //当前客户的所属人有部门，且有领导，则进入
            if (ownerUser.getOwnerDeptCode() != null) {

                UserEntity leader = userManager.selectUniqueByProp(deptManager.getDeptEntity(ownerUser.getOwnerDeptCode()).getLeaderCode());
                //当前客户所属人有领导
                if (leader != null) {
                    //当前操作人为领导或者客服则跳过判断
                    if (leader.getUserCode().equals(cur.getUserCode())) {

                        isNoPass = false;
                        checkCusPhoneCanSee = true;

                    } else {
                        //遍历当前操作人的所有子部门，如果子部门包含客户所属人的部门，则是大区总，大区总也可以做所有操作
                        List<String> deptCode = deptManager.getDownDeptCode(cur.getOwnerDeptCode());

                        if (deptCode != null
                                && deptCode.size() > 1
                                && deptCode.contains(ownerUser.getOwnerDeptCode())
                                && deptManager.getDeptEntity(cur.getOwnerDeptCode()).getLeaderCode().equals(cur.getUserCode())) {

                            isNoPass = false;
                            checkCusPhoneCanSee = true;

                        }
                    }
                }
            }
        }

        if (isNoPass) {
            //判断是平台客户还是经纪人类型和平台拉私客户
            if (CustomerType.PLATFORM.equals(customer.getCusType())) {
                //是否需要进行平台客户申请查看检查
                if (checkApply) {
                    if (checkPlatformCusApply(customer)) {
                        checkCusPhoneCanSee = true;
                    }
                } else {
                    //checkCusPhoneCanSee = checkPlatFormCusDeptAreaMatch(customer, cur);
                    checkCusPhoneCanSee = true;
                }
            }else if (CustomerType.FOCUS_CUS.equals(customer.getCusType())){
                if (checkFocusCus(customer)
                        || checkIsLeader(customer)
                        || checkFocusCusPush (customer)){
                    checkCusPhoneCanSee=true;
                }else{
                    throw new RemoveStackBusinessException("当前客户是集中获客类型客户，只能由优选推送的人员" +
                            "和客服单独推送的员工进行电话拨打！");
                }
            } else{
                if (checkIsMyCus(customer.getCusCode())
                        || checkCusPush(customer)
                        || checkCooperate(customer)
                        || checkCusSameDeptForCallPhone(ownerUser, customer)
                        || checkCusSharePoolForCallPhone(ownerUser, customer)) {
                    checkCusPhoneCanSee = true;
                }
            }
        } else {
            checkCusPhoneCanSee = true;
        }


        return checkCusPhoneCanSee;
    }

    private boolean checkFocusCusPush (CustomerEntity customer) {
        Boolean checkFocusCusPush = false;
        String preSql = "SELECT\n" + "\tCOUNT (*)\n" + "FROM\n" + "\tt_hkp_crm_customer_push push\n"
                + "INNER JOIN t_hkp_sys_user_user u ON push.push_code = u.user_code\n"
                + "WHERE\n"
                + "\tpush.cus_code = \'%s\'\n"
                + "AND push.receive_code = \'%s\'\n"
                + "AND push.push_open_flag = TRUE\n"
                + "AND u.enter_type = \'%s\'";
        Integer count = customerDbDao.countByNativeSql(String.format(preSql,customer.getCusCode() ,CurrentContext.getUserCode (),"1"));
        if (count>0){
            checkFocusCusPush=true;
        }
        return checkFocusCusPush;
    }

    protected boolean checkIsLeader(CustomerEntity customer) {
        boolean checkIsLeader = false;
        String preSql = "SELECT\n" +
                "\tCOUNT (*)\n" +
                "FROM\n" +
                "\t(\n" +
                "\t\tSELECT\n" +
                "\t\t\towner_dept_code\n" +
                "\t\tFROM\n" +
                "\t\t\tt_hkp_sys_user_user\n" +
                "\t\tWHERE\n" +
                "\t\t\tuser_code IN (\n" +
                "\t\t\t\tSELECT\n" +
                "\t\t\t\t\tuser_code\n" +
                "\t\t\t\tFROM\n" +
                "\t\t\t\t\tt_hkp_crm_focus_cus\n" +
                "\t\t\t\tWHERE\n" +
                "\t\t\t\t\tcus_code = \'%s\'\n" +
                "\t\t\t)\n" +
                "\t) ow\n" +
                "INNER JOIN t_hkp_hrm_org_dept dept ON dept_code = ow.owner_dept_code\n" +
                "WHERE\n" +
                "\tleader_code = \'%s\'";
        Integer count = customerDbDao.countByNativeSql(String.format(preSql,customer.getCusCode() ,CurrentContext.getUserCode()));
        if (count>0){
            checkIsLeader=true;
        }
        return checkIsLeader;
    }

    @Override
    protected void checkCusCallPhoneNum(CustomerEntity customer, UserEntity cur) {
        //跟进中客户一天可以拨打30次
        String followingMaxCallPhoneCountKey = "ky:hkp:callCusPhone:followingMaxCallPhoneCount";
        //已租好客户一天可以拨打40次
        String hasDealMaxCallPhoneCountKey = "ky:hkp:callCusPhone:hasDealMaxCallPhoneCount";
        String followupingKey = "ky:hkp:callCusPhone:following:"+cur.getUserCode();
        String hasDealKey = "ky:hkp:callCusPhone:hasDeal:"+cur.getUserCode ();

        List<String> cusList;

        if (customer.getCusStatus ().equals (CustomerStatus.FOLLOWING)){
            if(template.hasKey(followupingKey)){
                cusList = JSON.parseArray(template.opsForValue().get(followupingKey),String.class);
                //今天没有拨打电话的，要记录次数
                if(!cusList.contains(customer.getCusCode())){
                    int max;
                    if(template.hasKey(followingMaxCallPhoneCountKey)){
                        max = Integer.parseInt(template.opsForValue().get(followingMaxCallPhoneCountKey));
                    }else {
                        max = 30;
                        template.opsForValue().set(followingMaxCallPhoneCountKey,max+"");
                    }
                    if(cusList.size() >= max){
                        throw new RemoveStackBusinessException("今天拨打电话已超上限！");
                    }else {
                        cusList.add(customer.getCusCode());
                        template.opsForValue().set(followupingKey,JSON.toJSONString(cusList),getEndTime(), TimeUnit.MILLISECONDS);
                    }
                }
            }else {
                cusList = new ArrayList<>();
                cusList.add(customer.getCusCode());
                template.opsForValue().set(followupingKey, JSON.toJSONString(cusList),getEndTime(), TimeUnit.MILLISECONDS);
            }
        }else if (customer.getCusStatus ().equals (CustomerStatus.HASDEAL)){
            if(template.hasKey(hasDealKey)){
                cusList = JSON.parseArray(template.opsForValue().get(hasDealKey),String.class);
                //今天没有拨打电话的，要记录次数
                if(!cusList.contains(customer.getCusCode())){
                    int max;
                    if(template.hasKey(hasDealMaxCallPhoneCountKey)){
                        max = Integer.parseInt(template.opsForValue().get(hasDealMaxCallPhoneCountKey));
                    }else {
                        max = 40;
                        template.opsForValue().set(hasDealMaxCallPhoneCountKey,max+"");
                    }
                    if(cusList.size() >= max){
                        throw new RemoveStackBusinessException("今天拨打电话已超上限！");
                    }else {
                        cusList.add(customer.getCusCode());
                        template.opsForValue().set(hasDealKey,JSON.toJSONString(cusList),getEndTime(), TimeUnit.MILLISECONDS);
                    }
                }
            }else {
                cusList = new ArrayList<>();
                cusList.add(customer.getCusCode());
                template.opsForValue().set(hasDealKey, JSON.toJSONString(cusList),getEndTime(), TimeUnit.MILLISECONDS);
            }
        }else{
            throw new RemoveStackBusinessException ("该客户的状态未知~");
        }


    }

    @Override
    public Boolean checkCreateCusFollowup(CustomerEntity customer, Boolean checkApply) {
        Boolean canFollowp = true;
       /* UserEntity cur = (UserEntity) CurrentContext.getUserInfo();
        UserEntity ownerUser = userManager.findUserByUserCode(customerEntity.getEmpCode());
        //判断是平台客户还是经纪人类型和平台拉私客户
        if (CustomerType.PLATFORM.equals(customerEntity.getCusType())) {
            //如果要求判断是否进行了平台客户申请查看检查则checkApply为true
            if (checkApply) {
                if (this.checkPlatformCusApply(customerEntity)) {
                    canFollowp = true;
                }
            } else {
                canFollowp = true;
            }
        } else {
            if (this.checkIsMyCus(customerEntity.getCusCode())
                    || this.checkSameDeptShare(cur, ownerUser, customerEntity, false)
                    || this.checkCusSharePool(ownerUser, customerEntity, false)
                    || this.checkCusPush(customerEntity)
                    || this.checkCooperate(customerEntity)) {
                canFollowp = true;
            }
        }*/

        return canFollowp;
    }


    @Override
    protected boolean checkCustomerRelease(CustomerEntity customer, boolean b) {
        Boolean checkCustomerRelease = false;
        CustomerEntity cus = customerManager.findOne("cusCode", customer.getCusCode());
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();
        UserEntity ownerUser = userManager.selectUniqueByProp(cus.getEmpCode());
        Boolean isNoPass = true;

        if (cur.getEnterType().equals("1")) {
            isNoPass = false;
            checkCustomerRelease = true;
        }

        if (isNoPass) {
            //当前操作人不是客户所属人，则判断是否是领导
            if (checkIsMyCus(customer.getCusCode())) {
                checkCustomerRelease = true;
            }else if (ownerUser.getOwnerDeptCode() != null){

                UserEntity leader =userManager.selectUniqueByProp(deptManager.getDeptEntity(ownerUser.getOwnerDeptCode()).getLeaderCode());
                //当前客户所属人有领导
                if (leader != null) {
                    //当前操作人为领导或者客服则跳过判断
                    if (leader.getUserCode().equals(cur.getUserCode())) {
                        isNoPass = false;
                        checkCustomerRelease = true;
                    } else {
                        //遍历当前操作人的所有子部门，如果子部门包含客户所属人的部门，则是大区总，大区总也可以做所有操作
                        List<String> deptCode = deptManager.getDownDeptCode(cur.getOwnerDeptCode());
                        if (deptCode != null
                                && deptCode.size() > 1
                                && deptCode.contains(ownerUser.getOwnerDeptCode())) {

                            isNoPass = false;
                            checkCustomerRelease = true;
                        }
                    }
                }
            }
        }

        return checkCustomerRelease;

    }

    @Override
    public Boolean checkCreateShare(CustomerEntity customer) {
        return true;
    }

    @Override
    public Boolean checkCustomerInfoCanUpdate(CustomerEntity customer, Boolean checkApply) {
        Boolean canUpdate = false;
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();
        UserEntity ownerUser = userManager.selectUniqueByProp(customer.getEmpCode());

        //判断是平台客户还是经纪人类型和平台拉私客户
        if (CustomerType.PLATFORM.equals(customer.getCusType())) {
            //是否需要进行平台客户申请查看检查
            if (checkApply) {
                if (checkPlatformCusApply(customer)) {
                    canUpdate = true;
                }
            } else {
                canUpdate = true;
            }
        } else {
            if (checkIsMyCus(customer.getCusCode())
                    || checkSameDeptShare(cur, ownerUser, customer, true)
                    || checkCusSharePool(ownerUser, customer, true)) {
                canUpdate = true;
            }
        }

        return canUpdate;
    }

    @Override
    public Boolean checkSeeCusFollowup(CustomerEntity customer, Boolean checkApply) {
        /*Boolean canSeeFollowp = false;
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();
        UserEntity ownerUser = userManager.findUserByUserCode(customer.getEmpCode());
        //判断是平台客户还是经纪人类型和平台拉私客户

        if (CustomerType.PLATFORM.equals(customer.getCusType())) {

            if (checkApply) {
                if (this.checkPlatformCusApply(customer)) {
                    canSeeFollowp = true;
                }
            } else {
                canSeeFollowp = true;
            }
        } else {

            if (cur.getUserCode().equals(ownerUser.getUserCode())
                    || this.checkSameDeptShare(cur, ownerUser, customer, true)
                    || this.checkCusSharePool(ownerUser, customer, true)
                    || this.checkCusPush(customer)
                    || this.checkCooperate(customer)
                    || customer.getOpenFlag()==true) {
                canSeeFollowp = true;
            }
        }

        return canSeeFollowp;*/
        return true;
    }

    @Override
    protected boolean checkCustomerCallPhoneTimeInterval (CustomerEntity customer, UserEntity cur) {
        Boolean checkCustomerCallPhoneTimeInterval = true;
        CusCallPhoneRuleConfig callPhoneRule = SystemConfig.create ().getObject ("cus_call_phone_rule", CusCallPhoneRuleConfig.class);
        Map callPhoneTimeMap = MapUtil.newHashMap ();
        callPhoneTimeMap.put ("bizCode",customer.getCusCode ());
        callPhoneTimeMap.put ("userCode",cur.getUserCode ());
        OperationEntity cusPhoneLog = operationManager.getLastCallCusPhoneLog (callPhoneTimeMap);
        if (cusPhoneLog!=null){
            Date intervalTime = DateFormatUtil.addDate (cusPhoneLog.getCreateTime (), callPhoneRule.getCallPhoneInterval (), callPhoneRule.getCallPhoneUnit ());
            if (intervalTime.compareTo (DateFormatUtil.getCurrentDateTime ())>=0){
                long diff = intervalTime.getTime () - DateFormatUtil.getCurrentDateTime ().getTime ();
                long day = diff / (24 * 60 * 60 * 1000);
                long hour = (diff / (60 * 60 * 1000) - day * 24);
                long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
                long s = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

                throw new RemoveStackBusinessException ("当前客户被其他业务员拨打过电话，为了保护客户不被频繁骚扰,请稍后再进行拨打！该客户还需"+min+"分钟"+s+"秒才能拨打");
            }
        }


        return checkCustomerCallPhoneTimeInterval;
    }

    @Override
    protected boolean checkCusCallPhoneRule (CustomerEntity customer, UserEntity cur) {
        boolean checkCusCallPhoneRule = false;
        return checkCusCallPhoneRule;
    }

    @Override
    protected boolean checkCusToDayCallPhoneCount (CustomerEntity customer, UserEntity cur) {
        Boolean checkCusToDayCallPhonePeopleCount = true;
        CusCallPhoneRuleConfig callPhoneRule = SystemConfig.create ().getObject ("cus_call_phone_rule", CusCallPhoneRuleConfig.class);
        Map callPhoneCountMap = MapUtil.newHashMap ();
        callPhoneCountMap.put ("cusCode",customer.getCusCode ());
        callPhoneCountMap.put ("createTimeStart", DateFormatUtil.addDate (DateFormatUtil.getCurrentDateTime (),callPhoneRule.getCallPhoneCountInterval (),"DD"));
        List<String> callPhonePeopleCount = operationManager.getCallPhonePeopleCount (callPhoneCountMap);

        //拨打客户的人数达到上限，且当前拨打人不是客户所属人,且不是今天已经拨打过该号码的几个人
        if (callPhonePeopleCount!=null && callPhonePeopleCount.size ()>=callPhoneRule.getCallPhoneCount ()
                && !customer.getEmpCode ().equals (cur.getUserCode ())
                && !callPhonePeopleCount.contains (cur.getUserCode ())){
            checkCusToDayCallPhonePeopleCount = false;
        }

        return checkCusToDayCallPhonePeopleCount;
    }
    @Override
    public  Boolean checkOffOpenFlag(CustomerEntity customer){
        Boolean isOffOpenFlag = false;
        if (this.checkIsMyCus(customer.getCusCode())&&this.checkCustomerApplyHidden(customer)) {
            isOffOpenFlag = true;
        }
        return isOffOpenFlag;
    }


    @Override
    protected boolean checkCreateCus(CustomerEntity customer) {
        Boolean canCreate = true;
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();
        UserEntity ownerUser = userManager.selectUniqueByProp(customer.getEmpCode());
        if (cur.getUserCode().equals(ownerUser.getUserCode())
                || customer.getCusType().equals(CustomerType.PLATFORM)
                || customer.getCusType().equals(CustomerType.PRIVATE)
                || customer.getCusType().equals(CustomerType.FOCUS_CUS)
                || checkSameDeptShare(cur,ownerUser,customer,false)
                || checkCusSharePool(ownerUser,customer,false)
                || checkCusPush(customer)
                || checkCooperate(customer)
                ){
            canCreate=false;
        }

        return canCreate;
    }



}
