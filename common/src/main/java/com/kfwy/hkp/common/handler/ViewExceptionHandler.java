package com.kfwy.hkp.common.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by davidcun on 2018/5/17.
 * TODO
 */
//@ControllerAdvice
public class ViewExceptionHandler {


    @ExceptionHandler({})
    public ModelAndView handler400HandlerException(Exception ex, WebRequest request){

        return null;
    }

}
