package com.kfwy.hkp.crm.customer.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.gniuu.framework.user.entity.BaseUserEntity;
import com.kfwy.hkp.crm.customer.bo.FocusCusUserScoreMatchBo;
import com.kfwy.hkp.crm.customer.dao.IFocusCusDbDao;
import com.kfwy.hkp.crm.customer.dto.CustomerFocusCusDto;
import com.kfwy.hkp.crm.customer.entity.FocusCusEntity;
import com.kfwy.hkp.sys.user.entity.UserFocusCusBo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class FocusCusDbDaoImpl extends AbstractMyBatisDao<FocusCusEntity,Long> implements IFocusCusDbDao {


    @Override
    public int updateFocusCusToPlatform(List<CustomerFocusCusDto> list,Map map) {
        return this.getSqlSession().update(this.mapperNamespace+"updateFocusCusToPlatform",map);
    }

    @Override
    public List<CustomerFocusCusDto> findFocusCusToPlatForm(Map map) {
        return this.getSqlSession().selectList(this.mapperNamespace+"findFocusCusToPlatForm",map);
    }

    @Override
    public List<FocusCusUserScoreMatchBo> focusCusUserScoreMatch(Map map) {

        return this.getSqlSession().selectList(this.mapperNamespace+"focusCusUserScoreMatch",map);

    }

    @Override
    public Integer checkFocusCusNotice(Map map) {
        return this.getSqlSession().selectOne(this.mapperNamespace+"checkFocusCusNotice",map);
    }

    @Override
    public List<UserFocusCusBo> leastGetGuestQuery (Map map) {
        return this.getSqlSession ().selectList (this.mapperNamespace+"leastGetGuestQuery",map);
    }

    @Override
    public int deletedByCusCode (String cusCode) {
        return this.getSqlSession ().delete (this.mapperNamespace+"deleteByCudCode",cusCode);
    }
}
