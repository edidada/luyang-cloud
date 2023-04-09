package club.luyang.platform.uac.user.request;

import club.luyang.framework.base.validation.IsEmail;
import club.luyang.framework.base.validation.IsEnum;
import club.luyang.platform.uac.user.enums.UserGenderEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

/**
 * @author lu_yang
 */
@Value
public class AddUserRequest {

	@NotBlank
	@Length(max = 16)
	String jobNum;

	@NotBlank
	@Length(max = 128)
	String name;

	@IsEmail
	String email;

	@IsEnum(value = UserGenderEnum.class)
	Integer userGender;

	@Length(max = 255)
	String remark;
}
