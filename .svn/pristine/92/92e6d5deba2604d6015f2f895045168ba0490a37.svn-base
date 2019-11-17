package com.kfwy.hkp.log.entity;

import java.math.BigInteger;
import java.util.Date;
import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 基础操作日志表(BaseOperation)实体类
 *
 * @author liwensihan
 * @since 2019-05-28 10:41:40
 */
@Document(collection = "t_hkp_base_operation")
public class BaseOperationEntity implements Serializable {
    private static final long serialVersionUID = -74047386890839511L;
    /**
     * id
     */
    @Id
    protected String id;

    //操作人
    @Indexed(name = "opt_name", direction = IndexDirection.DESCENDING)
    @Field
    protected String optName;

    //操作接口url
    @Indexed(name = "url")
    @Field
    protected String url;

    //操作人编号
    @Indexed(name = "opt_code")
    @Field
    protected String optCode;

    /**
     * 是否作废或者删除
     */
    @Field
    protected Boolean isDeleted;

    /**
     * 创建时间
     */
    @Indexed(name = "create_time", direction = IndexDirection.DESCENDING)
    @Field
    protected Date createTime;

    @Indexed(name = "ip")
    @Field
    protected String ip;

    @Indexed(name = "client_type")
    @Field
    protected String clientType;

    @Indexed(name = "request_param")
    @Field
    protected String requestParam;

    public String getResponseResult() {
        return responseResult;
    }

    public void setResponseResult(String responseResult) {
        this.responseResult = responseResult;
    }

    @Indexed(name="response_result")
    @Field
    protected String responseResult;

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }
    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
    }

    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getOptCode() {
        return optCode;
    }

    public void setOptCode(String optCode) {
        this.optCode = optCode;
    }

    public BaseOperationEntity() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BaseOperationEntity(String optName, Date createTime, Boolean isDeleted, String url, String optCode,String ip,String clientType) {
        this.optName = optName;
        this.createTime = createTime;
        this.isDeleted = isDeleted;
        this.url = url;
        this.optCode = optCode;
        this.clientType=clientType;
        this.ip=ip;
    }

    public BaseOperationEntity(String optName, Date createTime, Boolean isDeleted, String url, String optCode,String ip,String clientType,String requestParam) {
        this.optName = optName;
        this.createTime = createTime;
        this.isDeleted = isDeleted;
        this.url = url;
        this.optCode = optCode;
        this.clientType=clientType;
        this.ip=ip;
        this.requestParam=requestParam;
    }
}