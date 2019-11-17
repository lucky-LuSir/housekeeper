package com.kfwy.hkp.log.manager.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.user.entity.BaseUserEntity;
import com.gniuu.framework.utils.JsonMapper;
import com.google.gson.JsonObject;
import com.kfwy.hkp.common.enums.OperationType;
import com.kfwy.hkp.common.utils.RequestWrapper;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerEntity;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.log.annotation.OperLog;
import com.kfwy.hkp.log.dao.IOperationDbDao;
import com.kfwy.hkp.log.entity.OperationEntity;
import com.kfwy.hkp.log.manager.IOperationManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserType;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.Current;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2019/1/19 14:08
 */
@Component
public class OperationManagerImpl extends AbstractManager<OperationEntity> implements IOperationManager {
    private static Logger logger = LoggerFactory.getLogger(OperationManagerImpl.class);
    @Autowired
    private IOperationDbDao operationDbDao;

    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private ICustomerDbDao customerDbDao;


    //日志记录操作延时
    private final int OPERATE_DELAY_TIME = 10;

    //异步操作记录日志的线程池
    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);

    @Override
    protected IMyBatisBaseDao<OperationEntity, Long> getMyBatisDao() {
        return operationDbDao;
    }


    @Override
    public void save(OperationEntity operationEntity) {

        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                operationDbDao.insert(operationEntity);
            }
        });
    }

    @Override
    public void save(HttpServletRequest request, String path, Object handler) throws IOException {
        String userName = CurrentContext.getUserInfo().getUserName();
        String createCode = CurrentContext.getUserCode();
        String createDeptCode = CurrentContext.getUserInfo().getOwnerDeptCode();
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                JsonMapper jsonMapper = JsonMapper.nonDefaultMapper();
                //操作类型
                String optType = "";
                //操作参数
                String bizCode = "";
                //将路径的数据截取
                String url = path;
                //操作内容
                String content = "";
                BufferedReader br = null;
                try {
                    br = request.getReader();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String str, wholeStr = "";
                try {
                    while ((str = br.readLine()) != null) {
                        wholeStr += str;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Map<String, Object> values = jsonMapper.fromJson(wholeStr, Map.class);
                HandlerMethod h = (HandlerMethod) handler;
                optType = h.getMethodAnnotation(OperLog.class).optType();
                //查看房源详情
                if (optType.equals(OperationType.SEE_HOS_DETAIL)) {
                    bizCode = (String) values.get("houseCode");
                    content = userName + "对房源编号为" + bizCode + "的房源进行了查看";
                    //查看业主详情
                } else if (optType.equals(OperationType.SEE_HOUSE_OWNER_DETAIL)) {
                    LinkedHashMap hashMap = (LinkedHashMap) values.get("entity");
                    bizCode = (String) hashMap.get("houseownerCode");
                    content = userName + "对业主编号为" + bizCode + "的客户进行了查看";
                    //查看客户详情
                } else if (optType.equals(OperationType.SEE_CUS_DETAIL)) {
                    LinkedHashMap hashMap = (LinkedHashMap) values.get("entity");
                    bizCode = (String) hashMap.get("cusCode");
                    content = userName + "对客户编号为" + bizCode + "的客户进行了查看";
                    //拨打客户电话
                } else if (optType.equals(OperationType.CALL_CUS_PHONE)) {
                    LinkedHashMap hashMap = (LinkedHashMap) values.get("entity");
                    bizCode = (String) hashMap.get("cusPhone");
                    content = userName + "对电话号码为" + bizCode + "的客户进行了电话拨打";
                    //拨打业主电话
                } else if (optType.equals(OperationType.CALL_HOUSE_OWNER_PHONE)) {
                    LinkedHashMap hashMap = (LinkedHashMap) values.get("entity");
                    bizCode = (String) hashMap.get("houseownerPhone");
                    content = userName + "对电话号码为" + bizCode + "的业主进行了电话拨打";
                }
                OperationEntity operationEntity = new OperationEntity(CodeGenUtils.generate("log"), optType, bizCode, url, content, wholeStr, createCode, createDeptCode, createCode);
                OperateFillUtils.fill(operationEntity);
                operationDbDao.insert(operationEntity);
            }
        });
    }


    @Override
    public void save(String bizCode, String bizType, String empName) {
        UserEntity user = (UserEntity) CurrentContext.getUserInfo();
        String userName = user.getUserName();
        String createCode = user.getUserCode();
        String createDeptCode = (user.getUserType().equals(UserType.Individual)) ? "" : user.getOwnerDeptCode();
        taskExecutor.execute(() -> {

            switch (bizType) {
                //拨打客户电话
                case OperationType.CALL_CUS_PHONE:
                    callCusPhoneLog(bizCode, userName, createCode, createDeptCode, empName);
                    break;
                //拨打业主电话
                case OperationType.CALL_HOUSE_OWNER_PHONE:
                    callHouseOwnerPhoneLog(bizCode, userName, createCode, createDeptCode, empName);
                    break;
                //查看客户详情
                case OperationType.SEE_CUS_DETAIL:
                    cusDetailLog(bizCode, userName, createCode, createDeptCode, empName);
                    break;
                //查看房源详情
                case OperationType.SEE_HOS_DETAIL:
                    hosDetailLog(bizCode, userName, createCode, createDeptCode, empName);
                    break;
                //查看业主详情
                case OperationType.SEE_HOUSE_OWNER_DETAIL:
                    houseOwnerDetailLog(bizCode, userName, createCode, createDeptCode, empName);
                    break;
                case OperationType.DELETE_CUS:
                     deleteCusLog(bizCode,userName, createCode,createDeptCode,empName);
                     break;
                default:
                    break;
            }
        });

    }


    private void deleteCusLog (String bizCode, String userName, String createCode, String createDeptCode, String empName) {
        CustomerEntity detail = customerDbDao.detail (bizCode);
        JsonMapper jsonMapper = new JsonMapper();
        String json = jsonMapper.toJson (detail);
        OperationEntity operationEntity = OperationEntity.newBuilder()
                .optCode(CodeGenUtils.generate("log"))
                .optType(OperationType.DELETE_CUS)
                .bizCode(bizCode)
                .url("/customer/delete")
                .content(userName + "对" + empName + "电话号码为"+detail.getCusPhone ()+"的客户进行了删除操作")
                .remark (json)
                .createCode(createCode)
                .createDeptCode(createDeptCode)
                .lastUpdateCode(createCode)
                .build();
        OperateFillUtils.fill(operationEntity);
        save(operationEntity);
    }
  /*@Override
  public TimerTask save(String bizCode, String bizType, String empName) {
      UserEntity user = (UserEntity) CurrentContext.getUserInfo();
      String userName = user.getUserName();
      String createCode = user.getUserCode();
      String createDeptCode = (user.getUserType().equals(UserType.Individual)) ? "" : user.getOwnerDeptCode();
      return new TimerTask(){

          @Override
          public void run () {
              try {
                  switch (bizType) {
                      //拨打客户电话
                      case OperationType.CALL_CUS_PHONE:
                          callCusPhoneLog(bizCode, userName, createCode, createDeptCode, empName);
                          break;
                      //拨打业主电话
                      case OperationType.CALL_HOUSE_OWNER_PHONE:
                          callHouseOwnerPhoneLog(bizCode, userName, createCode, createDeptCode, empName);
                          break;
                      //查看客户详情
                      case OperationType.SEE_CUS_DETAIL:
                          cusDetailLog(bizCode, userName, createCode, createDeptCode, empName);
                          break;
                      //查看房源详情
                      case OperationType.SEE_HOS_DETAIL:
                          hosDetailLog(bizCode, userName, createCode, createDeptCode, empName);
                          break;
                      //查看业主详情
                      case OperationType.SEE_HOUSE_OWNER_DETAIL:
                          houseOwnerDetailLog(bizCode, userName, createCode, createDeptCode, empName);
                          break;
                      default:
                          break;
                  }
              } catch (Exception e) {
                  logger.error("创建业务日志异常!", e);
              }
          }
      };

  }*/

    @Override
    public List<String> getCallPhonePeopleCount (Map map) {
        return this.operationDbDao.getCallPhonePeopleCount(map);
    }

    @Override
    public OperationEntity getLastCallCusPhoneLog (Map map) {
        return this.operationDbDao.getLastCallCusPhoneLog(map);
    }

    @Override
    public String callPhoneIsHandle (Map map) {
        return this.operationDbDao.callPhoneIsHandle(map);
    }

    @Override
    public int updateCallPhoneHandle (Map map) {
        return this.operationDbDao.updateCallPhoneHandle(map);
    }

    @Override
    public OperationEntity recordEmpCallPhone(OperationEntity operationQuery) {
        BaseUserEntity userInfo = CurrentContext.getUserInfo();
        OperationEntity optNewObj = OperationEntity.newBuilder()
                .optCode(CodeGenUtils.generate("log"))
                .optType(OperationType.CALL_EMP_PHONE)
                .bizCode(userInfo.getUserCode())
                .url("/operation/recordEmpCallPhone")
                .content("("+userInfo.getUserName()+")拨打(" + operationQuery.getContent() + ")" + "的员工电话!")
                .createCode(userInfo.getUserCode())
                .createDeptCode(userInfo.getOwnerDeptCode())
                .lastUpdateCode(userInfo.getOwnerDeptCode())
                .build();

        OperateFillUtils.fill(optNewObj);
        save(optNewObj);

        return optNewObj;
    }

    private void cusDetailLog(String cusCode, String userName, String createCode, String createDeptCode, String empName) {
        OperationEntity operationEntity = OperationEntity.newBuilder()
                .optCode(CodeGenUtils.generate("log"))
                .optType(OperationType.SEE_CUS_DETAIL)
                .bizCode(cusCode)
                .url("/customer/detail")
                .content(userName + "对" + empName + "客户编号为" + cusCode + "的客户进行了查看")
                .createCode(createCode)
                .createDeptCode(createDeptCode)
                .lastUpdateCode(createCode)
                .build();
        OperateFillUtils.fill(operationEntity);
        save(operationEntity);
    }

    private void houseOwnerDetailLog(String bizCode, String userName, String createCode, String createDeptCode, String empName) {
        OperationEntity operationEntity = OperationEntity.newBuilder()
                .optCode(CodeGenUtils.generate("log"))
                .optType(OperationType.SEE_HOUSE_OWNER_DETAIL)
                .bizCode(bizCode)
                .url("/houseowner/details")
                .content(userName + "对" + empName + "房源业主编号为" + bizCode + "的业主进行了查看")
                .createCode(createCode)
                .createDeptCode(createDeptCode)
                .lastUpdateCode(createCode)
                .build();
        OperateFillUtils.fill(operationEntity);
        save(operationEntity);
    }

    private void hosDetailLog(String bizCode, String userName, String createCode, String createDeptCode, String empName) {
        OperationEntity operationEntity = OperationEntity.newBuilder()
                .optCode(CodeGenUtils.generate("log"))
                .optType(OperationType.SEE_HOS_DETAIL)
                .bizCode(bizCode)
                .url("/house/detail")
                .content(userName + "对" + empName + "房源编号为" + bizCode + "的房源进行了查看")
                .createCode(createCode)
                .createDeptCode(createDeptCode)
                .lastUpdateCode(createCode)
                .build();
        OperateFillUtils.fill(operationEntity);
        save(operationEntity);
    }

    private void callHouseOwnerPhoneLog(String bizCode, String userName, String createCode, String createDeptCode, String empName) {
        OperationEntity operationEntity = OperationEntity.newBuilder()
                .optCode(CodeGenUtils.generate("log"))
                .optType(OperationType.CALL_HOUSE_OWNER_PHONE)
                .bizCode(bizCode)
                .url("/houseowner/callHouseOwnerPhone")
                .content(userName + "对" + empName + "房源编码为" + bizCode + "的房源业主进行了电话拨打")
                .createCode(createCode)
                .createDeptCode(createDeptCode)
                .lastUpdateCode(createCode)
                .build();
        OperateFillUtils.fill(operationEntity);
        save(operationEntity);
    }

    private void callCusPhoneLog(String bizCode, String userName, String createCode, String createDeptCode, String empName) {
        OperationEntity operationEntity = OperationEntity.newBuilder()
                .optCode(CodeGenUtils.generate("log"))
                .optType(OperationType.CALL_CUS_PHONE)
                .bizCode(bizCode)
                .url("/customer/callCusPhone")
                .content(userName + "对" + empName + "客户编码为" + bizCode + "的客户进行了电话拨打")
                .createCode(createCode)
                .createDeptCode(createDeptCode)
                .lastUpdateCode(createCode)
                .build();
        OperateFillUtils.fill(operationEntity);
        save(operationEntity);
    }

}
