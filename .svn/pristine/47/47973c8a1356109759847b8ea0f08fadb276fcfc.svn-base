package com.kfwy.hkp.hos.house.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.kfwy.hkp.hos.house.business.ISharePoolManager;
import com.kfwy.hkp.hos.house.dao.ISharePoolDbDao;
import com.kfwy.hkp.hos.house.entity.SharePoolEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/7/24
 * @author kfwy_it_013
 */
@Service
public class SharePoolManagerImpl extends AbstractManager<SharePoolEntity> implements ISharePoolManager {

    private static Logger logger = LoggerFactory.getLogger(SharePoolManagerImpl.class);

    @Autowired
    private ISharePoolDbDao sharePoolDbDao;

    @Override
    protected IMyBatisBaseDao<SharePoolEntity, Long> getMyBatisDao() {
        return null;
    }

    @Override
    public PageResult<SharePoolEntity> selectSharePool(Map map, Integer start, Integer pageSize) {
        return this.sharePoolDbDao.selectByStatement("selectByMap",map,start,pageSize);
    }

    @Override
    public int addSharePoolInfo(SharePoolEntity sharePoolEntity) {
        return this.sharePoolDbDao.insert(sharePoolEntity);
    }

    @Override
    public int updateSharePoolInfo(SharePoolEntity sharePoolEntity) {
        return this.sharePoolDbDao.update(sharePoolEntity);
    }

    @Override
    public int delSharePoolInfo(Long id) {
        return this.sharePoolDbDao.deleteById(id);
    }

    @Override
    public SharePoolEntity findSharePoolByShareCode(String shareCode) {
        return this.sharePoolDbDao.selectUniqueByProp("shareCode",shareCode);
    }

    @Override
    public int addSharePoolInfoList(List<SharePoolEntity> sharePoolEntityList) {
        return this.sharePoolDbDao.batchInsert(sharePoolEntityList);
    }

    @Override
    public List<SharePoolEntity> selectByMyDept(Map map) {
        return sharePoolDbDao.selectByMyDept(map);
    }

    @Override
    public List<SharePoolEntity> findByShareCodeMap(HashMap<String, Object> map) {
        List<SharePoolEntity> spList = sharePoolDbDao.selectByShareCodeMap(map);
        return spList;
    }
}