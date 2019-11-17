package com.kfwy.hkp.controller.quit;

import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.quit.vo.QuitDataTransferRequest;
import com.kfwy.hkp.quit.entity.QuitEntity;
import com.kfwy.hkp.quit.manager.QuitDataTransferManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 离职数据转移
 */
@RequestMapping("/quit")
@RestController
public class QuitDataTransferService extends SpringRestService {


    @Autowired
    private QuitDataTransferManager quitDataTransferManager;

    /**
     * 离职数据转移
     * @param request 离职实体 详情见QuitEntity
     * @return
     */
    @RequestMapping(path = "/dataTransfer",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public QuitEntity dataTransfer(@RequestBody  QuitDataTransferRequest request){

        QuitEntity quitEntity = this.quitDataTransferManager.getQuitEntity(request.getEntity());

        this.quitDataTransferManager.update(request.getEntity());
        QuitEntity result = request.getEntity();
        result.setUserCodes(quitEntity.getUserCodes());
        result.setHosCodes(quitEntity.getHosCodes());
        result.setCusCodes(quitEntity.getCusCodes());
        result.setOwnCodes(quitEntity.getOwnCodes());
        return result;
    }

    /**
     * 数据撤回
     * @param request
     * @return
     */
    @RequestMapping(path = "/withdraw",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Integer withdraw (@RequestBody  QuitDataTransferRequest request){
        Integer result = quitDataTransferManager.withdraw(request.getEntity());
        return result;
    }


    /**
     * 关闭账号
     * @param request
     * @return
     */
    @RequestMapping(path = "/deleteEmp",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Integer deleteEmp (@RequestBody  QuitDataTransferRequest request){
        Integer result = quitDataTransferManager.deleteEmp(request.getEntity());
        return result;
    }
}









