package com.kfwy.hkp.cooperate.business;


import com.gniuu.framework.common.business.IManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.cooperate.entity.CooperateEntity;
import com.kfwy.hkp.cooperate.entity.EvaluateEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by zjp on 2018/8/15.
 */
public interface ICooperateManager extends IManager<CooperateEntity> {

    /**
     * 批量新增
     * @param list
     */
    int batchCreate(List<CooperateEntity> list);

    /**
     * 通过cooCode来查询详情
     * @param cooCode
     * @return
     */
    CooperateEntity detail(String cooCode);

    /**
     *查询合作列表，只返回简单的合作信息
     * @param param
     * @return
     */
    List<CooperateEntity> querySimpleCooperateListByMap(Map param);

    /**
    * @Description 描述:客户详情页申请合作
    * @Auth xpp
    * @Date 2018/12/18 17:16
    * @Version 1.0
    * @Param [entity]
    * @return int
    **/
    int createcus(CooperateEntity entity);

    /**
    * @Description 描述:房源详情页申请合作
    * @Auth xpp
    * @Date 2018/12/18 18:35
    * @Version 1.0
    * @Param [entity]
    * @return int
    **/
    int createhos(CooperateEntity entity);

    /**
    * @Description 描述:合作列表加入申请人和接受人信息实体
    * @Auth xpp
    * @Date 2018/12/20 14:09
    * @Version 1.0
    * @Param [param, start, pageSize, create_time, b]
    * @return com.gniuu.framework.dataaccess.PageResult<com.kfwy.hkp.cooperate.entity.CooperateEntity>
    **/
    PageResult<CooperateEntity> findByMapCoo(Map param, Integer start, Integer pageSize, String create_time, boolean b,String applyOrRecei);

    /**
     * 用户合作 评价 新增
     * @param evaluateEntity
     * @return
     */
    int evaluvteInsert(EvaluateEntity evaluateEntity);

    /**
     * 用户合作 评价 查看
     * @param userCode
     * @return
     */
    PageResult<EvaluateEntity> evaluvteSelect(String userCode,Integer start,Integer pageSize);

    /**
     * 判断合作是否能关闭
     * @param cooperateEntity
     * @param userCode
     * @return
     */
    public Boolean endCoo(CooperateEntity cooperateEntity, String userCode);
    }
