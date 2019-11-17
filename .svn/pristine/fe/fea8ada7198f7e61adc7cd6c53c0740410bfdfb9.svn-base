package com.kfwy.hkp.controller.hos.house.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.hkp.hos.house.entity.HouseLocRegionEntity;
import com.kfwy.hkp.hos.house.entity.HouseQueryEntity;

import javax.validation.constraints.NotNull;

/**
 * Create By hjh on 2018/7/25
 */
public class HouseServiceRequest extends AbstractServiceRequest<HouseQueryVo> {

    /**
     * 关键字
     */
    @NotNull(message = "keyword不能为空")
    private String keyword;

    private HouseLocRegionEntity leftDown;
    private HouseLocRegionEntity  rightUp;

    private String houseCode;

    private HouseQueryEntity houseQuery;

    private String empCode;

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public HouseQueryEntity getHouseQuery() {
        return houseQuery;
    }

    public void setHouseQuery(HouseQueryEntity houseQuery) {
        this.houseQuery = houseQuery;
    }


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public HouseLocRegionEntity getLeftDown() {
        return leftDown;
    }

    public void setLeftDown(HouseLocRegionEntity leftDown) {
        this.leftDown = leftDown;
    }

    public HouseLocRegionEntity getRightUp() {
        return rightUp;
    }

    public void setRightUp(HouseLocRegionEntity rightUp) {
        this.rightUp = rightUp;
    }
}
