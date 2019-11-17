package com.kfwy.hkp.common.utils;

import java.util.Random;

/**
 * Created by moyi on 15-7-20.
 * 15-7-20
 */
public class SimpleSecurityCodeUtil {

    /**
     * 生成随机整数
     *
     * @param length 长度4-8
     * @return 4-8位的整形
     */
    public static int random(int length) {
        if(length >8 || length <4){
            throw new RuntimeException("random number's length between 4 and 8");
        }
        return (int)((Math.random()*9+1)* Math.pow(10,length-1));
    }

    /**
     * 生成随机字符串
     *
     * @param length 长度4-8
     * @return 4-8位字符串
     */
    public static String security(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
