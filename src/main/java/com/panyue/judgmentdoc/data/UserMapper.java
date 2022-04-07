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

}
