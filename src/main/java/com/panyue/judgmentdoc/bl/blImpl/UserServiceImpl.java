package com.panyue.judgmentdoc.bl.blImpl;

import com.panyue.judgmentdoc.bl.OssService;
import com.panyue.judgmentdoc.bl.UserService;
import com.panyue.judgmentdoc.data.UserMapper;
import com.panyue.judgmentdoc.exception.FileException;
import com.panyue.judgmentdoc.exception.LoginException;
import com.panyue.judgmentdoc.exception.RegisterException;
import com.panyue.judgmentdoc.po.User;
import com.panyue.judgmentdoc.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author: panyue
 * @create: 2022-04-07
 */
@Service
public class UserServiceImpl implements UserService {

    private static final String USER_NOT_EXIST = "用户不存在";
    private static final String WRONG_PWD = "密码错误";
    private static final String USERNAME_EXIST = "用户名已存在";
    private static final String EMPTY_FILE = "文件为空";

    @Value("${aliyun.oss.directory.avatars}")
    private String DIRECTORY_AVATARS;

    @Autowired
    UserMapper userMapper;

    @Autowired
    OssService ossService;

    @Override
    public UserVO login(String username, String password) throws LoginException {
        User user = userMapper.getUserByUsername(username);
        if (user != null) {  //存在该username
            if (user.getPassword().equals(password)) {  //密码正确
                return new UserVO(user);
            }
            throw new LoginException(WRONG_PWD);
        }
        throw new LoginException(USER_NOT_EXIST);
    }

    @Override
    public Long register(User user) throws RegisterException {
        try {
            return userMapper.insertUser(user);
        } catch (DuplicateKeyException e) {
            throw new RegisterException(USERNAME_EXIST);
        }
    }

    @Override
    public User getUserInfoById(Long userId) {
        return userMapper.getUserInfoById(userId);
    }

    @Override
    public int updateAvatarById(Long userId, MultipartFile file) throws FileException {
        if (file.isEmpty()) {
            throw new FileException(EMPTY_FILE);
        }
        String originalFileName = file.getOriginalFilename();
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));  //文件后缀，例：.png
        String fileName = UUID.randomUUID().toString().replace("-", "") + suffix;  //文件名：uuid去除-后加上后缀
        String avatar = ossService.upload(file, DIRECTORY_AVATARS + fileName);
        return userMapper.updateAvatarById(userId, avatar);
    }

}
