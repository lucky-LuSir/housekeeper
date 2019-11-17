package com.kfwy.hkp.hos.house.business.impl;

import com.kfwy.hkp.common.annotion.HandlerType;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserLevel;
import org.springframework.stereotype.Service;

/**
 * @AUTHOR 李文思汗
 * @Description 类描述:
 */
@HandlerType(UserLevel.K0)
@Service(value = "k0HouseCheckManager")
public class HouseLevelK0CheckManagerImpl extends AbstractHouseCheckManager {

    @Override
    public Boolean checkDetail(HouseEntity house, UserEntity cur, UserEntity owner) {
        if (cur.getEnterType().equals("1")
                || checkIsMyHos(house)
                || checkSameDeptShare(house, owner)
                || hasSharePool(house, cur, owner)
                || hasCooperate(house, cur)) {
            return true;
        }

        return false;
    }

    @Override
    public Boolean checkFollowupRecord(HouseEntity house, UserEntity cur, UserEntity owner) {

        if (cur.getEnterType().equals("1")
//                || house.getOpenFlag()
                || checkIsMyHos(house)
                || checkSameDeptShare(house, owner)
                || hasSharePool(house, cur, owner)
                || hasCooperate(house, cur)) {
            return true;
        }

        return false;
    }

    @Override
    public Boolean checkFollowUp(HouseEntity house, UserEntity cur, UserEntity owner) {

        if (cur.getEnterType().equals("1")
//                || house.getOpenFlag()
                || checkIsMyHos(house)
                || checkSameDeptShare(house, owner)
                || hasSharePool(house, cur, owner)
                || hasCooperate(house, cur)) {

            return true;

        }

        return false;
    }

    @Override
    public Boolean checkAuthOfUpdate(HouseEntity house, UserEntity cur, UserEntity owner) {

        if (cur.getEnterType().equals("1")
                || checkIsMyHos(house)
                || checkSameDeptShare(house, owner)
                || hasSharePool(house, cur, owner)
                || hasCooperate(house, cur)) {
            return true;
        }

        return false;

    }

    @Override
    public Boolean checkAuthOfUp(HouseEntity house, UserEntity cur, UserEntity owner) {
        if ("1".equals(cur.getEnterType())
                ||checkIsMyHos(house)
                || checkSameDeptShare(house,owner)
                || hasSharePool(house,cur,owner)){
            return true;
        }

        return false;
    }

    @Override
    public Boolean checkAuthOfDown(HouseEntity house, UserEntity cur, UserEntity owner) {
        /*if ("1".equals(cur.getEnterType())
                ||checkIsMyHos(house)){
            return true;
        }
        return false;*/
        return true;
    }

    @Override
    public Boolean checkAuthOfHide(HouseEntity house, UserEntity cur, UserEntity owner) {
        if ("1".equals(cur.getEnterType())
                ||checkIsMyHos(house)){
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkAuthOfShow(HouseEntity house, UserEntity cur, UserEntity owner) {
        if ("1".equals(cur.getEnterType())
                ||checkIsMyHos(house)){
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkAuthOfVisitCustomer(HouseEntity house, UserEntity cur, UserEntity owner) {
        return true;
    }

    @Override
    public Boolean checkAuthOfAddVisitOwner(HouseEntity house, UserEntity cur, UserEntity owner) {
        return true;
    }

    @Override
    public Boolean checkAuthOfVisitOwner(HouseEntity house, UserEntity cur, UserEntity owner) {
        return true;
    }

    @Override
    public Boolean checkAuthOfAddVisitCustomer(HouseEntity house, UserEntity cur, UserEntity owner) {
        return true;
    }

    @Override
    public Boolean checkCollect(HouseEntity house, UserEntity cur, UserEntity owner) {
        return true;
    }

    @Override
    public Boolean checkShare(HouseEntity house, UserEntity cur, UserEntity owner) {
        return true;
    }



    @Override
    public Boolean checkApply(HouseEntity house, UserEntity cur, UserEntity owner) {

        if (cur.getUserCode().equals(owner.getUserCode())){
            return false;
        }

        return true;
    }

    @Override
    public Boolean checkBusinessOpportunities() {
        return true;
    }
}
