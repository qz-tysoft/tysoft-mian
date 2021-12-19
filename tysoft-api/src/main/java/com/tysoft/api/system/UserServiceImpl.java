package com.tysoft.api.system;

import com.tysoft.api.mapper.system.UserMapper;
import com.tysoft.entity.system.UserModel;
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
    public UserModel selectUserModelByUserName(String userName) {
        return userMapper.selectUserModelByUserName(userName);
    }
}
