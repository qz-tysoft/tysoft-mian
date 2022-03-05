package tysoft.auth.handler;

import com.tysoft.entity.system.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import tysoft.auth.redis.RedisUtil;
import tysoft.util.jwt.JWTToken;
import tysoft.util.jwt.TokenConstants;
import tysoft.util.response.ServerResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 登录成功处理类
 * @Author: Hxx
 * @Date: 2021/12/19
 **/
@Slf4j
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    RedisUtil redisUtil;
    /**
     * 登录成功返回结果
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 组装JWT
        UserModel userModel = (UserModel) authentication.getPrincipal();
        // redis设置过期时间
        redisUtil.set(userModel.getUserId(),JWTToken.createAccessToken(userModel));
        redisUtil.expire(userModel.getUserId(),TokenConstants.EXPIRE_TIME_LONG);
        ServerResponse.createResponseEnumJson(response, ServerResponse.createBySuccess(JWTToken.createAccessToken(userModel)));
    }
}
