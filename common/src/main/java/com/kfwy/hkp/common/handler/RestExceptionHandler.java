package com.kfwy.hkp.common.handler;

import com.gniuu.framework.common.enums.ResponseMessage;
import com.gniuu.framework.exception.BaseException;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.exception.RestBusinessException;
import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.gniuu.framework.service.AbstractServiceResponse;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by davidcun on 2018/5/17.
 */
@ControllerAdvice
public class RestExceptionHandler{


    private static Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * 针对REST风格接口，统一返回HTTP OK状态，消息内容是失败的
     * @param ex
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({RestBusinessException.class, BusinessException.class})
    public ResponseEntity<AbstractServiceResponse> handlerRestException(BaseException ex, WebRequest request){

        AbstractServiceResponse response = new AbstractServiceResponse();
        response.setIsSuccess(false);
        response.setIsException(true);

        logger.error("RestExceptionHandler happen",ex);

        if (ex instanceof BaseException){

            BaseException be = (BaseException) ex;

            response.setCode(be.getCode());
            response.setMessage(be.getErrorMsg());
        }else{
            response.setMessage(ResponseMessage.unKnownErrorCode_message);
            response.setCode(ResponseMessage.unKnownErrorCode);
        }

        return new ResponseEntity<AbstractServiceResponse>(response,HttpStatus.OK);
    }

    /**
     * 针对REST风格接口，统一返回HTTP OK状态，消息内容是失败的
     * @param ex
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class, MissingServletRequestPartException.class,
            TypeMismatchException.class})
    public ResponseEntity<AbstractServiceResponse> handler400Exception(Exception ex, WebRequest request){

        AbstractServiceResponse response = new AbstractServiceResponse();
        response.setIsSuccess(false);
        response.setIsException(true);
        response.setMessage("参数错误异常，包括必填，格式校验，参数类型错误等");
        response.setCode("400");

        logger.error("RestExceptionHandler happen",ex);

        return new ResponseEntity<AbstractServiceResponse>(response,HttpStatus.OK);
    }

    /**
     * @param ex
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<AbstractServiceResponse> handler404Exception(Exception ex, WebRequest request){

        AbstractServiceResponse response = new AbstractServiceResponse();
        response.setIsSuccess(false);
        response.setIsException(true);
        response.setMessage("接口地址错误");
        response.setCode("404");

        logger.error("RestExceptionHandler happen",ex);

        return new ResponseEntity<AbstractServiceResponse>(response,HttpStatus.OK);
    }

    /**
     * @param ex
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<AbstractServiceResponse> handler405Exception(Exception ex, WebRequest request){

        AbstractServiceResponse response = new AbstractServiceResponse();
        response.setIsSuccess(false);
        response.setIsException(true);
        response.setMessage("调用方法类型错误，比如应该用POST，请求的却是GET");
        response.setCode("405");

        logger.error("RestExceptionHandler happen",ex);

        return new ResponseEntity<AbstractServiceResponse>(response,HttpStatus.OK);
    }

    /**
     * @param ex
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public ResponseEntity<AbstractServiceResponse> handler406Exception(Exception ex, WebRequest request){

        AbstractServiceResponse response = new AbstractServiceResponse();
        response.setIsSuccess(false);
        response.setIsException(true);
        response.setMessage("客户端浏览器不接受所请求页面的MIME类型");
        response.setCode("406");

        logger.error("RestExceptionHandler happen",ex);

        return new ResponseEntity<AbstractServiceResponse>(response,HttpStatus.OK);
    }

    /**
     * @param ex
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<AbstractServiceResponse> handler415Exception(Exception ex, WebRequest request){

        AbstractServiceResponse response = new AbstractServiceResponse();
        response.setIsSuccess(false);
        response.setIsException(true);
        response.setMessage("不支持的媒体类型");
        response.setCode("415");

        logger.error("RestExceptionHandler happen",ex);

        return new ResponseEntity<AbstractServiceResponse>(response,HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({Exception.class})
    public ResponseEntity<AbstractServiceResponse> handlerDefaultException(Exception ex, WebRequest request){

        AbstractServiceResponse response = new AbstractServiceResponse();
        response.setIsSuccess(false);
        response.setIsException(true);
        response.setMessage("");
        response.setCode("415");

        logger.error("exception happen",ex);

        return new ResponseEntity<AbstractServiceResponse>(response,HttpStatus.OK);
    }


}
