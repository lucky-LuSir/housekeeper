package com.kfwy.hkp.bi.count.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.business.IHosAndCusTotalManager;
import com.kfwy.hkp.bi.count.dao.IHosAndCusTotalDbDao;
import com.kfwy.hkp.bi.count.dto.CommonTotalDto;
import com.kfwy.hkp.bi.count.entity.HosAndCusTotalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: HJH
 * @Date: 2019/7/1
 * @Description: TODO
 */
@Service
public class HosAndCusTotalManagerImpl extends AbstractManager<HosAndCusTotalEntity> implements IHosAndCusTotalManager {

    @Autowired
    private IHosAndCusTotalDbDao hosAndCusTotalDbDao;

    @Override
    protected IMyBatisBaseDao<HosAndCusTotalEntity, Long> getMyBatisDao() {
        return null;
    }

    @Override
    public CommonTotalDto<HosAndCusTotalEntity> select(String yearMonthStart, String yearMonthEnd) {
        CommonTotalDto<HosAndCusTotalEntity> dto = new CommonTotalDto<>();
        Map<String, Object> map = new HashMap<>();
        map.put("yearMonthStart", yearMonthStart);
        map.put("yearMonthEnd", yearMonthEnd);
        map.put("isDeleted", false);
        List<HosAndCusTotalEntity> cusTotals = hosAndCusTotalDbDao.selectCusByMap(map);
        List<HosAndCusTotalEntity> hosTotals = hosAndCusTotalDbDao.selectHosByMap(map);
        dto.setTotal1(cusTotals);
        dto.setTotal2(hosTotals);
        return dto;
    }
}
