package com.kuaidi100.bdindex.exception;

import com.kuaidi100.bdindex.pojo.ResponseBean;
import com.kuaidi100.bdindex.pojo.StatusCode;
import com.kuaidi100.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ValidationException;

/**
 * @author zefeng_lin
 * @date 2019/5/13 11:01
 */
@ControllerAdvice
public class AutoExceptionHandler {
    Logger logger = LoggerFactory.getLogger(AutoExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseBean exceptionHandler(Exception exception) {
        logger.error(exception.getMessage(), exception);
        return ResponseBean.response().setCode(StatusCode.C_ERROR.getCode()).setMessage(StatusCode.C_ERROR.getDes());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseBean businessExceptionHandler(Exception exception) {
        logger.error(exception.getMessage(), exception);
        return ResponseBean.response().setCode(StatusCode.C_ERROR.getCode()).setMessage(exception.getMessage());
    }

    @ExceptionHandler(value = {BindException.class})
    @ResponseBody
    public ResponseBean bindExceptionHandler(BindException exception) {
        logger.error(exception.getMessage(), exception);
        return ResponseBean.response().setCode(StatusCode.C_10002.getCode()).setMessage(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }


    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseBody
    public ResponseBean bindExceptionHandler(MethodArgumentNotValidException exception) {
        logger.error(exception.getMessage(), exception);
        return ResponseBean.response().setCode(StatusCode.C_10002.getCode()).setMessage(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseBean handle(ValidationException exception) {
        logger.error(exception.getMessage(), exception);
        return ResponseBean.response().setCode(StatusCode.C_ERROR.getCode()).setMessage(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseBean handleAccessException(AccessDeniedException exception) {
        logger.error(exception.getMessage(), exception);
        return ResponseBean.warn(StatusCode.C_402);
    }


}
