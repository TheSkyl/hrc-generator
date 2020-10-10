package com.cebrains.hrc.core.exception;

/**
 * 封装hrc的异常
 *
 * @author frank
 * @Date 2017/12/28 下午10:32
 */
public class HRCException extends RuntimeException {

    private Integer code;

    private String message;

    public HRCException(ServiceExceptionEnum serviceExceptionEnum) {
        this.code = serviceExceptionEnum.getCode();
        this.message = serviceExceptionEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
