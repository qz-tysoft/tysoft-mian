package tysoft.util.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tysoft.vo.system.GateWayVO;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import tysoft.util.jwt.TokenConstants;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * @Description: 服务返回封装
 * @Author: hxx
 * * @Date: 2021/12/17
 **/

// 保证序列化json的时候,如果对象为null,则不会转化为json
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ServerResponse<T> implements Serializable {

    private Integer status;
    private String msg;
    private String url;
    private String refreshToken;
    private T data;


    private ServerResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse(String refreshToken, Integer status, String msg, T data) {
        this.data = data;
        this.status = status;
        //this.msg = msg;
        this.refreshToken = refreshToken;
    }

    private ServerResponse(Integer status, String msg, T data) {
        this.data = data;
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse(String refreshToken, Integer status, String msg) {
        this.data = data;
        this.status = status;
        this.msg = msg;
    }

    // 使该对象不在json序列化中
    @JsonIgnore
    public boolean isSuccess() {
        return this.status.equals(ResponseEnum.SUCCESS.getCode());
    }


    public static ServerResponse noToken() {
        return new ServerResponse(ResponseEnum.NOT_TOKEN.getCode(), ResponseEnum.NOT_TOKEN.getDesc());
    }

    public static ServerResponse tokenExpire() {
        return new ServerResponse(ResponseEnum.TOKEN_EXPIRE.getCode(), ResponseEnum.TOKEN_EXPIRE.getDesc());
    }

    public static ServerResponse tokenNoLegal() {
        return new ServerResponse(ResponseEnum.TOKEN_NO_LEGAL.getCode(), ResponseEnum.TOKEN_NO_LEGAL.getDesc());
    }

    public static ServerResponse createBySuccessMessage(String msg) {
        return new ServerResponse(ResponseEnum.SUCCESS.getCode(), msg);
    }

    public static ServerResponse createBySuccess(String msg) {
        return new ServerResponse(ResponseEnum.SUCCESS.getCode(), msg);
    }

    public static <T> ServerResponse<T> createBySuccessMessage(HttpServletRequest request, T data) {
        return getServerResponse(getRefreshToken(request), ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc(), data);
    }

    public static <T> ServerResponse<T> createByErrorMessage(HttpServletRequest request, T data) {
        return new ServerResponse(ResponseEnum.ERROR.getCode(), ResponseEnum.ERROR.getDesc());
    }

    /**
     * 未登录
     *
     * @return ServerResponse
     */
    public static ServerResponse notLogin() {
        return new ServerResponse(ResponseEnum.NOT_LOGIN.getCode(), ResponseEnum.NOT_LOGIN.getDesc());
    }

    /**
     * 用户名或密码错误
     *
     * @return ServerResponse
     */
    public static ServerResponse userNameOrPasswordError() {
        return new ServerResponse(ResponseEnum.USERNAME_PASSWORD_ERROR.getCode(), ResponseEnum.USERNAME_PASSWORD_ERROR.getDesc());
    }

    /**
     * 用户冻结
     *
     * @return ServerResponse
     */
    public static ServerResponse userLock() {
        return new ServerResponse(ResponseEnum.USER_LOCK.getCode(), ResponseEnum.USER_LOCK.getDesc());
    }


    /**
     * 使用response输出JSON
     *
     * @param serverResponse 返回响应
     */
    public static void createResponseEnumJson(ServletResponse response, ServerResponse serverResponse) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSON.toJSONString(serverResponse));
        } catch (Exception e) {
//            log.error("【JSON输出异常】" + e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    public static String getRefreshToken(HttpServletRequest request) {
        GateWayVO userToken = JSON.parseObject(request.getHeader(TokenConstants.TOKEN_DATA_FIELD), GateWayVO.class);
        //JSONObject tokenData = JSON.parseObject(request.getHeader(TokenConstants.TOKEN_DATA_FIELD));
        if (userToken != null) {
            return userToken.getRefreshToken();
        }
        return null;
    }


    public static <T> ServerResponse<T> getServerResponse(String refreshToken, Integer status, String msg, T data) {
        if (refreshToken == null) {
            if (data == null) {
                return new ServerResponse(status, msg);
            } else {
                return new ServerResponse(status, msg, data);
            }
        } else {
            if (data == null) {
                return new ServerResponse(refreshToken, status, msg);
            } else {
                return new ServerResponse(refreshToken, status, msg, data);
            }
        }
    }


}
