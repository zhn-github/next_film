package com.next.zhn.film.controller.user.vo;

import com.next.zhn.film.controller.common.BaseVO;
import com.next.zhn.film.controller.exception.ParamErrorException;
import lombok.Data;

@Data
public class EnrollUserVO extends BaseVO {

    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;

    @Override
    public void checkParam() throws ParamErrorException {
        if(this.getUsername().trim().isEmpty()){
            throw new ParamErrorException(400,"用户名不能为空！");
        }
        if(this.getPassword().trim().isEmpty()){
            throw new ParamErrorException(400,"密码不能为空！");
        }
    }
}
