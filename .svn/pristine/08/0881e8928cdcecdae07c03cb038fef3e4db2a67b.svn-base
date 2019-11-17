package com.kfwy.hkp.sys.file.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.sys.file.dao.IFileDbDao;
import com.kfwy.hkp.sys.file.entity.FileEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Auth davidCun
 * @Date 2018/6/13 08:38
 * @Version 1.0
 */
@Repository
public class FileDbDaoImpl extends AbstractMyBatisDao<FileEntity, Long> implements IFileDbDao {
    @Override
    public List<FileEntity> selectByMapCut(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectByMapCut", map);
    }
}
