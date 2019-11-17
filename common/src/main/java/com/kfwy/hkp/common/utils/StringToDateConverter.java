package com.kfwy.hkp.common.utils;



import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;


public class StringToDateConverter implements Converter<String,Date>{
    private String pattern;
    public StringToDateConverter(String pattern){
        this.pattern=pattern;
        System.out.println("初始化**********"+pattern);
    }

    @Override
    public Date convert(String str){
        try{
            System.out.println("*************convert 被调用了*************");
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);
            simpleDateFormat.setLenient(false);
            Date date= simpleDateFormat.parse(str);
            System.out.println("格式化之前的str"+str+"格式化之后的date"+date);
            return date;
        }catch (Exception e) {
            return null;
        }
    }
}
