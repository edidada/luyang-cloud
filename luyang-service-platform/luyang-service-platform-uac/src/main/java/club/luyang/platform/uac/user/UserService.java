package club.luyang.platform.uac.user;

import club.luyang.framework.base.util.validation.ValidationUtil;
import club.luyang.platform.uac.account.AccountService;
import club.luyang.platform.uac.user.request.AddUserRequest;
import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lu_yang
 */
@Service
@RequiredArgsConstructor
public class UserService {

	private final AccountService accountService;

	@Transactional
	public void addUser(AddUserRequest request) {

		ValidationUtil.valid(request);
		UserTool.checkEmailUnique(request.email());

		long userId = IdUtil.getSnowflakeNextId();
		accountService.addAccount(userId, request.email());
		UserConvert.INSTANCE.toEntity(request, userId).insert();
	}
}
