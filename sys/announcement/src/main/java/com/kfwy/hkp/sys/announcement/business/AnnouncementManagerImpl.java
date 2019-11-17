package com.kfwy.hkp.sys.announcement.business;

import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.hrm.org.dept.dao.IDeptDbDao;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.announcement.dao.IAnnouncementDbDao;
import com.kfwy.hkp.sys.announcement.entity.AnnouncementEntity;
import com.kfwy.hkp.sys.quota.dao.IMyAchievementDbDao;
import com.kfwy.hkp.sys.quota.dao.IQuotaAchievementDbDao;
import com.kfwy.hkp.sys.quota.entity.MyAchievementEntity;
import com.kfwy.hkp.sys.quota.entity.QuotaAchievementEntity;
import com.kfwy.hkp.sys.user.dao.IUserDbDao;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by davidcun on 2018/5/25.
 */
@Service
public class AnnouncementManagerImpl extends AbstractManager<AnnouncementEntity> implements IAnnouncementManager {

    @Autowired
    private IAnnouncementDbDao announcementDbDao;
    @Autowired
    private IQuotaAchievementDbDao quotaAchievementDbDao;
    @Autowired
    private IMyAchievementDbDao myAchievementDbDao;
    @Autowired
    private IDeptDbDao deptDbDao;
    @Autowired
    private IUserDbDao userDbDao;

    @Override
    protected IMyBatisBaseDao<AnnouncementEntity, Long> getMyBatisDao() {
        return null;
    }

    @Override
    public void createAnnouncement(List<String> deptCodes, String announcementType) {

        //先删除历史数据
        Map<String, Object> param = new HashMap<>();
        param.put("announcementType", announcementType);
        announcementDbDao.deletedData(param);
        String createCode = CurrentContext.getUserCode();
        Date createTime = new Date();
        String createDeptCode = CurrentContext.getUserInfo().getOwnerDeptCode();

        List<AnnouncementEntity> announcementEntities = new ArrayList<>();
        for (String deptCode : deptCodes) {
            param = new HashMap<>();
            param.put("deptCode", deptCode);
            DeptEntity deptEntity = deptDbDao.selectUniqueByMap(param);
            AnnouncementEntity announcementEntity = new AnnouncementEntity();
            announcementEntity.setUserCode(deptEntity.getLeaderCode());
            announcementEntity.setAnnouncementType(announcementType);
            announcementEntity.setCreateCode(createCode);
            announcementEntity.setCreateTime(createTime);
            announcementEntity.setCreateDeptCode(createDeptCode);
            announcementEntity.setIsDeleted(false);
            announcementEntities.add(announcementEntity);
        }
        if (null == announcementEntities || announcementEntities.size() == 0) {
            return;
        }
        announcementDbDao.batchInsert(announcementEntities);

    }

    @Override
    public AnnouncementEntity query() {
        Map<String, Object> param = new HashMap<>();

        AnnouncementEntity result = new AnnouncementEntity();

        param.put("announcementType", "1");
        param.put("isDeleted", false);
        List<AnnouncementEntity> achievement = announcementDbDao.selectByMap(param);
        List<String> userCodes = new ArrayList<>();
        if (null != achievement && achievement.size() > 0) {
            for (AnnouncementEntity announcementEntity : achievement) {
                userCodes.add(announcementEntity.getUserCode());
            }
        }
        if (null != userCodes && userCodes.size() > 0) {
            param.put("userCodes", userCodes);
            List<UserEntity> userEntities = userDbDao.selectUserBasisInfoByMap(param);
            if (null != userEntities) {
                result.setAchievement(userEntities);
            }
        }

        param.put("announcementType", "2");
        List<AnnouncementEntity> achievementBad = announcementDbDao.selectByMap(param);
        userCodes = new ArrayList<>();
        if (null != achievementBad && achievementBad.size() > 0) {
            for (AnnouncementEntity announcementEntity : achievementBad) {
                userCodes.add(announcementEntity.getUserCode());
            }
        }
        if (null != userCodes && userCodes.size() > 0) {
            param.put("userCodes", userCodes);
            List<UserEntity> userEntities = userDbDao.selectUserBasisInfoByMap(param);
            if (null != userEntities) {
                result.setAchievementBad(userEntities);
            }
        }

        param.put("announcementType", "3");
        List<AnnouncementEntity> service = announcementDbDao.selectByMap(param);
        userCodes = new ArrayList<>();
        if (null != service && service.size() > 0) {
            for (AnnouncementEntity announcementEntity : service) {
                userCodes.add(announcementEntity.getUserCode());
            }
        }
        if (null != userCodes && userCodes.size() > 0) {
            param.put("userCodes", userCodes);
            List<UserEntity> userEntities = userDbDao.selectUserBasisInfoByMap(param);
            if (null != userEntities) {
                result.setService(userEntities);
            }
        }

        param.put("announcementType", "4");
        List<AnnouncementEntity> serviceBad = announcementDbDao.selectByMap(param);
        userCodes = new ArrayList<>();
        if (null != serviceBad && serviceBad.size() > 0) {
            for (AnnouncementEntity announcementEntity : serviceBad) {
                userCodes.add(announcementEntity.getUserCode());
            }
        }
        if (null != userCodes && userCodes.size() > 0) {
            param.put("userCodes", userCodes);
            List<UserEntity> userEntities = userDbDao.selectUserBasisInfoByMap(param);
            if (null != userEntities) {
                result.setServiceBad(userEntities);
            }
        }

        param.put("announcementType", "5");
        List<AnnouncementEntity> standard = announcementDbDao.selectByMap(param);
        userCodes = new ArrayList<>();
        if (null != standard && standard.size() > 0) {
            for (AnnouncementEntity announcementEntity : standard) {
                userCodes.add(announcementEntity.getUserCode());
            }
        }
        if (null != userCodes && userCodes.size() > 0) {
            param.put("userCodes", userCodes);
            List<UserEntity> userEntities = userDbDao.selectUserBasisInfoByMap(param);
            if (null != userEntities) {
                result.setStandard(userEntities);
            }
        }

        param.put("announcementType", "6");
        List<AnnouncementEntity> standardBad = announcementDbDao.selectByMap(param);
        userCodes = new ArrayList<>();
        if (null != standardBad && standardBad.size() > 0) {
            for (AnnouncementEntity announcementEntity : standardBad) {
                userCodes.add(announcementEntity.getUserCode());
            }
        }
        if (null != userCodes && userCodes.size() > 0) {
            param.put("userCodes", userCodes);
            List<UserEntity> userEntities = userDbDao.selectUserBasisInfoByMap(param);
            if (null != userEntities) {
                result.setStandardBad(userEntities);
            }
        }
        MyAchievementEntity mh = getMyAchievementEntity();
        result.setMyAchievement(mh);
        return result;
    }

    private MyAchievementEntity getMyAchievementEntity() {
        // 查询我的实时业绩
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", CurrentContext.getUserCode());
        param.put("createTimeStart", DateUtil.beginOfMonth(DateUtil.date()));
        param.put("createTimeEnd", DateUtil.endOfDay(DateUtil.date()));
        MyAchievementEntity me = myAchievementDbDao.selectAchievementByMap(param);
        // 计算推荐成交额 =  房源推荐成交额 + 客户推荐成交额
        BigDecimal sum = new BigDecimal(0);

        if (null != me.getRecCusMoney() && null != me.getRecHosMoney()) {
            sum = me.getRecHosMoney().add(me.getRecCusMoney());
        } else if (null != me.getRecHosMoney()) {
            sum = me.getRecHosMoney();
        } else if (null != me.getRecCusMoney()) {
            sum = me.getRecCusMoney();
        }
        me.setRecTurnVolume(sum);
        this.getQuotaDefine(me);
        return me;
    }

    private void getQuotaDefine(MyAchievementEntity me) {
        // 查询个人业绩指标
        Map<String, Object> param = new HashMap<>();
        param.put("qaTime", DateUtil.format(DateUtil.date(), "yyyy-MM"));
        param.put("ownerCode", CurrentContext.getUserCode());
        QuotaAchievementEntity qa = quotaAchievementDbDao.selectUniqueByMap(param);
        if (null != qa) {
            me.setTotalMoneyValue(qa.getTotalMoneyValue());
            me.setDefineAddCusCount(qa.getCusValue());
            me.setDefineFollowupCount(qa.getCusFollowValue());
            me.setDefineLookFollowupCount(qa.getSeeValue());
            me.setDefineAddHosCount(qa.getHosValue());
            me.setDefineOwnerVisitCount(qa.getOwnerFollowValue());
            me.setDefineAddUserCount(qa.getPtValue());
        } else {
            BigDecimal sum = new BigDecimal("0");
            me.setTotalMoneyValue(sum);
            me.setDefineAddCusCount(0);
            me.setDefineFollowupCount(0);
            me.setDefineLookFollowupCount(0);
            me.setDefineAddHosCount(0);
            me.setDefineOwnerVisitCount(0);
            me.setDefineAddUserCount(0);
        }
    }
}
