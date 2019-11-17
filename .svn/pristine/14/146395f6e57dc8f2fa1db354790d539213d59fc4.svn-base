package com.kfwy.hkp.log;

import com.kfwy.hkp.common.annotion.AutoIncKey;
import com.kfwy.hkp.common.mongo.entity.SeqInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @AUTHOR 李文思汗
 * @Description 类描述:
 */
//@Component
public class SaveEventListener extends AbstractMongoEventListener<Object> {
    @Autowired
    MongoTemplate mgt;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        final Object source = event.getSource();
        if (source != null) {
            ReflectionUtils.doWithFields(source.getClass(),new ReflectionUtils.FieldCallback(){
                @Override
                public void doWith(Field field) throws IllegalAccessException {
                    //将一个字段设置为可读写，主要针对private字段；
                    ReflectionUtils.makeAccessible(field);
                    // 如果字段添加了我们自定义的AutoValue注解
                    if (field.isAnnotationPresent(AutoIncKey.class)
                            && field.get(source) instanceof Number
                            && field.getLong(source) == 0) {
                        // 设置自增ID
                        field.set(source, getNextAutoId(source.getClass().getSimpleName()));
                    }
                }
            });
        }
    }



    // 获取下一个自增ID
    private Long getNextAutoId(String collName) {
        Query query = new Query(Criteria.where("collName").is(collName));
        Update update = new Update();
        update.inc("seqId", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true);
        options.returnNew(true);
        SeqInfo seqInfo = mgt
                .findAndModify(query, update, options, SeqInfo.class);
        return seqInfo.getSeqId();
    }
}
