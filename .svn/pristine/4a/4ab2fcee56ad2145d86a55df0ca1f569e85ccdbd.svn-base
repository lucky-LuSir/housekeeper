

package com.kfwy.hkp.hrm.org.dept.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.user.entity.BaseUserEntity;
import com.kfwy.hkp.common.enums.DeptType;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.dao.IDeptAreaDbDao;
import com.kfwy.hkp.hrm.org.dept.dao.IDeptDbDao;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.hrm.org.dept.enums.PayStatus;
import com.kfwy.hkp.hrm.org.dept.util.DeptTreeUtils;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * Created by davidcun on 2018/5/22.
 */
@Service
public class DeptManagerImpl extends AbstractManager<DeptEntity> implements IDeptManager {

    @Autowired
    private IDeptDbDao deptDbDao;
    @Autowired
    private IDeptAreaDbDao deptAreaDbDao;
    @Autowired
    private IUserManager userManager;


    @Override
    protected IMyBatisBaseDao<DeptEntity, Long> getMyBatisDao() {
        return this.deptDbDao;
    }

    @Override
    public String insert(DeptEntity dept) {

        String msg = "进入了DeptManagerImpl新增方法aaa" + dept.getDeptType();
        List<DeptEntity> plist = new ArrayList<DeptEntity>();
        plist.add(dept);
        deptDbDao.batchInsert(plist);
        return msg;
    }

    @Override
    public String delete(DeptEntity dept) {
        String code = dept.getDeptCode();
        deptDbDao.deleteByCode(code);
        String msg = "部门删除方法";
        return msg;
    }


    @Transactional
    @Override
    public void saveOrUpdate(DeptEntity dept) {

        if (dept != null) {
            DeptEntity tmp = deptDbDao.selectUniqueByProp("deptCode", dept.getDeptCode());

            if (ObjectUtils.isEmpty(tmp)) {
                deptDbDao.insert(dept);

            } else {
                deptDbDao.update(dept);
            }

            deptAreaDbDao.deleteByDeptCode(dept.getDeptCode());

            deptAreaDbDao.batchInsert(dept.getDeptAreas());

        }
    }

    @Override
    public List<DeptEntity> getlist() {
        List<DeptEntity> deptList = deptDbDao.getList();
        return deptList;
    }

    @Override
    public List<DeptEntity> selectDeptTree(Map<String, Object> map) {

        // 根节点
        List<DeptEntity> rootList = deptDbDao.selectSimpleList(map);
        map.clear();

        // 查询当前有效部门
        map.put("isDeleted", false);
        List<DeptEntity> deptList = deptDbDao.selectSimpleList(map);

        // 转换为树结构
        DeptTreeUtils utils = new DeptTreeUtils(rootList, deptList);
        List<DeptEntity> tree = utils.getTree();

        return tree;
    }

    @Override
    public DeptEntity getDeptEntity(String deptCode) {
        String preSql = "SELECT * FROM t_hkp_hrm_org_dept" +
                " WHERE dept_code=\'%s\'";
        String sql = String.format(preSql, deptCode);
        List<DeptEntity> deptEntities = deptDbDao.selectByNativeSql(sql);
        DeptEntity deptEntity = new DeptEntity();
        if (deptEntities == null || deptEntities.size() == 0) {
            deptEntity = null;
        } else {
            deptEntity = deptDbDao.selectByNativeSql(sql).get(0);
        }

        return deptEntity;
    }

    @Override
    public List<String> getValidDownDeptCode(String code, Date startTime, Date endTime) {
        List<String> codes = new ArrayList<>();

        DeptEntity dept = deptDbDao.selectUniqueByProp("deptCode", code);

        if (dept != null) {
            List<String> deptEntities = this.recursionValidDeptCode(dept.getDeptCode(), startTime, endTime);
            if (!deptEntities.isEmpty()) {
                codes.addAll(deptEntities);
            }
            codes.add(dept.getDeptCode());
        }
        return codes;
    }

    @Override
    public List<String> getDownDeptCode(String code) {

        List<String> codes = new ArrayList<>();

        DeptEntity dept = deptDbDao.selectUniqueByProp("deptCode", code);

        if (dept != null) {
            List<String> deptEntities = this.recursionDeptCode(dept.getDeptCode());
            if (!deptEntities.isEmpty()) {
                codes.addAll(deptEntities);
            }
            codes.add(dept.getDeptCode());
        }
        return codes;
    }

    @Override
    public List<String> getDownAllDeptCode(String code) {

        List<String> codes = new ArrayList<>();

        DeptEntity dept = deptDbDao.selectUniqueByProp("deptCode", code);

        if (dept != null) {
            List<DeptEntity> deptEntities = this.recursionAllDept(dept);
            if (!deptEntities.isEmpty()) {
                for (DeptEntity d : deptEntities) {
                    codes.add(d.getDeptCode());
                }
            }
            codes.add(dept.getDeptCode());
        }
        return codes;
    }

    public List<String> recursionValidDeptCode(String deptCode, Date startTime, Date endTime) {
        Map<String, Object> map = new HashMap<>();
        List<String> result = new ArrayList<>();
        if (StringUtils.isNotEmpty(deptCode)) {
            map.put("deleteTimeStart", startTime);
            map.put("createTimeEnd", endTime);
            map.put("parentCode", deptCode);
            List<String> deptEntities = deptDbDao.selectDeptCodeByMap(map);
            if (!deptEntities.isEmpty()) {
                for (String d : deptEntities) {
                    result.add(d);
                    List<String> ds = this.recursionValidDeptCode(d, startTime, endTime);
                    if (ds != null && !ds.isEmpty()) {
                        result.addAll(ds);
                    }
                }
            }
        }
        return result;
    }


    public List<String> recursionDeptCode(String deptCode) {
        Map<String, Object> map = new HashMap<>();
        List<String> result = new ArrayList<>();
        if (StringUtils.isNotEmpty(deptCode)) {
            map.put("isDeleted", false);
            map.put("parentCode", deptCode);
            List<String> deptEntities = deptDbDao.selectDeptCodeByMap(map);
            map.clear();
            if (!deptEntities.isEmpty()) {
                for (String d : deptEntities) {
                    result.add(d);
                    List<String> ds = this.recursionDeptCode(d);
                    if (ds != null && !ds.isEmpty()) {
                        result.addAll(ds);
                    }
                }
            }
        }
        return result;
    }

    public List<DeptEntity> recursionDept(DeptEntity dept) {
        Map<String, Object> map = new HashMap<>();
        List<DeptEntity> result = new ArrayList<>();
        if (dept != null && StringUtils.isNotEmpty(dept.getDeptCode())) {
            map.put("isDeleted", false);
            map.put("parentCode", dept.getDeptCode());
            List<DeptEntity> deptEntities = deptDbDao.selectByMap(map);
            map.clear();
            if (!deptEntities.isEmpty()) {
                for (DeptEntity d : deptEntities) {
                    result.add(d);
                    List<DeptEntity> ds = this.recursionDept(d);
                    if (ds != null && !ds.isEmpty()) {
                        result.addAll(ds);
                    }
                }
            }
        }
        return result;
    }

    public List<DeptEntity> recursionAllDept(DeptEntity dept) {
        Map<String, Object> map = new HashMap<>();
        List<DeptEntity> result = new ArrayList<>();
        if (dept != null && StringUtils.isNotEmpty(dept.getDeptCode())) {
            map.put("parentCode", dept.getDeptCode());
            List<DeptEntity> deptEntities = deptDbDao.selectByMap(map);
            map.clear();
            if (!deptEntities.isEmpty()) {
                for (DeptEntity d : deptEntities) {
                    result.add(d);
                    List<DeptEntity> ds = this.recursionAllDept(d);
                    if (ds != null && !ds.isEmpty()) {
                        result.addAll(ds);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Boolean hasDeptLeader(String deptCode, String leaderCode) {
        boolean hasLeader = false;
        String preSql = "SELECT * FROM t_hkp_hrm_org_dept" +
                " WHERE dept_code=\'%s\' and leader_code=\'%s\'";
        String sql = String.format(preSql, deptCode, leaderCode);
        DeptEntity deptEntity = deptDbDao.selectOneByNativeSql(sql);
        if (deptEntity != null) {
            hasLeader = true;
        }
        return hasLeader;
    }

    @Override
    public DeptEntity createTeam(DeptEntity dept) {
        String curUserCode = CurrentContext.getUserCode();
        BaseUserEntity userInfo = CurrentContext.getUserInfo();
        if (userInfo == null) {
            throw new RemoveStackBusinessException ("用户信息为空,请重新登陆." + curUserCode);
        }


        Map<String, Object> map = new HashMap<>();
        map.put("leaderCode", userInfo.getUserCode());
        List<DeptEntity> deptEntities = deptDbDao.selectByMap(map);
        if (deptEntities != null) {
            if (deptEntities.size() > 0) {
                throw new RemoveStackBusinessException("您已经创建过部门!不可重复创建!");
            }
        }


        String genCode = "Team" + CodeGenUtils.generate();
        //加入生成编码
        dept.setDeptCode(genCode);
        //加入创建人修改人
        OperateFillUtils.fill(dept);
        dept.setTeamFlag(true);
        dept.setPayStatus(PayStatus.Begin);
        dept.setParentCode("DP201605260001");
        dept.setParentName("IT管理部");
        dept.setDeptLevel(3);
        dept.setDeptType(DeptType.Team);
        dept.setLeaderCode(userInfo.getUserCode());
        dept.setLeaderName(userInfo.getUserName());

        if (dept.getDeptAreas() == null || dept.getDeptAreas().size() == 0) {
            throw new RemoveStackBusinessException("需要传入省市区区域列表deptAreas");
        }

        String deptAddress = dept.getDeptAreas().get(0).getCityName() + dept.getDeptAreas().get(0).getRegionName();
        dept.setDeptAddress(deptAddress);
        int i = deptDbDao.insert(dept);

        for (int j = 0; j < dept.getDeptAreas().size(); j++) {
            dept.getDeptAreas().get(j).setDeptCode(genCode);
        }
        deptAreaDbDao.batchInsert(dept.getDeptAreas());
        String msg = "个数:" + i + ":编码:" + genCode;

        UserEntity user = userManager.findUserByUserCode(userInfo.getUserCode());
        UserEntity newUser = new UserEntity();
        newUser.setId(user.getId());
        newUser.setUserCode(user.getUserCode());
        newUser.setOwnerDeptCode(dept.getDeptCode());
        newUser.setOwnerDeptName(dept.getDeptName());
        newUser.setUserType(UserType.Individual);
        userManager.update(newUser);

        return dept;
    }

    @Override
    public List<DeptEntity> findNearby(Map<String, Object> map) {

        return deptDbDao.findNearby(map);
    }

    /**
     * 查询企业招商选址事业部非华南部门（不包含撤点）
     *
     * @return
     */
    @Override
    public List<String> getWageDeptCodes() {
        //招商选址所有部门
        List<String> deptCodes = this.getDownDeptCode("DP201607040005");
        //华南部门
        List<String> hnDeptCodes = this.getDownDeptCode("DP201802020001e0f3");
        deptCodes.removeAll(hnDeptCodes);

        deptCodes.remove("DP201812170002c1cc");
        deptCodes.remove("DP2018122800011e3a");
        deptCodes.remove("DP201903010002ebd3");
        deptCodes.remove("DP201901190001d616");
        deptCodes.remove("DP201802020001e0f3");


        return deptCodes;
    }

    /**
     * 查询企业招商选址事业部非华南部门（包含撤点）
     *
     * @return
     */
    @Override
    public List<String> getWageAllDeptCodes() {
        //招商选址所有部门
        List<String> deptCodes = this.getDownAllDeptCode("DP201607040005");
        //华南部门
        List<String> hnDeptCodes = this.getDownAllDeptCode("DP201802020001e0f3");
        deptCodes.removeAll(hnDeptCodes);

        deptCodes.remove("DP201812170002c1cc");
        deptCodes.remove("DP2018122800011e3a");
        deptCodes.remove("DP201903010002ebd3");
        deptCodes.remove("DP201901190001d616");
        deptCodes.remove("DP201802020001e0f3");


        return deptCodes;
    }

    /**
     * 用于通讯录权限
     *
     * @return
     */
    @Override
    public boolean getHasSeeAddressBook(String deptCode) {
        //华南部门
        Boolean hasSee = false;
        List<String> hnDeptCodes = this.getDownAllDeptCode("DP201802020001e0f3");
        List<String> ItDeptCodes = this.getDownAllDeptCode("DP201605260001");

        List<String> deptCodes = new ArrayList<>();
        deptCodes.addAll(hnDeptCodes);
        deptCodes.addAll(ItDeptCodes);
        if (deptCodes.contains(deptCode)) {
            return Boolean.TRUE;
        } else {
            hasSee = false;
        }
        return hasSee;
    }

    @Override
    public DeptEntity findOneDept(Map map) {
        return this.deptDbDao.findOneDept(map);
    }

    @Override
    public List<DeptEntity> findAllDeptByMap(Map map) {
        return deptDbDao.selectSimpleList(map);
    }
}
