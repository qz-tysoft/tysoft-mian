package tysoft.server;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

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

}
