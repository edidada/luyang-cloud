package club.luyang.platform.uac.user.repository;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

/**
 * @author lu_yang
 */
@Validated
public interface UserRepository {

	UserEntity findByJobNum(@NotBlank String jobNum);
	UserEntity findByEmail(@NotBlank String email);
}
