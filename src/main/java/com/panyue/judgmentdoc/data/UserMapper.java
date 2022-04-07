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
     * 根据用户名username获取用户User
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
    Long insertUser(@Param(value = "user") User user);

    /**
     * 根据id获取用户信息
     *
     * @param id 用户id
     * @return User 用户信息（不含密码）
     */
    User getUserInfoById(@Param(value = "id") Long id);

}
