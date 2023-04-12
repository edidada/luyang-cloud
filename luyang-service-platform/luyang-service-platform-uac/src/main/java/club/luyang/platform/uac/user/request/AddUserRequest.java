package club.luyang.platform.uac.user.request;

import club.luyang.framework.base.validation.IsEmail;
import club.luyang.framework.base.validation.IsEnum;
import club.luyang.platform.uac.user.enums.GenderEnum;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * @author lu_yang
 */
public record AddUserRequest(
	@NotBlank
	@Length(max = 16)
	String jobNum,

	@NotBlank
	@Length(max = 128)
	String name,

	@IsEmail
	String email,

	@IsEnum(value = GenderEnum.class)
	Integer gender,

	@Length(max = 255)
	String avatar,

	@Length(max = 255)
	String remark
) {}
