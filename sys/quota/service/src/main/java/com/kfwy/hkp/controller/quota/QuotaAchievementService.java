package com.kfwy.hkp.controller.quota;

import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.quota.vo.QuotaAchievementServiceRequest;
import com.kfwy.hkp.controller.quota.vo.QuotaAchievementServiceResponse;
import com.kfwy.hkp.sys.quota.business.IQuotaAchievementManager;
import com.kfwy.hkp.sys.quota.entity.QuotaAchievementEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Create By hjh on 2019/02/28
 */
@Controller
@RequestMapping(path = "quota")
public class QuotaAchievementService extends SpringRestService {
    /**
     * 业绩指标接口
     */
    @Autowired
    private IQuotaAchievementManager quotaManager;

    /**
     * 查询业绩指标
     *
     * @param
     * @return
     */
    @RequestMapping(path = "/select",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> select(@RequestBody QuotaAchievementServiceRequest request) {

        QuotaAchievementServiceResponse response = new QuotaAchievementServiceResponse();

        Map<String, Object> map = new HashMap<>();

        if (null != request.getQaTime()) {
            map.put("qaTime", DateUtil.format(request.getQaTime(), "yyyy-MM"));
        }

        if (StringUtils.isNotEmpty(request.getOwnerCode())) {
            map.put("ownerCode", request.getOwnerCode());
        }

        PageResult<QuotaAchievementEntity> r = quotaManager.findByMap(map, request.getStart(), request.getPageSize(), "create_time", false);

        response.setResult(r);

        return this.success(response);
    }

    /**
     * 新增业绩指标
     *
     * @param
     * @return
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody QuotaAchievementServiceRequest request) {

        QuotaAchievementServiceResponse response = new QuotaAchievementServiceResponse();

        quotaManager.create(request.getEntity());

        return this.success(response);
    }

    /**
     * 更新业绩指标
     *
     * @param
     * @return
     */
    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody QuotaAchievementServiceRequest request) {

        QuotaAchievementServiceResponse response = new QuotaAchievementServiceResponse();

        quotaManager.update(request.getEntity());

        return this.success(response);
    }

}
