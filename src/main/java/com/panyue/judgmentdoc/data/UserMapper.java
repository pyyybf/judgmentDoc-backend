package com.panyue.judgmentdoc.data;

import com.panyue.judgmentdoc.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author: panyue
 * @create: 2022-04-07
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return User
     */
    User getUserByUsername(@Param(value = "username") String username);

    /**
     * 插入新用户
     *
     * @param user 用户
     * @return Long 新增user的id
     */
    Long insertUser(User user);

    /**
     * 根据id获取用户信息
     *
     * @param id 用户id
     * @return User 用户信息（不含密码）
     */
    User getUserInfoById(@Param(value = "id") Long id);

    /**
     * 根据用户id更新头像
     *
     * @param userId 用户id
     * @param avatar 头像
     * @return int 是否成功更新
     */
    int updateAvatarById(@Param(value = "userId") Long userId, @Param(value = "avatar") String avatar);

    /**
     * 根据用户id更改用户信息
     *
     * @param user 用户信息
     * @return int 是否成功更新
     */
    int updateUserInfoById(User user);

    /**
     * 根据用户id更新密码
     *
     * @param userId   用户id
     * @param password 新密码
     * @return int 是否成功更改
     */
    int updatePasswordById(@Param(value = "userId") Long userId, @Param(value = "password") String password);

    /**
     * 根据用户id获取用户
     *
     * @param userId 用户id
     * @return User 用户
     */
    User getUserById(@Param(value = "userId") Long userId);

    /**
     * 根据用户id获取头像
     *
     * @param id 用户id
     * @return String 头像图片url
     */
    String getAvatarById(@Param(value = "id") Long id);

}
