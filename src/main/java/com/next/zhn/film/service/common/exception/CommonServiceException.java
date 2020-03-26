package com.next.zhn.film.service.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 张浩男
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CommonServiceException extends Exception {
    private Integer code;
    private String errMsg;

    public CommonServiceException(int code, String errMsg) {
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }
}