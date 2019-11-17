package com.kfwy.hkp.sys.file.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/7/30
 */
public interface IFileRelationManager extends IManager<FileRelationEntity> {


    /**
     * 通过文件编码查询文件实体信息
     * @auth hjh
     * @version 1.0
     * @param fileCode
     * @return com.kfwy.hkp.sys.file.entity.FileEntity
     */
    FileRelationEntity findByCode(String fileCode);


    List<FileRelationEntity> findByMap(Map<String, Object> param);


    /**
     *
     * @param files
     */
    public void create(List<FileRelationEntity> files) throws IllegalAccessException;

    public void update(List<FileRelationEntity> files,String ownerCode,String fileName,String anotherName,String empCode);


    public void tranTest(String pm);

}
