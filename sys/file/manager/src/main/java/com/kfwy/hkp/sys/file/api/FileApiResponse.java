package com.kfwy.hkp.sys.file.api;

import com.gniuu.framework.service.AbstractServiceResponse;
import com.kfwy.hkp.sys.file.entity.FileEntity;

import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/11/7 16:32
 */
public class FileApiResponse extends AbstractServiceResponse {

    private List<FileEntity> fileList;

    private FileEntity entity;

    public FileEntity getEntity() {
        return entity;
    }

    public void setEntity(FileEntity entity) {
        this.entity = entity;
    }

    public List<FileEntity> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileEntity> fileList) {
        this.fileList = fileList;
    }
}
