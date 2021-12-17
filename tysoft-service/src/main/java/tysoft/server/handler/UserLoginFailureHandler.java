package tysoft.server.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import tysoft.server.response.ServerResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 登录失败处理类
 * @Author: hxx
 * @Date: 2021/12/17
 **/
@Slf4j
@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {
    /**
     * 登录失败返回结果
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        // 判断异常类型
        if (exception instanceof UsernameNotFoundException) {
            log.info("【登录失败】" + exception.getMessage());
            ServerResponse.createResponseEnumJson(response, ServerResponse.userNameOrPasswordError());
        }
        if (exception instanceof LockedException) {
            log.info("【登录失败】" + exception.getMessage());
            ServerResponse.createResponseEnumJson(response, ServerResponse.userLock());
        }
        if (exception instanceof BadCredentialsException) {
            log.info("【登录失败】" + exception.getMessage());
            ServerResponse.createResponseEnumJson(response, ServerResponse.userNameOrPasswordError());
        }
        ServerResponse.createResponseEnumJson(response, ServerResponse.createByError());
    }
}
