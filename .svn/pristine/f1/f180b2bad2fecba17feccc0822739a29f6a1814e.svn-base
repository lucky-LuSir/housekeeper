package com.kfwy.hkp.sys.notice.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjh on 2018/8/17.
 */
@Component
public class NoticeOperateType implements DictionaryProvider {

    public static final String Add = "1";
    public static final String Update = "2";
    public static final String Deleted = "3";
    //不用4的原因是因为数据库存在4，不知道以前4是干嘛的
    public static final String HouseDownRemind = "5";
    public static final String Up = "6";
    public static final String FollowUp = "7";
    public static final String COLLECT = "8";
    public static final String Share = "9";
    public static final String CusPush = "10";
    public static final String AutoRelease = "11";
    public static final String CusDownRemind = "12";
    public static final String Refuse= "13";
    public static final String Agree = "14";
    public static final String End = "15";
    public static final String FOCUS_CUS = "16";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(getKey(),"NoticeBusinessType","通知业务类型"));
        list.add(new Dictionary(getKey(),Add,"新增","NoticeBusinessType"));
        list.add(new Dictionary(getKey(),Update,"修改","NoticeBusinessType"));
        list.add(new Dictionary(getKey(),Deleted,"删除","NoticeBusinessType"));
        list.add(new Dictionary(getKey(),HouseDownRemind,"房源下架提醒","NoticeBusinessType"));
        list.add(new Dictionary(getKey(),Up,"上架","NoticeBusinessType"));
        list.add(new Dictionary(getKey(),FollowUp,"跟进","NoticeBusinessType"));
        list.add(new Dictionary(getKey(),COLLECT,"收藏","NoticeBusinessType"));
        list.add(new Dictionary(getKey(),Share,"分享","NoticeBusinessType"));
        list.add(new Dictionary(getKey(),CusPush,"推送","NoticeBusinessType"));
        list.add(new Dictionary(getKey(),AutoRelease,"自动释放","NoticeBusinessType"));
        list.add(new Dictionary(getKey(),CusDownRemind,"客户下架提醒","NoticeBusinessType"));
        list.add(new Dictionary(getKey(),Refuse,"拒绝","NoticeBusinessType"));
        list.add(new Dictionary(getKey(),Agree,"同意","NoticeBusinessType"));
        list.add(new Dictionary(getKey(),End,"结束","NoticeBusinessType"));
        list.add(new Dictionary(getKey(),FOCUS_CUS,"集中获客","NoticeBusinessType"));
        return list;
    }

    public static String getKey(){
        return NoticeOperateType.class.getName();
    }
}
