package com.panyue.judgmentdoc.bl;

import com.panyue.judgmentdoc.exception.LoginException;
import com.panyue.judgmentdoc.exception.RegisterException;
import com.panyue.judgmentdoc.po.User;
import com.panyue.judgmentdoc.vo.UserVO;

/**
 * @author: panyue
 * @create: 2022-04-07
 */
public interface UserService {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return User
     * @throws LoginException 登录异常
     */
    UserVO login(String username, String password) throws LoginException;

    /**
     * 注册
     *
     * @param user 新用户
     * @return Long 新用户id
     * @throws RegisterException 注册异常
     */
    Long register(User user) throws RegisterException;

    /**
     * 根据用户id获取用户信息
     *
     * @param userId 用户id
     * @return User 用户信息
     */
    User getUserInfoById(Long userId);

}
