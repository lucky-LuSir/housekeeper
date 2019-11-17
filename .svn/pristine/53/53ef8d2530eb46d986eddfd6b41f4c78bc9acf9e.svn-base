package com.kfwy.hkp.controller.file;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.annotion.IgnoreLog;
import com.kfwy.hkp.common.utils.MediaTypeUtils;
import com.kfwy.hkp.controller.file.vo.FileServiceResponse;
import com.kfwy.hkp.sys.file.business.IFileManager;
import com.kfwy.hkp.sys.file.business.impl.FileRelationManagerImpl;
import com.kfwy.hkp.sys.file.dictionary.FileType;
import com.kfwy.hkp.sys.file.entity.FileEntity;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @Auth davidCun
 * @Date 2018/6/13 09:08
 * @Version 1.0
 */
@Controller
@RequestMapping(path = "/file")
public class FileService extends SpringRestService {

    @Autowired
    private IFileManager fileManager;


    @Value("${file.path.head}")
    private String filePathHead;

    /**
     * 暂时不支持同时分段取 TODO
     *
     * @param fileCode
     * @param request
     * @param response
     */
    @IgnoreLog
    @RequestMapping(path = "/read/{fileCode}", method = RequestMethod.GET)
    @ResponseBody
    public void read(@PathVariable String fileCode, HttpServletRequest request, HttpServletResponse response) {

        processFileRequest(fileCode, request, response, false);
    }

    @IgnoreLog
    @RequestMapping(path = "/download/{fileCode}", method = RequestMethod.GET)
    public void download(@PathVariable String fileCode, HttpServletRequest request, HttpServletResponse response) {

        this.processFileRequest(fileCode, request, response, true);
    }

    @IgnoreLog
    @RequestMapping(path = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> upload(@RequestParam("file") MultipartFile[] files,
                                                   @RequestParam(name = "parentCode", required = false) String parentCode) {

        FileServiceResponse response = new FileServiceResponse();

        List<FileEntity> fes = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                //因为文件名字可能会重复，所以不能用file.getOriginalFilename()得到的名字保存
                String fileCode = CodeGenUtils.generate();

                /**
                 * 1、创建检查文件路径
                 */
                Date dt = new Date();
                String year = DateUtils.formatDate(dt, "yyyy");
                String month = DateUtils.formatDate(dt, "MM");
                String day = DateUtils.formatDate(dt, "dd");

                String cpyCode = "default_company";

                if (CurrentContext.getUserInfo() != null && StringUtils.isNotEmpty(CurrentContext.getUserInfo().getCpyCode())) {
                    cpyCode = CurrentContext.getUserInfo().getCpyCode();
                }

                String userCode = StringUtils.isEmpty(CurrentContext.getUserCode()) ? "not_login" : CurrentContext.getUserCode();

                String fileMidPath =
                        FilenameUtils.concat(
                                FilenameUtils.concat(
                                        FilenameUtils.concat(
                                                FilenameUtils.concat(cpyCode, year), month), day), userCode);

                File f = new File(FilenameUtils.concat(filePathHead, fileMidPath));
                if (!f.exists()) {
                    f.mkdirs();
                }

                //上传的文件的原始名字
                String filename = fileCode + "." + FilenameUtils.getExtension(file.getOriginalFilename());

                /**
                 * 2、文件写到磁盘
                 */
                FileOutputStream fos = new FileOutputStream(new File(
                        FilenameUtils.concat(
                                FilenameUtils.concat(filePathHead, fileMidPath), filename)));

                IOUtils.write(file.getBytes(), fos);
                fos.close();

                /**
                 * 3、保存指定的实体到数据
                 */
                FileEntity fe = new FileEntity();
                fe.setPathHead(filePathHead);
                fe.setFilePath(FilenameUtils.concat(fileMidPath, filename));
                fe.setFileCode(fileCode);
                fe.setParentCode(StringUtils.isNotEmpty(parentCode) ? parentCode : "0");
                fe.setFileName(FilenameUtils.getBaseName(file.getOriginalFilename()));
                fe.setFileType(FileType.getFileType(file.getOriginalFilename()));
                //填充必要的属性数据
                OperateFillUtils.fill(fe);

                fes.add(fe);


            } catch (IOException e) {
//                e.printStackTrace();

                throw new BusinessException("写文件报错", e);

            }

        }

        fileManager.create(fes);
        response.setResult(fes);
        return this.success(response);
    }

    /**
     * 测试环境上传
     *
     * @param mm
     * @return
     */
    @RequestMapping(path = "load", method = RequestMethod.GET)
    public String fileLoad(ModelAndView mm) {

        return "file";
    }

    @IgnoreLog
    @RequestMapping(path = "/tran/{pm}", method = RequestMethod.GET)
    public ResponseEntity<IServiceResponse> tranTest(@PathVariable String pm) {
        FileServiceResponse response = new FileServiceResponse();

        fileManager.tranTest(pm);

        return this.success(response);
    }


    private void processFileRequest(String fileCode, HttpServletRequest request, HttpServletResponse response, Boolean down) {
        FileEntity file = fileManager.findByCode(fileCode);

        if (file == null) {
            try {
                ServletOutputStream out = response.getOutputStream();
//            throw new BusinessException(String.format("文件不存在{%s}",fileCode));

                out.write(String.format("文件不存在{%s}", fileCode).getBytes());
                out.close();
            } catch (IOException e) {

            }
            return;
        }

        if (!ObjectUtils.isEmpty(file) && StringUtils.startsWith(file.getFilePath(), File.separator)) {
            file.setFilePath(StringUtils.substring(file.getFilePath(), 1));
        }

        InputStream in = null;

        try {


            if (FileType.FOLDER.equalsIgnoreCase(file.getFileType())) {

                in = IOUtils.toInputStream("current request fileCode is a folder,it is not a file", "utf-8");

            } else {

                in = readFileByte(file, request);

                if (in != null) {
                    response.setContentType(MediaTypeUtils.getMediaType(file.getFilePath()) + ";charset=UTF-8");

                    if (down) {
                        String tmpName = file.getFileName();
                        if (StringUtils.isEmpty(tmpName)) {
                            tmpName = file.getFileCode();
                        }
                        try {
                            tmpName = new String(tmpName.getBytes("UTF-8"), "ISO8859-1");
                        } catch (UnsupportedEncodingException e) {
                            logger.error("编码错误", e);
                        }
                        response.addHeader("Content-Disposition", "attachment;filename=" + tmpName +
                                file.getFilePath().substring(
                                        file.getFilePath().lastIndexOf(".")));
                    }
                } else {
                    in = IOUtils.toInputStream("file not found", "utf-8");
                }
            }

            ServletOutputStream out = response.getOutputStream();


            /**
             * 表示断点续传
             */
            String rg = request.getHeader("Range");
            long start = 0;
            long end = 0;

            if (StringUtils.isNotEmpty(rg)) {

                rg = rg.replaceAll("bytes=", "");
                String[] rgs = rg.split("-");
                start = Long.parseLong(rgs[0]);
                end = 0;
                if (rgs.length > 1) {
                    end = Long.parseLong(rgs[1]);
                }

                if (start > 0 || end > 0) {

                    response.addHeader("Connection", "keep-alive");
                    long fileSize = in.available();
                    response.setHeader("Accept-Ranges", "bytes");

                    //获取这次请求的内容长度大小
                    long cl = fileSize - start;
                    if (end > 0) {
                        cl = end - start + 1;
                    }
                    response.setHeader("Content-Length", cl + "");

                    //说明之前已经请求过一部分，状态变更为206
                    response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

                    StringBuffer sb = new StringBuffer("bytes ");

                    long ed = end > 0 ? end : fileSize - 1;

                    sb.append(start + "-").append(ed + "/").append(fileSize);

                    response.setHeader("Content-Range", sb.toString());

                    //跳过已经传输的部分
                    if (start > 0) {
                        in.skip(start);
                    }

                }
            }

            //开始往客户端发数据
            byte[] b = null;
            int len = 0;

            if (end > 0) {
                b = new byte[(int) end + 1];
                len = in.read(b);
                out.write(b, 0, len);

            } else {

                b = new byte[10240];

                while ((len = in.read(b)) > 0) {
                    out.write(b, 0, len);
                }
            }

            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
//            logger.error("IOException happen ", e);

            throw new BusinessException("文件读取错误", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private InputStream readFileByte(FileEntity file, HttpServletRequest request) {


        FileInputStream fis = null;

        if (!FileType.IMAGE.equalsIgnoreCase(file.getFileType())) {

            try {
                fis = new FileInputStream(new File(FilenameUtils.concat(file.getPathHead(), file.getFilePath())));
            } catch (FileNotFoundException e) {

            }
        } else {
            String zm = request.getParameter("zoom");
            String rt = request.getParameter("rotate");
            Float zoom = null;
            Double rotate = null;

            try {
                if (zm != null) {
                    zoom = Float.valueOf(zm);
                }
                if (rt != null) {
                    rotate = Double.valueOf(rt);
                }
            } catch (NumberFormatException e) {
                logger.error("zoom参数是缩放比例，必须传输浮点型数值");
            }

            try {

                if (file != null) {

                    String fName = FilenameUtils.concat(file.getPathHead(), file.getFilePath());

                    File f = new File(fName);

                    if (f.exists()) {

                        String pth = fName.substring(0, FilenameUtils.indexOfExtension(fName));

                        String subFix = fName.substring(FilenameUtils.indexOfExtension(fName));

                        File fzm = null;
                        //初始化图片处理源
                        Thumbnails.Builder<File> tb = Thumbnails.of(f);

                        zoom = null; // 取消缩放
                        long size = f.length() / 1024; // 获取图片大小 kb
                        // 获取图片像素
                        BufferedImage sourceImg = ImageIO.read(new FileInputStream(f));
                        int width = sourceImg.getWidth();
                        int height = sourceImg.getHeight();
                        long pixel = width * height;

                        // 像素大于50万或图片大小超过200kb即进行缩放
                        if (pixel > 48000 || size >= 200) {
                            // 图片固定像素缩放 jpg格式占用最小
                            String newPath = pth + "_pix" + 800 + "-" + 600 + ".jpg";

                            InputStream is = FileRelationManagerImpl.class.getClassLoader().getResourceAsStream("water.png");

                            fzm = new File(newPath);

                            tb.size(800, 600);
                            tb.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(is), 0.2f);

                            if (!fzm.exists()) {
                                tb.toFile(fzm);
                            }

                        } else if (zoom != null) {

                            //图片设置缩放比例
                            tb.scale(zoom);
                            if (rotate != null) {
                                fzm = new File(pth + "_zm" + zoom + "_rt" + rotate + subFix);
                                //图片旋转设置
                                tb.rotate(rotate);
                            } else {
                                fzm = new File(pth + "_zm" + zoom + subFix);
                            }
                            if (!fzm.exists()) {
                                tb.toFile(fzm);
                            }
                        } else if (rotate != null) {
                            fzm = new File(pth + "_rt" + rotate + subFix);
                            //设置图片旋转
                            tb.rotate(rotate);
                        }

                        //根据实际需要返回的图片
                        if (fzm != null) {
                            fis = new FileInputStream(fzm);
                        } else {
                            fis = new FileInputStream(f);
                        }

                    } else {
                        logger.error("文件不存在!");
                    }
                } else {
                    logger.error("找不到指定文件编码的文件!");
                }
            } catch (FileNotFoundException e) {
                logger.error("系统发生异常，文件不存在!", e);
            } catch (IOException e) {
                logger.error("生成压缩图出错啦！", e);
            }


        }

        return fis;

    }
}
