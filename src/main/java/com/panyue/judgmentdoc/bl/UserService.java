package com.panyue.judgmentdoc.bl;

import com.panyue.judgmentdoc.vo.ResponseVO;

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
     * @return ResponseVO
     */
    ResponseVO login(String username, String password);

}
