package com.kfwy.hkp.crm.customer.business.impl;

import cn.hutool.core.map.MapUtil;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.user.dao.IBaseUserDbDao;
import com.kfwy.hkp.base.CusCallPhoneRuleConfig;
import com.kfwy.hkp.base.SystemConfig;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.cooperate.dao.ICooperateDbDao;
import com.kfwy.hkp.cooperate.entity.CooperateEntity;
import com.kfwy.hkp.crm.customer.business.ICustomerCheckManager;
import com.kfwy.hkp.crm.customer.business.ICustomerManager;
import com.kfwy.hkp.crm.customer.business.IFocusCusManager;
import com.kfwy.hkp.crm.customer.dao.*;
import com.kfwy.hkp.crm.customer.entity.CustomerApplyHidOrPriEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerAreaEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.entity.FocusCusEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerApplyHidOrPriStatus;
import com.kfwy.hkp.crm.customer.enums.CustomerApplyHidOrPriType;
import com.kfwy.hkp.crm.customer.enums.CustomerCheckType;
import com.kfwy.hkp.crm.customer.enums.CustomerType;
import com.kfwy.hkp.hos.house.dao.ISharePoolDeptDbDao;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.dao.IDeptDbDao;
import com.kfwy.hkp.hrm.org.dept.entity.DeptAreaEntity;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.log.manager.IOperationManager;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserAreaEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserLevel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.*;

/**
 * @AUTHOR 李文思汗
 * @Description 类描述:抽象客户操作检查管理类
 */
public abstract class AbstractCustomerCheckManager implements ICustomerCheckManager {

    @Autowired
    protected IOperationManager operationManager;
    @Autowired
    protected ICustomerManager customerManager;

    @Autowired
    protected IBaseUserDbDao<UserEntity> loginUserDbDao;

    @Autowired
    protected IUserManager userManager;
    @Autowired
    protected IDeptManager deptManager;
    @Autowired
    protected ICustomerDbDao customerDbDao;
    @Autowired
    protected ICustomerApplyDbDao customerApplyDbDao;
    @Autowired
    protected ICustomerFollowupDbDao customerFollowupDbDao;
    @Autowired
    protected ICooperateDbDao cooperateDbDao;
    @Autowired
    protected ISharePoolDeptDbDao sharePoolDeptDbDao;
    @Autowired
    protected IDeptDbDao deptDbDao;
    @Autowired
    protected SystemConfig systemConfig;
    @Autowired
    protected IFocusCusManager iFocusCusManager;
    @Autowired
    protected StringRedisTemplate template;
    @Autowired
    protected ICustomerApplyHidOrPriDbDao customerApplyHidOrPriDbDao;
    @Autowired
    protected IFocusCusDbDao focusCusDbDao;

    @Override
    public Boolean checkIsMyCus (String cusCode) {
        Boolean isMyCus = false;

        CustomerEntity customer = customerManager.findOne ("cusCode", cusCode);

        if (! Objects.equals (customer.getCusType (), CustomerType.PLATFORM) && customer.getEmpCode ().equals (CurrentContext.getUserCode ())) {
            isMyCus = true;
        }

        return isMyCus;
    }

    @Override
    public Boolean checkCustomerApplyPrivate (CustomerEntity customer) {
        Boolean chech = Boolean.TRUE;
        if (customer.getCusType ().equals (CustomerType.PRIVATE)) {
            throw new RemoveStackBusinessException ("客户已经被拉私，不能再次发起申请");
        }
        if (! customer.getCusType ().equals (CustomerType.PLATFORM) && ! customer.getCusType ().equals (CustomerType.FOCUS_CUS)) {
            throw new RemoveStackBusinessException ("客户不是平台客户，不能发起拉私申请");
        }
        Map<String, Object> map = new HashMap<String, Object> ();
        map.put ("createCode", CurrentContext.getUserCode ());
        map.put ("status", CustomerApplyHidOrPriStatus.PENDING);
        map.put ("type", CustomerApplyHidOrPriType.PRIVATE);
        int count = customerApplyHidOrPriDbDao.selectCount ("selectByMapCount", map);
        if (count > 5) {
            throw new RemoveStackBusinessException ("您的未处理拉私申请已经超过5条，不能再发起申请");
        }
        Map<String, Object> map2 = new HashMap<String, Object> ();
        map2.put ("ownerCode", customer.getCusCode ());
        map2.put ("status", CustomerApplyHidOrPriStatus.PENDING);
        CustomerApplyHidOrPriEntity ahop = customerApplyHidOrPriDbDao.selectUniqueByMap (map2);
        if (ahop != null) {
            throw new RemoveStackBusinessException ("该客户已经被" + ahop.getCreateName () + "发起申请,您不能再次发起申请");
        }
        return chech;
    }

    @Override
    public Boolean checkCustomerApplyHidden (CustomerEntity customer) {
        Boolean chech = Boolean.TRUE;
        Map<String, Object> map = new HashMap<String, Object> ();
        map.put ("createCode", CurrentContext.getUserCode ());
        map.put ("status", CustomerApplyHidOrPriStatus.PENDING);
        map.put ("type", CustomerApplyHidOrPriType.HIDDEN);
        int count = customerApplyHidOrPriDbDao.selectCount ("selectByMapCount", map);
        if (count > 5) {
            throw new RemoveStackBusinessException ("您的未处理隐藏申请已经超过5条，不能再发起申请");
        }
        Map<String, Object> map2 = new HashMap<String, Object> ();
        map2.put ("ownerCode", customer.getCusCode ());
        map2.put ("status", CustomerApplyHidOrPriStatus.PENDING);
        map2.put ("createCode", CurrentContext.getUserCode ());
        int count2 = customerApplyHidOrPriDbDao.selectCount ("selectByMapCount", map2);
        if (count2 > 0) {
            throw new RemoveStackBusinessException ("你已经对该客户发起隐藏申请,您不能再次发起申请");
        }
        return chech;
    }

    @Override
    public Boolean checkSameDeptShare (UserEntity cur, UserEntity ownerUser, CustomerEntity customerEntity, Boolean checkOpenFlag) {
        Boolean isSameDept = false;

        //当前客户所属经纪人有所属部门，且和当前操作人同部门则进入
        if (ownerUser.getOwnerDeptCode () != null && StringUtils.isNotEmpty (cur.getOwnerDeptCode ()) && cur.getOwnerDeptCode ().equals (ownerUser.getOwnerDeptCode ())) {

            DeptEntity deptEntity = deptManager.getDeptEntity (cur.getOwnerDeptCode ());
            //是否判断客户公开，如果需要判断，则被隐藏的客户，就算是部门内部共享，也不能执行操作
            if (checkOpenFlag) {
                //当前登陆人部门是否和客户所有人部门一致，如果一致则判断该部门是否开启部门内客户电话共享，如果开启则返回电话号码
                if (deptEntity.getHasShareCus ().equals (Boolean.TRUE) && customerEntity.getOpenFlag () == true && ! customerEntity.getCusType ().equals (CustomerType.PRIVATE)) {
                    isSameDept = true;
                }
            } else {
                isSameDept = true;
            }

        }
        return isSameDept;
    }

    @Override
    public Boolean checkCusSharePool (UserEntity ownerUser, CustomerEntity customer, Boolean checkOpenFlag) {
        Boolean isInSharePool = false;
        //不是同一个部门才去做共享池
        if (StringUtils.isNotEmpty (CurrentContext.getUserInfo ().getOwnerDeptCode ()) && StringUtils.isNotEmpty (ownerUser.getOwnerDeptCode ()) && ! CurrentContext.getUserInfo ().getOwnerDeptCode ().equals (ownerUser.getOwnerDeptCode ())) {
            String preSql = "SELECT COUNT(*) FROM t_hkp_share_pool_dept" + " WHERE share_dept_code=\'%s\'" + "AND share_type=\'cus\'" + "AND share_code IN (" + "SELECT share_code FROM t_hkp_share_pool_dept" + " WHERE share_dept_code = \'%s\'" + "AND share_type=\'cus\'" + "GROUP BY share_code)";
            String sql = String.format (preSql, CurrentContext.getUserInfo ().getOwnerDeptCode (), ownerUser.getOwnerDeptCode ());
            int count = sharePoolDeptDbDao.countByNativeSql (sql);
            if (checkOpenFlag) {
                if (count > 0 && customer.getOpenFlag () == true && ! customer.getCusType ().equals (CustomerType.PRIVATE)) {
                    isInSharePool = true;
                }
            } else {
                if (count > 0) {
                    isInSharePool = true;
                }
            }
        }
        return isInSharePool;
    }

    @Override
    public Boolean checkCusSharePoolForCallPhone (UserEntity ownerUser, CustomerEntity customer) {
        Boolean checkCusSharePoolForCallPhone = false;
        UserEntity cur = (UserEntity)CurrentContext.getUserInfo ();
        if (ownerUser.getOwnerDeptCode ()!=null){
            DeptEntity entity = deptManager.getDeptEntity (cur.getOwnerDeptCode ());
            if (entity.getAgentCusSeeTimeLimit ()) {
                if (checkCusSharePool (ownerUser, customer, true) && checkCreateTime (customer)) {
                    checkCusSharePoolForCallPhone = true;
                }
            }else{
                if (checkCusSharePool (ownerUser, customer, true)) {
                    checkCusSharePoolForCallPhone = true;
                }
            }
        }
        return checkCusSharePoolForCallPhone;
    }

    /**
     * 检查客户创建时间区间
     *
     * @param customer
     * @return
     */
    private boolean checkCreateTime (CustomerEntity customer) {
        CusCallPhoneRuleConfig callPhoneRule = SystemConfig.create ().getObject ("cus_call_phone_rule", CusCallPhoneRuleConfig.class);
        Date intervalTime = DateFormatUtil.dayBegin (DateFormatUtil.addDate (customer.getCreateTime (), callPhoneRule.getCallPhoneCreateTimeInterval (), "DD"));
        if (intervalTime.compareTo (DateFormatUtil.getCurrentDateTime ()) >= 0) {
            long diff = intervalTime.getTime () - DateFormatUtil.getCurrentDateTime ().getTime ();
            long day = diff / (24 * 60 * 60 * 1000);
            long hour = (diff / (60 * 60 * 1000) - day * 24);
            long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

            throw new RemoveStackBusinessException ("个人客户初次录入保护，\n还需" + day + "天" + hour + "小时" + min + "分钟" + s + "秒才能拨打");
        }
        return true;
    }

    private void checkFocusCusSeeTime (Date focusCusSeeTime) {
        long diff = focusCusSeeTime.getTime () - DateFormatUtil.getCurrentDateTime ().getTime ();
        long day = diff / (24 * 60 * 60 * 1000);
        long hour = (diff / (60 * 60 * 1000) - day * 24);
        long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        throw new RemoveStackBusinessException ("端口发布人获客保护中，还需" + min + "分钟" + s + "秒才能拨打");
    }

    @Override
    public Boolean checkCusSameDeptForCallPhone (UserEntity ownerUser, CustomerEntity customer) {
        UserEntity cur = (UserEntity)CurrentContext.getUserInfo ();
        if (ownerUser.getOwnerDeptCode ()!=null){
            DeptEntity entity = deptManager.getDeptEntity (ownerUser.getOwnerDeptCode ());
            if (entity.getAgentCusSeeTimeLimit ()){
                if (checkSameDeptShare (cur, ownerUser, customer, true)
                        && checkCreateTime (customer)) {
                    return true;
                }
            }else{
                if (checkSameDeptShare (cur, ownerUser, customer, true)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean checkPlatformCusApply (CustomerEntity customer) {
        Boolean isApply = true;
/*        String preSql = "select count(*) from t_hkp_crm_apply " +
                " where emp_code=\'%s\' and cus_code=\'%s\'";
        String sql = String.format(preSql, CurrentContext.getUserCode(), customer.getCusCode());
        int count = customerApplyDbDao.countByNativeSql(sql);
        if (count > 0) {
            isApply = true;
        }*/
        return isApply;
    }

    @Override
    public Boolean checkCusPush (CustomerEntity customer) {
        Boolean isPush = false;
        //查看是否有推送
        String preSql = "select count(*) from t_hkp_crm_customer_push " + "where cus_code=\'%s\' and (receive_code=\'%s\'%s) and push_open_flag = true";
        //判断是否有部门，也可以判断是否是独立经纪人
        String tmp = " ";
        //所属部门不为空则进入
        if (StringUtils.isNotEmpty (CurrentContext.getUserInfo ().getOwnerDeptCode ())) {
            tmp = " or receive_dept_code=\'" + CurrentContext.getUserInfo ().getOwnerDeptCode () + "\'";
        }
        String sql = String.format (preSql, customer.getCusCode (), CurrentContext.getUserCode (), tmp);
        int count = customerDbDao.countByNativeSql (sql);
        if (count > 0) {
            isPush = true;
        }
        return isPush;
    }

    @Override
    public Boolean checkCooperate (CustomerEntity customer) {
        String userCode = CurrentContext.getUserCode ();
        String preSql = "select * from t_hkp_union_cooperate " + "where cus_code=\'%s\' " + "and is_deleted=false " + "and coo_status in ('3','4','5') " + "and (apply_code=\'%s\' or receive_code=\'%s\')" + "order by create_time desc limit 1";
        String sql = String.format (preSql, customer.getCusCode (), userCode, userCode);

        CooperateEntity cooperateEntity = cooperateDbDao.selectOneByNativeSql (sql);

        if (null != cooperateEntity && cooperateEntity.getCooOpenFlag ()) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkCusFollowupDetail (CustomerEntity customer) {
        return checkCreateCusFollowup (customer, true);
    }


    @Override
    public abstract Boolean checkOffOpenFlag (CustomerEntity customer);

    @Override
    public Boolean checkOnOpenFlag (CustomerEntity customer) {
        Boolean isOnOpenFlag = false;
        if (this.checkIsMyCus (customer.getCusCode ())) {
            isOnOpenFlag = true;
        }
        return isOnOpenFlag;
    }


    @Override
    public Boolean checkCreateCusPush (CustomerEntity customer) {
        Boolean isCreatePush = false;
        if (this.checkIsMyCus (customer.getCusCode ())) {
            isCreatePush = true;
        }
        return isCreatePush;
    }

    @Override
    public Boolean checkMatchHos (CustomerEntity customer) {
        return true;
    }

    @Override
    public abstract Boolean checkCusSawHouses (CustomerEntity customer);

    @Override
    public abstract Boolean checkCreateSelectAddressReport (CustomerEntity customer);

    @Override
    public abstract Boolean checkUpShelfCustomer (CustomerEntity customer);

    @Override
    public abstract Boolean checkCreateCooperate (CustomerEntity customer);

    @Override
    public abstract Boolean checkApplySeeCusPhone (CustomerEntity customer);

    @Override
    public abstract Boolean checkCreatePullPrivate (CustomerEntity customer);

    @Override
    public abstract Boolean checkCustomerPhoneCanSee (CustomerEntity customer, Boolean checkApply);

    @Override
    public void customerPhoneApplyCanRead (CustomerEntity customer, String cusPhone) {
        //检查平台客户申请
        if (checkPlatformCusApply (customer)) {
            customer.setCusPhone (cusPhone);
        } else {
            customer.setCusPhone (null);
        }
    }

    protected boolean checkFocusCus (CustomerEntity customer) {
        boolean checkFocusCus = false;
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
        DeptEntity entity = deptManager.getDeptEntity (cur.getOwnerDeptCode ());
        if (entity.getFocusCusSeeTimeLimit ()) {
            String sql = "SELECT\n" + "\t*\n" + "FROM\n" + "\tt_hkp_crm_focus_cus\n" + "WHERE\n" + "\tuser_code = \'%s\'\n" + "\tAND cus_code =\'%s\' limit 1";
            FocusCusEntity focusCusEntity = focusCusDbDao.selectOneByNativeSql (String.format (sql, CurrentContext.getUserCode (), customer.getCusCode ()));
            if (focusCusEntity != null) {
                if (focusCusEntity.getSeeCusTime ().compareTo (DateFormatUtil.getCurrentDateTime ())<=0){
                    checkFocusCus = true;
                }else{
                    checkFocusCusSeeTime(focusCusEntity.getSeeCusTime ());
                }
            }
        } else {
            String preSql = "SELECT\n" + "\tCOUNT (*)\n" + "FROM\n" + "\tt_hkp_crm_focus_cus\n" + "WHERE\n" + "\tuser_code = \'%s\'\n" + "\tAND cus_code =\'%s\' limit 1";
            Integer count = customerDbDao.countByNativeSql (String.format (preSql, CurrentContext.getUserCode (), customer.getCusCode ()));
            if (count > 0) {
                checkFocusCus = true;
            }
        }
        return checkFocusCus;
    }

    @Override
    public abstract Boolean checkCreateCusFollowup (CustomerEntity customer, Boolean checkApply);

    @Override
    public abstract Boolean checkCreateShare (CustomerEntity customer);

    @Override
    public abstract Boolean checkCustomerInfoCanUpdate (CustomerEntity customer, Boolean checkApply);

    @Override
    public abstract Boolean checkSeeCusFollowup (CustomerEntity customer, Boolean checkApply);

    @Override
    public Boolean checkCreateFavorite (CustomerEntity customer) {
        return true;
    }

    @Override
    public Boolean checkDownCus (CustomerEntity customer) {
        return true;
    }

    @Override
    public Boolean checkCusPermissions (String checkCode, CustomerEntity customer, UserEntity cur) {
        switch (checkCode) {
            case CustomerCheckType.CHECK_CUSTOMER_CALL_PHONE_TIME_INTERVAL:
                if (! checkCustomerCallPhoneTimeInterval (customer, cur)) {
                    throw new RemoveStackBusinessException ("半个小时内该客户被其他员工拨打过电话，为了保护客户不被频繁骚扰,请稍后再进行拨打！");
                }
                break;
            case CustomerCheckType.CHECK_CUSTOMER_CALL_PHONE_PEOPLE_COUNT:
                if (! checkCusToDayCallPhoneCount (customer, cur)) {
                    throw new RemoveStackBusinessException ("客户今天已经达到被拨打人数的上限，为了保护客户不被频繁骚扰，请明天再进行拨打！");
                }
                break;
            case CustomerCheckType.CHECK_CREATE_CUS:
                if (! checkCreateCus (customer)) {
                    return false;
                }
                break;
            case CustomerCheckType.CHECK_FOCUS_CUS_NOTICE:
                if (! checkFocusCusNotice (customer)) {
                    return false;
                }

                break;
            //检查是否可以收藏
            case CustomerCheckType.CHECK_CREATE_FAVORITE:

                if (! checkCreateFavorite (customer)) {
                    throw new RemoveStackBusinessException ("当前客户你不可以收藏!");
                }

                break;

            //检查是否可以分享客户
            case CustomerCheckType.CHECK_CREATE_SHARE:

                if (! checkCreateShare (customer)) {
                    throw new RemoveStackBusinessException ("当前客户你不可以分享!");
                }

                break;

            //检查是否可以修改客户信息
            case CustomerCheckType.CHECK_CUSTOMER_INFO_CAN_UPDATE:

                if (! checkCustomerInfoCanUpdate (customer, true)) {
                    throw new RemoveStackBusinessException ("客户所属者隐藏了该客户，您不能修改当前客户信息!" + "\n如果是平台客户，需要申请查看号码后才能进行修改!");
                }

                break;

            //检查是否可以查看客户跟进信息
            case CustomerCheckType.CHECK_SEE_CUS_FOLLOWUP:

                if (! checkSeeCusFollowup (customer, false)) {
                    throw new RemoveStackBusinessException ("客户所属者隐藏了该客户，您不能查看当前客户的跟进信息!" + "\n如果是平台客户，需要申请查看号码后才能进行查看!");
                }

                break;

            //检查是否可以进行客户跟进创建
            case CustomerCheckType.CHECK_CREATE_FOLLOWUP:
                if (! checkCreateCusFollowup (customer, false)) {
                    throw new RemoveStackBusinessException ("您没有权限对当前客户进行跟进");
                }

                break;

            //检查是否可以下架客户
            case CustomerCheckType.CHECK_DOWN_CUS:
                if (! checkDownCus (customer)) {
                    throw new RemoveStackBusinessException ("只能下架自己的客户" + "，或者平台拉私的客户" + "，如果是平台客户下架，" + "内部员工才能下架平台客户");
                }

                break;

            //检查是否可以隐藏客户
            case CustomerCheckType.CHECK_OFF_OPEN_FLAG:
                if (! checkOffOpenFlag (customer)) {
                    throw new RemoveStackBusinessException ("当前客户不是您的客户,您只能隐藏您自己" + "或平台拉私的客户!");
                }
                break;
            //检查是否可以公开客户
            case CustomerCheckType.CHECK_ON_OPEN_FLAG:
                if (! checkOnOpenFlag (customer)) {
                    throw new RemoveStackBusinessException ("当前客户不是您的客户,您只能公开您自己的客户!");
                }

                break;

            //检查是否可以上架客户
            case CustomerCheckType.CHECK_UP_SHELF_CUSTOMER:
                if (! checkUpShelfCustomer (customer)) {
                    throw new RemoveStackBusinessException ("非本人、共享部门、平台所属客户，无法执行上架操作!");
                }

                break;

            //检查是否可以创建客户推送
            case CustomerCheckType.CHECK_CREATE_CUS_PUSH:
                if (! checkCreateCusPush (customer)) {
                    throw new RemoveStackBusinessException ("您只能推送您自己的客户、\n" + "部门内共享的、部门间共享的公开客户");
                }
                break;

            //检查是否可以创建客户选址报告
            case CustomerCheckType.CHECK_CREATE_SELECT_ADDRESS_REPORT:

                if (! checkCreateSelectAddressReport (customer)) {
                    throw new RemoveStackBusinessException ("你没有权限给当前客户创建选址报告");
                }

                break;

            //检查是否可以使用智能匹配房源
            case CustomerCheckType.CHECK_MATCH_HOS:

                if (! checkMatchHos (customer)) {
                    throw new RemoveStackBusinessException ("您无法创建当前客户的智能匹配房源，" + "您可以选择向客户所属者申请合作!");
                }
                break;

            //检查是否可以申请合作
            case CustomerCheckType.CHECK_CREATE_COOPERATE:

                if (! checkCreateCooperate (customer)) {
                    throw new RemoveStackBusinessException ("不能对自己的客户发起申请合作!");
                }
                break;

            //检查是否可以查看当前客户的看房记录
            case CustomerCheckType.CHECK_CUS_SAW_HOUSES:

                if (! checkCusSawHouses (customer)) {
                    throw new RemoveStackBusinessException ("本人客户、共享部门公开客户、" + "平台已申请过查看号码的客户，才能查看客户的看房记录!");
                }

                break;

            //检查能否申请查看当前客户电话
            case CustomerCheckType.CHECK_APPLY_SEE_CUS_PHONE:

                if (cur.getUserLevel () == null || cur.getUserLevel ().equals (UserLevel.K2)) {
                    throw new RemoveStackBusinessException ("平台获客只有库房无忧合作经纪人和T0、K1认证付费用户才可以免费申请查看号码哦！");
                }

                if (! checkApplySeeCusPhone (customer)) {
                    throw new RemoveStackBusinessException ("只有平台客户才能进行电话号码查看申请哦！");
                }

                break;

            //检查是否能够对当前客户进行拉私
            case CustomerCheckType.CHECK_CREATE_PULL_PRIVATE:

                if (cur.getUserLevel () == null || cur.getUserLevel ().equals (UserLevel.K2)) {
                    throw new RemoveStackBusinessException ("平台拉私只有库房无忧合作经纪人和T0、K1认证付费用户才可以使用哦！");
                }

                if (! checkCreatePullPrivate (customer)) {
                    throw new RemoveStackBusinessException ("只有平台客户才能进行拉私申请");
                }

                break;
            case CustomerCheckType.CHECK_CUSTOMER_PHONE_CAN_SEE:
                if (! checkCustomerPhoneCanSee (customer, false)) {
                    return false;
                }
                break;
            case CustomerCheckType.CHECK_CUSTOMER_RELEASE:
                if (cur.getUserLevel () == null || cur.getUserLevel ().equals (UserLevel.K2)) {
                    throw new RemoveStackBusinessException ("平台释放只有库房无忧合作经纪人和K1认证付费用户才可以使用哦！");
                }
                if (! checkCustomerRelease (customer, true)) {
                    throw new RemoveStackBusinessException ("当前客户你没有权限进行释放!");
                }
                break;
            case CustomerCheckType.CHECK_CUSTOMER_OFF_OPEN_FLAG:
                if (! checkCanOffOpenFlagCus ()) {
                    return false;
                }
                break;
            case CustomerCheckType.CHECK_CUS_CALL_PHONE_NUM:
                checkCusCallPhoneNum (customer, cur);
                break;
            case CustomerCheckType.CHECK_CUS_CALL_PHONE_RULE:
                if (! checkCusCallPhoneRule (customer, cur)) {
                    return false;
                }
                break;
            default:
                throw new RemoveStackBusinessException ("当前传入的检查类型不存在!");
        }
        return true;
    }


    protected abstract boolean checkCustomerCallPhoneTimeInterval (CustomerEntity customer, UserEntity cur);

    protected boolean checkCusCallPhoneRule (CustomerEntity customer, UserEntity cur) {
        return true;
    }

    protected abstract boolean checkCusToDayCallPhoneCount (CustomerEntity customer, UserEntity cur);

    protected abstract boolean checkCreateCus (CustomerEntity customer);

    private boolean checkFocusCusNotice (CustomerEntity customer) {
        Map map = MapUtil.newHashMap ();
        map.put ("userCode", CurrentContext.getUserCode ());
        map.put ("cusCode", customer.getCusCode ());
        map.put ("handle", false);
        map.put ("cusType", CustomerType.FOCUS_CUS);
        Integer count = iFocusCusManager.checkFocusCusNotice (map);
        if (count > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean checkCanOffOpenFlagCus () {
        // 判断团队是否限制隐藏客户
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
        DeptEntity deptEntity = deptManager.getDeptEntity (cur.getOwnerDeptCode ());
        if (deptEntity.getHasLimitHiden ()) {
            String preSql = "SELECT\n" + "\tCOUNT (*)\n" + "FROM\n" + "\tt_hkp_crm_customer\n" + "WHERE\n" + "\temp_code = \'%s\'\n" + "AND open_flag = FALSE\n" + "AND cus_type ='2'";
            Integer count = customerDbDao.countByNativeSql (String.format (preSql, CurrentContext.getUserCode ()));
            int configValue = checkSeeCusLimitFromErp ();

            if (count >= configValue) {
                return false;
            }
        }
        return true;
    }

    private int checkSeeCusLimitFromErp () {
        // 切换erp数据库,查询限制客户配置数量
        int configValue = 0;
        try {
            configValue = systemConfig.getValueInt ("hkp_cus_hide_count");
        } catch (Exception e) {
            configValue = 3;
        }
        return configValue;
    }

    protected abstract boolean checkCustomerRelease (CustomerEntity customer, boolean b);

    public void checkPullPrivateByFollowup (String cusCode) {

        String preSql = "select count(*) from t_hkp_crm_customer_followup " + "where cus_code=\'%s\' and emp_code=\'%s\' and followup_type='1'";

        String sql = String.format (preSql, cusCode, CurrentContext.getUserCode ());
        int count = customerFollowupDbDao.countByNativeSql (sql);
        if (count < 1) {
            throw new RemoveStackBusinessException ("你没有带看过该客户，不能拉私");
        }
    }

    /**
     * 检查拉私次数
     *
     * @param cusCode
     * @return
     */
    public int checkPullPrivateByHadPullPrivateCount (String cusCode) {
        Map<String, Object> param = new HashMap ();
        param.put ("empCode", CurrentContext.getUserCode ());
        param.put ("cusType", CustomerType.PRIVATE);
        //每天只能够拉私三个客户
        int count = checkTodayPullPrivateCus (param);
        int ct = customerDbDao.countByMap (param);
        if (ct >= 6) {
            throw new RemoveStackBusinessException ("最多可以拉私六位客户,不可以再拉私");
        }
        return count;
    }

    /**
     * 当天拉私客户个数限制检查
     *
     * @param param
     * @return
     */
    public int checkTodayPullPrivateCus (Map<String, Object> param) {
        param.put ("createTimeStart", DateFormatUtil.dayBegin (new Date ()));
        param.put ("createTimeEnd", DateFormatUtil.dayEnd (new Date ()));
        String preSql = "select count(*) " + "from t_hkp_crm_customer where emp_code=\'%s\' " + "and cus_type=\'%s\' " + "and last_update_time>=\'%s\' " + "and last_update_time<=\'%s\'";
        String sql = String.format (preSql, param.get ("empCode"), param.get ("cusType"), param.get ("createTimeStart"), param.get ("createTimeEnd"));
        int count = customerDbDao.countByNativeSql (sql);
        if (count >= 3) {
            throw new RemoveStackBusinessException ("最多可拉私六位平台客户,单日不超过3个，您今天已拉私" + count + "位!,不能再进行拉私操作");
        }
        return count;
    }


    public Boolean customerPhonePushCanRead (CustomerEntity customerEntity) {
        Boolean customerPhonePushCanRead = false;
        if (checkCusPush (customerEntity)) {
            customerPhonePushCanRead = true;
        }
        return customerPhonePushCanRead;
    }


    public Boolean checkPlatFormCusDeptAreaMatch (CustomerEntity customer, UserEntity cur) {
        Boolean checkPlatFormCusDeptAreaMatch = false;
        List<String> deptArea = new ArrayList<> ();
        Map map = new HashMap ();
        map.put ("userCode", cur.getUserCode ());
        cur = (UserEntity) loginUserDbDao.selectByMap (map).get (0);
        for (DeptAreaEntity deptAreaEntity : cur.getDeptAreas ()) {
            deptArea.add (deptAreaEntity.getRegionCode ());
        }

        for (CustomerAreaEntity areaEntity : customer.getCustomerAreaEntityList ()) {
            if (deptArea.contains (areaEntity.getRegion ())) {
                checkPlatFormCusDeptAreaMatch = true;
            }
        }
        return checkPlatFormCusDeptAreaMatch;
    }

    protected Boolean checkPlatFormCusUserAreaMatch (CustomerEntity customer, UserEntity cur) {
        Boolean checkCusPhoneCanSee = false;
        List<String> userArea = new ArrayList<> ();
        Map map = new HashMap ();
        map.put ("userCode", cur.getUserCode ());
        cur = (UserEntity) loginUserDbDao.selectByMap (map).get (0);
        for (UserAreaEntity userAreaEntity : cur.getUserAreas ()) {
            userArea.add (userAreaEntity.getRegionCode ());
        }

        for (CustomerAreaEntity areaEntity : customer.getCustomerAreaEntityList ()) {
            if (userArea.contains (areaEntity.getRegion ())) {
                checkCusPhoneCanSee = true;
            }
        }
        return checkCusPhoneCanSee;
    }

    /**
     * 判断拨打电话次数是否达到上限 未达到则记录
     */
    protected abstract void checkCusCallPhoneNum (CustomerEntity customer, UserEntity cur);

    /**
     * 获取今天还有多少毫秒 结束
     *
     * @return
     */
    protected Long getEndTime () {
        Calendar calendar = Calendar.getInstance ();
        calendar.setTime (new Date ());
        calendar.set (Calendar.HOUR_OF_DAY, 24);
        calendar.set (Calendar.MINUTE, 0);
        calendar.set (Calendar.SECOND, 0);
        Long end = calendar.getTimeInMillis ();
        return end - System.currentTimeMillis ();
    }
}
