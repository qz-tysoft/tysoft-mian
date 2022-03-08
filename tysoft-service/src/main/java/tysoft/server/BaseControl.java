package tysoft.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import tysoft.util.jwt.TokenConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author hxx
 * 基础父类
 */
public class BaseControl {

    @Value("${jasypt.encryptor.password}")
    private String salt;

    @Autowired
    StringEncryptor stringEncryptor;

    /**
     * 获取UUID
     * @return
     */
    public String getUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }

    /**
     * 获取加密后的密码
     * @param passWord
     * @return
     */
    public String getEncodePassWord(String passWord) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(salt);
        return textEncryptor.encrypt(passWord);
    }

    protected String getUserId(HttpServletRequest request) {
        JSONObject tokenData = JSON.parseObject(request.getHeader(TokenConstants.TOKEN_DATA_FIELD));
        if (tokenData != null) {
            return (String) tokenData.get(TokenConstants.USER_ID_FIELD);
        }
        return null;
    }


    protected String getRefresh(HttpServletRequest request) {
        JSONObject tokenData = JSON.parseObject(request.getHeader(TokenConstants.TOKEN_DATA_FIELD));
        if (tokenData != null) {
            return (String) tokenData.get(TokenConstants.USER_ID_FIELD);
        }
        return null;
    }

}
