package com.kfwy.hkp.sys.notice.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.sys.notice.dao.INoticeDbDao;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by jupe on 2018/5/3.
 */
@Repository
public class NoticeDbDaoImpl extends AbstractMyBatisDao<NoticeEntity, Long> implements INoticeDbDao {

    @Override
    public List<Map<String, Object>> selectDeptEmpInfo(Map param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectDeptEmpInfo",param);
    }

    @Override
    public List<NoticeEntity> noticeQuery (Map map,Integer start,Integer pageSize) {
        return this.getSqlSession ().selectList (this.mapperNamespace+"noticeQuery",map, new RowBounds (start, pageSize));
    }

    @Override
    public int deleteByBussinessCode (String bussiness) {
        return this.getSqlSession ().delete (this.mapperNamespace+"deleteByBussinessCode",bussiness);
    }
}
