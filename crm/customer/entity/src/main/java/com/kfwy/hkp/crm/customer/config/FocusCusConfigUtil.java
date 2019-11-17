package com.kfwy.hkp.crm.customer.config;

import com.kfwy.hkp.base.FocusCusConfig;
import com.kfwy.hkp.base.SystemConfig;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;

import java.util.ArrayList;
import java.util.List;

public class FocusCusConfigUtil {

    public static FocusCusConfig getFocusCusConfig (CustomerEntity cus) {
        FocusCusConfig focusCusConfig = SystemConfig.create ().getObject ("focus_cus_config", FocusCusConfig.class);
        if (cus.getNoticeDepts () != null) {
            if (cus.getNoticeDepts ().size () > 1) {
                List<FocusCusConfig> focusCusConfigs = new ArrayList<> ();
                for (String dept : cus.getNoticeDepts ()) {
                    FocusCusConfig deptFocusCusStrtegy = SystemConfig.create ().getObject (dept + "_focus_cus_config", FocusCusConfig.class);
                    if (deptFocusCusStrtegy == null) {
                        deptFocusCusStrtegy = focusCusConfig;
                    }
                    focusCusConfigs.add (deptFocusCusStrtegy);
                }

                Boolean flag = true;
                FocusCusConfig strategy = SystemConfig.create ().getObject (cus.getNoticeDepts ().get (0) + "_focus_cus_config", FocusCusConfig.class);
                if (strategy == null) {
                    strategy = focusCusConfig;
                }

                for (FocusCusConfig config : focusCusConfigs) {
                    if (! config.getStrategy ().equals (strategy.getStrategy ())) {
                        flag = false;
                    }
                }

                if (flag) {
                    focusCusConfig.setStrategy (strategy.getStrategy ());
                }


            } else if (cus.getNoticeDepts ().size () == 1) {
                FocusCusConfig deptFocusCusStrtegy = SystemConfig.create ().getObject (cus.getNoticeDepts ().get (0) + "_focus_cus_config", FocusCusConfig.class);
                if (deptFocusCusStrtegy != null) {
                    focusCusConfig = deptFocusCusStrtegy;
                }
            }
        }
        return focusCusConfig;
    }
}
