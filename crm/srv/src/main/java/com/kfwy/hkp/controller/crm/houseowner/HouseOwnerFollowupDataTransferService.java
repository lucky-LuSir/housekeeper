package com.kfwy.hkp.controller.crm.houseowner;


import api.HouseownerFollowupDataTransferApiClient;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.crm.houseowner.vo.HouseownerFollowupServiceRequest;
import com.kfwy.hkp.controller.crm.houseowner.vo.HouseownerFollowupServiceResponse;
import com.kfwy.hkp.crm.houseowner.dao.IHouseownerFollowupDbDao;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerFollowupEntity;
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
@RequestMapping(path = "/houseOwnerFollowupDataTransfer")
public class HouseOwnerFollowupDataTransferService extends SpringRestService {
    @Autowired
    private IHouseownerFollowupDbDao houseownerFollowupDbDao;

    @RequestMapping(path = "/houseOwnerFollowupTransfer",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> customerTransfer(@RequestBody HouseownerFollowupServiceRequest request){
        HouseownerFollowupServiceResponse response=new HouseownerFollowupServiceResponse();
        Integer start = 0;
        Integer pageSize = 1000;
        do {
            List<HouseownerFollowupEntity> houseownerEntityList = HouseownerFollowupDataTransferApiClient.findCustomerTransfer(start,pageSize);
            if (houseownerEntityList==null || houseownerEntityList.size()==0){
                break;
            }
            int isInsert = houseownerFollowupDbDao.batchInsert(houseownerEntityList);
            if (isInsert>0){
                System.out.println("客户数据"+start+"条到"+(start+houseownerEntityList.size())+"条插入成功");
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
