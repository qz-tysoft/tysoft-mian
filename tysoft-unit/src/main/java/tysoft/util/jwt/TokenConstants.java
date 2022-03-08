package tysoft.util.jwt;

import java.util.Date;

/**
 * @author hxx
 * token  token常量类
 */
public class TokenConstants {

    /**
     * 签发者
     */
    public static final String SING_NAME = "HXX";

    /**
     * 签名密匙
     */
    public static final String SING_KEY = "JWTSecret";

    /**
     * token过期时间
     */
    public static final Date EXPIRE_TIME_DATE =  new Date(System.currentTimeMillis() + 4 * 60 * 1000);

    public static final Long EXPIRE_TIME_LONG =  System.currentTimeMillis() + 4 * 60 * 1000;


    /**
     * 权限字段
     */
    public static final String AUTH_FIELD = "authorities";

    /**
     * 用户Id字段
     */
    public static final String USER_ID_FIELD = "userId";

    /**
     *
     */
    public static final String REFRESH_TOKEN_FIELD = "refreshToken";

    /**
     * token传输字段
     */
    public static final String TOKEN_DATA_FIELD= "tokenData";



}
