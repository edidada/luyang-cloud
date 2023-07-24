package club.luyang.boot.web.validation;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.text.CharSequenceUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * JSON格式验证器
 *
 * @author lu_yang
 */
public class IsEmailValidator implements ConstraintValidator<IsEmail, String> {

    private IsEmail annotation;

    @Override
    public void initialize(IsEmail constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String val, ConstraintValidatorContext constraintValidatorContext) {

        // 允许为空且值为null 则放行
        if (annotation.allowEmpty() && CharSequenceUtil.isBlank(val)) {
            return true;
        }

        return Validator.isEmail(val);
    }
}
