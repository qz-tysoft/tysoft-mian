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
    public static final Date EXPIRE_TIME_DATE =  new Date(System.currentTimeMillis() + 24 * 3600000);

    public static final Long EXPIRE_TIME_LONG =  System.currentTimeMillis() + 24 * 3600000;


    /**
     * 权限字段
     */
    public static final String AUTH_FIELD = "authorities";



}
