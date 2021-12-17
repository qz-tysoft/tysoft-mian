package com.tysoft.api.mapper.system;

import com.tysoft.entity.system.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author hxx
 */
@Repository
public interface  UserMapper {

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findUserById (@Param("id") String id);

    /**
     * 根据id账号密码查询用户
     * @param account
     * @param passWord
     * @return
     */
    User findUserByPassword (String account, String passWord);
}
