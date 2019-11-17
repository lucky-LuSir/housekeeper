package com.kfwy.hkp.sys.file.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;

import java.util.Map;

/**
 * Create By hjh on 2018/7/30
 */
public interface IFileRelationDbDao extends IMyBatisBaseDao<FileRelationEntity,Long> {
    /*
    *   删除不包含传入得id得数据
    *
    * */
    public void deleteNotIds(Map<String,Object> map);

    public int add(FileRelationEntity fileRelationEntity);
}
