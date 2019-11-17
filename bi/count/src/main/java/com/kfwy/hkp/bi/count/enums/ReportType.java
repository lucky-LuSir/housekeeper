package com.kfwy.hkp.bi.count.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjh on 2018/10/26.
 */
@Component
public class ReportType implements DictionaryProvider {

    public final static String REGIONAL = "1";
    public final static String DEPT = "2";
    public final static String EMP = "3";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(ReportType.class.getName(),"ReportType","报表类型"));
        list.add(new Dictionary(ReportType.class.getName(),REGIONAL,"大区报表","ReportType"));
        list.add(new Dictionary(ReportType.class.getName(),DEPT,"部门报表","ReportType"));
        list.add(new Dictionary(ReportType.class.getName(),EMP,"人员报表","ReportType"));
        return list;
    }
}
