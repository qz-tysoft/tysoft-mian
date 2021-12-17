package tysoft.server.control.system;

import com.tysoft.api.system.UserService;
import com.tysoft.entity.system.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tysoft.server.BaseControl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * @author hxx
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseControl {

    @Resource
    UserService userService;


    @RequestMapping(value="test", method = {RequestMethod.POST})
    public String findUserById(HttpServletRequest request) {
        System.out.println("1111");
        String resultId = "4028209a6951e172016951e2b9070000";

        User resultUser = userService.findUserById(resultId);
        System.out.println("user:" + resultUser);
        return null;
    }

}
