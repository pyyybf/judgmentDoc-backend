package com.panyue.judgmentdoc.bl;

import com.panyue.judgmentdoc.exception.LoginException;
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

}
