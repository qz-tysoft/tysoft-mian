
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tysoft.entity.system.UserModel;
import org.junit.Test;


public class TestFastJson {
    @Test
    public void test(){
        UserModel test = new UserModel();
        test.setUserId("11111");
        test.setAccount("2222222223");
        String testStr = JSONObject.toJSONString(test);
        System.out.println(testStr);
        UserModel userModel = JSON.parseObject(testStr,UserModel.class);
        System.out.println(userModel.getUserId());
    }
}
