package com.kfwy.hkp.hrm.org.dept.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by davidcun on 2018/5/22.
 */
public interface IDeptManager extends IManager<DeptEntity> {

    public String insert(DeptEntity dept);

    public String delete(DeptEntity dept);

    //public String update(DeptEntity dept);

    public List<DeptEntity> getlist();

    /**
     * 部门树
     */
    public List<DeptEntity> selectDeptTree(Map<String, Object> map);


    public void saveOrUpdate(DeptEntity dept);

    DeptEntity getDeptEntity(String deptCode);

    /**
     * 根据时间查询当前部门下有效的部门(包括当前部门)
     *
     * @param code
     * @return
     */
    public List<String> getValidDownDeptCode(String code, Date startTime, Date endTime);

    /**
     * 查询当前部门的所有下级部门code（包括当前部门）
     *
     * @param code
     * @return
     */
    public List<String> getDownDeptCode(String code);

    /**
     * 查询当前部门的所有下级部门code（包括当前部门和撤点部门）
     *
     * @param code
     * @return
     */
    public List<String> getDownAllDeptCode(String code);

    /**
     * 判断该人员是否为所属部门的领导
     *
     * @param deptCode
     * @param leaderCode
     * @return
     */
    Boolean hasDeptLeader(String deptCode, String leaderCode);

    DeptEntity createTeam(DeptEntity entity);

    List<DeptEntity> findNearby(Map<String, Object> map);

    public List<String> getWageDeptCodes();

    public List<String> getWageAllDeptCodes();

    boolean getHasSeeAddressBook(String deptCode);

    public DeptEntity findOneDept(Map map);

    public List<DeptEntity> findAllDeptByMap(Map map);
}
