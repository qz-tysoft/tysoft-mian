package tysoft.server.security;


import com.tysoft.entity.system.User;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 自定义权限注解验证
 * @Author: hxx
 * @Date: 2020/4/28
 **/

@Component
public class UserPermissionEvaluator implements PermissionEvaluator {



    public UserPermissionEvaluator() {
    }

    /**
     * hasPermission鉴权方法
     * 这里仅仅判断PreAuthorize注解中的permission
     * 实际中可以根据业务需求设计数据库通过targetUrl和permission做更复杂鉴权
     *
     * @param authentication 用户身份
     * @param targetUrl      请求路径
     * @param permission     请求路径权限
     * @return boolean 是否通过
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        // 获取用户信息
        User userModel = (User) authentication.getPrincipal();
        // 权限对比先放空
        return userModel.getIsPermission();
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
