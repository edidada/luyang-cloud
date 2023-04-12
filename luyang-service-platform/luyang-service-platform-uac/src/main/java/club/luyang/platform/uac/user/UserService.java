package club.luyang.platform.uac.user;

import club.luyang.framework.base.util.validation.ValidationUtil;
import club.luyang.platform.uac.account.AccountService;
import club.luyang.platform.uac.user.request.AddUserRequest;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author lu_yang
 */
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final AccountService accountService;

	public void addUser(AddUserRequest request) {
		ValidationUtil.valid(request);
		checkJobNumUnique(request.jobNum());
		checkEmailUnique(request.email());
		long userId = IdUtil.getSnowflakeNextId();
		UserConvert.INSTANCE.toEntity(request, userId).insert();
		accountService.addAccount(userId, request.email());
	}

	private void checkJobNumUnique(String jobNum){
		Assert.isNull(userRepository.findByJobNum(jobNum));
	}

	private void checkEmailUnique(String email){
		Assert.isNull(userRepository.findByEmail(email));
	}
}
