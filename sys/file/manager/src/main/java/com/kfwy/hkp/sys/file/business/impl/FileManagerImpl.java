package com.kfwy.hkp.sys.file.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.Base64MOFileUtils;
import com.kfwy.hkp.sys.file.api.FileApiClient;
import com.kfwy.hkp.sys.file.business.IFileManager;
import com.kfwy.hkp.sys.file.dao.IFileDbDao;
import com.kfwy.hkp.sys.file.dictionary.FileType;
import com.kfwy.hkp.sys.file.entity.FileEntity;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description TODO
 * @Auth davidCun
 * @Date 2018/6/13 08:39
 * @Version 1.0
 */
@Service
public class FileManagerImpl extends AbstractManager<FileEntity> implements IFileManager {

    @Autowired
    private IFileDbDao fileDbDao;
    @Value("${file.path.kfwy.pic}")
    private String filePathHeadPic;
    @Autowired
    private TaskExecutor taskExecutor;
    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.fileDbDao;
    }

    @Override
    public FileEntity findByCode(String fileCode) {

        FileEntity file = fileDbDao.selectUniqueByProp("fileCode",fileCode);

        if (file == null){
            file = FileApiClient.findDetail(fileCode);
        }
        return file;
    }

    @Transactional
    public void create(List<FileEntity> files){

        fileDbDao.batchInsert(files);

    }

    @Override
    @Transactional
    public void tranTest(String pm) {

        FileEntity file = new FileEntity();

        create(file);

        if(pm.equalsIgnoreCase("1")){
            throw new RemoveStackBusinessException ("cuowu");
        }

    }

    /*
     * @Description 迭代查询目录
     * @Auth liuzhengyang
     * @Date 2018/7/12 11:56
     * @Version 1.0
     * @Param [code]
     * @return java.util.List<com.kfwy.hkp.sys.file.entity.FileEntity>
     */
    @Override
    public List<FileEntity> selectCatalog(String code,List<FileEntity> list) {

        FileEntity fileEntity = fileDbDao.selectUniqueByProp("fileCode",code);
        //点击我的文件夹时
        if(fileEntity == null){
            defaultAdd(list);
            return list;
        }
        list.add(fileEntity);
        if("0".equals(fileEntity.getParentCode())){
            defaultAdd(list);
            return list;
        }else {
            selectCatalog(fileEntity.getParentCode(),list);
        }
        return list;
    }

    @Override
    public int updateFile(FileEntity fileEntity){
        return  fileDbDao.update(fileEntity);
    }

    /**
     *
     * @param folderName    文件名称
     * @param anotherName   文件别名
     * @param list          文件
     */
    @Override
    public void createFolderAndUpdateFile(String folderName,String anotherName,List<FileRelationEntity> list){
        String userCode=CurrentContext.getUserCode();
        taskExecutor.execute(() -> {
            FileEntity fileEntity=new FileEntity();
            //如果没有这个别名的文件夹，则创建该文件文件的文件夹
            Map<String,Object> map= new HashMap<>();
            map.put("anotherName",anotherName);
            map.put("createCode",CurrentContext.getUserCode());
            FileEntity ff= fileDbDao.selectUniqueByMap(map);
            if(ff==null){
                fileEntity.setFileName(folderName);
                fileEntity.setParentCode("0");
                fileEntity.setFileCode(CodeGenUtils.generate());
                fileEntity.setFileType(FileType.FOLDER);
                fileEntity.setAnotherName(anotherName);
                fileEntity.setCreateCode(userCode);
                create(fileEntity);
            }
            //如果文件存在上级文件夹，则取上级文件夹的名称（主要用于处理历史数据）
            FileEntity fileP =fileDbDao.selectUniqueByProp("fileCode",list.get(0).getFileCode());
            if(!fileP.getParentCode().equals("0")&&fileEntity.getFileCode()!=null){
                String parentFileName =fileDbDao.selectUniqueByProp("fileCode",fileP.getParentCode()).getFileName();
                FileEntity f=new FileEntity();
                f.setFileCode(fileEntity.getFileCode());
                f.setFileName(parentFileName);
                fileDbDao.update(f);
            }
            //将文件存放致新的文件夹之下
            for(int i=0;i<list.size();i++){
                if(!StringUtils.isEmpty(list.get(i).getFileCode())){
                    FileEntity file = new FileEntity();
                    file.setParentCode(fileEntity.getFileCode()==null?ff.getFileCode():fileEntity.getFileCode());
                    file.setFileCode(list.get(i).getFileCode());
                    file.setCreateCode(userCode);
                    fileDbDao.update(file);
                }
            }
        });


    }



    private void defaultAdd(List<FileEntity> list) {
        FileEntity defalt = new FileEntity();
        defalt.setFileCode("0");
        defalt.setFileName("我的文件夹");
        list.add(defalt);
    }


    @Override
    public String uploadImages(String img,String name) {
        try {
            String filepath = "emp"+ File.separator;
            String fs = this.saveFile(img, filePathHeadPic, filepath,name);
            return fs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String saveFile(String file, String root, String path,String name) {

        String code=null;
        if (file != null) {
            code= Base64MOFileUtils.saveFile(file, root, path,name);

        }
        return code;
    }
}
