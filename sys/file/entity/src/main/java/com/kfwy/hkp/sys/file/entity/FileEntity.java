package com.kfwy.hkp.sys.file.entity;

import com.gniuu.framework.entity.BaseEntity;

/**
 * @Description TODO
 * @Auth davidCun
 * @Date 2018/6/12 23:29
 * @Version 1.0
 */
public class FileEntity extends BaseEntity {

    /**
     * 文件编码
     */
    private String fileCode;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件类型
     * @see com.kfwy.hkp.sys.file.dictionary.FileType
     */
    private String fileType;

    /**
     * 文件前缀
     */
    private String pathHead;

    /**
     * 文件路径
     * 值示例：house/2018/123abc.jpg
     * 所以，要去文件的后缀可以从filePath取
     */
    private String filePath;

    /**
     * 所属文件夹编码
     * 默认顶层文件夹不设置为0
     */
    private String parentCode = "0";

    /**
     * 是否共享，默认FALSE
     */
    private Boolean isShare = Boolean.FALSE;


    private Integer start;
    private Integer pageSize;
    private String transferFileType;
    /*
     * 别名
     */
    private String anotherName;


    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPathHead() {
        return pathHead;
    }

    public void setPathHead(String pathHead) {
        this.pathHead = pathHead;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Boolean getIsShare() {
        return isShare;
    }

    public void setIsShare(Boolean share) {
        isShare = share;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getTransferFileType() {
        return transferFileType;
    }

    public void setTransferFileType(String transferFileType) {
        this.transferFileType = transferFileType;
    }

    public String getAnotherName() {
        return anotherName;
    }

    public void setAnotherName(String anotherName) {
        this.anotherName = anotherName;
    }
}
