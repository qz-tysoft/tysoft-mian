package tysoft.gateway.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tysoft.util.response.ServerResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 暂无权限处理类
 * @Author: Hxx
 * @Date: 2021/12/19
 **/
@Component
public class UserNotPermissionHandler implements AccessDeniedHandler {
    /**
     * 暂无权限返回结果
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) {
        ServerResponse.createResponseEnumJson(response, ServerResponse.noAccessPermissions());
    }
}