package com.kfwy.hkp.controller.base.history;



import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.base.history.business.IHistoryManager;
import com.kfwy.hkp.base.history.entity.HistoryEntity;
import com.kfwy.hkp.controller.base.history.vo.HistoryRequest;
import com.kfwy.hkp.controller.base.history.vo.HistoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description 描述:
* @Auth xpp
* @Date 2018/11/27 10:57
* @Version 1.0
* @Param 
* @return 
**/
@RestController
@RequestMapping(path = "/history")
public class HistoryService extends SpringRestService {

    @Autowired
    private IHistoryManager historyManager;


    /**
     * 创建历史记录
     * @param request
     * @return
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody HistoryRequest request) {

        HistoryResponse response = new HistoryResponse();

        //selectAddressManager.create(request.getEntity());

        HistoryEntity saEntity = new HistoryEntity();
        saEntity = historyManager.createHistory(request.getEntity());


        response.setResult(saEntity);
        return this.success(response);
    }




}
