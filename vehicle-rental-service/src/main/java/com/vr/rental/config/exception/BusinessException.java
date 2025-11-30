package com.vr.rental.config.exception;


import com.vr.common.core.response.ResultCode;

/**
 * @Description
 * @Author ys
 * @Date 2023/5/11 10:32
 */
public class BusinessException extends RuntimeException{

    public ResultCode code;

    public String message;

    public BusinessException(ResultCode resultCode){
        this(resultCode,resultCode.getMessage());
    }

    public BusinessException(String reason){
        this(ResultCode.FAILED,reason);
    }

    public BusinessException(Throwable throwable){
        this(ResultCode.FAILED,throwable.getMessage());
    }

    public BusinessException(ResultCode code,String message){
        this.code=code;
        this.message=message;
    }

    public ResultCode getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
