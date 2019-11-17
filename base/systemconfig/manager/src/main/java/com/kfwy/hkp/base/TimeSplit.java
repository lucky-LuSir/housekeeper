package com.kfwy.hkp.base;

import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.gniuu.framework.service.AbstractDto;
import org.apache.commons.lang3.StringUtils;

public class TimeSplit extends AbstractDto {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeSplit.class);

    private String key;
    private int afterNumber;
    private String afterFormat;

    public TimeSplit(String key) {
        this.key = key;
        change();
    }

    public TimeSplit(String key, int afterNumber) {
        this.key = key;
        this.afterNumber = afterNumber;
        change();
    }

    public TimeSplit(String key, int afterNumber, String afterFormat) {
        this.key = key;
        this.afterNumber = afterNumber;
        this.afterFormat = afterFormat;
        change();
    }

    public int getAfterNumber() {
        return afterNumber;
    }

    public String getAfterFormat() {
        return afterFormat;
    }

    protected void change() {
        if (StringUtils.isNotEmpty(this.key)) {

            //获取其中间隔时间和日期
            try {
                //获取系统配置设置的时间间隔
                TimeDto td = SystemConfig.create().getObject(key, TimeDto.class);
                if (td != null) {
                    Integer an = td.getAfterNumber();
                    String fm = td.getAfterFormat();
                    if (an != null) {
                        this.afterNumber = an;
                    }
                    if (StringUtils.isNotEmpty(fm)) {
                        this.afterFormat = fm;
                    }
                }
            } catch (Exception e) {
                //如果出现错误,说明是配置参数有问题
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error(e);
                }
            }
        }
    }
}
