package tysoft.auth.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import tysoft.auth.redis.RedisUtil;
import tysoft.util.response.ServerResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 用户登出类
 * @Author: Hxx
 * @Date: 2021/12/19
 **/
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    RedisUtil redisUtil;
    /**
     * 用户登出返回结果
     * 这里应该让前端清除掉Token
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        SecurityContextHolder.clearContext();
        // redis清除token
        redisUtil.del(request.getParameter("userId"));
        ServerResponse.createResponseEnumJson(response, ServerResponse.createBySuccessMessage("登出成功"));
    }
}