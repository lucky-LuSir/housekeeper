package com.kfwy.hkp.hos.house.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.kfwy.hkp.hos.house.business.ISharePoolDeptManager;
import com.kfwy.hkp.hos.house.dao.ISharePoolDeptDbDao;
import com.kfwy.hkp.hos.house.entity.SharePoolDeptEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/7/24
 * @author kfwy_it_013
 */
@Service
public class SharePoolDeptManagerImpl extends AbstractManager<SharePoolDeptEntity> implements ISharePoolDeptManager {

    private static Logger logger = LoggerFactory.getLogger(SharePoolDeptManagerImpl.class);

    @Autowired
    private ISharePoolDeptDbDao sharePoolDeptDbDao;

    @Override
    protected IMyBatisBaseDao<SharePoolDeptEntity, Long> getMyBatisDao() {
        return null;
    }

    @Override
    public PageResult<SharePoolDeptEntity> selectSharePoolDept(Map map, Integer start, Integer pageSize) {
        return this.sharePoolDeptDbDao.selectByStatement("selectByMap",map,start,pageSize);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addSharePoolDeptInfo(SharePoolDeptEntity sharePoolDeptEntity) {
        return this.sharePoolDeptDbDao.insert(sharePoolDeptEntity);
    }

    @Override
    public int updateSharePoolDeptInfo(SharePoolDeptEntity sharePoolDeptEntity) {
        return this.sharePoolDeptDbDao.update(sharePoolDeptEntity);
    }

    @Override
    public int delSharePoolDeptInfo(Long id) {
        return this.sharePoolDeptDbDao.deleteById(id);
    }

    @Override
    public int addSharePoolDeptInfo(List<SharePoolDeptEntity> sharePoolDeptEntityList) {
        return this.sharePoolDeptDbDao.batchInsert(sharePoolDeptEntityList);
    }

    @Override
    public List<SharePoolDeptEntity> findByShareCodeMap(HashMap<String, Object> spdeptmap) {
        List<SharePoolDeptEntity> spdList = sharePoolDeptDbDao.selectByShareCodeMap(spdeptmap);
        return spdList;
    }
}