package com.kfwy.hkp.sys.announcement.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.sys.announcement.enums.AnnounementType;
import com.kfwy.hkp.sys.quota.entity.MyAchievementEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;

import java.util.List;


public class AnnouncementEntity extends BaseEntity {

    private String userCode;

    private String userName;

    private String announcementType;

    private String announcementTypeName;

    private MyAchievementEntity myAchievement;
    /**
     * yyyy-MM
     */
    private String yearMonth;
    List<UserEntity> achievement;
    List<UserEntity> achievementBad;
    List<UserEntity> service;
    List<UserEntity> serviceBad;
    List<UserEntity> standard;
    List<UserEntity> standardBad;

    public MyAchievementEntity getMyAchievement() {
        return myAchievement;
    }

    public void setMyAchievement(MyAchievementEntity myAchievement) {
        this.myAchievement = myAchievement;
    }

    public List<UserEntity> getAchievement() {
        return achievement;
    }

    public void setAchievement(List<UserEntity> achievement) {
        this.achievement = achievement;
    }

    public List<UserEntity> getAchievementBad() {
        return achievementBad;
    }

    public void setAchievementBad(List<UserEntity> achievementBad) {
        this.achievementBad = achievementBad;
    }

    public List<UserEntity> getService() {
        return service;
    }

    public void setService(List<UserEntity> service) {
        this.service = service;
    }

    public List<UserEntity> getServiceBad() {
        return serviceBad;
    }

    public void setServiceBad(List<UserEntity> serviceBad) {
        this.serviceBad = serviceBad;
    }

    public List<UserEntity> getStandard() {
        return standard;
    }

    public void setStandard(List<UserEntity> standard) {
        this.standard = standard;
    }

    public List<UserEntity> getStandardBad() {
        return standardBad;
    }

    public void setStandardBad(List<UserEntity> standardBad) {
        this.standardBad = standardBad;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAnnouncementType() {
        return announcementType;
    }

    public void setAnnouncementType(String announcementType) {
        this.announcementType = announcementType;
    }

    public void setAnnouncementTypeName(String announcementTypeName) {
        this.announcementTypeName = announcementTypeName;
    }

    public String getAnnouncementTypeName() {
        return DictionaryStorage.get(AnnounementType.class.getName(),this.getAnnouncementType()).getName();
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }
}
