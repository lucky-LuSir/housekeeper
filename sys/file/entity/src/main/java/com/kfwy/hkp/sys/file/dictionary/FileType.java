package com.kfwy.hkp.sys.file.dictionary;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Auth davidCun
 * @Date 2018/6/12 23:32
 * @Version 1.0
 */
public class FileType implements DictionaryProvider {


    public static String IMAGE = "image";
    public static String VIDEO = "video";
    public static String AUDIO = "audio";
    public static String TEXT = "text";
    public static String EXCEL = "excel";
    public static String WORD = "word";
    public static String PDF = "pdf";
    public static String PPT = "ppt";
    public static String FILE = "file";
    public static String FOLDER = "folder";


    @Override
    public List<Dictionary> produce() {

        List<Dictionary> dic = new ArrayList<Dictionary>();

        dic.add(new Dictionary(FileType.class.getName(), FileType.class.getSimpleName(),"文件类型"));
        dic.add(new Dictionary(FileType.class.getName(),IMAGE,"图片", FileType.class.getSimpleName()));
        dic.add(new Dictionary(FileType.class.getName(),VIDEO,"视频", FileType.class.getSimpleName()));
        dic.add(new Dictionary(FileType.class.getName(),AUDIO,"音频", FileType.class.getSimpleName()));
        dic.add(new Dictionary(FileType.class.getName(),TEXT,"文本", FileType.class.getSimpleName()));
        dic.add(new Dictionary(FileType.class.getName(),EXCEL,"EXCEL", FileType.class.getSimpleName()));
        dic.add(new Dictionary(FileType.class.getName(),WORD,"WORD", FileType.class.getSimpleName()));
        dic.add(new Dictionary(FileType.class.getName(),PPT,"PPT", FileType.class.getSimpleName()));
        dic.add(new Dictionary(FileType.class.getName(),PDF,"PDF", FileType.class.getSimpleName()));
        dic.add(new Dictionary(FileType.class.getName(),FILE,"其他文件", FileType.class.getSimpleName()));
        dic.add(new Dictionary(FileType.class.getName(),FOLDER,"文件夹", FileType.class.getSimpleName()));

        return dic;
    }


    private static Map<String,String> map = new HashMap<String,String>();


    static {

        //视频
        map.put("asf", VIDEO);
        map.put("asx", VIDEO);
        map.put("avi", VIDEO);
        map.put("IVF", VIDEO);
//        map.put("mp4", "video/mpeg4");
        map.put("mp4",VIDEO);
        map.put("mpg", VIDEO);
        map.put("wmv", VIDEO);
        map.put("wmx", VIDEO);
        map.put("swf", VIDEO);

        //音频
        map.put("mid", AUDIO);
        map.put("midi", AUDIO);
        map.put("mp1", AUDIO);
        map.put("mp2", AUDIO);
        map.put("mp3", AUDIO);
        map.put("rmi", AUDIO);
        map.put("wav", AUDIO);
        map.put("wma", AUDIO);

        //图片
        map.put("tif", IMAGE);
        map.put("fax", IMAGE);
        map.put("gif", IMAGE);
        map.put("ico", IMAGE);
        map.put("jpe", IMAGE);
        map.put("jpeg", IMAGE);
        map.put("jfif", IMAGE);
        map.put("jpg", IMAGE);
        map.put("png", IMAGE);
        map.put("tiff", IMAGE);

        //文本
        map.put("txt", TEXT);
        map.put("css", TEXT);
        map.put("xml", TEXT);
        map.put("html", TEXT);
        map.put("htm", TEXT);

        map.put("pdf", PDF);
        map.put("doc", WORD);
        map.put("docx", WORD);
        map.put("xlsx", EXCEL);
        map.put("xls", EXCEL);
    }

    public static String getFileType(String fileName){

        if (StringUtils.isEmpty(fileName)){
            return FILE;
        }

        int i = fileName.lastIndexOf(".");

        String fl = i >= 0 ? fileName.substring(i + 1) : fileName;

        String tm = map.get(fl.toLowerCase());

        return StringUtils.isEmpty(tm) ? FILE : tm;

    }
}
