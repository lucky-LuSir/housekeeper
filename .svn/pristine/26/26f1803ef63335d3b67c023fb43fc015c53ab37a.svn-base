package com.kfwy.hkp.hos.house.business;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dic.DictionaryStorage;
import com.kfwy.hkp.cooperate.dao.ICooperateDbDao;
import com.kfwy.hkp.cooperate.entity.CooperateEntity;
import com.kfwy.hkp.cooperate.enums.CooStatus;
import com.kfwy.hkp.crm.houseowner.business.IHouseownerManager;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerEntity;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hos.house.entity.HouseLocationEntity;
import com.kfwy.hkp.hos.house.enums.HouseStatus;
import com.kfwy.hkp.sys.notice.business.INoticeManager;
import com.kfwy.hkp.sys.notice.business.NoticeTaskExecutor;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.entity.NoticeRecordEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeOperateType;
import com.kfwy.hkp.sys.notice.enums.NoticeType;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import static java.awt.SystemColor.info;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/12/20 09:52
 */
public abstract class AbstractHouseNoticeAop {

    @Autowired
    protected NoticeTaskExecutor noticeTaskExecutor;
    @Autowired
    protected IUserManager userManager;
    @Autowired
    protected IHouseLocationManager houseLocationManager;
    @Autowired
    protected IHouseManager houseManager;
    @Autowired
    protected INoticeManager noticeManager;
    @Autowired
    protected IHouseownerManager houseownerManager;
    @Autowired
    protected ICooperateDbDao cooperateDbDao;
    @Autowired
    protected IHouseFavoriteManager houseFavoriteManager;


    protected List<HouseEntity> getArgs(JoinPoint point){
        Object[] args = point.getArgs();
        Object obj = null;
        if (ArrayUtils.isNotEmpty(args)){
            obj = args[0];
        }
        return obj instanceof List? (List<HouseEntity>) obj :null;
    }

    //适用于第一个参数位房源实体
    protected HouseEntity getArg(JoinPoint point){
        Object[] args = point.getArgs();
        Object obj = null;
        if (ArrayUtils.isNotEmpty(args)){
            obj = args[0];
        }
        return obj instanceof HouseEntity? (HouseEntity)obj :null;
    }

    protected HouseEntity getHouseCode(JoinPoint point){
        Object[] args = point.getArgs();
        Object obj = null;
        if (ArrayUtils.isNotEmpty(args)){
            obj = args[0];
        }
        Map<String, Object> map = (Map<String, Object>) obj;

        HouseEntity houseEntity = houseManager.findOne("houseCode",map.get("houseCode").toString());
        if(houseEntity!=null){
            return houseEntity;
        }else{
            return null;
        }
    }


    public HouseLocationEntity getHouseLocation(HouseEntity house){
        HouseLocationEntity location = house.getHouseLocation();
        if (!ObjectUtils.isEmpty(location)){
            location = houseLocationManager.findOne("locationCode",house.getLocationCode());
        }else {
            house= houseManager.findOne("houseCode",house.getHouseCode());
            location = houseLocationManager.findOne("locationCode",house.getLocationCode());
        }

        return location;
    }

    //设置推送人群
    public abstract List<UserEntity> getTargetUsers(HouseEntity house);

    protected NoticeEntity create(HouseEntity house,String type){
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();
        //初始化通知消息
        NoticeEntity notice = new NoticeEntity();

        notice.setNoticeCode(CodeGenUtils.generate());
        notice.setNoticeType(NoticeType.HOUSE);
        notice.setBussinessCode(house.getHouseCode());
        notice.setBussinessEmpName(cur.getUserName());
        notice.setBussinessDeptName(cur.getOwnerDeptName());
//        noticeEntity.setNoticeTitle(title.toString());
//        noticeEntity.setNoticeContent(content.toString());
        notice.setCreateCode(cur.getUserCode());
        notice.setLastUpdateCode(cur.getUserCode());
        notice.setIsDeleted(Boolean.FALSE);
        notice.setCreateTime(new Date());
        notice.setLastUpdateTime(new Date());
        notice.setBussinessType(type);

        //设置推送内容
        initNoticeTitleAndContent(notice,house,cur);

        //获取推送目标人群
        List<UserEntity> targetUsers = getTargetUsers(house);

        //设置推送目标人员
        addNoticeRecord(notice,targetUsers,cur);
        return notice;
    }

    //设置推送内容
    public abstract void initNoticeTitleAndContent(NoticeEntity notice,HouseEntity house,UserEntity cur);

    //设置推送人员
    protected void addNoticeRecord(NoticeEntity notice,List<UserEntity> targetUsers,UserEntity cur){

        if (!CollectionUtils.isEmpty(targetUsers)){
            List<NoticeRecordEntity> recordEntityList = new ArrayList<NoticeRecordEntity>();

            for (UserEntity target : targetUsers){
                if(cur != null){
                    if (target.getUserCode().equals(cur.getUserCode())){
                        continue;
                    }

                    if (cur.getCpyCode ()!=null){
                        UserEntity userEntity = userManager.findOne ("userCode", target.getUserCode ());
                        if (userEntity.getCpyCode ()==null
                                || (userEntity.getCpyCode () !=null && !cur.getCpyCode ().equals (userEntity.getCpyCode ()))){
                            continue;
                        }
                    }

                }
                if (StringUtils.isNotEmpty(target.getDeviceToken())){
                    NoticeRecordEntity record = new NoticeRecordEntity();

                    record.setRecordCode(CodeGenUtils.generate());
                    record.setNoticeCode(notice.getNoticeCode());
                    record.setHasRead(Boolean.FALSE);
                    record.setEmpCode(target.getUserCode());
                    record.setDeviceToken(target.getDeviceToken());
                    record.setIsDeleted(Boolean.FALSE);
                    record.setCreateCode(cur.getUserCode());
                    record.setLastUpdateCode(cur.getUserCode());
                    recordEntityList.add(record);
                }

            }
            notice.setNoticeRecordEntityList(recordEntityList);
        }
    }


    //获取部门人员
    protected List<UserEntity> getUserListByDept (){
        Map<String,Object> param = new HashMap<>();
        param.put("isDeleted",Boolean.FALSE);
        param.put("ownerDeptCode",CurrentContext.getUserInfo().getOwnerDeptCode());
        List<UserEntity> targetUsers = userManager.findByMap(param);
        return targetUsers;
    }

    //去重
    protected List<UserEntity> distinct(List<UserEntity> userList){
        if(userList == null || userList.size() == 0){
            return new ArrayList<>();
        }
        userList = userList.stream().collect( Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(UserEntity::getUserCode))), ArrayList::new)
        );
        return userList;
    }

    //获取合作人的实体
    protected List<UserEntity> getCooperateUserList(String houseCode){
        List<UserEntity> list = new ArrayList<>();

        Map<String,Object> cooMap = new HashMap<>();
        cooMap.put("houseCode",houseCode);
        cooMap.put("isDeleted",Boolean.FALSE);
        List<CooperateEntity> cooperateEntityList = cooperateDbDao.selectByMap(cooMap);

        cooperateEntityList.forEach((coo) -> {
            if(!coo.getCooStatus().equals(CooStatus.END)){
                return;
            }
            Map<String,Object> param = new HashMap<>(2);
            param.put("isDeleted",Boolean.FALSE);
            //合作申请者
            param.put("userCode",coo.getApplyCode());
            UserEntity userEntity = userManager.findOne(param);
            list.add(userEntity);
        });
        return list;
    }
}
