package com.kfwy.hkp.hos.house.business.impl;


import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.hos.house.business.IHouseCheckManager;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hos.house.enums.HouseCheckType;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserLevel;

/**
 * @AUTHOR 李文思汗
 * @Description 类描述:抽象房源操作检查管理类
 */
public abstract class AbstractHouseCheckManager extends HouseCheckBase implements IHouseCheckManager {


    @Override
    public Boolean checkHousePermissions(String checkCode, HouseEntity house, UserEntity cur, UserEntity owner) {

        switch (checkCode) {
            //详情
            case HouseCheckType.CHECK_DETAILS:
                if (!checkDetail(house,cur, owner)){
                    return false;
                }
                break;
            //跟进记录
            case HouseCheckType.CHECK_FOLLOWUP_RECORD:
                if (!checkFollowupRecord(house, cur, owner)) {
                    if (cur.getUserLevel().equals(UserLevel.K0)){
                        throw new RemoveStackBusinessException ("未与房源所属人或部门合作该房源、" +
                                "或该房源所属人已关闭其他人查看该房源跟进记录的权限");
                    }else if (cur.getUserLevel().equals(UserLevel.K1)){
                        throw new RemoveStackBusinessException("只能查看自己的、或达成合作且关键信息可见的房源的跟进记录！");
                    }else if (cur.getUserLevel().equals(UserLevel.K2)){
                        throw new RemoveStackBusinessException("只能查看自己房源的跟进记录");
                    }else if (cur.getUserLevel().equals(UserLevel.T0)){
                        throw new RemoveStackBusinessException("未与房源所属人或部门、团队合作该房源");
                    }

                }
                break;

            //创建跟进
            case HouseCheckType.CHECK_FOLLOW_UP:
                if (!checkFollowUp(house, cur, owner)) {
                    if (cur.getUserLevel().equals(UserLevel.K0)){
                        throw new RemoveStackBusinessException("未与房源所属人或部门合作该房源、或该房源所属人已关闭其他人进行跟进该房源的权限");
                    }else if (cur.getUserLevel().equals(UserLevel.K1)){
                        throw new RemoveStackBusinessException("只能跟进自己的房源");
                    }else if (cur.getUserLevel().equals(UserLevel.K2)){
                        throw new RemoveStackBusinessException("只能跟进自己的房源");
                    }else if (cur.getUserLevel().equals(UserLevel.T0)){
                        throw new RemoveStackBusinessException("未与房源所属人或部门、团队合作该房源");
                    }

                }
                break;

            //修改信息
            case HouseCheckType.CHECK_UPDATE:
                if (!checkAuthOfUpdate(house, cur, owner)) {
                    if (cur.getUserLevel().equals(UserLevel.K0)){
                        throw new RemoveStackBusinessException("只能修改自己的、部门共享的、达成合作且关键信息可见的房源信息！");
                    }else if (cur.getUserLevel().equals(UserLevel.K1)){
                        throw new RemoveStackBusinessException("只能修改自己的、达成合作且关键信息可见的房源信息！");
                    }else if (cur.getUserLevel().equals(UserLevel.K2)){
                        throw new RemoveStackBusinessException("只能修改自己的房源信息");
                    }else if (cur.getUserLevel().equals(UserLevel.T0)){
                        throw new RemoveStackBusinessException("只能修改自己的、团队共享的、达成合作且关键信息可见的房源信息！");
                    }

                }
                break;

            //上架
            case HouseCheckType.CHECK_UP:
                if (!checkAuthOfUp(house, cur, owner)) {
                    throw new RemoveStackBusinessException("不是本部门房源或未与该房源所属部门共享房源无权限上架");
                }
                break;

            //下架
            case HouseCheckType.CHECK_DOWN:
                if (!checkAuthOfDown(house, cur, owner)) {
                    if (cur.getUserLevel().equals(UserLevel.K0)
                            || cur.getUserLevel().equals(UserLevel.K1)
                            || cur.getUserLevel().equals(UserLevel.K2)){
                        throw new RemoveStackBusinessException("您只能对自己的房源进行下架操作哦！");
                    }
                }
                break;

            //隐藏
            case HouseCheckType.CHECK_HIDE:
                if (!checkAuthOfHide(house, cur, owner)) {
                    throw new RemoveStackBusinessException("您只能对自己的房源关键信息进行隐藏哦！");
                }
                break;

            //公开
            case HouseCheckType.CHECK_SHOW:
                if (!checkAuthOfShow(house, cur, owner)) {
                    throw new RemoveStackBusinessException("不是房源所属者无公开权限");
                }
                break;

            //来访客户
            case HouseCheckType.CHECK_VISIT_CUSTOMER:
                if (!checkAuthOfVisitCustomer(house, cur, owner)) {
                    throw new RemoveStackBusinessException("客户看房记录属于敏感信息，您只能查看自己的、部门共享公开的、达成合作并公开的房源！");
                }
                break;

            //创建业主跟进
            case HouseCheckType.CHECK_FOLLOWUP_OWNER:
                if (!checkAuthOfAddVisitOwner(house, cur, owner)) {
                    throw new RemoveStackBusinessException("未与房源所属人或部门合作该房源、或该房源所属人已关闭其他人进行跟进该业主的权限");
                }
                break;

            //查询业主拜访
            case HouseCheckType.CHECK_FOLLOWUP_OWNER_RECORD:
                if (!checkAuthOfVisitOwner(house, cur, owner)) {
                    throw new RemoveStackBusinessException("业主拜访记录属于敏感信息，您只能查看自己的、部门共享公开的、达成合作并公开的房源！");
                }
                break;

            //新增来访客户
            case HouseCheckType.CHECK_AUTH_OF_ADD_VISIT_CUSTOMER:
                break;

            //收藏
            case HouseCheckType.CHECK_COLLECT:
                break;

            //分享
            case HouseCheckType.CHECK_SHARE:
                break;

            //智能匹配
            case HouseCheckType.CHECK_MATCHING:
                if (!checkMatching(house,cur, owner)){
                    throw new RemoveStackBusinessException("不能执行智能匹配");
                }
                break;

            //申请和合作
            case HouseCheckType.CHECK_APPLY:
                if (!checkApply(house,cur, owner)){
                    if (cur.getUserLevel().equals(UserLevel.K2)){
                        throw new RemoveStackBusinessException("您不能执行申请合作操作!");
                    }else{
                        throw new RemoveStackBusinessException("您不能对自己的房源申请合作!");
                    }

                }
                break;

            //平台拉私
            case HouseCheckType.CHECK_PERSONAL:
                break;
            case HouseCheckType.BUSINESS_OPPORTUNITY:
                if (cur.getUserLevel().equals(UserLevel.K2)){
                    throw new RemoveStackBusinessException("商机不能查看，跳转引导页面");
                }
                break;
            case HouseCheckType.WEBSITE_DISPLAY:
                if (cur.getUserLevel().equals(UserLevel.K2)){
                    throw new RemoveStackBusinessException("房源不能在官网展示，跳转引导页面");
                }

                break;

            default:
                throw new RemoveStackBusinessException("权限查询未知错误");

        }
        return true;
    }


    public abstract Boolean checkDetail(HouseEntity house,UserEntity cur,UserEntity owner);

    /**
     * 跟进记录
     * @param house
     * @param cur
     * @param owner
     * @return
     */
    public abstract Boolean checkFollowupRecord(HouseEntity house,UserEntity cur,UserEntity owner);

    public abstract Boolean checkFollowUp(HouseEntity house,UserEntity cur,UserEntity owner);

    public abstract Boolean checkAuthOfUpdate(HouseEntity house,UserEntity cur,UserEntity owner);

    public abstract Boolean checkAuthOfUp(HouseEntity house,UserEntity cur,UserEntity owner);

    public abstract Boolean checkAuthOfDown(HouseEntity house,UserEntity cur,UserEntity owner);

    public abstract Boolean checkAuthOfHide(HouseEntity house,UserEntity cur,UserEntity owner);

    public abstract Boolean checkAuthOfShow(HouseEntity house,UserEntity cur,UserEntity owner);

    public abstract Boolean checkAuthOfVisitCustomer(HouseEntity house,UserEntity cur,UserEntity owner);

    public abstract Boolean checkAuthOfAddVisitOwner(HouseEntity house,UserEntity cur,UserEntity owner);

    public abstract Boolean checkAuthOfVisitOwner(HouseEntity house,UserEntity cur,UserEntity owner);

    public abstract Boolean checkAuthOfAddVisitCustomer(HouseEntity house,UserEntity cur,UserEntity owner);

    public abstract Boolean checkCollect(HouseEntity house,UserEntity cur,UserEntity owner);

    public abstract Boolean checkShare(HouseEntity house,UserEntity cur,UserEntity owner);

    public  Boolean checkMatching(HouseEntity house,UserEntity cur,UserEntity owner){
        return true;
    }

    public abstract Boolean checkApply(HouseEntity house,UserEntity cur,UserEntity owner);

    public abstract Boolean checkBusinessOpportunities();

}
