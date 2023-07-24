package club.luyang.boot.base.component.strategy;

import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 策略模式标记
 * 如实现类标记该注解，则无需在使用@Service等
 *
 * @author lu_yang
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface StrategyMark {

    /**
     * 策略名，根据该名称选择对应实现
     */
    String value();

    /**
     * 策略描述信息
     */
    String desc() default "";
}
