package com.kfwy.hkp.sys.cpy.business;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.RestBusinessException;
import com.kfwy.hkp.sys.cpy.dao.ICompanyDbDao;
import com.kfwy.hkp.sys.cpy.entity.CompanyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by davidcun on 2018/5/25.
 */
@Service
public class CompanyManagerImpl extends AbstractManager<CompanyEntity> implements ICompanyManager {

    @Autowired
    private ICompanyDbDao companyDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.companyDbDao;
    }

    @Override
    protected void beforeCreate(CompanyEntity companyEntity) {


    }

    @Override
    protected void beforeUpdate(CompanyEntity companyEntity) {
        if(StringUtils.isEmpty(companyEntity.getCpyCode())
                && companyEntity.getId()==null){
            throw new RestBusinessException("更新企业帐套，cpyCode和Id不能都为空");
        }
    }
}
