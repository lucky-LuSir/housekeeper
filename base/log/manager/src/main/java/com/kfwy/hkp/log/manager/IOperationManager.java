package com.kfwy.hkp.log.manager;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.log.entity.OperationEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2019/1/19 14:07
 */
public interface IOperationManager extends IManager<OperationEntity> {

    public void save(OperationEntity operationEntity);

    public void save(HttpServletRequest request, String path, Object handler) throws IOException;

    public void save(String bizCode, String bizType, String empName);

    //public TimerTask save(String bizCode, String bizType, String empName);

    public List<String> getCallPhonePeopleCount(Map map);

    public OperationEntity getLastCallCusPhoneLog(Map map);

    public String callPhoneIsHandle(Map map);

    public int updateCallPhoneHandle(Map map);

    OperationEntity recordEmpCallPhone(OperationEntity operationQuery);
}
