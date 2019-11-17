package com.kfwy.imports.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.common.utils.MD5Utils;
import com.kfwy.hkp.sys.notice.dao.INoticeDbDao;
import com.kfwy.hkp.sys.notice.dao.INoticeRecordDbDao;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeOperateType;
import com.kfwy.hkp.sys.notice.enums.NoticeType;
import com.kfwy.imports.entity.TestDeptEntity;
import com.kfwy.imports.entity.TestEmployeeEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by kfwy_it_010 on 2018/9/17.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/*.xml")
public class EmployeeInit {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    INoticeDbDao noticeDbDao;

    @Autowired
    INoticeRecordDbDao noticeRecordDbDao;

    @Test
    public  void deleteNotice(){
        Integer start = 1;
        Integer pageSize = 100;
        while (true){
            Map map = new HashMap ();
            map.put("noticeType", NoticeType.SYSTEM);
            map.put("bussinessType", NoticeOperateType.Add);

           // PageResult<NoticeEntity> result = noticeDbDao.selectByMap (map, (start - 1) * 100, pageSize);
            PageResult<NoticeEntity> result = noticeDbDao.selectByMap (map, 0, pageSize);
            if (result==null){
                break;
            }

            for (NoticeEntity noticeEntity : result.getData ()) {
                List<String> recordCodes = noticeEntity.getNoticeRecordEntityList ()
                        .stream().map(p -> p.getRecordCode ()).collect(Collectors.toList());
                int i = noticeRecordDbDao.deleteByRecordCode (recordCodes);
                System.out.println ("成功删除"+i+"条record数据");
            }

            List<Long> ids = result.getData ().stream ().map (p -> p.getId ()).collect (Collectors.toList ());
            int ids1 = noticeDbDao.batchDeleteByIds (ids);
            System.out.println ("成功删除"+ids1+"条notice数据");
            //start++;
        }
    }


}
