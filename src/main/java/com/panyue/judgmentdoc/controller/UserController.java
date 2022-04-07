package com.panyue.judgmentdoc.controller;

import com.panyue.judgmentdoc.bl.UserService;
import com.panyue.judgmentdoc.exception.FileException;
import com.panyue.judgmentdoc.exception.LoginException;
import com.panyue.judgmentdoc.exception.RegisterException;
import com.panyue.judgmentdoc.po.User;
import com.panyue.judgmentdoc.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: panyue
 * @create: 2022-04-07
 */
@RestController
@RequestMapping("/api/user")
@Api(tags = "UserController", description = "用户信息管理")
public class UserController {

    private static final String LOGIN_ERROR = "登录失败";
    private static final String REGISTER_ERROR = "注册失败";
    private static final String USER_INFO_FETCH_ERROR = "个人信息获取失败";
    private static final String UPDATE_ERROR = "更改失败";

    @Autowired
    UserService userService;

    @ApiOperation(value = "登录")
    @GetMapping("/login")
    public ResponseVO login(@RequestParam(value = "username") String username,
                            @RequestParam(value = "password") String password) {
        try {
            return ResponseVO.buildSuccess(userService.login(username, password));
        } catch (LoginException loginException) {
            return ResponseVO.buildFailure(loginException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(LOGIN_ERROR);
        }
    }

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public ResponseVO register(@RequestBody User user) {
        try {
            return ResponseVO.buildSuccess(userService.register(user));
        } catch (RegisterException registerException) {
            return ResponseVO.buildFailure(registerException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(REGISTER_ERROR);
        }
    }

    @ApiOperation(value = "获取用户个人信息")
    @GetMapping("/getUserInfoById/{userId}")
    public ResponseVO getUserInfoById(@PathVariable(value = "userId") Long userId) {
        try {
            return ResponseVO.buildSuccess(userService.getUserInfoById(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(USER_INFO_FETCH_ERROR);
        }
    }

    @ApiOperation(value = "更改用户头像")
    @PutMapping("/updateAvatarById")
    public ResponseVO updateAvatarById(@RequestParam(value = "userId") Long userId,
                                       @RequestParam(value = "file") MultipartFile file) {
        try {
            return ResponseVO.buildSuccess(userService.updateAvatarById(userId, file));
        } catch (FileException fileException) {
            return ResponseVO.buildFailure(fileException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
    }

    @ApiOperation(value = "更改用户个人信息")
    @PutMapping("/updateUserInfoById/{userId}")
    public ResponseVO updateUserInfoById(@PathVariable(value = "userId") Long userId, @RequestBody User user) {
        try {
            return ResponseVO.buildSuccess(userService.updateUserInfoById(userId, user));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
    }

}
