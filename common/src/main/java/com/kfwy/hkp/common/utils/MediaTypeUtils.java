package com.kfwy.hkp.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/6/13 20:57
 */
public class MediaTypeUtils {


    public static Map<String, String> map = new HashMap<String, String>();

    static {

        //视频
        map.put("asf", "video/x-ms-asf");
        map.put("asx", "video/x-ms-asf");
        map.put("avi", "video/avi");
        map.put("IVF", "video/x-ivf");
//        map.put("mp4", "video/mpeg4");
        map.put("mp4","video/mp4");
        map.put("mpg", "video/mpg");
        map.put("wmv", "video/x-ms-wmv");
        map.put("wmx", "video/x-ms-wmx");
        map.put("swf", "application/x-shockwave-flash");
        map.put("flv", "video/flv");

        //音频
        map.put("mid", "audio/mid");
        map.put("midi", "audio/mid");
        map.put("mp1", "audio/mp1");
        map.put("mp2", "audio/mp2");
        map.put("mp3", "audio/mp3");
        map.put("rmi", "audio/mid");
        map.put("wav", "audio/wav");
        map.put("wma", "audio/x-ms-wma");

        //图片
        map.put("tif", "image/tiff");
        map.put("fax", "image/fax");
        map.put("gif", "image/gif");
        map.put("ico", "image/x-icon");
        map.put("jpe", "image/jpeg");
        map.put("jpeg", "image/jpeg");
        map.put("jfif", "image/jpeg");
        map.put("jpg", "image/jpeg");
        map.put("png", "image/png");
        map.put("tiff", "image/tiff");

        //文本
        map.put("txt", "text/plain");
        map.put("css", "text/css");
        map.put("xml", "text/xml");
        map.put("html", "text/html");
        map.put("htm", "text/html");

        map.put("pdf", "application/pdf");
        map.put("doc", "application/msword");
        map.put("docx", "application/msword");
        map.put("xlsx", "application/x-xls");
        map.put("xls", "application/x-xls");
//        map.put(".xls","application/vnd.ms-excel");
//        map.put(".xlsx","application/vnd.ms-excel");
//        map.put("","");
//        map.put("","");
//        map.put("","");

    }

    public static String getMediaType(String fileName) {

        int i = fileName.lastIndexOf(".");

        String fl = i >= 0 ? fileName.substring(i + 1) : fileName;


        String tm = map.get(fl.toLowerCase());

        return StringUtils.isEmpty(tm) ? "application/octet-stream" : tm;
    }
}
