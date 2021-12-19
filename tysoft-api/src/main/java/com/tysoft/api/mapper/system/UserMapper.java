package com.tysoft.api.mapper.system;

import com.tysoft.entity.system.UserModel;
import org.springframework.stereotype.Repository;

/**
 * @author hxx
 */
@Repository
public interface  UserMapper {

    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名称
     * @return UserModel
     */
    UserModel selectUserModelByUserName(String userName);
}
