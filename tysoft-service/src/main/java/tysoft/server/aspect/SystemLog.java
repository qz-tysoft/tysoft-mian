package tysoft.server.aspect;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义日志注解切面
 * @author hxx
 * @Date 2021/12/22
 */
@Target(ElementType.METHOD) // 方法注解
@Retention(RetentionPolicy.RUNTIME) // 运行时可见
public @interface SystemLog {
    // 保存方法
    String targetType() default "";
    // 备注
    String remark() default "";
}
