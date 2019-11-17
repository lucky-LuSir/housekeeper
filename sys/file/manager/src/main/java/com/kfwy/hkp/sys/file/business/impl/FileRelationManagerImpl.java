package com.kfwy.hkp.sys.file.business.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.ParamUtil;
import com.kfwy.hkp.sys.file.business.IFileManager;
import com.kfwy.hkp.sys.file.business.IFileRelationManager;
import com.kfwy.hkp.sys.file.dao.IFileRelationDbDao;
import com.kfwy.hkp.sys.file.dictionary.FileType;
import com.kfwy.hkp.sys.file.entity.FileEntity;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/7/30
 */
@Service
public class FileRelationManagerImpl extends AbstractManager<FileRelationEntity> implements IFileRelationManager {

    @Autowired
    private IFileRelationDbDao fileRelationDbDao;
    @Autowired
    private IFileManager fileManager;

    private static Logger logger = LoggerFactory.getLogger(FileRelationManagerImpl.class);


    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.fileRelationDbDao;
    }

    @Override
    public FileRelationEntity findByCode(String fileCode) {

        return fileRelationDbDao.selectUniqueByProp("fileCode", fileCode);
    }

    @Override
    public List<FileRelationEntity> findByMap(Map<String, Object> param) {

        return fileRelationDbDao.selectByMap(param);
    }

    @Override
    @Transactional
    public void create(List<FileRelationEntity> files) throws IllegalAccessException {

        // ownerCode校验
        for (FileRelationEntity file : files) {

            //给房源图片添加水印
            addWaterToHousePicture(file);

            List<String> list = new ArrayList();
            list.add("fileCode");
            //list.add("fileName");
            list.add("fileType");
            ParamUtil<FileRelationEntity> paramUtil = new ParamUtil<>();
            paramUtil.check(file, list);
            //Ownercode是客户跟进的uuid或者房源的uuid
            String ownerCode = file.getOwnerCode();
            if (ownerCode == null || ownerCode == "") {
                throw new RemoveStackBusinessException ("必填String字段出错-" + ownerCode + "-出错fileReMa");
            }
        }

        fileRelationDbDao.batchInsert(files);
    }

    @Override
    public void beforeCreate(FileRelationEntity fileRelationEntity) {
        OperateFillUtils.fill(fileRelationEntity);
    }

    @Override
    public int create(FileRelationEntity fileRelationEntity) {

        List<String> list = new ArrayList();
        list.add("fileCode");
        //list.add("fileName");
        list.add("fileType");
        ParamUtil<FileRelationEntity> paramUtil = new ParamUtil<>();
        try {
            paramUtil.check(fileRelationEntity, list);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //Ownercode是客户跟进的uuid或者房源的uuid
        String ownerCode = fileRelationEntity.getOwnerCode();
        if (ownerCode == null || ownerCode == "") {
            throw new RemoveStackBusinessException ("必填String字段出错-" + ownerCode + "-出错fileReMa");
        }
        beforeCreate(fileRelationEntity);
        //给房源图片添加水印
        addWaterToHousePicture(fileRelationEntity);
        return fileRelationDbDao.insert(fileRelationEntity);
    }

    /**
     * @param files       文件
     * @param ownerCode   关联编码
     * @param fileName    文件名称
     * @param anotherName 文件别名
     */
    @Override
    @Transactional
    public void update(List<FileRelationEntity> files, String ownerCode, String fileName, String anotherName, String empCode) {
        // 不删除的id集合
        List<Long> ids = null;
        if (CollectionUtil.isNotEmpty(files)) {
            ids = new ArrayList<>();
            for (FileRelationEntity file : files) {
                if (file.getId() == null) {
                    // 保存新文件

                    OperateFillUtils.fill(file);
                    file.setOwnerCode(ownerCode);
                    fileRelationDbDao.add(file);
                    addWaterToHousePicture(file);
                    ids.add(file.getId());
                } else {
                    ids.add(file.getId());
                }
            }
        }
        //  删除ownerCode内 不包含得id
        Map<String, Object> map = new HashMap<>();
        map.put("notIds", ids);
        map.put("ownerCode", ownerCode);
        fileRelationDbDao.deleteNotIds(map);
        if (empCode.equals(CurrentContext.getUserCode()) && CollectionUtil.isNotEmpty(files)) {
            fileManager.createFolderAndUpdateFile(fileName, anotherName, files);
        }
    }

    @Override
    @Transactional
    public void tranTest(String pm) {

        FileRelationEntity file = new FileRelationEntity();

        create(file);

        if (pm.equalsIgnoreCase("1")) {
            throw new RemoveStackBusinessException ("cuowu");
        }
    }

    private void addWaterToHousePicture(FileRelationEntity frs) {

        //TODO 应该加异步

        FileEntity fileEntity = fileManager.findByCode(frs.getFileCode());

        String fName = FilenameUtils.concat(fileEntity.getPathHead(), fileEntity.getFilePath());

        File file = new File(fName);

        if (file.exists()) {

            InputStream is = FileRelationManagerImpl.class.getClassLoader().getResourceAsStream("water.png");
            if (FileType.IMAGE.equalsIgnoreCase(frs.getFileType())) {
                try {
                    Thumbnails.Builder<File> tb = Thumbnails.of(file);
                    // 固定像素缩放 大于2m
                    long size = file.length() / 1024;
                    // 获取图片像素
                    BufferedImage sourceImg = ImageIO.read(new FileInputStream(file));
                    int width = sourceImg.getWidth();
                    int height = sourceImg.getHeight();
                    long pixel = width * height;
                    if (pixel > 1920000 || size > 2500) {
                        tb.size(1600, 1200);
                    } else {
                        tb.scale(1.0f);
                    }
                    tb.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(is), 0.2f);
                    tb.toFile(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            logger.error("添加水印，本地片文件不存在！");
        }

    }

}
