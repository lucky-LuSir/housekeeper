package com.kfwy.hkp.bi.count.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.business.IHouseDictionaryManager;
import com.kfwy.hkp.bi.count.dao.IHouseDictionaryDbDao;
import com.kfwy.hkp.bi.count.entity.HouseDictionaryEntity;
import com.kfwy.hkp.hos.house.enums.HouseStatus;
import com.kfwy.hkp.sys.area.dao.ICompanyAreaDbDao;
import com.kfwy.hkp.sys.area.entity.CompanyAreaEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Create By hjh on 2019/2/26
 */
@Service
public class HouseDictionaryManagerImpl extends AbstractManager<HouseDictionaryEntity> implements IHouseDictionaryManager {

    @Autowired
    private IHouseDictionaryDbDao houseDictionaryDbDao;
    @Autowired
    private ICompanyAreaDbDao companyAreaDbDao;

    @Override
    protected IMyBatisBaseDao<HouseDictionaryEntity, Long> getMyBatisDao() {
        return null;
    }

    @Override
    public List<HouseDictionaryEntity> select(String code) {
        List<HouseDictionaryEntity> hds = Collections.synchronizedList(new ArrayList<>());
        List<CompanyAreaEntity> as = new ArrayList<>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isDeleted", false);
        if (StringUtils.isEmpty(code)) {
            // 默认查询
            map.put("level", 2);
        } else {
            map.put("parentCode", code);
        }
        as = companyAreaDbDao.selectByMap(map);

        if (as.isEmpty()) {
            return hds;
        }

        // 设置线程池的数量大小
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");

        as.parallelStream().forEach(e -> {
            try {
                HouseDictionaryEntity hd = this.getHouseDictionary(e);
                hds.add(hd);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        });

        return hds;
    }

    private HouseDictionaryEntity getHouseDictionary(CompanyAreaEntity e) {
        HouseDictionaryEntity hd = new HouseDictionaryEntity();
        hd.setShowName(e.getName());

        this.getParkCount(e, hd);
        this.getHouseCount(e, hd);
        this.getHouseAreaSum(e, hd);

        return hd;
    }

    private void getParkCount(CompanyAreaEntity e, HouseDictionaryEntity hd) {
        Map<String, Object> map = new HashMap<>();
        map.put("level", e.getLevel());
        map.put("areaCode", e.getAreaCode());
        map.put("isDeleted", false);
        long parkTotal = houseDictionaryDbDao.selectLocationCount(map);
        hd.setParkTotal(parkTotal);

        hd.setMarketParkTotal((long) (parkTotal / 0.9));

        map.put("hotLocationCode", e.getAreaCode());
        long parkHotRent = houseDictionaryDbDao.selectLocationCount(map);
        hd.setParkHotRent(parkHotRent);

        hd.setParkRented(parkTotal - parkHotRent);
    }

    private void getHouseCount(CompanyAreaEntity e, HouseDictionaryEntity hd) {
        Map<String, Object> map = new HashMap<>();
        map.put("level", e.getLevel());
        map.put("areaCode", e.getAreaCode());
        map.put("isDeleted", false);
        long houseTotal = houseDictionaryDbDao.selectHouseCount(map);
        hd.setHouseTotal(houseTotal);

        hd.setMarketHouseTotal((long) (houseTotal / 0.9));

        map.put("houseStatus", HouseStatus.hotRenting);
        long houseHire = houseDictionaryDbDao.selectHouseCount(map);
        hd.setHouseHotRent(houseHire);

        map.put("houseStatus", HouseStatus.HasLease);
        long houseRented = houseDictionaryDbDao.selectHouseCount(map);
        hd.setHouseRented(houseRented);
    }

    private void getHouseAreaSum(CompanyAreaEntity e, HouseDictionaryEntity hd) {
        Map<String, Object> map = new HashMap<>();
        map.put("level", e.getLevel());
        map.put("areaCode", e.getAreaCode());
        map.put("isDeleted", false);
        float houseAreaTotal = houseDictionaryDbDao.selectHouseAreaSum(map);
        hd.setHouseAreaTotal(houseAreaTotal);

        hd.setMarketHouseAreaTotal(houseAreaTotal / 0.9F);

        map.put("houseStatus", HouseStatus.hotRenting);
        float houseAreaHotRent = houseDictionaryDbDao.selectHouseAreaSum(map);
        hd.setHouseAreaHotRent(houseAreaHotRent);

        map.put("houseStatus", HouseStatus.HasLease);
        float houseAreaRented = houseDictionaryDbDao.selectHouseAreaSum(map);
        hd.setHouseAreaRented(houseAreaRented);
    }


    @Override
    public HouseDictionaryEntity getHosDicByCurCode(String areaCode) {

        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("areaCode", areaCode);
        CompanyAreaEntity e = companyAreaDbDao.selectUniqueByMap(mp);
        HouseDictionaryEntity hd = new HouseDictionaryEntity();
        Map<String, Object> map = new HashMap<>();
        map.put("level", e.getLevel());
        map.put("areaCode", e.getAreaCode());
        map.put("isDeleted", false);
        Long parkTotal = houseDictionaryDbDao.selectLocationCount(map);
        Long houseTotal =  houseDictionaryDbDao.selectHouseCount(map);
        float selectHouseAreaSum = houseDictionaryDbDao.selectHouseAreaSum(map);

        hd.setParkTotal(parkTotal);
        hd.setHouseTotal(houseTotal);
        hd.setHouseAreaTotal(selectHouseAreaSum);

        return hd;
    }

}
