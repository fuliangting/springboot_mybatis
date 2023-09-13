package com.fu.springboot.exceptions;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice   //作为全局异常，并交给IOC容器维护
public class GlobalExceptionHandlerResolver {

    //    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public Map<String, Object> exceptionHandler(Exception ex) {
//        Map<String, Object> map = new HashMap<>();
//        if(ex instanceof ParamsException) {
//            ParamsException paramsException = (ParamsException)ex;
//            map.put("code", paramsException.getCode());
//            map.put("msg", paramsException.getMsg());
//        } else {
//            map.put("code", 500);
//            map.put("msg", "系统异常，请重试！");
//        }
//
//        return map;
//    }
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, Object> exceptionHandler(Exception ex) {
        Map<String, Object> map = new HashMap<>();

        map.put("code", 500);
        map.put("msg", "系统异常，请重试！");

        return map;
    }

    @ExceptionHandler(value = ParamsException.class)
    @ResponseBody
    public Map<String, Object> paramExceptionHandler(ParamsException ex) {
        Map<String, Object> map = new HashMap<>();

        map.put("code", ex.getCode());
        map.put("msg", ex.getMsg());

        return map;
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Map<String, Object> paramExceptionHandler(BindException ex) {
        Map<String, Object> map = new HashMap<>();

        map.put("code", ex.hashCode());
        map.put("msg", ex.getBindingResult().getFieldError().getDefaultMessage());

        return map;
    }
}
