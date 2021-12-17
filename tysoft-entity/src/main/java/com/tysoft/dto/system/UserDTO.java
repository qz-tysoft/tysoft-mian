package com.tysoft.dto.system;

import lombok.Data;

/**
 * @author hxx
 * 用户传输层对象
 */
@Data
public class UserDTO {
    /**
     * id
     */
    private String id;

    /**
     * 名字
     */
    private String name;

    /**
     * 账号
     */
    private String loginName;

    /**
     * 密码
     */
    private String loginPsw;

    /**
     * 人员状态
     */
    private Integer state;

    /**
     * 电话
     */
    private String phone;
}
