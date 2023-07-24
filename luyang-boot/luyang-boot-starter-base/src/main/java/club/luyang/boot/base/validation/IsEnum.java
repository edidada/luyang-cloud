package club.luyang.boot.base.validation;

import club.luyang.boot.base.IBaseEnum;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 有效枚举
 *
 * @author lu_yang
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(IsEnum.List.class)
@Constraint(validatedBy = IsEnumValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
public @interface IsEnum {

    /**
     * 提示消息
     */
    String message() default "无效值";

    /**
     * 指定目标枚举类型
     */
    Class<? extends IBaseEnum> value();

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
        IsEnum[] value();
    }
}
