package club.luyang.platform.uac.user;

import club.luyang.framework.base.util.validation.ValidationUtil;
import club.luyang.platform.uac.account.AccountService;
import club.luyang.platform.uac.user.request.AddUserRequest;
import cn.hutool.core.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		// 校验工号唯一性
		checkJobNumUnique(request.jobNum());
		// 校验邮箱唯一性
		checkEmailUnique(request.email());
		UserEntity userEntity = UserConvert.INSTANCE.toEntity(request);
		userEntity.insert();

		accountService.addAccount(userEntity.getId(), userEntity.getEmail());
	}

	private void checkJobNumUnique(String jobNum){
		Assert.isNull(userRepository.findByJobNum(jobNum));
	}

	private void checkEmailUnique(String email){
		Assert.isNull(userRepository.findByEmail(email));
	}
}
