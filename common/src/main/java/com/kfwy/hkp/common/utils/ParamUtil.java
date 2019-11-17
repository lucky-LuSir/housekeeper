package com.kfwy.hkp.common.utils;


import com.kfwy.hkp.common.exception.RemoveStackBusinessException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;


/**
 * @author liwensihan
 */
public class ParamUtil<T> {

    /**
     * @Description 该方法不处理boolean,由于前端传来的boolean默认为false
     * 同时不处理父类带有的字段,如BaseEntity中的createCode等
     * @Date 2018/7/12 18:19
     * @Version 1.0
     * @Param [t, map]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> objectToMap(T t,Map map) throws IllegalAccessException {

        if(t != null){
            Class clazz = t.getClass();
            Field[] fields = clazz.getFields();
            Method[] methods = clazz.getMethods();
            for (Method method: methods) {
                String prop = method.getName();

                if (prop.startsWith("get")){
                    try {
                        Object val = method.invoke(t);
                        String upperName = prop.substring(3);
                        upperName = upperName.substring(0, 1).toLowerCase() + upperName.substring(1);
                        if (val != null) {
                            map.put(upperName,val);
                        }
                    }catch (Exception e){

                        e.printStackTrace();
                    }

                }
            }
        }
        return map;
    }



    /**
     * @Description 新增修改参数校验
     * @Auth liuzhengyang
     * @Date 2018/7/20 13:33
     * @Version 1.0
     * @Param [t, params]
     * @return void
     */
    public void check(T t,List<String> params) throws IllegalAccessException {
        if(t != null){

            Class clazz = t.getClass();


            List<Field> fieldList = new ArrayList<>() ;

            while (clazz != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
                fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
                clazz = clazz.getSuperclass(); //得到父类,然后赋给自己
            }

                //填充两个map集合
            Map<String,Object> valMap = new HashMap<>(16);
            Map<String,String> typeMap = new HashMap<>(16);
            for (Field f:fieldList){
                f.setAccessible(true);
                //变量值
                Object val = f.get(t);
                //变量名
                String param = f.getName();
                //变量名和变量值map
                valMap.put(param,val);
                //变量名和变量类型map
                typeMap.put(param,f.getType().toString());
            }
            for(String param : params){
                Object val = valMap.get(param);
                String type = typeMap.get(param);
                if(type == null){
                    throw new RemoveStackBusinessException ("设置的必填字段"+param+"不存在");
                }else if(type.endsWith("String")){
                    if(val == null || val == ""){
                        throw new RemoveStackBusinessException("必填String字段出错-"+param+"-出错");
                    }
                }else if(type.endsWith("Integer") || type.endsWith("int")){
                    if(val == null || val == ""){
                        throw new RemoveStackBusinessException("必填Integer字段出错-"+param+"-出错");
                    }
                }else if(type.endsWith("BigDecimal")){
                    if(val == null || val == ""){
                        throw new RemoveStackBusinessException("必填BigDecimal字段出错-"+param+"-出错");
                    }
                }else if(type.endsWith("boolean") || type.endsWith("Boolean")){
                    if(val == null || val == ""){
                        throw new RemoveStackBusinessException("必填Boolean字段出错-"+param+"-出错");
                    }
                }else if(type.endsWith("Date")){
                    if(val == null || val == ""){
                        throw new RemoveStackBusinessException("必填Date字段出错-"+param+"-出错");
                    }
                }else if(type.endsWith("List")){
                    if(val == null || val == ""){
                        throw new RemoveStackBusinessException("必填String字段出错-"+param+"-出错");
                    }
                }else if(val == null || val == ""){
                    throw new RemoveStackBusinessException("字段出错-"+param+"-出错");
                }
            }
        }
    }
}
