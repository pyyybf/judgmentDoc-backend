package com.panyue.judgmentdoc.controller;

import com.panyue.judgmentdoc.bl.UserService;
import com.panyue.judgmentdoc.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: panyue
 * @create: 2022-04-07
 */
@RestController
@RequestMapping("/api/user")
@Api(tags = "UserController", description = "用户信息管理")
public class UserController {

    private static final String LOGIN_ERROR = "登录出错";

    @Autowired
    UserService userService;

    @ApiOperation(value = "登录")
    @GetMapping("/login")
    public ResponseVO login(@RequestParam String username,
                            @RequestParam String password) {
        try {
            return userService.login(username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(LOGIN_ERROR);
        }
    }

}
