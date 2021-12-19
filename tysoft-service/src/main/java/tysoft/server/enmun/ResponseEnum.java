package tysoft.server.enmun;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @Description: Response枚举
 * @Author: hxx
 * @Date: 2021/12/17
 **/
@Getter
@AllArgsConstructor
public enum ResponseEnum {

    /**
     * 响应成功
     */
    SUCCESS(200, "成功"),
    /**
     * 响应失败
     */
    ERROR(400, "失败"),
    /**
     * 用户名密码错误
     */
    USERNAME_PASSWORD_ERROR(401, "用户名或密码错误"),
    /**
     * 用户冻结
     */
    USER_LOCK(402, "用户冻结"),
    /**
     * 无对应资源权限
     */
    NO_ACCESS_PERMISSIONS(403, "无对应资源权限"),
    /**
     * 系统异常
     */
    EXCEPTION(500, "系统异常"),
    /**
     * 未登录
     */
    NOT_LOGIN(530, "未登录");

    private final Integer code;
    private final String desc;
}
