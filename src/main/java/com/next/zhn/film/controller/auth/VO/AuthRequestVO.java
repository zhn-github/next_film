package com.next.zhn.film.controller.auth.VO;

import com.next.zhn.film.controller.common.BaseVO;
import com.next.zhn.film.controller.exception.ParamErrorException;
import lombok.Data;

@Data
public class AuthRequestVO extends BaseVO {

    private  String username;
    private  String password;

    @Override
    public void checkParam() throws ParamErrorException {

    }
}
