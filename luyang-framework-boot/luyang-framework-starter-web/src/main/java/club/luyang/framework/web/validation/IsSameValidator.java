package club.luyang.framework.web.validation;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Arrays;

/**
 * 字段值相同验证器
 *
 * @author lu_yang
 */
public class IsSameValidator implements ConstraintValidator<IsSame, Object> {

    private String[] fields;

    @Override
    public void initialize(IsSame constraintAnnotation) {
        this.fields = constraintAnnotation.fields();
    }

    @Override
    public boolean isValid(Object bean, ConstraintValidatorContext context) {
        BeanWrapperImpl wrapper = new BeanWrapperImpl(bean);
        int length = fields.length;
        for (int i = 0; i < length; i++) {
            if (i == length - 1) {
                return true;
            }

            Object obj = wrapper.getPropertyValue(fields[i]);
            Object obj1 = wrapper.getPropertyValue(fields[i + 1]);
            if (ObjectUtil.notEqual(obj, obj1)) {
                context.disableDefaultConstraintViolation();
                String message = context.getDefaultConstraintMessageTemplate();
                context.buildConstraintViolationWithTemplate(CharSequenceUtil.format(message, Arrays.toString(this.fields))).addConstraintViolation();
                return false;
            }
        }

        return true;
    }
}
