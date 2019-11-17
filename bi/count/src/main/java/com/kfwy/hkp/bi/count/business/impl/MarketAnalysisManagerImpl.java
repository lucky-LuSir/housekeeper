package com.kfwy.hkp.bi.count.business.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.base.SystemConfig;
import com.kfwy.hkp.bi.count.business.IMarketAnalysisManager;
import com.kfwy.hkp.bi.count.dto.CommonReportDto;
import com.kfwy.hkp.bi.count.entity.MarketAnalysisEntity;
import com.kfwy.hkp.bi.count.enums.ReportType;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.hos.house.dao.IHouseDbDao;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.auth.dao.IReportAccessDbDao;
import com.kfwy.hkp.sys.user.dao.IUserDbDao;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Create By hjh on 2019/1/9
 */
@Service
public class MarketAnalysisManagerImpl extends AbstractManager<MarketAnalysisEntity> implements IMarketAnalysisManager {

    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private IUserDbDao userDbDao;
    @Autowired
    private IHouseDbDao houseDbDao;
    @Autowired
    private ICustomerDbDao customerDbDao;
    @Autowired
    private SystemConfig systemConfig;
    @Autowired
    private IReportAccessDbDao reportAccessDbDao;

    @Override
    protected IMyBatisBaseDao<MarketAnalysisEntity, Long> getMyBatisDao() {
        return null;
    }

    @Override
    public CommonReportDto<MarketAnalysisEntity> count(Date startTime, Date endTime, String code) {

        // 根据权限查询报表
        if (code == null) {
            List<String> deptCodes = reportAccessDbDao.selectAccess(CurrentContext.getUserCode());
            if (CollectionUtil.isNotEmpty(deptCodes)) {
                code = deptCodes.get(0);
            } else {
                UserEntity u = (UserEntity) CurrentContext.getUserInfo();
                code = u.getOwnerDeptCode() == null ? u.getUserCode() : u.getOwnerDeptCode();
            }
        }

        CommonReportDto<MarketAnalysisEntity> reportDto = new CommonReportDto<>();

        // 加入线程安全
        List<MarketAnalysisEntity> ms = Collections.synchronizedList(new ArrayList<>());

        // 切换erp数据库,查询限制客户配置数量
        // 行业性质所有值
        String[] industrys = systemConfig.getArrayValue("cus_market_industry_value");
        // 客户需求面积分段
        String[] markets = systemConfig.getArrayValue("cus_market_area_value");

        if (industrys != null) {

            // 设置线程池的数量大小
            System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "15");

            DeptEntity dept = deptManager.findOne("deptCode", code);

            String type = null;

            List<String> codes = new ArrayList<>();

            Map<String, Object> map = new HashMap<>();
            map.put("isDeleted", false);

            if (null == dept) {
                // 查询人员
                map.put("userCode", code);
                map.put("isDeleted", Boolean.FALSE);
                List<UserEntity> userEntities = userDbDao.selectSimpleUserByMap(map);
                for (UserEntity u : userEntities) {
                    codes.add(u.getUserCode());
                }
                type = ReportType.EMP;
            } else {
                // 查询下级部门
                map.put("isDeleted", false);
                map.put("parentCode", code);
                List<DeptEntity> depts = deptManager.findByMap(map);
                map.clear();
                if (depts == null || depts.isEmpty()) {
                    // 查询人员
                    map.put("ownerDeptCode", code);
                    map.put("quitTimeStart", startTime);
                    map.put("entryTimeEnd", endTime);
                    List<String> userCodes = userDbDao.selectUserCodeByMap(map);
                    // 获取人员报表信息
                    if (CollectionUtil.isEmpty(userCodes)) {
                        return reportDto;
                    }
                    codes.addAll(userCodes);
                    type = ReportType.EMP;
                } else {
                    type = ReportType.DEPT;
                    // 获取所有下级部门编码包括自身
                    List<String> deptCodes = deptManager.getValidDownDeptCode(code, startTime, endTime);
                    codes.addAll(deptCodes);
                }
            }

            if (codes.isEmpty()) {
                return reportDto;
            }

            // 查询客户
            List<String> asList = Arrays.asList(industrys);
            String finalType = type;
            asList.parallelStream().forEach(e -> {
                MarketAnalysisEntity marketValue = this.getCusMarketValue(startTime, endTime, codes, e, markets, finalType);
                ms.add(marketValue);
            });

            // 总和
            ms.add(this.valueSum(ms));

            //查询目前房源的需求面积
            MarketAnalysisEntity houseMae = this.getHosMarketValue(startTime, endTime, codes, markets, type);

            ms.add(houseMae);

        }
        reportDto.setReportEntities(ms);

        this.getTableHead(reportDto);

        return reportDto;
    }

    public MarketAnalysisEntity valueSum(List<MarketAnalysisEntity> ms) {

        MarketAnalysisEntity maket = new MarketAnalysisEntity();

        int marketOne = 0;
        int marketTwo = 0;
        int marketThree = 0;
        int marketFour = 0;
        int marketFive = 0;
        int marketSix = 0;
        int marketCount = 0;

        for (MarketAnalysisEntity m : ms) {
            marketOne += m.getMarketOne();
            marketTwo += m.getMarketTwo();
            marketThree += m.getMarketThree();
            marketFour += m.getMarketFour();
            marketFive += m.getMarketFive();
            marketSix += m.getMarketSix();
            marketCount += m.getMarketCount();
        }

        maket.setIndustry("总和");
        maket.setMarketOne(marketOne);
        maket.setMarketTwo(marketTwo);
        maket.setMarketThree(marketThree);
        maket.setMarketFour(marketFour);
        maket.setMarketFive(marketFive);
        maket.setMarketSix(marketSix);
        maket.setMarketCount(marketCount);

        return maket;
    }

    public MarketAnalysisEntity getCusMarketValue(Date startTime, Date endTime, List<String> codes, String industry, String[] markets, String type) {

        MarketAnalysisEntity m = new MarketAnalysisEntity();
        String s = DateUtil.format(startTime, "yyyy-MM-dd");
        String e = DateUtil.format(endTime, "yyyy-MM-dd");
        m.setTimeNumber("客户" + s + "至" + e);
        m.setIndustry(industry);
        Map<String, Object> map = new HashMap<>();

        if (ReportType.DEPT.equals(type)) {
            map.put("createDeptCodes", codes);
        } else {
            map.put("createCodes", codes);
        }

        map.put("industry", industry);
        map.put("createTimeStart", startTime);
        map.put("createTimeEnd", endTime);

        long needAcreageStart = 0;
        long needCount = 0;
        if (markets != null && markets.length > 0) {
            int i = 1;
            for (String market : markets) {
                if (NumberUtils.isDigits(market)) { //判断必须是数字
                    long needAcreageEnd = Long.parseLong(market);
                    map.put("needAcreageStart", needAcreageStart);
                    map.put("needAcreageEnd", needAcreageEnd);
                    int count = customerDbDao.selectCountByMap(map);
                    needCount += count;
                    this.toMarketParameter(m, i, count);
                    needAcreageStart = needAcreageEnd;
                    i++;
                }
            }
            m.setMarketCount(needCount);
        } else {
            long count = customerDbDao.selectCountByMap(map);
            m.setMarketOne(count);
            m.setMarketCount(count);
        }
        return m;
    }

    public MarketAnalysisEntity getHosMarketValue(Date startTime, Date endTime, List<String> codes, String[] markets, String type) {

        MarketAnalysisEntity m = new MarketAnalysisEntity();

        String s = DateUtil.format(startTime, "yyyy-MM-dd");
        String e = DateUtil.format(endTime, "yyyy-MM-dd");
        m.setTimeNumber("房源" + s + "至" + e);
        m.setIndustry("可租用房源");

        Map<String, Object> map = new HashMap<>();

        if (ReportType.DEPT.equals(type)) {
            map.put("createDeptCodes", codes);
        } else {
            map.put("createCodes", codes);
        }

        map.put("createTimeStart", startTime);
        map.put("createTimeEnd", endTime);

        long needAcreageStart = 0;
        long needCount = 0;
        if (markets != null && markets.length > 0) {
            int i = 1;
            for (String market : markets) {
                if (NumberUtils.isDigits(market)) { //判断必须是数字
                    long needAcreageEnd = Long.parseLong(market);
                    map.put("acreageStart", needAcreageStart);
                    map.put("acreageEnd", needAcreageEnd);
                    int count = houseDbDao.selectCountByMap(map);
                    needCount += count;
                    this.toMarketParameter(m, i, count);
                    needAcreageStart = needAcreageEnd;
                    i++;
                }
            }
            m.setMarketCount(needCount);
        }
        return m;
    }

    protected void toMarketParameter(MarketAnalysisEntity m, int i, long count) {
        switch (i) {
            case 1:
                m.setMarketOne(count);
                break;
            case 2:
                m.setMarketTwo(count);
                break;
            case 3:
                m.setMarketThree(count);
                break;
            case 4:
                m.setMarketFour(count);
                break;
            case 5:
                m.setMarketFive(count);
                break;
            case 6:
                m.setMarketSix(count);
                break;
        }
    }

    private void getTableHead(CommonReportDto<MarketAnalysisEntity> reportDto) {
        Map<String, String> tableHead = new LinkedHashMap<>();

        tableHead.put("industry", "对应需求面积的客户");
        tableHead.put("marketOne", "0-499平");
        tableHead.put("marketTwo", "500-999平");
        tableHead.put("marketThree", "1000-1999平");
        tableHead.put("marketFour", "2000-2999平");
        tableHead.put("marketFive", "3000-4999平");
        tableHead.put("marketSix", "5000平以上");
        tableHead.put("marketCount", "总计");

        reportDto.setTableHeads(tableHead);
    }
}
