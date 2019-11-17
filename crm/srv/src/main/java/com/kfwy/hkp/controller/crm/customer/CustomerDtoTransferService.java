package com.kfwy.hkp.controller.crm.customer;

import api.*;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.crm.customer.vo.CustomerServiceRequest;
import com.kfwy.hkp.controller.crm.customer.vo.CustomerServiceResponse;
import com.kfwy.hkp.crm.customer.dao.*;
import com.kfwy.hkp.crm.customer.entity.*;
import com.kfwy.hkp.sys.file.dao.IFileRelationDbDao;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/customerDtoTransferService")
public class CustomerDtoTransferService extends SpringRestService {

    @Autowired
    private ICustomerDbDao dbDao;
    @Autowired
    private IFileRelationDbDao iFileRelationDbDao;
    @Autowired
    private ICustomerHouseDbDao iCustomerHouseDbDao;
    @Autowired
    private ICustomerFavoriteDbDao iCustomerFavoriteDbDao;
    @Autowired
    private ICustomerAreaDbDao iCustomerAreaDbDao;
    @Autowired
    private ICustomerPushDbDao iCustomerPushDbDao;
    @Autowired
    private ICustomerUpdownDbDao iCustomerUpdownDbDao;
    @Autowired
    private ICustomerFollowupDbDao iCustomerFollowupDbDao;
    /**
     * ERP客户数据转移
     * @param request
     * @return
     */
    @RequestMapping(path = "/customerTransfer",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse>  customerTransfer(@RequestBody CustomerServiceRequest request){
        CustomerServiceResponse response = new CustomerServiceResponse();
        Integer start = 0;
        Integer pageSize = 20000;
        List<CustomerEntity> customerEntityList=null;
        do {
            customerEntityList = CustomerApiClient.findCustomerTransfer(start,pageSize);
            if (customerEntityList==null || customerEntityList.size()==0){
                break;
            }
            int isInsert = dbDao.batchInsert(customerEntityList);
            if (isInsert>0){
                System.out.println("客户数据"+start+"条到"+(start+customerEntityList.size())+"条插入成功");
                start+=pageSize;
                customerEntityList.clear();
                customerEntityList=null;
            }else{
                System.out.println("客户数据插入失败");
                return  this.error(response,"客户数据插入失败");
            }
           // sleepForMemory(start);
        }while (true);
        System.out.println("\n\n\n执行完成");
        return this.success(response,"客户数据转移成功,共转移"+start+"条数据");
    }

    /**
     * ERP客户名片数据转移
     * @param request
     * @return
     */
    @RequestMapping(path = "/customerCardTransfer",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse>  customerCardTransfer(@RequestBody CustomerServiceRequest request){
        CustomerServiceResponse response = new CustomerServiceResponse();
        Integer start = 0;
        Integer pageSize = 500;
        List<FileRelationEntity> fileRelationEntityList=null;
        do {
            fileRelationEntityList = CustomerCardApiClient.findCustomerCardTransfer(start,pageSize);
            if (fileRelationEntityList==null || fileRelationEntityList.size()==0){
                break;
            }
            int isInsert = iFileRelationDbDao.batchInsert(fileRelationEntityList);
            if (isInsert>0){
                System.out.println("客户名片数据"+start+"条到"+(start+fileRelationEntityList.size())+"条插入成功");
                start+=pageSize;
                fileRelationEntityList.clear();
                fileRelationEntityList=null;
            }else{
                System.out.println("客户名片数据插入失败");
                return  this.error(response,"客户名片数据插入失败");
            }
         //   sleepForMemory(start);
        }while (true);
        System.out.println("\n\n\n执行完成");
        return this.success(response,"客户名片数据转移成功,共转移"+start+"条数据");
    }

    /**
     * ERP客户跟进图片、视频数据转移
     * @param request
     * @return
     */
    @RequestMapping(path = "/customerFollowupFileTransfer",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse>  customerFollowupFileTransfer(@RequestBody CustomerServiceRequest request){
        CustomerServiceResponse response = new CustomerServiceResponse();
        Integer start = 0;
        Integer pageSize = 500;
        List<FileRelationEntity> fileRelationEntityList=null;
        do {
            fileRelationEntityList = CustomerFollowupFileApiClient.findCustomerFollowupFileTransfer(start,pageSize);
            if (fileRelationEntityList==null || fileRelationEntityList.size()==0){
                break;
            }
            int isInsert = iFileRelationDbDao.batchInsert(fileRelationEntityList);
            if (isInsert>0){
                System.out.println("客户跟进文件数据"+start+"条到"+(start+fileRelationEntityList.size())+"条插入成功");
                start+=pageSize;
                fileRelationEntityList.clear();
                fileRelationEntityList=null;
            }else{
                System.out.println("客户跟进文件插入失败");
                return  this.error(response,"客户跟进文件数据插入失败");
            }
         //   sleepForMemory(start);
        }while (true);
        System.out.println("\n\n\n执行完成");
        return this.success(response,"客户跟进文件数据转移成功,共转移"+start+"条数据");
    }

    /**
     * ERP客户看房明细数据转移
     * @param request
     * @return
     */
    @RequestMapping(path = "/customerHouseTransfer",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse>  customerHouseTransfer(@RequestBody CustomerServiceRequest request){
        CustomerServiceResponse response = new CustomerServiceResponse();
        Integer start = 0;
        Integer pageSize = 500;
        List<CustomerHouseEntity> customerHouseEntityList=null;
        do {
             customerHouseEntityList = CustomerHouseApiClient.findCustomerHouseTransfer(start,pageSize);
            if (customerHouseEntityList==null || customerHouseEntityList.size()==0){
                break;
            }
            int isInsert = iCustomerHouseDbDao.batchInsert(customerHouseEntityList);
            if (isInsert>0){
                System.out.println("客户看房明细数据"+start+"条到"+(start+customerHouseEntityList.size())+"条插入成功");
                start+=pageSize;
                customerHouseEntityList.clear();
                customerHouseEntityList=null;
            }else{
                System.out.println("客户看房明细插入失败");
                return  this.error(response,"客户看房明细数据插入失败");
            }
          //  sleepForMemory(start);
        }while (true);
        System.out.println("\n\n\n执行完成");
        return this.success(response,"客户看房明细数据转移成功,共转移"+start+"条数据");
    }

    /**
     * ERP客户收藏数据转移
     * @param request
     * @return
     */
    @RequestMapping(path = "/customerFavoriteTransfer",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse>  customerFavoriteTransfer(@RequestBody CustomerServiceRequest request){
        CustomerServiceResponse response = new CustomerServiceResponse();
        Integer start = 0;
        Integer pageSize = 500;
        List<CustomerFavoriteEntity> customerFavoriteEntityList=null;
        do {
            customerFavoriteEntityList  = CustomerFavoriteApiClient.findCustomerFavoriteTransfer(start,pageSize);
            if (customerFavoriteEntityList==null || customerFavoriteEntityList.size()==0){
                break;
            }
            int isInsert = iCustomerFavoriteDbDao.batchInsert(customerFavoriteEntityList);
            if (isInsert>0){
                System.out.println("客户收藏数据"+start+"条到"+(start+customerFavoriteEntityList.size())+"条插入成功");
                start+=pageSize;
                customerFavoriteEntityList.clear();
                customerFavoriteEntityList=null;
            }else{
                System.out.println("客户收藏插入失败");
                return  this.error(response,"客户收藏数据插入失败");
            }
           // sleepForMemory(start);
        }while (true);
        System.out.println("\n\n\n执行完成");
        return this.success(response,"客户收藏数据转移成功,共转移"+start+"条数据");
    }

    /**
     * ERP客户需求区域数据转移
     * @param request
     * @return
     */
    @RequestMapping(path = "/customerAreaTransfer",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> customerAreaTransfer(@RequestBody CustomerServiceRequest request){
        CustomerServiceResponse response = new CustomerServiceResponse();
        Integer start = 0;
        Integer pageSize = 500;
        List<CustomerAreaEntity> customerAreaEntityList=null;
        do {
            customerAreaEntityList = CustomerAreaApiClient.findCustomerAreaTransfer(start,pageSize);
            if (customerAreaEntityList==null || customerAreaEntityList.size()==0){
                break;
            }
            int isInsert = iCustomerAreaDbDao.batchInsert(customerAreaEntityList);
            if (isInsert>0){
                System.out.println("客户需求区域数据"+start+"条到"+(start+customerAreaEntityList.size())+"条插入成功");
                start+=pageSize;
                customerAreaEntityList.clear();
                customerAreaEntityList=null;
            }else{
                System.out.println("客户需求区域插入失败");
                return  this.error(response,"客户需求区域数据插入失败");
            }
           // sleepForMemory(start);
        }while (true);
        System.out.println("\n\n\n执行完成");
        return this.success(response,"客户需求区域数据转移成功,共转移"+start+"条数据");
    }

    /**
     * ERP客户推送数据转移
     * @param request
     * @return
     */
    @RequestMapping(path = "/customerPushTransfer",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> customerPushTransfer(@RequestBody CustomerServiceRequest request){
        CustomerServiceResponse response = new CustomerServiceResponse();
        Integer start = 0;
        Integer pageSize = 500;
        List<CustomerPushEntity> customerPushEntityList=null;
        do {
            customerPushEntityList = CustomerPushApiClient.findCustomerPushTransfer(start,pageSize);
            if (customerPushEntityList==null || customerPushEntityList.size()==0){
                break;
            }
            int isInsert = iCustomerPushDbDao.batchInsert(customerPushEntityList);
            if (isInsert>0){
                System.out.println("客户推送数据"+start+"条到"+(start+customerPushEntityList.size())+"条插入成功");
                start+=pageSize;
                customerPushEntityList.clear();
                customerPushEntityList=null;
            }else{
                System.out.println("客户推送数据插入失败");
                return  this.error(response,"客户推送数据插入失败");
            }
           // sleepForMemory(start);
        }while (true);
        System.out.println("\n\n\n执行完成");
        return this.success(response,"客户推送数据转移成功,共转移"+start+"条数据");
    }


    /**
     * ERP客户上下架数据转移
     * @param request
     * @return
     */
    @RequestMapping(path = "/customerUpdownTransfer",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> customerUpdownTransfer(@RequestBody CustomerServiceRequest request){
        CustomerServiceResponse response = new CustomerServiceResponse();
        Integer start = 0;
        Integer pageSize = 10000;
        List<CustomerUpdownEntity> customerUpdownEntityList=null;
        do {
            customerUpdownEntityList = CustomerUpdownApiClient.findCustomerUpdownTransfer(start,pageSize);
            if (customerUpdownEntityList==null || customerUpdownEntityList.size()==0){
                break;
            }
            int isInsert = iCustomerUpdownDbDao.batchInsert(customerUpdownEntityList);
            if (isInsert>0){
                System.out.println("客户上下架数据"+start+"条到"+(start+customerUpdownEntityList.size())+"条插入成功");
                start+=pageSize;
                customerUpdownEntityList.clear();
                customerUpdownEntityList=null;
            }else{
                System.out.println("客户上下架数据插入失败");
                return  this.error(response,"客户上下架数据插入失败");
            }
            //sleepForMemory(start);
        }while (true);
        System.out.println("\n\n\n执行完成");
        return this.success(response,"客户上下架数据转移成功,共转移"+start+"条数据");
    }
    /**
     * ERP客户跟进数据转移
     * @param request
     * @return
     */
    @RequestMapping(path = "/customerFollowupTransfer",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> customerFollowupTransfer(@RequestBody CustomerServiceRequest request){
        CustomerServiceResponse response = new CustomerServiceResponse();
        Integer start = 0;
        Integer pageSize = 10000;
        List<CustomerFollowupEntity> customerFollowupEntityList=null;
        do {
            customerFollowupEntityList = CustomerFollowupApiClient.findCustomerFollowupTransfer(start,pageSize);
            if (customerFollowupEntityList==null || customerFollowupEntityList.size()==0){
                break;
            }
            int isInsert = iCustomerFollowupDbDao.batchInsert(customerFollowupEntityList);
            if (isInsert>0){
                System.out.println("客户跟进数据"+start+"条到"+(start+customerFollowupEntityList.size())+"条插入成功");
                start+=pageSize;
                customerFollowupEntityList.clear();
                customerFollowupEntityList=null;
            }else{
                System.out.println("客户跟进数据插入失败");
                return  this.error(response,"客户跟进数据插入失败");
            }
           // sleepForMemory(start);
        }while (true);
        System.out.println("\n\n\n执行完成");
        return this.success(response,"客户跟进数据转移成功,共转移"+start+"条数据");
    }
    private void sleepForMemory(Integer start){
        if (start%10000==0){
            try {
                System.gc();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            System.gc();
        }
    }
}
