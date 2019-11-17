package com.kfwy.hkp.common.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @AUTHOR 李文思汗
 * @Description 类描述:
 */
@Document(collection = "sequence")
public class SeqInfo {
    @Id
    private String id;//主键

    private Long seqId;//序列值

    private String collName;//集合名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }

    public String getCollName() {
        return collName;
    }

    public void setCollName(String collName) {
        this.collName = collName;
    }
}
