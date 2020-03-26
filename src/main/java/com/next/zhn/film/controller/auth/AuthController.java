package com.next.zhn.film.controller.auth;

import com.next.zhn.film.controller.auth.VO.AuthRequestVO;
import com.next.zhn.film.controller.auth.VO.AuthResponseVO;
import com.next.zhn.film.controller.auth.utils.JwtTokenUtil;
import com.next.zhn.film.controller.common.BaseResponseVO;
import com.next.zhn.film.controller.exception.ParamErrorException;
import com.next.zhn.film.service.common.exception.CommonServiceException;
import com.next.zhn.film.service.user.UserServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户鉴权验证
 */
@RestController
public class AuthController {
    @Autowired
    private UserServiceAPI userServiceAPI;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @RequestMapping(value = "auth", method = RequestMethod.POST)
    public BaseResponseVO auth(@RequestBody AuthRequestVO authRequestVO) throws ParamErrorException, CommonServiceException {

        authRequestVO.checkParam();

        boolean isValid = userServiceAPI.userAuth(authRequestVO.getUsername(), authRequestVO.getPassword());

        if (isValid) {
            String randomKey = jwtTokenUtil.getRandomKey();
            String token = jwtTokenUtil.generateToken(authRequestVO.getUsername(),randomKey);
            AuthResponseVO authResponseVO = AuthResponseVO.builder()
                    .randomKey(randomKey).token(token).build();

            return BaseResponseVO.success(authResponseVO);
        } else {
            return BaseResponseVO.serviceFailed("用户名或密码不正确！");
        }

    }
}
