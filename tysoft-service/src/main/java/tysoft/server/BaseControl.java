package tysoft.server;

import java.util.UUID;

/**
 * @author hxx
 * 基础父类
 */
public class BaseControl {

    /**
     * 获取UUID
     * @return
     */
    public String getUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }

}
