package tysoft.server.aspect;

import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.gson.Gson;
import com.tysoft.api.system.LogService;
import com.tysoft.api.system.LogServiceImpl;
import com.tysoft.entity.system.LogModel;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tysoft.util.DateUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志具体实现
 *
 * @author hxx
 * @Date 2021/12/22
 */
@Component
@Aspect
public class LogAopAspect {

    // 日志常量
    public static final String LOG_TARGET_TYPE = "targetType";
    public static final String LOG_REMARK = "remark";

    @Resource
    LogService logService;

    @Pointcut("@annotation(tysoft.server.aspect.SystemLog)")
    public void webLog() {
    }


    /**
     * 配置日志后置注解
     *
     * @param joinPoint
     * @throws Throwable
     */
    @After("webLog()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {
        LogModel saveLogModel = new LogModel();
        Long start = System.currentTimeMillis();
        // 上下文获取当前相关属性
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        saveLogModel.setIp(getClientIp(request));
        saveLogModel.setCreateTime(DateUtil.dateToStrLong(new Date(), DateUtil.DATE_FORMATE_YMDSFM));
        // 头部获取token
        String token = request.getHeader("Authorization");
        // 解析JWT
//        Claims claims = Jwts.parser()
//                .setSigningKey("JWTSecret")
//                .parseClaimsJws(token)
//                .getBody();
//        saveLogModel.setOptUserId(claims.getId());
//        saveLogModel.setOptUserName(claims.getSubject());
//        Map resultMap = getLogMark(joinPoint);
//        saveLogModel.setOptMethod((String) resultMap.get(LOG_TARGET_TYPE));
//        saveLogModel.setOptRemark((String) resultMap.get(LOG_REMARK));
//        Map<String,Object> fieldsName = getFieldsName(joinPoint);
//        // Map转Json字符串
//        saveLogModel.setParams(new Gson().toJson(fieldsName));
        logService.saveOrUpDateLog(saveLogModel);
    }


    /**
     * 获取注解日志的方法信息
     *
     * @param joinPoint
     * @return
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("rawtypes")
    private Map<String, Object> getLogMark(JoinPoint joinPoint) throws ClassNotFoundException {
        Map<String, Object> map = new HashMap<String, Object>();
        String methodName = joinPoint.getSignature().getName();
        String targetName = joinPoint.getTarget().getClass().getName();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                SystemLog logAnnotation = method.getAnnotation(SystemLog.class);
                map.put(LOG_TARGET_TYPE, logAnnotation.targetType());
                map.put(LOG_REMARK, logAnnotation.remark());
            }
        }
        return map;
    }


    /**
     * 获取客户端ip
     *
     * @param request
     * @return
     */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }


    /**
     * 获取参数列表
     *
     * @param joinPoint
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     */
    private static Map<String, Object> getFieldsName(JoinPoint joinPoint) {
        Map<String, Object> paramMap = new HashMap<>(32);
        //这一步获取到的方法有可能是代理方法也有可能是真实方法
        Method m = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //判断代理对象本身是否是连接点所在的目标对象，不是的话就要通过反射重新获取真实方法
        if (joinPoint.getThis().getClass() != joinPoint.getTarget().getClass()) {
            m = ReflectUtil.getMethod(joinPoint.getTarget().getClass(), m.getName(), m.getParameterTypes());
        }
        //通过真实方法获取该方法的参数名称
        LocalVariableTableParameterNameDiscoverer paramNames = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = paramNames.getParameterNames(m);

        //获取连接点方法运行时的入参列表
        Object[] args = joinPoint.getArgs();
        //将参数名称与入参值一一对应起来
        Map<String, Object> params = new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            if("request".equals(parameterNames[i]) || "response".equals(parameterNames[i])){
                continue;
            }
            paramMap.put(parameterNames[i], args[i]);
        }

        return paramMap;
    }

}
