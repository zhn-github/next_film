package com.next.zhn.film.service.user;

import com.next.zhn.film.controller.user.vo.EnrollUserVO;
import com.next.zhn.film.controller.user.vo.UserInfoVO;
import com.next.zhn.film.service.common.exception.CommonServiceException;

public interface UserServiceAPI {
    /**
     * 用户注册
     * @param enrollUserVO
     * @throws CommonServiceException
     */
    void userEnroll(EnrollUserVO enrollUserVO) throws CommonServiceException;

    /**
     * 检查用户名是否存在
     * @param userName
     * @return
     * @throws CommonServiceException
     */
    boolean checkUserName(String userName) throws CommonServiceException;

    /**
     * 用户名密码验证
     * @param userName
     * @param usePwd
     * @return
     * @throws CommonServiceException
     */
    boolean userAuth(String userName,String usePwd) throws CommonServiceException;


    /**
     * 获取用户信息
     * @param userId
     * @return 用户信息
     * @throws CommonServiceException
     */
    UserInfoVO getUserInfo(String userId) throws CommonServiceException;

    /**
     * 修改用户信息
     * @param userInfoVO
     * @return 用户信息
     * @throws CommonServiceException
     */
    UserInfoVO updateUserInfo(UserInfoVO userInfoVO) throws CommonServiceException;

    /**
     * 根据用户名获取用户ID
     * AuthFilter使用
     * @param userName
     * @return
     * @throws CommonServiceException
     */
    String getUuidByUsername(String userName) throws CommonServiceException;
}
