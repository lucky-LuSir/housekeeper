package com.kfwy.hkp.sys.notice.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by jupe on 2018/5/2.
 */
public interface INoticeDbDao extends IMyBatisBaseDao<NoticeEntity, Long> {

    List<Map<String, Object>> selectDeptEmpInfo(Map param);

    List<NoticeEntity> noticeQuery(Map map,Integer start,Integer pageSize);

    int deleteByBussinessCode(String bussiness);

}
