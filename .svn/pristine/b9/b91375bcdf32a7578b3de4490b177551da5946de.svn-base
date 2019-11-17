package com.kfwy.hkp.controller.file;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.file.vo.FileServiceRequest;
import com.kfwy.hkp.controller.file.vo.FileServiceResponse;
import com.kfwy.hkp.sys.file.dao.IFileDbDao;
import com.kfwy.hkp.sys.file.dictionary.FileType;
import com.kfwy.hkp.sys.file.entity.FileEntity;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: HJH
 * @Date: 2019/8/13
 * @Description: TODO
 */

@Controller
@RequestMapping(path = "/fileCut")
public class FileCutService extends SpringRestService {

    @Autowired
    private IFileDbDao fileDbDao;

    public int width = 1600;

    public int height = 1200;

    public int size = 2500;

    @RequestMapping(path = "/cut", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<IServiceResponse> cut(@RequestBody FileServiceRequest request) {

        FileServiceResponse response = new FileServiceResponse();

        if (request.getStartTime() == null || request.getEndTime() == null) {
            return error(response, "必须选择时间段！");
        }

        // 格式化日期
        DateTime startTime = DateUtil.beginOfDay(request.getStartTime());
        DateTime endTime = DateUtil.endOfDay(request.getEndTime());

        // 赋值
        if (request.getWidth() != null && request.getWidth() >= 1024) {
            this.width = request.getWidth();
        }

        if (request.getHeight() != null && request.getHeight() >= 768) {
            this.height = request.getHeight();
        }

        if (request.getSize() != null && request.getSize() >= 1024) {
            this.size = request.getSize();
        }

        // 查询时间段内文件
        Map<String, Object> map = new HashMap<>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);

        List<FileEntity> result = fileDbDao.selectByMapCut(map);

        // 文件压缩记录基本信息
        int num = 0;
        long oldSize = 0;
        long newSize = 0;

        for (FileEntity f : result) {
            if (f.getFileType().equals(FileType.IMAGE)) {
                String path = FilenameUtils.concat(f.getPathHead(), f.getFilePath());
                File file = new File(path);
                if (file.exists()) {
                    try {
                        Thumbnails.Builder<File> tb = Thumbnails.of(file);
                        // 固定像素缩放 大于2m
                        long sizes = file.length() / 1024;
                        // 获取图片像素
                        BufferedImage sourceImg = ImageIO.read(new FileInputStream(file));
                        long pixels = sourceImg.getWidth() * sourceImg.getHeight();
                        long pixel = this.width * this.height;
                        if (pixels > pixel || sizes > this.size) {
                            // 压缩
                            tb.size(this.width, this.height);
                            tb.toFile(file);
                            // 记录
                            num++; // 总数
                            oldSize += sizes; // 原先大小
                            newSize += file.length() / 1024; // 压缩后大小
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    logger.error("找不到文件！");
                }
            }
        }
        return success(response, "共压缩了" + num + "个文件，总计大小从" + oldSize / 1024 + "M，压缩成了" + newSize / 1024 + "M");
    }
}
