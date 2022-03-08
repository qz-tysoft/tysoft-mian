package tysoft.util.jwt;

import com.alibaba.fastjson.JSON;
import com.tysoft.entity.system.UserModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @Description: JWT工具类
 * @Author: Hxx
 * @Date: 2020/4/28
 **/
@Slf4j
public class JWTToken {

    /**
     * 生成token
     *
     * @param userModel 自定义的用户对象
     * @return String
     */
    public static String createAccessToken(UserModel userModel) {
        // 登陆成功生成JWT
        System.out.println("失效时间:" + TokenConstants.EXPIRE_TIME_DATE);
        System.out.println("失效时间Long:" + TokenConstants.EXPIRE_TIME_LONG);
        String token = Jwts.builder()
                // 放入用户名和用户ID
                .setId(userModel.getUserId() + "")
                // 主题
                .setSubject(userModel.getAccount())
                // 签发时间
                .setIssuedAt(new Date())
                // 签发者xx
                .setIssuer(TokenConstants.SING_NAME)
                // 自定义属性 放入用户拥有权限
                .claim(TokenConstants.AUTH_FIELD, JSON.toJSONString(userModel.getAuthorities()))
                .setExpiration(TokenConstants.EXPIRE_TIME_DATE)
                // 签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, TokenConstants.SING_KEY)
                .compact();
        return token;
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public static String checkToken(String token){

        return null;
    }
}
