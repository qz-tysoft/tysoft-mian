package com.tysoft.api.system;


import com.tysoft.entity.system.UserModel;

/**
 * @author hxx
 * 用户实现服务层
 */
public interface UserService {

    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名称
     * @return UserModel
     */
    UserModel selectUserModelByUserName(String userName);
}
