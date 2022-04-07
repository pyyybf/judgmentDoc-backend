package com.panyue.judgmentdoc.vo;

import com.panyue.judgmentdoc.po.User;

/**
 * @author: panyue
 * @create: 2022-04-07
 */
public class UserVO {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 身份
     */
    private int role;
    /**
     * 头像
     */
    private String avatar;

    public UserVO() {
    }

    public UserVO(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
        this.avatar = user.getAvatar();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
