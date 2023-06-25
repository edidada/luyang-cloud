package club.luyang.framework.base.validation;

import club.luyang.framework.base.util.json.JsonUtil;
import cn.hutool.core.text.CharSequenceUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * JSON格式验证器
 *
 * @author lu_yang
 */
public class IsJsonValidator implements ConstraintValidator<IsJson, String> {

    private IsJson annotation;

    @Override
    public void initialize(IsJson constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String val, ConstraintValidatorContext constraintValidatorContext) {

        // 允许为空且值为null 则放行
        if (annotation.allowEmpty() && CharSequenceUtil.isBlank(val)) {
            return true;
        }

        return JsonUtil.isJson(val);
    }
}
