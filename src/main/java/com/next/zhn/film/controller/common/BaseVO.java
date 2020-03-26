package com.next.zhn.film.controller.common;

import com.next.zhn.film.controller.exception.ParamErrorException;

public abstract class BaseVO {
    public abstract void checkParam() throws ParamErrorException;
}
