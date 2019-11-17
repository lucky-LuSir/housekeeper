package com.kfwy.hkp.crm.customer.business.strategy;

import com.kfwy.hkp.base.FocusCusConfig;
import com.kfwy.hkp.base.TimeSplit;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.common.utils.FilterUtil;
import com.kfwy.hkp.crm.customer.bo.FocusCusUserScoreMatchBo;
import com.kfwy.hkp.crm.customer.business.IFocusCusManager;
import com.kfwy.hkp.crm.customer.dao.IFocusCusBlackListDbDao;
import com.kfwy.hkp.crm.customer.dao.IFocusCusDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerAreaEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerHouseType;
import com.kfwy.hkp.hos.house.enums.HouseStatus;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public abstract class AbstractFocusCusStrategy implements FocusCusStrategy {
    @Autowired
    protected IUserManager userManager;
    @Autowired
    protected IFocusCusBlackListDbDao focusCusBlackListDbDao;
    @Autowired
    protected IFocusCusManager focusCusManager;
    @Autowired
    protected IFocusCusDbDao focusCusDbDao;


    /**
     * 设置排除部门经理，客服，客服主管
     *
     * @param map
     * @param customer
     * @param focusCusConfig
     */
    protected void setExcludeCodes (Map<String, Object> map, CustomerEntity customer, FocusCusConfig focusCusConfig) {
        excludeByPostCodes (map);
        //排除3天内获客5个以上的人,1天内获客2个以上的人
        List<String> excludeUserCodesByFocusCusCount = setExcludeUserCodesByFocusCusCount (focusCusConfig);
        if (excludeUserCodesByFocusCusCount != null && excludeUserCodesByFocusCusCount.size () > 0) {
            map.put ("excludeUserCodesByFocusCusCount", excludeUserCodesByFocusCusCount);
        }
        map.put ("isDeleted", false);
        //排除已经被指定推送的人
        if (customer.getNoticeUsers () != null && customer.getNoticeUsers ().size () > 0) {
            map.put ("excludeUserCodesByNoticeUsers", customer.getNoticeUsers ());
        }
        //TODO 排除黑名单中的人
        List<String> excludeUserCodesByBlackList = excludeUserCodesByBlackList();
        if (excludeUserCodesByBlackList!=null && excludeUserCodesByBlackList.size ()>0){
            map.put ("excludeUserCodesByBlackList",excludeUserCodesByBlackList);
        }
    }

    protected void excludeByPostCodes (Map<String, Object> map) {
        List<String> excludeByPostCodes = new ArrayList<> ();
        //排除部门经理、客服、客服主管、客服组长
        excludeByPostCodes.add (DEPARTMENT_MANAGER);
        excludeByPostCodes.add (CUSTOMER_SERVICE);
        excludeByPostCodes.add (CUSTOMER_SERVICE_DIRECTOR);
        excludeByPostCodes.add (CUSTOMER_SERVICE_GROUP_LEADER);
        excludeByPostCodes.add ("PT201605270002");
        excludeByPostCodes.add ("PT201711040001002b");
        map.put ("excludeByPostCodes", excludeByPostCodes);
    }


    /**
     * 处理是否有新人
     * @param remainFocusCusCount
     * @param list
     * @param newUser
     * @return
     */
    protected List<FocusCusUserScoreMatchBo> handleNewUser (Integer remainFocusCusCount, List<FocusCusUserScoreMatchBo> list, List<UserEntity> newUser) {
        if (newUser.size () > 0) {
            if (list.size () >= (remainFocusCusCount - 1)) {
                list = list.subList (0, (remainFocusCusCount - 1));
            }
        } else {
            if (list.size () >= remainFocusCusCount) {
                list = list.subList (0, remainFocusCusCount);
            }
        }
        return list;
    }

    protected Date getEntryTimeMoreThanMonth (FocusCusConfig focusCusConfig, Date entryTimeInOneMonth) {
        Integer timeMoreThanRange = null;
        if (focusCusConfig != null) {
            timeMoreThanRange = focusCusConfig.getEntryTimeMoreThanRange () != null ? focusCusConfig.getEntryTimeMoreThanRange () : 15;
        } else {
            timeMoreThanRange = 15;
        }
        TimeSplit entryTimeMoreThanRange = new TimeSplit (null, timeMoreThanRange, "DD");
        return DateFormatUtil.addDate (entryTimeInOneMonth, entryTimeMoreThanRange.getAfterNumber (), entryTimeMoreThanRange.getAfterFormat ());
    }

    /**
     * 获得入职时间小于一个月、大于半个月的新人，如果手动推送的新人大于一个，则不从评分的人里面选新人
     *
     * @param users
     * @param list
     * @param entryTimeInMonth
     * @param targetUsers
     * @return
     */
    protected List<UserEntity> getNewUser (List<UserEntity> users, List<FocusCusUserScoreMatchBo> list, Date entryTimeInMonth,Date entryTimeMoreThanMonth, List<UserEntity> targetUsers) {
        List<UserEntity> newUser = new ArrayList<> ();
        boolean targetUsersHasNewUser = false;
        for (UserEntity user : targetUsers) {
            if (user.getEntryTime ().compareTo (entryTimeInMonth) >= 0 && user.getEntryTime ().compareTo (entryTimeMoreThanMonth)<=0) {
                targetUsersHasNewUser = true;
            }
        }

        //如果手动选择的人中有新人，则不从评分的人中选新人。
        if (targetUsersHasNewUser == false) {
            //给所有人评分后，找其中是否有入职一个月内的员工，如果有，取排第一的那个，因为分数在新员工中最高。
            for (FocusCusUserScoreMatchBo user : list) {
                if (user.getEntryTime ().compareTo (entryTimeInMonth) >= 0 && user.getEntryTime ().compareTo (entryTimeMoreThanMonth)<=0) {
                    //取第一個新入职一个月内的排名最高的员工
                    UserEntity entity = users.stream ().filter (u -> u.getUserCode ().equals (user.getUserCode ())).findFirst ().orElse (null);
                    if (entity != null) {
                        newUser.add (entity);
                        //移除集中获客排名中的新人，防止重复选中
                        list.remove (user);
                        break;
                    }
                }
            }
        }
        return newUser;
    }

    /**
     * 获取集中获客排名分数
     *
     * @param customer
     * @param map
     * @param users
     * @param focusCusConfig
     * @return
     */
    protected List<FocusCusUserScoreMatchBo> getFocusCusUserScoreMatchBosList (CustomerEntity customer, Map<String, Object> map, List<UserEntity> users, FocusCusConfig focusCusConfig) {
        List<String> userCodes = new ArrayList<> ();
        if (users!=null && users.size ()>0 ){
            users.forEach (user -> userCodes.add (user.getUserCode ()));
            map.put ("userCodes", userCodes);
        }
        matchCondition (map, customer, focusCusConfig);
        return focusCusManager.focusCusUserScoreMatch (map);
    }

    protected List<String> getCusAreaRegionStringList (CustomerEntity customer) {
        List<String> cusAreaList = new ArrayList<> ();
        customer.getCustomerAreaEntityList ().forEach (customerAreaEntity -> cusAreaList.add (customerAreaEntity.getRegion ()));
        return cusAreaList;
    }

    protected Date getEntryTimeInOneMonth (FocusCusConfig focusCusConfig) {
        Integer timeRange = null;
        if (focusCusConfig != null) {
            timeRange = focusCusConfig.getEntryTimeRange () != null ? focusCusConfig.getEntryTimeRange () : - 30;
        } else {
            timeRange = - 30;
        }
        TimeSplit entryTimeRange = new TimeSplit (null, timeRange, "DD");
        return DateFormatUtil.addDate (DateFormatUtil.getCurrentDateTime (), entryTimeRange.getAfterNumber (), entryTimeRange.getAfterFormat ());
    }

    protected List<String> excludeUserCodesByBlackList () {
        return focusCusBlackListDbDao.excludeUserCodesByBlackList ();
    }

    /**
     * 设置需要排除的3天内获客指定数量的员工
     *
     * @param focusCusConfig
     * @return
     */
    protected List<String> setExcludeUserCodesByFocusCusCount (FocusCusConfig focusCusConfig) {
        Map param = new HashMap ();
        Date day = DateFormatUtil.getCurrentDateTime ();
        Integer checkFocusCusCountTime = null;
        Integer checkOneDayFocusCusCountTime = null;
        String afterFormat = null;
        Integer focusCount = null;
        Integer oneDayFocusCusCount = null;
        if (focusCusConfig != null && (focusCusConfig.getCheckFocusCusCountTime () != null && focusCusConfig.getAfterFormat () != null && focusCusConfig.getFocusCusCount () != null)) {
            checkFocusCusCountTime = focusCusConfig.getCheckFocusCusCountTime ();
            checkOneDayFocusCusCountTime = focusCusConfig.getCheckOneDayFocusCusCountTime ();
            afterFormat = focusCusConfig.getAfterFormat ();
            focusCount = focusCusConfig.getFocusCusCount ();
            oneDayFocusCusCount = focusCusConfig.getOneDayFocusCusCount ();
        } else {
            checkFocusCusCountTime = - 3;
            checkOneDayFocusCusCountTime = - 1;
            afterFormat = "DD";
            focusCount = 5;
            oneDayFocusCusCount = 2;
        }
        TimeSplit ts1 = new TimeSplit (null, checkFocusCusCountTime, afterFormat);
        TimeSplit ts2 = new TimeSplit (null, checkOneDayFocusCusCountTime, afterFormat);
        Date createTimeStart = DateFormatUtil.addDate (day, ts1.getAfterNumber (), ts1.getAfterFormat ());
        Date oneDayCreateTimeStart = DateFormatUtil.addDate (day, ts2.getAfterNumber (), ts2.getAfterFormat ());
        param.put ("createTimeStart", createTimeStart);
        param.put ("focusCusCount", focusCount);
        param.put ("oneDayFocusCusCount", oneDayFocusCusCount);
        param.put ("oneDayCreateTimeStart", oneDayCreateTimeStart);
        return userManager.excludeUserCodesByFocusCusCount (param);
    }


    /**
     * 区域条件匹配
     *
     * @param customer
     * @param map
     */
    protected void areaCondition (CustomerEntity customer, Map<String, Object> map) {
        List<String> communitys = new ArrayList<> ();
        List<String> streets = new ArrayList<> ();
        List<String> regions = new ArrayList<> ();
        List<String> citys = new ArrayList<> ();
        //找到客户需求区域下所有部门员工
        for (CustomerAreaEntity customerAreaEntity : customer.getCustomerAreaEntityList ()) {
            if (customerAreaEntity.getCommunity () != null) {
                communitys.add (customerAreaEntity.getCommunity ());
            } else if (customerAreaEntity.getStreet () != null) {
                streets.add (customerAreaEntity.getStreet ());
            } else if (customerAreaEntity.getRegion () != null) {
                regions.add (customerAreaEntity.getRegion ());
            } else if (customerAreaEntity.getCity () != null) {
                citys.add (customerAreaEntity.getCity ());
            }
        }
        if (communitys.size () > 0) {
            map.put ("communitys", communitys);
        }
        if (streets.size () > 0) {
            map.put ("streets", streets);
        }
        if (regions.size () > 0) {
            map.put ("regions", regions);
        }
        if (citys.size () > 0) {
            map.put ("citys", citys);
        }
        FilterUtil.filterMap (map);

    }

    /**
     * 匹配条件
     *
     * @param map
     * @param cu
     * @param focusCusConfig
     */
    protected void matchCondition (Map<String, Object> map, CustomerEntity cu, FocusCusConfig focusCusConfig) {
        //匹配面积 上浮不封顶，但过滤不可分割的房源
        //下浮 20%，解释：如客户需求面积为1000㎡，则匹配房源面积为最小800㎡
        Integer area = cu.getNeedAcreage ().intValue ();
        double num = area * 0.2;
        Integer acreageStart = area - (int) num;
        map.put ("minArea", acreageStart);
        map.put ("area", area);//客户需求面积
        map.put ("hasCut", Boolean.TRUE);//上浮过滤不可分割 只要可分割的
        //匹配仓储还是生产
        if (CustomerHouseType.STORAGE.equals (cu.getHouseType ())) {
            map.put ("housePurposes", "'1','3'");
        } else if (CustomerHouseType.PRODUCT.equals (cu.getHouseType ())) {
            map.put ("housePurposes", "'2','3'");
        }
        //不是匹配热租中的房源 都是耍流氓
        map.put ("houseStatus", HouseStatus.hotRenting);
        Date day = DateFormatUtil.getCurrentDateTime ();
        Integer focusMatchTime = null;
        String afterFormat = null;
        if (focusCusConfig != null && focusCusConfig.getFocusMatchTime () != null) {
            focusMatchTime = focusCusConfig.getFocusMatchTime ();
            afterFormat = focusCusConfig.getAfterFormat ();
        } else {
            focusMatchTime = - 3;
            afterFormat = "DD";
        }
        TimeSplit ts1 = new TimeSplit (null, focusMatchTime, afterFormat);
        Date createTimeStart = DateFormatUtil.addDate (day, ts1.getAfterNumber (), ts1.getAfterFormat ());
        map.put ("createTimeStart", DateFormatUtil.dayBegin (createTimeStart));
        areaCondition (cu, map);
        FilterUtil.filterMap (map);
    }

    @Override
    public List<UserEntity> handle (CustomerEntity customer, List<UserEntity> targetUsers, FocusCusConfig focusCusConfig) {
        //集中获客推送人数总数
        Integer focusUserAllCount;
        if (focusCusConfig != null && focusCusConfig.getFocusCusAllCount () != null) {
            focusUserAllCount = focusCusConfig.getFocusCusAllCount ();
        } else {
            focusUserAllCount = 4;
        }

        //指定了集中获客推送用户，则不走集中获客评分算法
        if (customer.getNoticeUsers () != null && customer.getNoticeUsers ().size () >= focusUserAllCount) {

            for (String noticeUser : customer.getNoticeUsers ()) {
                UserEntity userEntity = userManager.selectUniqueByProp (noticeUser);
                targetUsers.add (userEntity);
            }
            return targetUsers;
        } else {
            //添加人数少于总人数人，则先将指定人员加入集中获客通知人中
            if (customer.getNoticeUsers () != null && customer.getNoticeUsers ().size () > 0) {
                for (String noticeUser : customer.getNoticeUsers ()) {
                    UserEntity userEntity = userManager.selectUniqueByProp (noticeUser);
                    targetUsers.add (userEntity);
                }
            }

            //剩余集中获客名额数
            Integer remainFocusCusCount = focusUserAllCount - targetUsers.size ();

            //平台类型客户创建
            return handleFocusCus (customer,targetUsers, focusCusConfig, remainFocusCusCount);
        }
    }

    @Override
    public List<UserEntity> handleFocusCus (CustomerEntity customer, List<UserEntity> targetUsers, FocusCusConfig focusCusConfig, Integer remainFocusCusCount) {
        return targetUsers;
    }

}
