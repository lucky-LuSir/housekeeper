package com.kfwy.hkp.controller.sys.user;


import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.PoiUtils;
import com.kfwy.hkp.controller.sys.user.vo.PortUserServiceRequest;
import com.kfwy.hkp.controller.sys.user.vo.PortUserServiceResponse;
import com.kfwy.hkp.sys.file.business.IFileManager;
import com.kfwy.hkp.sys.file.entity.FileEntity;
import com.kfwy.hkp.sys.user.business.IPortUserManager;
import com.kfwy.hkp.sys.user.entity.PortUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 端口用户管理
 */
@Controller
@RequestMapping ("/portUser")
public class PortUserService extends SpringRestService {
    @Autowired
    private IFileManager fileManager;
    @Autowired
    private IPortUserManager portUserManager;

    /**
     * 上传端口用户excel
     *
     * @param request
     * @return
     */
    @RequestMapping (path = "/uploadPortUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> uploadPortUser (@RequestBody PortUserServiceRequest request) {
        List<PortUserEntity> portUserList = new ArrayList<PortUserEntity> ();
        PortUserServiceResponse response = new PortUserServiceResponse ();
        List<Map> valueList;
        String fielCode = request.getFileCode ();
        FileEntity file = fileManager.findByCode (fielCode);
        try {
            valueList = PoiUtils.readExcel (file.getPathHead (), file.getFilePath ());

        } catch (Exception e) {
            throw new RemoveStackBusinessException ("文件解析异常，请联系管理员");
        }

        for (int k = 0; k < valueList.size (); k++) {
            PortUserEntity portUser = new PortUserEntity ();
            //回款金额
            String showPhone = valueList.get (k).get ("展示号码").toString ();
            String portDeptName = valueList.get (k).get ("端口对应分部").toString ();
            String regionalName = valueList.get (k).get ("大区").toString ();
            String portUserName = valueList.get (k).get ("端口使用人").toString ();
            portUser.setShowPhone (showPhone);
            portUser.setPortDeptName (portDeptName);
            portUser.setRegionalName (regionalName);
            portUser.setPortUserName (portUserName);
            OperateFillUtils.fill (portUser);
            portUserList.add (portUser);
        }

        if (portUserList.size () > 0) {
            portUserManager.deleteAll ();
            portUserManager.createAll (portUserList);
        }
        //插入数据
        return this.success (response);
    }


    @RequestMapping (path = "/portUserQuery", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> portUserQuery (@RequestBody PortUserServiceRequest request) {
        PortUserServiceResponse response = new PortUserServiceResponse ();
        Map map = new HashMap ();
        PortUserEntity entity = request.getEntity ();
        if (entity != null){
            if (request.getKeyword ()!=null && request.getKeyword ()!=""){
                map.put ("keyword",request.getKeyword ());
            }
            if (entity.getShowPhone ()!=null){
                map.put ("showPhone",entity.getShowPhone ());
            }
            if (entity.getPortUserName ()!=null){
                map.put ("portUserName",entity.getPortUserName ());
            }
        }
        PageResult result = portUserManager.findByMap (map, request.getStart (), request.getPageSize ());
        response.setResult (result);
        return this.success (response);
    }

    @RequestMapping (path = "/portUserList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> portUserList (@RequestBody PortUserServiceRequest request) {
        PortUserServiceResponse response = new PortUserServiceResponse ();
        Map map = new HashMap ();
        PortUserEntity entity = request.getEntity ();
        if (entity != null){
            if (request.getKeyword ()!=null && request.getKeyword ()!=""){
                map.put ("keyword",request.getKeyword ());
            }
        }
        List<PortUserEntity> result = portUserManager.findByMap (map);
        response.setResult (result);
        return this.success (response);
    }

}
