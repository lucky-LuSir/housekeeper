package com.kfwy.hkp.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilterUtil {


    public static Map<String, Object> filterMap(Map<String, Object> map) {
        for (String key : map.keySet()) {
            Object obj = map.get(key);

            if (obj instanceof List || obj instanceof ArrayList) {
                String vls = "";
                for (Object s : (ArrayList<Object>) obj) {
                    if (s != null) {
                        if (s instanceof String) {
                            String t = (String) s;
                            if (StringUtils.isNotEmpty(t)) {
                                vls += "'" + t + "',";
                            }
                        } else {
                            vls += s + ",";
                        }
                    }
                }
                if (StringUtils.isNotEmpty(vls)) {
                    vls = vls.substring(0, vls.length() - 1);
                    map.put(key, vls);
                }
            }
        }
        return map;
    }
}