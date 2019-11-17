package com.kfwy.hkp.crm.customer.business.strategy.impl;


import com.kfwy.hkp.base.FocusCusConfig;
import com.kfwy.hkp.common.utils.FilterUtil;
import com.kfwy.hkp.crm.customer.bo.FocusCusUserScoreMatchBo;
import com.kfwy.hkp.crm.customer.business.strategy.AbstractFocusCusStrategy;
import com.kfwy.hkp.crm.customer.business.strategy.FocusCusStrategy;
import com.kfwy.hkp.crm.customer.business.strategy.FocusCusStrategyConst;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 用户评分算法
 */
@Component(FocusCusStrategyConst.SCORE_MATCH)
public class FocusCusScoreMatchStrategy extends AbstractFocusCusStrategy implements FocusCusStrategy {


    @Override
    /**
     * 集中获客匹配推送信息用户
     *
     * @param customer            集中获客推送的客户信息
     * @param targetUsers         集中获客推送人员
     * @param focusCusConfig      集中获客配置信息类
     * @param remainFocusCusCount 剩余集中获客推送人数
     * @return
     */
    public List<UserEntity> handleFocusCus (CustomerEntity customer, List<UserEntity> targetUsers, FocusCusConfig focusCusConfig, Integer remainFocusCusCount) {
        //剩余集中获客的人数

        //region 按照客户需求区域或者指定部门，查找需要进行集中获客评分排名的所有人（排除已经指定推送的人、排除客服、客服主管、客服组长、部门经理）
        Map<String, Object> map = new HashMap (16);
        //设置需要排除的人(包括客服、部门经理、已经被指定推送的人、三天内被推送5个的人、黑名单的人)
        setExcludeCodes (map, customer, focusCusConfig);

        //集中获客评分筛选人群#users
        List<UserEntity> users = new ArrayList<> ();
        //当前客户有指定集中获客的部门，则取指定部门下的所有员工，如果没有指定部门，则按照客户的需求区域查找需求区域内的员工
        if (customer.getNoticeDepts () != null && customer.getNoticeDepts ().size () > 0) {
            map.put ("noticeDepts", customer.getNoticeDepts ());
            FilterUtil.filterMap (map);

            users = userManager.findUsersByNoticeDepts (map);
        } else {
            //客户需求区域Code字符串集合
            List<String> cusAreaRegionList = getCusAreaRegionStringList (customer);
            //找到客户需求区域下所有部门员工
            if (cusAreaRegionList != null && cusAreaRegionList.size () > 0) {
                map.put ("cusAreaString", cusAreaRegionList);
                map = FilterUtil.filterMap (map);
                users = userManager.findUsersByDeptArea (map);
            }
        }

        //如果查出來的人数不足集中获客剩余人数，则进入当前方法
        if (users.size ()<remainFocusCusCount) {
            //移除集中获客次数限制的人
            map.remove ("excludeUserCodesByFocusCusCount");
            List<UserEntity> us = new ArrayList<> ();
            List<String> excludeUserCodes = new ArrayList<> ();

            if (users.size ()>0){
                us.addAll (users);
                us.forEach (e-> excludeUserCodes.add (e.getUserCode ()));
                targetUsers.addAll (us);
                remainFocusCusCount = remainFocusCusCount - us.size ();
                map.put ("excludeUserCodesByFocusCusCount",excludeUserCodes);
            }

            if (customer.getNoticeDepts () != null && customer.getNoticeDepts ().size () > 0) {
                map.put ("noticeDepts", customer.getNoticeDepts ());
                FilterUtil.filterMap (map);

                users = userManager.findUsersByNoticeDepts (map);
            } else {
                //客户需求区域Code字符串集合
                List<String> cusAreaRegionList = getCusAreaRegionStringList (customer);
                //找到客户需求区域下所有部门员工
                if (cusAreaRegionList != null && cusAreaRegionList.size () > 0) {
                    map.put ("cusAreaString", cusAreaRegionList);
                    map = FilterUtil.filterMap (map);
                    users = userManager.findUsersByDeptArea (map);
                }
            }
        }
        map.clear ();
        //endregion
        //给所有该需求区域下的员工评分，取前remainFocusCusCount名分数的员工推送客户，如果存在新员工，则只取排名前remainFocusCusCount-1个员工推送。
       return getUsersForFocusCusNotice (customer, targetUsers, users, focusCusConfig, remainFocusCusCount);
    }


    /**
     * 获得集中获客评分后排名前remainFocusCusCount位的员工
     *
     * @param customer            集中获客推送客户
     * @param targetUsers         集中获客推送人
     * @param users               集中获客被评分员工
     * @param focusCusConfig      配置
     * @param remainFocusCusCount 集中获客剩余推送名额
     * @return
     */
    protected List<UserEntity> getUsersForFocusCusNotice (CustomerEntity customer, List<UserEntity> targetUsers, List<UserEntity> users, FocusCusConfig focusCusConfig, Integer remainFocusCusCount) {
        Map map = new HashMap ();
        List<FocusCusUserScoreMatchBo> list = new ArrayList<> ();
        if (users!=null && users.size ()>0){
            list = getFocusCusUserScoreMatchBosList (customer, map, users, focusCusConfig);
        }

        map.clear ();

        if (list != null && list.size () > 0) {
            Collections.sort (list);
            //如果有一个月内入职的新员工，则添加到新员工集合中，如果新员工数量大于1，则按照评分去判断，选分数最高的一位。如果新员工为0，则直接取负责区域下最符合的三个人。
            Date entryTimeInMonth = getEntryTimeInOneMonth (focusCusConfig);
            Date entryTimeMoreThanMonth = getEntryTimeMoreThanMonth (focusCusConfig,entryTimeInMonth);

            List<UserEntity> newUser = getNewUser (users, list, entryTimeInMonth,entryTimeMoreThanMonth, targetUsers);

            list = handleNewUser (remainFocusCusCount, list, newUser);

            for (FocusCusUserScoreMatchBo cusUserScoreMatchBo : list) {
                UserEntity userEntity = userManager.selectUniqueByProp (cusUserScoreMatchBo.getUserCode ());
                if (userEntity != null) {
                    targetUsers.add (userEntity);
                }
            }
            //如果新员工不为空，则最后加入
            if (newUser != null && newUser.size () > 0) {
                targetUsers.addAll (newUser);
            }
        }

        return targetUsers;
    }
}
