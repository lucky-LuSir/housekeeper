package com.kfwy.hkp.crm.customer.business.scheduled;

import com.kfwy.hkp.base.FocusCusConfig;
import com.kfwy.hkp.base.SystemConfig;
import com.kfwy.hkp.crm.customer.dao.IFocusCusDbDao;
import com.kfwy.hkp.crm.customer.dto.CustomerFocusCusDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @AUTHOR 李文思汗
 * @Description 类描述:定时将创建自定义小时以上的集中获客客户转移为平台客户,并推送客户新增消息
 */
@Component
public class ScheduledUpdateFocusCus implements InitializingBean {
    public Logger logger = LoggerFactory.getLogger(ScheduledUpdateFocusCus.class);
    @Autowired
    private ScheduledExecutorService mScheduledExecutorService;

    @Autowired
    private IFocusCusDbDao focusCusDbDao;


    @Autowired
    private SystemConfig config;

    @Override
    public void afterPropertiesSet() {
        mScheduledExecutorService.scheduleWithFixedDelay(() -> {

                    try {

                        String focusToPlatformTime = config.getObject ("focus_cus_config", FocusCusConfig.class).getFocusToPlatformTime ();
                        if (focusToPlatformTime == null) {
                            focusToPlatformTime = "48hour";
                        }
                        Map map = new HashMap(2);
                        map.put("updateFocusCusToPlatform", true);
                        map.put("focusToPlatformTime", focusToPlatformTime);
                        List<CustomerFocusCusDto> list = focusCusDbDao.findFocusCusToPlatForm(map);
                        if (list != null && list.size() > 0) {

                            int updateFocusCusToPlatform = focusCusDbDao.updateFocusCusToPlatform(list, map);

                            logger.info("集中获客转换平台客户数量  count:" + updateFocusCusToPlatform);

                        }else{
                            logger.info("集中获客转换平台客户数量  count:" + 0);
                        }
                    } catch (Exception e) {
                        System.out.println("异常执行" + e);
                    }

                },
                0, 30, TimeUnit.MINUTES);
    }
}
