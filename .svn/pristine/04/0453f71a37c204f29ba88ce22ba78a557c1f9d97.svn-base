package com.kfwy.hkp.sys.file.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.sys.file.dao.IFileDbDao;
import com.kfwy.hkp.sys.file.dao.IFileRelationDbDao;
import com.kfwy.hkp.sys.file.entity.FileEntity;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Create By hjh on 2018/7/30
 */
@Repository
public class FileRelationDbDaoImpl extends AbstractMyBatisDao<FileRelationEntity,Long> implements IFileRelationDbDao{

    @Override
    public int add(FileRelationEntity fileRelationEntity) {
        int i = this.getSqlSession().insert(this.mapperNamespace + "insert", fileRelationEntity);
        return i;
    }

    @Override
    public void deleteNotIds(Map<String,Object> map) {
        this.getSqlSession().delete(this.mapperNamespace+"deleteNotIds",map);
    }
}

