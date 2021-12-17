package com.tysoft.api.system;

import com.tysoft.api.BaseServiceImpl;
import com.tysoft.api.mapper.system.UserMapper;
import com.tysoft.entity.system.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hxx
 * 用户实现类
 */
@Service
@Transactional
public class UserServiceImpl  implements  UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserById(String id) {
        return userMapper.findUserById(id);
    }

    @Override
    public User findUserByPassword(String account, String passWord) {
        return null;
    }

    @Override
    public User findUserByUserName(String userName) {
        User user = new User();
        user.setUserName("test");
        user.setRealName("hxx");
        user.setPassWord("123456");
        user.setState(0);
        return user;
    }
}
