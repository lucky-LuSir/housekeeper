package com.kfwy.hkp.bi.count.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.business.ILocationAnalysisManager;
import com.kfwy.hkp.bi.count.dao.ILocationAnalysisDbDao;
import com.kfwy.hkp.bi.count.dto.CommonReportDto;
import com.kfwy.hkp.bi.count.entity.LocationAnalysisEntity;
import com.kfwy.hkp.hos.house.dao.IHouseLocationDbDao;
import com.kfwy.hkp.hos.house.entity.HouseLocationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Create By xpp on 2019/2/26
 */
@Service
public class LocationAnalysisManagerImpl extends AbstractManager<LocationAnalysisEntity> implements ILocationAnalysisManager {

    @Autowired
    private IHouseLocationDbDao houseLocationDbDao;

    @Autowired
    private ILocationAnalysisDbDao locationAnalysisDbDao;

    @Override
    protected IMyBatisBaseDao<LocationAnalysisEntity, Long> getMyBatisDao() {
        return null;
    }



    private void getTableHead(CommonReportDto<LocationAnalysisEntity> reportDto) {
        Map<String, String> tableHead = new LinkedHashMap<>();

        tableHead.put("wordName", "--");
        tableHead.put("heatRent", "热租中");
        tableHead.put("alreadyRent", "已出租");
        tableHead.put("willExpire", "在租将到期");

        reportDto.setTableHeads(tableHead);
    }

    @Override
    public CommonReportDto<LocationAnalysisEntity> count(String code) {

        return null;
    }

    @Override
    public CommonReportDto<LocationAnalysisEntity> locMapCount(String locCode) {
        CommonReportDto<LocationAnalysisEntity> commonReport = new CommonReportDto<LocationAnalysisEntity>();
        this.getTableHead(commonReport);
        List<LocationAnalysisEntity> dataList = new ArrayList<LocationAnalysisEntity>();

        Map<String, Object> param = new HashMap<>();


        LocationAnalysisEntity areaObj = new LocationAnalysisEntity();
        param.put("locationCode",locCode);
        HouseLocationEntity locAliasEntity =houseLocationDbDao.selectUniqueByMap(param);
        commonReport.setRemark(locAliasEntity.getStreetName()+"--"+locAliasEntity.getLocationAlias());
        param.put("houseStatus","1");
        int heatRent = 0;
        heatRent = locationAnalysisDbDao.selectRentArea(param);
        param.remove("houseStatus");
        param.put("houseStatus","2");
        int alreadyRent =0;
        alreadyRent = locationAnalysisDbDao.selectRentArea(param);
        int willExpire = 0;
        areaObj.setWordName("面积(m²)");
        areaObj.setHeatRent(heatRent+"");
        areaObj.setAlreadyRent(alreadyRent+"");
        areaObj.setWillExpire(willExpire+"");

        LocationAnalysisEntity suitObj = new LocationAnalysisEntity();
        param.put("locationCode",locCode);
        param.put("houseStatus","1");
        heatRent=0;
        heatRent = locationAnalysisDbDao.countSuitArea(param);
        param.remove("houseStatus");
        param.put("houseStatus","2");
        alreadyRent =0;
        alreadyRent = locationAnalysisDbDao.countSuitArea(param);
        willExpire = 0;
        suitObj.setWordName("套数(套)");
        suitObj.setHeatRent(heatRent+"");
        suitObj.setAlreadyRent(alreadyRent+"");
        suitObj.setWillExpire(willExpire+"");

        dataList.add(suitObj);
        dataList.add(areaObj);   //dataList = locationAnalysisDbDao.locMapCount(param);

        commonReport.setReportEntities(dataList);

        return commonReport;
    }
}
