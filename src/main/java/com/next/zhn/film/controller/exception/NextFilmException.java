package com.next.zhn.film.controller.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 张浩男
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class NextFilmException extends Exception{

    private Integer code;
    private String errMsg;

    public NextFilmException(int code, String errMsg){
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }

}
