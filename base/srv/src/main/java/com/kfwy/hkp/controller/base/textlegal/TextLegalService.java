package com.kfwy.hkp.controller.base.textlegal;



import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.base.history.business.IHistoryManager;
import com.kfwy.hkp.base.history.entity.HistoryEntity;
import com.kfwy.hkp.base.textlegal.business.ITextLegalManager;
import com.kfwy.hkp.base.textlegal.entity.TextLegalEntity;
import com.kfwy.hkp.controller.base.history.vo.HistoryRequest;
import com.kfwy.hkp.controller.base.history.vo.HistoryResponse;
import com.kfwy.hkp.controller.base.textlegal.vo.TextLegalRequest;
import com.kfwy.hkp.controller.base.textlegal.vo.TextLegalResponse;
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
@RequestMapping(path = "/textlegal")
public class TextLegalService extends SpringRestService {

    @Autowired
    private ITextLegalManager textLegalManager;


    /**
     * 创建历史记录
     * @param request
     * @return
     */
    @RequestMapping(path = "/querycootext",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody TextLegalRequest request) {

        TextLegalResponse response = new TextLegalResponse();

        TextLegalEntity saEntity = new TextLegalEntity();
        Map param = new HashMap<String, Object>();
        param.put("textCode", "cootext");
        saEntity = textLegalManager.findOne(param);

        response.setResult(saEntity);
        return this.success(response);
    }


    /**
     * 申请离开显示协议
     * @param request
     * @return
     */
    @RequestMapping(path = "/queryLeaveText",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryLeaveText(@RequestBody TextLegalRequest request) {

        TextLegalResponse response = new TextLegalResponse();

        TextLegalEntity saEntity = new TextLegalEntity();
        Map param = new HashMap<String, Object>();
        param.put("textCode", "leaveText");
        saEntity = textLegalManager.findOne(param);

        response.setResult(saEntity);
        return this.success(response);
    }



}
