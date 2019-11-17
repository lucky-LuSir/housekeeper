package com.kfwy.hkp.controller.remind;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.remind.vo.RemindDownServiceRequest;
import com.kfwy.hkp.controller.remind.vo.RemindDownServiceResponse;
import com.kfwy.hkp.crm.customer.business.ICustomerFollowupManager;
import com.kfwy.hkp.crm.customer.entity.CustomerFollowupEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerFollowupType;
import com.kfwy.hkp.hos.house.business.IHouseFollowupManager;
import com.kfwy.hkp.hos.house.entity.HouseFollowupEntity;
import com.kfwy.hkp.sys.remind.business.IRemindDownManager;
import com.kfwy.hkp.sys.remind.entity.RemindDownEntity;
import com.kfwy.hkp.sys.remind.enums.DownType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: HJH
 * @Date: 2019/4/19
 * @Description: TODO
 */

@RestController
@RequestMapping(path = "/remindDown")
public class RemindDownService extends SpringRestService {

    @Autowired
    private IRemindDownManager remindDownManager;
    @Autowired
    private ICustomerFollowupManager cusFollowupManager;
    @Autowired
    private IHouseFollowupManager houseFollowupManager;


    @RequestMapping(path = "/select",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Transactional
    public ResponseEntity<IServiceResponse> select(@RequestBody RemindDownServiceRequest request) {
        RemindDownServiceResponse response = new RemindDownServiceResponse();
        Map<String, Object> map = new HashMap<>();
        map.put("ownerCode", request.getEntity().getOwnerCode());
        map.put("hasOperate", false);
        map.put("isDeleted", false);
        RemindDownEntity remindDownEntity = remindDownManager.findOne(map);
        response.setResult(remindDownEntity);
        return this.success(response);
    }

    @RequestMapping(path = "/detail",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail(@RequestBody RemindDownServiceRequest request) {
        RemindDownServiceResponse response = new RemindDownServiceResponse();
        Map<String, Object> map = new HashMap<>();
        map.put("remindCode", request.getEntity().getRemindCode());
        RemindDownEntity remindDownEntity = remindDownManager.findOne(map);
        map.clear();
        if (remindDownEntity == null) {
            map.put("ownerCode", request.getEntity().getRemindCode());
            List<RemindDownEntity> byMap = remindDownManager.findByMap(map, "create_time", false);
            if (byMap != null && !byMap.isEmpty()) {
                remindDownEntity = byMap.get(0);
            }
        }
        response.setResult(remindDownEntity);
        return this.success(response);
    }

    /**
     * 暂不下架
     *
     * @param
     * @return
     */
    @RequestMapping(path = "/notDown",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Transactional
    public ResponseEntity<IServiceResponse> notDown(@RequestBody RemindDownServiceRequest request) {

        RemindDownServiceResponse response = new RemindDownServiceResponse();

        RemindDownEntity rs = request.getEntity();
        Map<String, Object> map = new HashMap<>();
        map.put("ownerCode", rs.getOwnerCode());
        map.put("hasOperate", false);
        map.put("isDeleted", false);
        RemindDownEntity r = remindDownManager.findOne(map);
        if (r == null) {
            return this.error(response, "您已经操作过！");
        }
        r.setUserCause(rs.getUserCause());
        r.setLeaseExpiration(rs.getLeaseExpiration());
        if (r != null) {
            // 判断下架类型
            String downType = r.getDownType();
            if (downType.equals(DownType.CUS)) {
                CustomerFollowupEntity fe = new CustomerFollowupEntity();
                fe.setCusCode(r.getOwnerCode());
                fe.setFollowupType(CustomerFollowupType.VisitPhone);
                fe.setFollowupContent(r.getUserCause());
                //fe.setLeaseExpiration(r.getLeaseExpiration());
                cusFollowupManager.create(fe);
            } else if (downType.equals(DownType.HOS)) {
                HouseFollowupEntity hf = new HouseFollowupEntity();
                hf.setHouseCode(r.getOwnerCode());
                hf.setFollowupContext(r.getUserCause());
                //hf.setLeaseExpiration(r.getLeaseExpiration());
                houseFollowupManager.create(hf);
            }
        }

        //r.setHasOperate(true);
        r.setLastUpdateCode(CurrentContext.getUserCode());
        r.setLastUpdateTime(new Date());
        remindDownManager.update(r);
        return this.success(response);
    }

}
