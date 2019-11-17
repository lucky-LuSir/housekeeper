package com.kfwy.hkp.controller.parttime;


import api.ParttimeDataTransferApiClient;
import api.ParttimeFollowupDataTransferApiClient;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.parttime.vo.ParttimeFollowupServiceRequest;
import com.kfwy.hkp.controller.parttime.vo.ParttimeFollowupServiceResponse;
import com.kfwy.hkp.controller.parttime.vo.ParttimeServiceRequest;
import com.kfwy.hkp.controller.parttime.vo.ParttimeServiceResponse;
import com.kfwy.hkp.sys.parttime.dao.IParttimeDbDao;
import com.kfwy.hkp.sys.parttime.dao.IParttimeFollowupDbDao;
import com.kfwy.hkp.sys.parttime.entity.PartTimerFollowupEntity;
import com.kfwy.hkp.sys.parttime.entity.ParttimeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/12/6.
 */
@RestController
@RequestMapping(path = "/parttimeDataTransfer")
public class ParttimeDataTransferService extends SpringRestService {
    @Autowired
    private IParttimeDbDao parttimeDbDao;
    @Autowired
    private IParttimeFollowupDbDao parttimeFollowupDbDao;

    @RequestMapping(path = "/parttimeTransfer",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> customerTransfer(@RequestBody ParttimeServiceRequest request){
        ParttimeServiceResponse response=new ParttimeServiceResponse();
        Integer start = 0;
        Integer pageSize = 1000;
        do {
            List<ParttimeEntity> parttimeEntityList = ParttimeDataTransferApiClient.findCustomerTransfer(start,pageSize);
            if (parttimeEntityList==null || parttimeEntityList.size()==0){
                break;
            }
            int isInsert = parttimeDbDao.batchInsert(parttimeEntityList);
            if (isInsert>0){
                System.out.println("客户数据"+start+"条到"+(start+parttimeEntityList.size())+"条插入成功");
                start+=pageSize;
            }else{
                System.out.println("客户数据插入失败");
                return  this.error(response,"客户数据插入失败");
            }
        }while (true);
        System.out.println("\n\n\n执行完成");
        return this.success(response,"插入成功");
    }


    @RequestMapping(path = "/parttimeFollowupTransfer",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> parttimeFollowupTransfer(@RequestBody ParttimeFollowupServiceRequest request){
        ParttimeFollowupServiceResponse response=new ParttimeFollowupServiceResponse();
        Integer start = 0;
        Integer pageSize = 1000;
        do {
            List<PartTimerFollowupEntity> parttimeEntityList = ParttimeFollowupDataTransferApiClient.findCustomerTransfer(start,pageSize);
            if (parttimeEntityList==null || parttimeEntityList.size()==0){
                break;
            }
            int isInsert = parttimeFollowupDbDao.batchInsert(parttimeEntityList);
            if (isInsert>0){
                System.out.println("客户数据"+start+"条到"+(start+parttimeEntityList.size())+"条插入成功");
                start+=pageSize;
            }else{
                System.out.println("客户数据插入失败");
                return  this.error(response,"客户数据插入失败");
            }
        }while (true);
        System.out.println("\n\n\n执行完成");
        return this.success(response,"插入成功");
    }



}
