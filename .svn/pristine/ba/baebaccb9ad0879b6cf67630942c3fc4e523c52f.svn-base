package com.kfwy.hkp.job.qiChaCha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 重置调用企查查次数
 * 每个部门每月规定多少次
 */
@Component
public class QiChaChaReset {

    @Autowired
    private StringRedisTemplate template;

    public void reset(){
        Set<String> keys = template.keys("ky:hkp:qiChaCha:currentNum:*");
        keys.forEach(i -> {
            template.opsForValue().set(i,"0");
        });
    }
}
