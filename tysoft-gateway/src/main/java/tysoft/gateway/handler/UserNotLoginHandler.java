package tysoft.gateway.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tysoft.util.response.ServerResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 用户未登录处理类
 * @Author: Hxx
 * @Date: 2021/12/19
 **/
@Component
public class UserNotLoginHandler implements AuthenticationEntryPoint {
    /**
     * 用户未登录返回结果
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        ServerResponse.createResponseEnumJson(response, ServerResponse.notLogin());
    }
}