package com.kfwy.hkp.crm.customer.business.impl;

import cn.hutool.core.map.MapUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.crm.customer.bo.FocusCusUserScoreMatchBo;
import com.kfwy.hkp.crm.customer.business.IFocusCusManager;
import com.kfwy.hkp.crm.customer.dao.IFocusCusDbDao;
import com.kfwy.hkp.crm.customer.dto.FocusCusDto;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.entity.FocusCusEntity;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @AUTHOR 李文思汗
 * @Description 类描述:
 */
@Service
public class FocusCusManagerImpl extends AbstractManager<FocusCusEntity> implements IFocusCusManager {

    @Autowired
    private IFocusCusDbDao focusCusDbDao;
    @Autowired
    private IDeptManager deptManager;

    @Override
    protected IMyBatisBaseDao<FocusCusEntity, Long> getMyBatisDao() {
        return focusCusDbDao;
    }


    @Override
    public List<FocusCusUserScoreMatchBo> focusCusUserScoreMatch(Map map) {
        return focusCusDbDao.focusCusUserScoreMatch(map);
    }

    @Override
    public Integer checkFocusCusNotice(Map map) {
        return focusCusDbDao.checkFocusCusNotice(map);
    }

    @Override
    public PageResult<FocusCusDto> focusCusQuery(CustomerEntity entity,Integer start, Integer pageSize) {
        Map map  = MapUtil.newHashMap ();
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
        if (entity != null) {
            if (entity.getCusPhone () != null) {
                map.put ("cusPhone", entity.getCusPhone ());
            }
            if (entity.getCusName () != null) {
                map.put ("cusName", entity.getCusName ());
            }
        }
        if (! cur.getEnterType ().equals ("1")){
            getLowerDeptCodes(map,cur);
        }
        map.put ("self_page", true);

        return this.focusCusDbDao.selectByStatement("focusCusQuery",map,start,pageSize,"last_upshelf_time",false);
    }

    private void getLowerDeptCodes(Map param, UserEntity cur) {
        if (StringUtils.isNotEmpty(cur.getOwnerDeptCode())){
            String leaderCode = deptManager.getDeptEntity(cur.getOwnerDeptCode()).getLeaderCode();
            if (leaderCode.equals(cur.getUserCode ())){

                List<String> deptCodes = deptManager.getDownDeptCode(cur.getOwnerDeptCode());

                if (deptCodes!=null && deptCodes.size()>1){
                    param.put("ownerDeptCodes",deptCodes);

                }else {
                    param.put("ownerDeptCode", cur.getOwnerDeptCode());
                }
            }else{
                param.put("ownerDeptCode", cur.getOwnerDeptCode());
            }
        }
    }

    @Override
    public PageResult<FocusCusDto> myDeptFocusCusQuery (Map map, Integer start, Integer pageSize) {
        return this.focusCusDbDao.selectByStatement ("myDeptFocusCusQuery",map,start,pageSize,"focus_time",false);
    }
}
