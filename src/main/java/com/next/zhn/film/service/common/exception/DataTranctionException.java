package com.next.zhn.film.service.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DataTranctionException extends Exception {
    private Integer code;
    private String errMsg;

    public DataTranctionException(int code, String errMsg) {
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }
}
