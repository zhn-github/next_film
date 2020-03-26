package com.next.zhn.film.controller.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/*
    VO参数验证异常
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ParamErrorException extends Exception {

    private Integer code;
    private String errMsg;

    public ParamErrorException(int code, String errMsg){
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }

}