package club.luyang.framework.base.util.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * 校验工具类
 *
 * @author lu_yang
 */
@UtilityClass
public class ValidationUtil {

	private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

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
