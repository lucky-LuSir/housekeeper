package com.kfwy.hkp.bi.count.business.impl;


import cn.hutool.core.collection.CollectionUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.business.IContractReportManager;
import com.kfwy.hkp.bi.count.dao.IContractReportDbDao;
import com.kfwy.hkp.bi.count.entity.ContractPeriodEntity;
import com.kfwy.hkp.hrm.org.dept.api.DeptApiClient;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.dto.DeptTreeDto;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Create By lzp on 2019/06/21
 */
@Service
public class ContractReportManagerImpl extends AbstractManager<ContractPeriodEntity> implements IContractReportManager {
    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private IContractReportDbDao contractReportDbDao;
    @Override
    protected IMyBatisBaseDao<ContractPeriodEntity, Long> getMyBatisDao() {
        return null;
    }



    @Override
    public List<ContractPeriodEntity> selectContractPeriodHos(Map<String, Object> param,Date startTime) {
        getAuthDeptCodes(param);
        List<ContractPeriodEntity> result = contractReportDbDao.selectContractPeriodHos(param);
        result=perfectData(result, startTime);
        return result;
    }

    @Override
    public List<ContractPeriodEntity> selectContractPeriodCus(Map<String, Object> param,Date startTime) {
        getAuthDeptCodes(param);
        List<ContractPeriodEntity> result = contractReportDbDao.selectContractPeriodCus(param);
        result=perfectData(result, startTime);
        return result;
    }



    public List<ContractPeriodEntity> perfectData(List<ContractPeriodEntity> contractPeriodEntities,Date startTime){

        List<ContractPeriodEntity> result = new ArrayList<>();
        Calendar cals = Calendar.getInstance();
        cals.setTime(startTime);
        String year="YYYY";
        String month="MM";
        SimpleDateFormat formatY = new SimpleDateFormat(year);
        SimpleDateFormat formatM = new SimpleDateFormat(month);
        for(int i=0;i<=23;i++){
            ContractPeriodEntity contractPeriodEntity = new ContractPeriodEntity();

            if (i==0){
                cals.add(Calendar.MONTH,0);
            }else {
                cals.add(Calendar.MONTH,1);
            }
            cals.set(Calendar.DAY_OF_MONTH,1);
            contractPeriodEntity.setContractYear(formatY.format(cals.getTime()));
            contractPeriodEntity.setContractMonth(formatM.format(cals.getTime()));
            contractPeriodEntity.setContractNum(0);
            result.add(contractPeriodEntity);
        }
        for (int j=0;j<result.size();j++) {
            for (ContractPeriodEntity periodEntity : contractPeriodEntities) {
                if (periodEntity.getContractYear().equals(result.get(j).getContractYear())
                        &&periodEntity.getContractMonth().equals(result.get(j).getContractMonth())){
                    result.get(j).setContractNum(periodEntity.getContractNum());
                }
            }
        }

        return result;
    }

    public void  getAuthDeptCodes(Map<String,Object> param){
        String code = "";
        List<DeptTreeDto> deptTreeDtos = DeptApiClient.deptAllTree(CurrentContext.getUserCode());
        if (CollectionUtil.isNotEmpty(deptTreeDtos)) {
            code = deptTreeDtos.get(0).getDeptCode();
        } else {
            code = CurrentContext.getUserCode();
        }


        DeptEntity deptEntity = deptManager.findOne("deptCode", code);

        String userCode = "";

        Map<String, Object> map = new HashMap<>();

        if (deptEntity == null) { // 个人
            userCode = code;
            param.put("userCode",userCode);
        } else { // 部门
                // 获取当前部门下所有部门编码
                List<String> deptCodes = deptManager.getDownDeptCode(code);
                // 查询人员编码
                param.put("deptCodes", deptCodes);
        }
    }
}
