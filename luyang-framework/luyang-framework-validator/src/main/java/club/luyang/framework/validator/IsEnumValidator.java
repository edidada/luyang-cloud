package club.luyang.framework.validator;

import club.luyang.framework.base.IBaseEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 有效枚举验证器
 *
 * @author lu_yang
 */
public class IsEnumValidator implements ConstraintValidator<IsEnum, Integer> {

	private IsEnum annotation;

	@Override
	public void initialize(IsEnum constraintAnnotation) {
		this.annotation = constraintAnnotation;
	}

	@Override
	public boolean isValid(Integer val, ConstraintValidatorContext context) {
		boolean allowEmpty = annotation.allowEmpty();
		// 允许为空且值为null 则放行
		if (allowEmpty && null == val) {
			return true;
		}

		IBaseEnum[] enumConstants = annotation.value().getEnumConstants();
		for (IBaseEnum iEnum : enumConstants) {
			if (iEnum.getCode() == val) {
				return true;
			}
		}

		return false;
	}
}
