package com.tysoft.entity.system;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author hxx
 * 展示层对象
 */
@Data
public class User implements Serializable {

    private String id;

    private String userName;

    private String passWord;

    private String realName;

    private Set<GrantedAuthority> authorities;

    private Boolean isPermission;

    private Integer State;

}
