package com.kfwy.hkp.common.utils;

import com.gniuu.framework.security.cryptography.Base64;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by chenjie on 2019/6/27.
 * 编码文件的存储
 */
public class Base64MOFileUtils {





    /**
     * 处理base64编码的文件,尾部为下划线和文件类型比如{_jpg}
     *
     * @param fileBase64
     * @param root
     * @param path
     * @return
     */
    public static String saveFile(String fileBase64,String root, String path, String name) {

        if (root.endsWith(File.separator)) {
            root = root.substring(0, root.lastIndexOf(File.separator));
        }
        String str = fileBase64;
        //文件字节
        byte[] bt = Base64.decodeBase64Binrary(str);
        //最终传入的图片存储路径类似：/srv/site/kfwy/pic/

        File file = new File(root + path + name+".JPG");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream(file);
            fs.write(bt);
            fs.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RemoveStackBusinessException("保存图片失败!");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RemoveStackBusinessException ("保存图片失败!");
        }
        return path +name;
    }

}
