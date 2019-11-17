package com.kfwy.hkp.hos.house.business.impl;

import com.gniuu.framework.common.context.CurrentContext;
import com.kfwy.hkp.cooperate.dao.ICooperateDbDao;
import com.kfwy.hkp.cooperate.entity.CooperateEntity;
import com.kfwy.hkp.hos.house.business.IHouseManager;
import com.kfwy.hkp.hos.house.dao.ISharePoolDeptDbDao;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @AUTHOR 李文思汗
 * @Description 类描述:
 */
@Service
public class HouseCheckBase {

    @Autowired
    protected IHouseManager houseManager;

    @Autowired
    protected IUserManager userManager;

    @Autowired
    private ISharePoolDeptDbDao sharePoolDeptDbDao;

    @Autowired
    private ICooperateDbDao cooperateDbDao;

    @Autowired
    private IDeptManager deptManager;

    /**
     * 是否是我的房源
     *
     * @param house
     * @return
     */
    public Boolean checkIsMyHos(HouseEntity house) {
        //判断是否是本人
        if (house.getEmpCode()
                .equals(CurrentContext.getUserCode())) {
            return true;
        }
        return false;
    }

    /**
     * 部门内部共享
     *
     * @param house
     * @param owner
     * @return
     */
    public Boolean checkSameDeptShare(HouseEntity house, UserEntity owner) {
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();

        if (owner.getOwnerDeptCode() != null
                && owner.getOwnerDeptCode().equals(cur.getOwnerDeptCode())) {

            DeptEntity deptEntity = deptManager.getDeptEntity(cur.getOwnerDeptCode());
            if (cur.getUserLevel().equals(UserLevel.K0)) {
                if (deptEntity != null && deptEntity.getHasShareHos()
//                        && house.getOpenFlag()
                        ) {
                    return true;
                }
            } else if (cur.getUserLevel().equals(UserLevel.T0)) {
                return true;
            }

        }


        return false;
    }

    /**
     * 共享部门共享房源
     *
     * @param deptCode
     * @param houseUserDeptCode
     * @return
     */
    public Boolean hasSharePool(HouseEntity house, UserEntity cur, UserEntity owner) {
        //房源所属人，部门不能为空
        if (owner.getOwnerDeptCode() != null
                && !cur.getOwnerDeptCode().equals(owner.getOwnerDeptCode())) {
            String preSql = "SELECT count(*) FROM t_hkp_share_pool_dept" +
                    " WHERE share_dept_code=\'%s\'" +
                    "AND share_type=\'hos\'" +
                    "AND share_code IN (" +
                    "SELECT share_code FROM t_hkp_share_pool_dept" +
                    " WHERE share_dept_code = \'%s\'" +
                    "AND share_type=\'hos\'" +
                    "GROUP BY share_code)";
            String sql = String.format(preSql, cur.getOwnerDeptCode(), owner.getOwnerDeptCode());
            int count = sharePoolDeptDbDao.countByNativeSql(sql);
            if (count > 0 ) {
                return true;
            }
        }
        return false;
    }

    /**
     * 合作
     *
     * @param house
     * @param cur
     * @return
     */
    public Boolean hasCooperate(HouseEntity house,UserEntity cur){
        String preSql = "select * from t_hkp_union_cooperate " +
                "where house_code=\'%s\' " +
                "and is_deleted=false " +
                "and coo_status in ('3','4','5') " +
                "and (apply_code=\'%s\' or receive_code=\'%s\')" +
                "order by create_time desc limit 1";
        String sql = String.format(preSql,house.getHouseCode(),cur.getUserCode(),cur.getUserCode());

        CooperateEntity cooperateEntity = cooperateDbDao.selectOneByNativeSql(sql);

        if (null!=cooperateEntity&&cooperateEntity.getCooOpenFlag()){
            return true;
        }
        return false;
    }


}
