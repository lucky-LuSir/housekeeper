package com.kfwy.hkp.common.handler;


import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by davidcun on 2017/11/16.
 * Freemarker模版出错异常处理类
 */
public class FreemarkerExceptionHandler implements TemplateExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(FreemarkerExceptionHandler.class);

    @Override
    public void handleTemplateException(TemplateException te, Environment env, Writer out) throws TemplateException {

        logger.info("[FreemarkerException : "+te.getMessage()+"]");

        String missingVariable = "undefined";
        try {
            String[] tmp = te.getMessageWithoutStackTop().split("\n");
            if (tmp.length > 1)
                tmp = tmp[1].split(" ");
            if (tmp.length > 1)
                missingVariable = tmp[1];

            out.write("[${ " + missingVariable
                    + "}]");
            logger.error("[出错了，请联系网站管理员]", te);
        } catch (IOException e) {
            throw new TemplateException(
                    "Failed to print error message. Cause: " + e, env);
        }
    }
}
