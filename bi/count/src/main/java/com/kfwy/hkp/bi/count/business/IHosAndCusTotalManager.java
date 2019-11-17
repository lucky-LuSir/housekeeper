package com.kfwy.hkp.bi.count.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.bi.count.dto.CommonTotalDto;
import com.kfwy.hkp.bi.count.entity.HosAndCusTotalEntity;

/**
 * @Auther: HJH
 * @Date: 2019/7/1
 * @Description: TODO
 */
public interface IHosAndCusTotalManager extends IManager<HosAndCusTotalEntity> {

    public CommonTotalDto<HosAndCusTotalEntity> select(String yearMonthStart, String yearMonthEnd);
}
