package tysoft.server.control.system;

import com.tysoft.api.system.UserService;
import com.tysoft.entity.system.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tysoft.server.aspect.SystemLog;
import tysoft.server.BaseControl;
import tysoft.util.jwt.JWTToken;
import tysoft.util.jwt.TokenConstants;
import tysoft.util.response.ServerResponse;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author hxx
 */
@Api(description = "用户控制层")
@RestController
@RequestMapping("/user")
public class UserController extends BaseControl {

    @Resource
    UserService userService;

    @PostMapping(value = "list")
    //@PreAuthorize("hasPermission(null ,'system_manage')")
    @SystemLog(targetType = "/user/list", remark = "查询用户列表")
    @ApiOperation(value = "用户列表")
    public void userList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("request1111" + request.getParameter("username"));
        System.out.println("request1111" + request.getHeader(TokenConstants.TOKEN_DATA_FIELD));
        ServerResponse.createResponseEnumJson(response, ServerResponse.createBySuccessMessage(request, userService.selectUserModelByUserName("187123456789")));
    }

}
