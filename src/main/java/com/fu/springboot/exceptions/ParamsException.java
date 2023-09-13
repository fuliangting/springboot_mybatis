package com.fu.springboot.exceptions;

import com.fu.springboot.dao.UserMapper;
import com.fu.springboot.po.User;
import com.fu.springboot.util.AssertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//自定义参数异常
public class ParamsException extends RuntimeException {
    private Integer code = 500;
    private String msg = "参数异常!";

    public ParamsException() {
        super("参数异常!");
    }

    public ParamsException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ParamsException(Integer code) {
        super("参数异常!");
        this.code = code;
    }

    public ParamsException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}