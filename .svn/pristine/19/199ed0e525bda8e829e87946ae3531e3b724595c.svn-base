package com.kfwy.hkp.controller.file;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.file.vo.FileRelationServiceRequest;
import com.kfwy.hkp.controller.file.vo.FileRelationServiceResponse;
import com.kfwy.hkp.controller.file.vo.FileServiceRequest;
import com.kfwy.hkp.controller.file.vo.FileServiceResponse;
import com.kfwy.hkp.sys.file.api.FileTransfer;
import com.kfwy.hkp.sys.file.api.ParkDicDataTransfer;
import com.kfwy.hkp.sys.file.business.IFileManager;
import com.kfwy.hkp.sys.file.dao.IFileDbDao;
import com.kfwy.hkp.sys.file.dao.IFileRelationDbDao;
import com.kfwy.hkp.sys.file.dictionary.FileType;
import com.kfwy.hkp.sys.file.entity.FileEntity;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/*
 * @Description FileEntity对数据库表的增删改查
 * @Auth liuzhengyang
 * @Date 2018/6/29 8:50
 * @Version 1.0
 * @Param
 * @return
 */
@Controller
@RequestMapping(path = "/file/data")
public class FileDataService extends SpringRestService {

    @Autowired
    private IFileManager fileManager;
    @Autowired
    private IFileDbDao fileDbDao;
    @Autowired
    private IFileRelationDbDao fileRelationDbDao;

    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody FileServiceRequest request) {

        FileServiceResponse response = new FileServiceResponse();

        FileEntity fileEntity = request.getEntity();
        String code = CodeGenUtils.generate();
        fileEntity.setFileCode(code);
        fileEntity.setFileType(FileType.FOLDER);
        fileManager.create(fileEntity);

        response.setResult(code);
        return this.success(response);
    }

    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody FileServiceRequest request){

        FileServiceResponse response = new FileServiceResponse();

        FileEntity fileEntity = request.getEntity();
        FileEntity entity = fileManager.findByCode(fileEntity.getFileCode());
        // 修改文件夹名字
        entity.setFileName(fileEntity.getFileName());
        fileManager.update(entity);

        return this.success(response);

    }

    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody FileServiceRequest request){
        FileServiceResponse response = new FileServiceResponse();

        Map<String,Object> map = new HashMap<String,Object>();

        map.put("createCode", CurrentContext.getUserInfo().getUserCode());
        if(request.getEntity() != null && StringUtils.isNotEmpty(request.getEntity().getParentCode())){
            map.put("parentCode",request.getEntity().getParentCode());
        }

        PageResult<FileEntity> page = fileManager.findByMap(map,request.getStart(),request.getPageSize(),"id",false);
        response.setResult(page);

        return this.success(response);
    }

    @RequestMapping(path = "/queryShare",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryShare(@RequestBody FileServiceRequest request){
        FileServiceResponse response = new FileServiceResponse();

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("parentCode",request.getEntity().getParentCode());
        map.put("isShare", true);

        PageResult<FileEntity> page = fileManager.findByMap(map,request.getStart(),request.getPageSize(),"id",false);
        response.setResult(page);

        return this.success(response);
    }

    /*
     * @Description 查询目录
     * @Auth liuzhengyang
     * @Date 2018/7/12 11:30
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/catalog",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> catalog(@RequestBody FileServiceRequest request){
        FileServiceResponse response = new FileServiceResponse();

        Map<String,Object> map = new HashMap<String,Object>();

        List<FileEntity> list = new ArrayList<FileEntity>();
        list =  fileManager.selectCatalog(request.getEntity().getParentCode(),list);
        Collections.reverse(list);

        response.setResult(list);
        return this.success(response);
    }


    @RequestMapping(path = "/test",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> test(@RequestBody FileServiceRequest request){
        FileServiceResponse response = new FileServiceResponse();
        Integer start = 0;
        Integer pageSize = 1000;
        do {
            List<FileEntity> houseownerEntityList = FileTransfer.findDept(start,pageSize);
            if (houseownerEntityList==null || houseownerEntityList.size()==0){
                break;
            }
            int isInsert = fileDbDao.batchInsert(houseownerEntityList);
            if (isInsert>0){
                System.out.println("文件数据"+start+"条到"+(start+houseownerEntityList.size())+"条插入成功");
                start+=pageSize;
            }else{
                System.out.println("文件数据插入失败");
                return  this.error(response,"文件数据插入失败");
            }
        }while (true);
        System.out.println("\n\n\n执行完成");
        return this.success(response,"插入成功");
    }

    @RequestMapping(path = "/parkDicDataTransfer",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> parkDicDataTransfer(@RequestBody FileRelationServiceRequest request){
        FileRelationServiceResponse response = new FileRelationServiceResponse();
        Integer start = 0;
        Integer pageSize = 1000;
        do {
            List<FileRelationEntity> houseownerEntityList = ParkDicDataTransfer.findDept(start,pageSize);
            if (houseownerEntityList==null || houseownerEntityList.size()==0){
                break;
            }
            int isInsert = fileRelationDbDao.batchInsert(houseownerEntityList);
            if (isInsert>0){
                System.out.println("房源图片数据"+start+"条到"+(start+houseownerEntityList.size())+"条插入成功");
                start+=pageSize;
            }else{
                System.out.println("房源图片插入失败");
                return  this.error(response,"房源图片插入失败");
            }
        }while (true);
        System.out.println("\n\n\n执行完成");
        return this.success(response,"插入成功");
    }
}
