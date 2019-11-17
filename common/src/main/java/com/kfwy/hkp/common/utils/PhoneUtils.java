package com.kfwy.hkp.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by davidcun on 2017/1/12.
 */
public class PhoneUtils {

    private static Pattern MOBILE_PATTERN = Pattern.compile("^(13[0-9]|14[579]|15[0-3,5-9]|16[0-9]|17[0135678]|18[0-9]|19[189])\\d{8}$|^[8][8][6][0][9]\\d{8}$|^[0][1-9]{2,3}-[0-9]{5,10}$|^[1-9]{1}[0-9]{5,8}$");

    public static boolean isMobile(String str) {
        if(str==null){
            return false;
        }
        Matcher m = null;
        boolean b = false;
        m = MOBILE_PATTERN.matcher(str);
        b = m.matches();
        return b;
    }

    public static String getHiddenMobile(String mobile) {
        if(StringUtils.isBlank(mobile)){
            return null;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    public static void main(String[] args) {
        //System.out.println(PhoneUtils.isMobile("13564578888"));
        System.out.println(PhoneUtils.isMobile("507971000"));
//        for (int i=0; i<1000; i++){
//            System.out.println(RandomUtil.random(10));
//        }

    }
}
