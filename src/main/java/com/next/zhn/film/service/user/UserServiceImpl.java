package com.next.zhn.film.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.next.zhn.film.common.utils.MD5Util;
import com.next.zhn.film.common.utils.NumberValidationUtil;
import com.next.zhn.film.common.utils.ToolUtils;
import com.next.zhn.film.controller.user.vo.EnrollUserVO;
import com.next.zhn.film.controller.user.vo.UserInfoVO;
import com.next.zhn.film.dao.entity.NextUserT;
import com.next.zhn.film.dao.mapper.NextUserTMapper;
import com.next.zhn.film.service.common.exception.CommonServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserServiceAPI {

    @Autowired
    private NextUserTMapper nextUserTMapper;

    @Override
    public void userEnroll(EnrollUserVO enrollUserVO) throws CommonServiceException {
        //验证参数

        //转换数据
        NextUserT nextUserT = new NextUserT();
        BeanUtils.copyProperties(enrollUserVO, nextUserT);
        nextUserT.setUserName(enrollUserVO.getUsername());
        nextUserT.setUserPwd(MD5Util.encrypt(enrollUserVO.getPassword()));

        //添加数据
        int isSuccess = nextUserTMapper.insert(nextUserT);

        if (isSuccess != 1) {
            throw new CommonServiceException(500, "用户登陆失败！");
        }
    }

    @Override
    public boolean checkUserName(String userName) throws CommonServiceException {
        //验证参数
        if (ToolUtils.isEmpty(userName)) {
            throw new CommonServiceException(400, "请输入用户名！");
        }

        //根据用户名查询数据
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", userName);

        int count = nextUserTMapper.selectCount(queryWrapper);

        return count == 0 ? false : true;
    }

    @Override
    public boolean userAuth(String userName, String usePwd) throws CommonServiceException {
        //验证参数
        if (ToolUtils.isEmpty(userName) || ToolUtils.isEmpty(usePwd)) {
            throw new CommonServiceException(400, "用户登录失败！");
        }

        //获取用户信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", userName);

        List<NextUserT> list = nextUserTMapper.selectList(queryWrapper);

        if (list.size() == 0) {
            return false;
        } else {
            //验证密码
            String pwd = list.get(0).getUserPwd();
            usePwd = MD5Util.encrypt(usePwd);

            if (usePwd.equals(pwd)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public UserInfoVO getUserInfo(String userId) throws CommonServiceException {
        //验证参数
        if (ToolUtils.isEmpty(userId)) {
            throw new CommonServiceException(400, "获取用户信息失败！");
        }

        //查询数据
        NextUserT nextUserT = nextUserTMapper.selectById(userId);

        if (nextUserT != null && nextUserT.getUuid() != null) {
            //转换数据
            return getUserInfoVOByNextUserT(nextUserT);
        } else {
            throw new CommonServiceException(404, "用户编号为[" + userId + "]的用户不存在！");
        }
    }

    @Override
    public UserInfoVO updateUserInfo(UserInfoVO userInfoVO) throws CommonServiceException {

        if (userInfoVO != null && userInfoVO.getUuid() != null) {
            //转换数据
            NextUserT nextUserT = getNextUserTByUserInfoVO(userInfoVO);

            //更新数据
            int isSuccess = nextUserTMapper.updateById(nextUserT);

            if (isSuccess == 1) {
                return getUserInfo(nextUserT.getUuid() + "");
            } else {
                throw new CommonServiceException(500, "用户信息修改失败！");
            }
        } else {
            throw new CommonServiceException(404, "用户编号为[" + userInfoVO.getUuid() + "]的用户不存在！");
        }
    }

    @Override
    public String getUuidByUsername(String userName) throws CommonServiceException {

        QueryWrapper<NextUserT> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", userName);

        NextUserT nextUserT = nextUserTMapper.selectOne(queryWrapper);

        if (nextUserT != null && nextUserT.getUuid() != null) {
            return nextUserT.getUuid().toString();
        } else {
            throw new CommonServiceException(1, "未查到用户信息！");
        }
    }

    //---------------------------------自定义方法----------------------------------------------------------

    /**
     * NextUserT转UserInfoVO
     *
     * @param nextUserT
     * @return
     */
    private UserInfoVO getUserInfoVOByNextUserT(NextUserT nextUserT) {
        UserInfoVO userInfoVO = new UserInfoVO();

        userInfoVO.setUsername(nextUserT.getUserName());
        userInfoVO.setNickname(nextUserT.getNickName());

        userInfoVO.setBeginTime(nextUserT.getBeginTime().toEpochSecond(ZoneOffset.of("+8")));
        userInfoVO.setUpdateTime(nextUserT.getUpdateTime().toEpochSecond(ZoneOffset.of("+8")));

        userInfoVO.setLifeState(nextUserT.getLifeState() + "");

        BeanUtils.copyProperties(nextUserT, userInfoVO);

        return userInfoVO;
    }

    /**
     * UserInfoVO转NextUserT
     *
     * @param userInfoVO
     * @return
     */
    private NextUserT getNextUserTByUserInfoVO(UserInfoVO userInfoVO) throws CommonServiceException {

        NextUserT nextUserT = new NextUserT();
        nextUserT.setUserName(userInfoVO.getUsername());
        nextUserT.setNickName(userInfoVO.getNickname());

        nextUserT.setUpdateTime(LocalDateTime.now());

        if (NumberValidationUtil.isInteger(userInfoVO.getLifeState())) {
            nextUserT.setLifeState(Integer.parseInt(userInfoVO.getLifeState()));
        } else {
            throw new CommonServiceException(501, "数字格式非法！");
        }

        BeanUtils.copyProperties(userInfoVO, nextUserT);

        return nextUserT;
    }
}
