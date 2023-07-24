package club.luyang.boot.base.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * @author lu_yang
 */
@UtilityClass
public class ValidationUtil {

    private static final Validator VALIDATOR;

    static {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            VALIDATOR = validatorFactory.getValidator();
        }
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
