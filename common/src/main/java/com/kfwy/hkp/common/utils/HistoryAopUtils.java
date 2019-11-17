package com.kfwy.hkp.common.utils;

import com.kfwy.hkp.base.history.entity.HistoryEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author kfwy_it_013
 */
public class HistoryAopUtils {
    public static List<HistoryEntity> editHistoryList(String[] array, Map oldObjMap,Map newObjMap,String batchCode,String className,String dataCode) {
        List<HistoryEntity> lists = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            String oldFieldVal = String.valueOf(oldObjMap.get(array[i]));
            String newFieldVal = String.valueOf(newObjMap.get(array[i]));
            if (!(oldFieldVal.equals(newFieldVal))&&null!=newFieldVal&&newFieldVal!="null") {
                HistoryEntity historyEntity = new HistoryEntity();
                historyEntity.setClassName(className);
                historyEntity.setOptDataCode(dataCode);
                historyEntity.setBatchCode(batchCode);
                historyEntity.setField(array[i]);
                historyEntity.setOldValue(oldFieldVal);
                historyEntity.setNewValue(newFieldVal);
                lists.add(historyEntity);
            }
        }
        return lists;
    }
}
