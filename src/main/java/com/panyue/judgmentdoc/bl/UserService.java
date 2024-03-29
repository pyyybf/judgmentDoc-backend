package com.panyue.judgmentdoc.bl;

import com.panyue.judgmentdoc.exception.FileException;
import com.panyue.judgmentdoc.exception.LoginException;
import com.panyue.judgmentdoc.exception.PasswordException;
import com.panyue.judgmentdoc.exception.RegisterException;
import com.panyue.judgmentdoc.po.User;
import com.panyue.judgmentdoc.vo.UserVO;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 根据用户id更改头像
     *
     * @param userId 用户id
     * @param file   头像文件
     * @return int 是否成功更改
     * @throws FileException 文件异常
     */
    int updateAvatarById(Long userId, MultipartFile file) throws FileException;

    /**
     * 根据用户id更改用户信息
     *
     * @param userId 用户id
     * @param user   用户信息
     * @return int 是否成功更改
     */
    int updateUserInfoById(Long userId, User user);

    /**
     * 根据用户id更新密码
     *
     * @param userId      用户id
     * @param password    原密码
     * @param newPassword 新密码
     * @return int 是否成功更改
     * @throws PasswordException 密码异常
     */
    int updatePasswordById(Long userId, String password, String newPassword) throws PasswordException;

}
