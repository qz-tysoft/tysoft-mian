package com.tysoft.api.system;

import com.tysoft.entity.system.User;

/**
 * @author hxx
 * 用户实现服务层
 */
public interface UserService {

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findUserById (String id);

    /**
     * 根据id账号密码查询用户
     * @param account
     * @param passWord
     * @return
     */
    User findUserByPassword (String account, String passWord);


    /**
     * 根据用户名查询用户是否存在
     * @param userName
     * @return
     */
    User findUserByUserName (String userName);
}
