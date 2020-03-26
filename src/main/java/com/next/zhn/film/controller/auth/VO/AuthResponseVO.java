package com.next.zhn.film.controller.auth.VO;

import com.next.zhn.film.controller.common.BaseVO;
import com.next.zhn.film.controller.exception.ParamErrorException;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseVO extends BaseVO {
    private String randomKey;
    private String token;

    @Override
    public void checkParam() throws ParamErrorException {

    }
}
