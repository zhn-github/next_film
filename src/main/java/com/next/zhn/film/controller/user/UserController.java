package com.next.zhn.film.controller.user;

import com.google.common.collect.Maps;
import com.next.zhn.film.common.utils.ToolUtils;
import com.next.zhn.film.controller.common.BaseResponseVO;
import com.next.zhn.film.controller.common.TraceUtil;
import com.next.zhn.film.controller.exception.NextFilmException;
import com.next.zhn.film.controller.exception.ParamErrorException;
import com.next.zhn.film.controller.user.vo.EnrollUserVO;
import com.next.zhn.film.controller.user.vo.UserInfoVO;
import com.next.zhn.film.service.common.exception.CommonServiceException;
import com.next.zhn.film.service.user.UserServiceAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/user/")
@Api("用户模块相关的api")
public class UserController {

    @Autowired
    private UserServiceAPI userServiceAPI;
    @ApiOperation(value = "用户名验证", notes = "用户名验证")
    @ApiImplicitParam(name = "username",
            value = "需要验证的用户名", paramType = "query", required = true, dataType = "string")
    @RequestMapping(value = "check", method = RequestMethod.POST)
    public BaseResponseVO checkUser(String username) throws CommonServiceException, NextFilmException {

        if (ToolUtils.isEmpty(username)) {
            throw new NextFilmException(1, "用户名不能为空！");
        }

        boolean hasUserName = userServiceAPI.checkUserName(username);

        if (hasUserName) {
            return BaseResponseVO.serviceFailed("用户名已存在！");
        } else {
            return BaseResponseVO.success();
        }
    }

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @ApiImplicitParam(name = "enrollUserVO",
            value = "用户注册信息", paramType = "query", required = true, dataType = "Object")
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public BaseResponseVO register(@RequestBody EnrollUserVO enrollUserVO) throws CommonServiceException, ParamErrorException {

        enrollUserVO.checkParam();

        userServiceAPI.userEnroll(enrollUserVO);

        return BaseResponseVO.success();
    }

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @RequestMapping(value = "getUserInfo", method = RequestMethod.GET)
    public BaseResponseVO getUserInfo() throws CommonServiceException, ParamErrorException {

        String userid = TraceUtil.getUserId();

        UserInfoVO userInfo = userServiceAPI.getUserInfo(userid);

        userInfo.checkParam();

        return BaseResponseVO.success(userInfo);
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @ApiImplicitParam(name = "userInfo",
            value = "用户信息", paramType = "query", required = true, dataType = "Object")
    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    public BaseResponseVO updateUserInfo(@RequestBody UserInfoVO userInfo) throws CommonServiceException, ParamErrorException {

        userInfo.checkParam();

        UserInfoVO result = userServiceAPI.updateUserInfo(userInfo);

        userInfo.checkParam();

        return BaseResponseVO.success(result);
    }
    @ApiOperation(value = "用户退出", notes = "用户退出")
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public BaseResponseVO logout() throws CommonServiceException, ParamErrorException {

        return BaseResponseVO.success();
    }
}
