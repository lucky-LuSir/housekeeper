package com.kfwy.imports.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.hrm.org.dept.api.DeptApiClient;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.user.api.UserApiClient;
import com.kfwy.hkp.sys.user.entity.UserDto;
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

/**
 * Created by hjh on 2018/11/26.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/*.xml")
public class DeptOrUserInit {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /*@Autowired
    @Qualifier("jdbcTemplate")
    JdbcTemplate jdbcTemplate;*/

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void test() throws Exception {


        String userSql = getUser();

        //String userSql = generateDeptSql();
        // 获取地址
        //Path path = Paths.get("d:/emp.txt");
        File file = new File("d:/initUser.sql");
        // 写入文件
        //BufferedWriter writer = Files.newBufferedWriter(path);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
        writer.write(userSql);
        writer.newLine();
        writer.newLine();
        writer.close();

        System.out.println(userSql);
    }

    public String getUser() throws IOException {
        List<UserDto> userDtos = UserApiClient.initUsersAll();

        String userSql = "";
        for (UserDto userDto : userDtos) {

            System.out.println("员工编码-----------------" + userDto.getUserCode());

            // 处理为null的日期字段
            String quitTime = null;
            if(null == userDto.getQuitTime()){
                quitTime = "null,";
            }else{
                quitTime = "'" + userDto.getQuitTime() + "',";
            }

            userSql += "insert into t_hkp_sys_user_user(" +
                    "user_code, user_name, user_phone, user_img, user_type, work_number, email, sex, password, secure, has_manager, " +
                    "owner_dept_code, owner_dept_name, enter_type, leader_code, emp_post_code, emp_post_name, entry_time, quit_time," +
                    "create_time, is_deleted)"
                    +" values (" +
                    "'" + userDto.getUserCode() + "'," +
                    "'" + userDto.getUserName() + "'," +
                    "'" + userDto.getUserPhone() + "'," +
                    "'" + userDto.getUserImg() + "'," +
                    "'" + userDto.getUserType() + "'," +
                    "'" + userDto.getWorkNumber() + "'," +
                    "'" + userDto.getEmail() + "'," +
                    "'" + userDto.getSex() + "'," +
                    "'" + userDto.getPassword() + "'," +
                    "'" + userDto.getSecure() + "'," +
                    "" + userDto.getHasManager() + "," +
                    "'" + userDto.getOwnerDeptCode() + "'," +
                    "'" + userDto.getOwnerDeptName() + "'," +
                    "'" + userDto.getEnterCusType() + "'," +
                    "'" + userDto.getLeaderCode() + "'," +
                    "'" + userDto.getEmpPostCode() + "'," +
                    "'" + userDto.getEmpPostName() + "'," +
                    "'" + (userDto.getEntryTime() == null ? "now()" : DateFormatUtil.dateToString(userDto.getEntryTime())) + "'," +
                    quitTime +
                    "'" + "now()"  + "'," +
                    "'" + userDto.getIsDeleted() + "'" +
                    ");\n";
        }
        return userSql;
    }


    /**
     * 部门信息
     * @throws Exception
     */
    public String generateDeptSql() throws Exception {

        List<DeptEntity> deptEntityList = DeptApiClient.findAll();

        String deptInsertSql = "";
        for (DeptEntity deptEntity : deptEntityList) {

            System.out.println("部门编码-----------------" + deptEntity.getDeptCode());

            deptInsertSql += "insert into t_hkp_hrm_org_dept(" +
                    "dept_code, dept_name, dept_phone, dept_type, dept_level, parent_code, parent_name, leader_code, leader_name, leader_phone," +
                    "dept_address, create_time) "
                    +" values (" +
                    "'" + deptEntity.getDeptCode() + "',"
                    + "'" +deptEntity.getDeptName() + "',"
                    + queryForOne("SELECT phone_number from t_ky_emp_employee where emp_code =  ?", String.class, new Object[]{deptEntity.getLeaderCode()}) + ","
                    + "'" +deptEntity.getDeptType() + "',"
                    + "'" +deptEntity.getDeptLevel() + "',"
                    + "'" +deptEntity.getParentCode() + "',"
                    + queryForOne("SELECT dept_name from t_ky_emp_dept where dept_code =  ?", String.class, new Object[]{deptEntity.getParentCode()}) + ","
                    + "'" +deptEntity.getLeaderCode() + "',"
                    + queryForOne("SELECT emp_name from t_ky_emp_employee where emp_code =  ?", String.class, new Object[]{deptEntity.getLeaderCode()}) + ","
                    + queryForOne("SELECT phone_number from t_ky_emp_employee where emp_code =  ?", String.class, new Object[]{deptEntity.getLeaderCode()}) + ","
                    + " '"+deptEntity.getDeptAddress()+"'"  + ","
                    + "now()"
                    + ");\n";
        }

        return  deptInsertSql;
    }

    /**
     * 处理驼峰命名
     * @return
     */
    List<Map<String, Object>> dealHump(List<Map<String, Object>> mapList) {

        List<Map<String, Object>> mapListNew = new ArrayList<>();
        for (Map map : mapList) {
            Map<String, Object> mapNew = new HashMap<>();
            Set<Map.Entry> set = map.entrySet();
            Iterator it = set.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry)it.next();
                String key = (String) entry.getKey();
                int index;
                while ((index = key.indexOf("_")) > 0) {
                    String c = key.substring(index + 1, index + 2);
                    key = key.replace("_" + c, c.toUpperCase());
                }
                mapNew.put(key, entry.getValue());
            }
            mapListNew.add(mapNew);
        }

        return mapListNew;
    }

    Object queryForOne(String sql, Class requiredType, Object... args) {

        Object object = null;
        try{
            object = jdbcTemplate.queryForObject(sql, requiredType, args);
        } catch (Exception e){
            return null;
        }

        if (object != null) {
            object = "'" + object + "'";
        }
        return object;
    }

    Object queryForOne1(String sql, Class requiredType, Object... args) {

        Object object = null;
        try{
            object = jdbcTemplate.queryForObject(sql, requiredType, args);
        } catch (Exception e){
            return null;
        }

        return object;
    }

}
