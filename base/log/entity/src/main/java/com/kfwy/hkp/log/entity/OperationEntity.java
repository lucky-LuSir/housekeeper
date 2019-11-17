package com.kfwy.hkp.log.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.common.enums.OperationType;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2019/1/19 14:02
 */
public class OperationEntity extends BaseEntity {
    /**
     * 操作编码
     */
    protected String optCode;
    /**
     * 操作类型
     */
    protected String optType;

    protected String optTypeName;


    /**
     * 业务编码
     */
    protected String bizCode;
    /**
     * 请求url
     */
    protected String url;
    /**
     * 操作内容
     */
    protected String content;

    public OperationEntity() {
    }
    public OperationEntity(String optCode, String optType, String bizCode, String url, String content) {
        this.optCode = optCode;
        this.optType = optType;
        this.bizCode = bizCode;
        this.url = url;
        this.content = content;
    }
    public OperationEntity(String optCode, String optType, String bizCode, String url, String content,String remark) {
        this.optCode = optCode;
        this.optType = optType;
        this.bizCode = bizCode;
        this.url = url;
        this.content = content;
        this.remark=remark;
    }
    public OperationEntity(String optCode, String optType, String bizCode, String url, String content,String remark,String createCode,String createDeptCode,String lastUpdateCode) {
        this.optCode = optCode;
        this.optType = optType;
        this.bizCode = bizCode;
        this.url = url;
        this.content = content;
        this.remark=remark;
        this.createCode = createCode;
        this.createDeptCode  = createDeptCode;
        this.lastUpdateCode = lastUpdateCode;
    }

    private OperationEntity(Builder builder) {
        setId(builder.id);
        setCpyCode(builder.cpyCode);
        setCpyName(builder.cpyName);
        setOwnerCode(builder.ownerCode);
        setOwnerName(builder.ownerName);
        setOwnerDeptCode(builder.ownerDeptCode);
        setOwnerDeptName(builder.ownerDeptName);
        setCreateCode(builder.createCode);
        setCreateName(builder.createName);
        setCreateDeptCode(builder.createDeptCode);
        setCreateDeptName(builder.createDeptName);
        setLastUpdateCode(builder.lastUpdateCode);
        setLastUpdateName(builder.lastUpdateName);
        setCreateTime(builder.createTime);
        setLastUpdateTime(builder.lastUpdateTime);
        setIsDeleted(builder.isDeleted);
        setRemark(builder.remark);
        setOptCode(builder.optCode);
        setOptType(builder.optType);
        setBizCode(builder.bizCode);
        setUrl(builder.url);
        setContent(builder.content);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getOptCode() {
        return optCode;
    }

    public void setOptCode(String optCode) {
        this.optCode = optCode;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
        if (this.optTypeName == null && null != this.optType){
            this.optTypeName = DictionaryStorage.get(OperationType.class.getName(),this.getOptType()).getName();
        }
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getOptTypeName() {
        return optTypeName;
    }

    public void setOptTypeName(String optTypeName) {
        this.optTypeName = optTypeName;
    }

    public static class Builder {
        private Long id;
        private String cpyCode;
        private String cpyName;
        private String ownerCode;
        private String ownerName;
        private String ownerDeptCode;
        private String ownerDeptName;
        private String createCode;
        private String createName;
        private String createDeptCode;
        private String createDeptName;
        private String lastUpdateCode;
        private String lastUpdateName;
        private Date createTime;
        private Date lastUpdateTime;
        private Boolean isDeleted;
        private String remark;
        private String optCode;
        private String optType;
        private String bizCode;
        private String url;
        private String content;

        private Builder() {
        }



        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder cpyCode(String val) {
            cpyCode = val;
            return this;
        }

        public Builder cpyName(String val) {
            cpyName = val;
            return this;
        }

        public Builder ownerCode(String val) {
            ownerCode = val;
            return this;
        }

        public Builder ownerName(String val) {
            ownerName = val;
            return this;
        }

        public Builder ownerDeptCode(String val) {
            ownerDeptCode = val;
            return this;
        }

        public Builder ownerDeptName(String val) {
            ownerDeptName = val;
            return this;
        }

        public Builder createCode(String val) {
            createCode = val;
            return this;
        }

        public Builder createName(String val) {
            createName = val;
            return this;
        }

        public Builder createDeptCode(String val) {
            if (StringUtils.isNotEmpty(val)){
                createDeptCode = val;
            }
            return this;
        }

        public Builder createDeptName(String val) {
            createDeptName = val;
            return this;
        }

        public Builder lastUpdateCode(String val) {
            lastUpdateCode = val;
            return this;
        }

        public Builder lastUpdateName(String val) {
            lastUpdateName = val;
            return this;
        }

        public Builder createTime(Date val) {
            createTime = val;
            return this;
        }

        public Builder lastUpdateTime(Date val) {
            lastUpdateTime = val;
            return this;
        }

        public Builder isDeleted(Boolean val) {
            isDeleted = val;
            return this;
        }

        public Builder remark(String val) {
            remark = val;
            return this;
        }

        public Builder optCode(String val) {
            optCode = val;
            return this;
        }

        public Builder optType(String val) {
            optType = val;
            return this;
        }

        public Builder bizCode(String val) {
            bizCode = val;
            return this;
        }

        public Builder url(String val) {
            url = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public OperationEntity build() {
            return new OperationEntity(this);
        }
    }
}
