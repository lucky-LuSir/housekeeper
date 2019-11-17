package com.kfwy.hkp.sys.file.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.sys.file.entity.FileEntity;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Auth davidCun
 * @Date 2018/6/13 08:37
 * @Version 1.0
 */
public interface IFileDbDao extends IMyBatisBaseDao<FileEntity,Long> {

    public List<FileEntity> selectByMapCut(Map<String,Object> map);
}
