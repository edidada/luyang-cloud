package club.luyang.boot.base.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 有效JSON
 *
 * @author lu_yang
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(IsJson.List.class)
@Constraint(validatedBy = IsJsonValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
public @interface IsJson {

    /**
     * 提示消息
     */
    String message() default "无效JSON格式";

    /**
     * 是否允许为空 true允许
     */
    boolean allowEmpty() default false;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        IsJson[] value();
    }
}
