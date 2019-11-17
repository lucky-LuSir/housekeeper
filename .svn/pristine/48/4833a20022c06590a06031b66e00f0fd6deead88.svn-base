package com.kfwy.hkp.log.manager;

import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.log.entity.BaseOperationEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 基础操作日志表(BaseOperation)服务接口
 *
 * @author liwensihan
 * @since 2019-05-28 10:59:33
 */
public interface BaseOperationMongoManager{

    void save(HttpServletRequest request, String path, Object handler);

    void save(String url, String requestParam);

    PageResult<BaseOperationEntity> findByMap(Map<String,Object> map, Integer start, Integer pageSize, String create_time, Boolean aFalse);

    List<BaseOperationEntity> findUrlsByOptCode();

    List<BaseOperationEntity> findClientType();

    BaseOperationEntity findOne(Map<String,Object> map);
}