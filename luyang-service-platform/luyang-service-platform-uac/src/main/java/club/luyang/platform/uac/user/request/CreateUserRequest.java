package club.luyang.platform.uac.user.request;

import club.luyang.boot.base.validation.IsEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.util.List;


/**
 * @author lu_yang
 */
public record CreateUserRequest(

    @NotBlank
    @Length(max = 128)
    String name,

    @NotNull
    @Size(min = 1)
    List<Long> deptIds,

    @IsEmail
    String email,

    // @IsEnum(value = GenderEnum.class)
    Integer gender,

    @Length(max = 255)
    String avatar,

    @Length(max = 255)
    String remark
) {}
