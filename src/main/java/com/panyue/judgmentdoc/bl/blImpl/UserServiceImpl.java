package com.panyue.judgmentdoc.bl.blImpl;

import com.panyue.judgmentdoc.bl.UserService;
import com.panyue.judgmentdoc.data.UserMapper;
import com.panyue.judgmentdoc.po.User;
import com.panyue.judgmentdoc.vo.ResponseVO;
import com.panyue.judgmentdoc.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: panyue
 * @create: 2022-04-07
 */
@Service
public class UserServiceImpl implements UserService {

    private static final String USER_NOT_EXIST = "用户不存在";
    private static final String WRONG_PWD = "密码错误";

    @Autowired
    UserMapper userMapper;

    @Override
    public ResponseVO login(String username, String password) {
        User user = userMapper.getUserByUsername(username);
        if (user != null) {  //存在该username
            if (user.getPassword().equals(password)) {  //密码正确
                return ResponseVO.buildSuccess(new UserVO(user));
            }
            return ResponseVO.buildFailure(WRONG_PWD);
        }
        return ResponseVO.buildFailure(USER_NOT_EXIST);
    }

}
