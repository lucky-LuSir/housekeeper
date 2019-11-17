package com.kfwy.hkp.controller.quit;

import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.quit.vo.ToolsDataTransferRequest;
import com.kfwy.hkp.quit.entity.SpecialEntity;
import com.kfwy.hkp.quit.manager.IToolsDataTransferManger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 工具箱 转移数据
 */
@RequestMapping(value = "/tools")
@RestController
public class ToolsService extends SpringRestService {

    @Autowired
    private IToolsDataTransferManger toolsDataTransferManger;

    @RequestMapping(path = "/transfer",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SpecialEntity resourceTransfer(@RequestBody ToolsDataTransferRequest request){
        SpecialEntity specialEntity = request.getEntity();
        Integer type = request.getType();
        SpecialEntity result = toolsDataTransferManger.transfer(specialEntity,type);
        return result;
    }

}
