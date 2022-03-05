package tysoft.auth.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    RedisUtil redisUtil;


    @RequestMapping("/test")
    @ResponseBody
    public String test(String key, String value){
        redisUtil.set(key,value);
        return (String) redisUtil.get(key);
    }
}
