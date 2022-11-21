package club.luyang.framework.validator.util;

import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * 校验工具类
 *
 * @author lu_yang
 */
public class ValidatorUtil {

	private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

	private ValidatorUtil() {
	}

	public static void valid(Object obj, Class<?>... clazz) {
		valid(VALIDATOR.validate(obj, clazz));
	}

	public static void valid(Object obj, String fieldName, Class<?>... clazz) {
		valid(VALIDATOR.validateProperty(obj, fieldName, clazz));
	}

	private static void valid(Set<ConstraintViolation<Object>> validate) {

		if (CollectionUtils.isEmpty(validate)) {
			return;
		}

		validate.forEach(v -> {
			throw new ValidationException(v.getPropertyPath() + ":" + v.getMessage());
		});
	}
}
