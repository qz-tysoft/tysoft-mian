package tysoft.server.handler;

import com.tysoft.entity.system.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import tysoft.server.response.ServerResponse;
import tysoft.server.security.JWTToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 登录成功处理类
 * @Author: hxx
 * @Date: 2021/12/17
 **/
@Slf4j
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * 登录成功返回结果
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 组装JWT
        User userModel = (User) authentication.getPrincipal();
        ServerResponse.createResponseEnumJson(response, ServerResponse.createBySuccess(JWTToken.createAccessToken(userModel)));
    }
}
