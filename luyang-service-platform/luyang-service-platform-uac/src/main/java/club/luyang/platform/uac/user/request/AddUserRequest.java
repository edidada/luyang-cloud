package club.luyang.platform.uac.user.request;

import club.luyang.framework.base.validation.IsEmail;
import club.luyang.framework.base.validation.IsEnum;
import club.luyang.platform.uac.user.constant.enums.GenderEnum;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * @author lu_yang
 */
public record AddUserRequest(

	@NotBlank
	@Length(max = 128)
	String name,

	List<String> deptIds,

	@IsEmail
	String email,

	@IsEnum(value = GenderEnum.class)
	Integer gender,

	@Length(max = 255)
	String avatar,

	@Length(max = 255)
	String remark
) {}
