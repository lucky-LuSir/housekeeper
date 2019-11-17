package com.kfwy.hkp.base;



import java.util.Map;

/**
 * @author liwensihan
 * @description TODO
 * @date 2019/5/23 16:11
 */
public interface ActivationCodeDbDao {


    public void setActvationCode(String activationCode);

    String getActivationCodeByUserCode(String userCode);

    Map<String,String> getActivationInfoByActivationCode(String activationCode);

    public void cleanActivationCode(Map<String,String> map);
}
