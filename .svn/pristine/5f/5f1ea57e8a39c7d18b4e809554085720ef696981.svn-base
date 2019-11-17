package com.kfwy.hkp.sys.area.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.sys.area.entity.AreaAllEntity;
import com.kfwy.hkp.sys.area.entity.BaseAreaEntity;
import com.kfwy.hkp.sys.area.entity.CompanyAreaEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/7/3 09:32
 */
public interface ICompanyAreaManager extends IManager<CompanyAreaEntity> {

    /**
     * 通过编码查询区域
     * @param areaCode
     * @return
     */
    CompanyAreaEntity findAreaByCode(String areaCode);

    void enableArea(int enableFlag, BaseAreaEntity provinceObj, BaseAreaEntity cityObj, BaseAreaEntity regionObj, List<BaseAreaEntity> readyInsertList);

    void enableFlagIsOne(BaseAreaEntity provinceObj, List<BaseAreaEntity> readyInsertList);

    void enableFlagIsTwo(BaseAreaEntity provinceObj, BaseAreaEntity cityObj, List<BaseAreaEntity> readyInsertList);

    void enableFlagIsThree(BaseAreaEntity provinceObj, BaseAreaEntity cityObj, BaseAreaEntity regionObj, List<BaseAreaEntity> readyInsertList);

    void createCompanyArea(List<BaseAreaEntity> readyInsertList);

    CompanyAreaEntity baseArea2CpyArea(BaseAreaEntity baseArea);

    public List<AreaAllEntity> getCascaderArea();
}
