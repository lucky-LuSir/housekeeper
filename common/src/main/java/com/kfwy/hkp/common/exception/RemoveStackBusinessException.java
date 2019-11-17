package com.kfwy.hkp.common.exception;

import com.gniuu.framework.exception.BusinessException;

/**
 * @AUTHOR liwensihan
 * @Description类描述:重写业务类型的异常,将异常的调用栈去掉，提高性能
 */
public class RemoveStackBusinessException extends BusinessException {

    public RemoveStackBusinessException(String codeOrMsg) {
        super(codeOrMsg);
    }

    public RemoveStackBusinessException(String codeOrMsg,Throwable e) {
        super(codeOrMsg,e);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}