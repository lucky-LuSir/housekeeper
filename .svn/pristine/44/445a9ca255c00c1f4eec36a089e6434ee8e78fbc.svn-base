package com.kfwy.hkp.hos.house.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by lzp on 2019/02/20.
 * 未跟进天数类型
 */
@Component
public class DaysNotFollowupType implements DictionaryProvider {

    public static final String LESSONE = "1";
    public static final String ONETHREE = "2";
    public static final String THREEFIVE = "3";
    public static final String FIVETEN = "4";
    public static final String MORETEN = "5";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(DaysNotFollowupType.class.getName(), "DaysNotFollowupType", "未跟进天数类型"));
        list.add(new Dictionary(DaysNotFollowupType.class.getName(), LESSONE, "一天以内", "DaysNotFollowupType"));
        list.add(new Dictionary(DaysNotFollowupType.class.getName(), ONETHREE, "一到三天", "DaysNotFollowupType"));
        list.add(new Dictionary(DaysNotFollowupType.class.getName(), THREEFIVE, "三到五天", "DaysNotFollowupType"));
        list.add(new Dictionary(DaysNotFollowupType.class.getName(), FIVETEN, "五到十天", "DaysNotFollowupType"));
        list.add(new Dictionary(DaysNotFollowupType.class.getName(), MORETEN, "十天以上", "DaysNotFollowupType"));

        return list;
    }

    /**
     * 设置多少天未跟进
     * @param daysNotFollowupType
     * @param dayNotFollowup
     * @return
     */
    public static void setDaysNotFollowup(Map param,String daysNotFollowupType, Integer dayNotFollowup) {
        if (StringUtils.isNotEmpty(daysNotFollowupType)) {
            Calendar startCal = Calendar.getInstance();
            Calendar endCal = Calendar.getInstance();
            switch (daysNotFollowupType) {
                case LESSONE:
                    startCal.add(Calendar.DATE, -1);
                    param.put("startLastFollowupTime", startCal.getTime());
                    break;
                case ONETHREE:
                    startCal.add(Calendar.DATE, -3);
                    endCal.add(Calendar.DATE, -1);
                    param.put("startLastFollowupTime", startCal.getTime());
                    param.put("endLastFollowupTime", endCal.getTime());
                    break;
                case THREEFIVE:
                    startCal.add(Calendar.DATE, -5);
                    endCal.add(Calendar.DATE, -3);
                    param.put("startLastFollowupTime", startCal.getTime());
                    param.put("endLastFollowupTime", endCal.getTime());
                    break;
                case FIVETEN:
                    startCal.add(Calendar.DATE, -10);
                    endCal.add(Calendar.DATE, -5);
                    param.put("startLastFollowupTime", startCal.getTime());
                    param.put("endLastFollowupTime", endCal.getTime());
                    break;
                case MORETEN:
                    endCal.add(Calendar.DATE, -10);
                    param.put("endLastFollowupTime", endCal.getTime());
                    break;
            }
        } else if (null != dayNotFollowup) {
            Calendar endCal = Calendar.getInstance();
            endCal.add(Calendar.DATE, -dayNotFollowup);
            param.put("endLastFollowupTime", endCal.getTime());
        }
    }
}
