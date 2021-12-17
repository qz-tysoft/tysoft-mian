package tysoft.server.security;

import com.tysoft.api.system.UserService;
import com.tysoft.entity.system.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 自定义登录验证
 * @Author: hxx
 * @Date: 2021/12/17
 **/

@Component
public class UserLoginProvider implements AuthenticationProvider {

    final UserService userService;

    public UserLoginProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取表单输入中返回的用户名
        String userName = (String) authentication.getPrincipal();
        // 获取表单中输入的密码
        String password = (String) authentication.getCredentials();
        // 查询用户是否存在
        User userModel = userService.findUserByUserName(userName);
        if (userModel == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 我们还要判断密码是否正确，这里我们的密码使用BCryptPasswordEncoder进行加密的
//        if (!new BCryptPasswordEncoder().matches(password, userModel.getPassword())) {
//            throw new BadCredentialsException("密码不正确");
//        }
        // 还可以加一些其他信息的判断，比如用户账号已停用等判断
        if (userModel.getState() != 0) {
            throw new LockedException("该用户已被冻结");
        }
        // 角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("测试用户"));
        userModel.setAuthorities(authorities);
        // 进行登录
        return new UsernamePasswordAuthenticationToken(userModel, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}