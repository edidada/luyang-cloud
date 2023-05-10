package club.luyang.platform.uac.user;

import club.luyang.framework.base.util.validation.ValidationUtil;
import club.luyang.platform.uac.account.AccountService;
import club.luyang.platform.uac.dept.DeptService;
import club.luyang.platform.uac.user.request.CreateUserRequest;
import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author lu_yang
 */
@Service
@RequiredArgsConstructor
public class UserService {

	private final AccountService accountService;
	private final DeptService deptService;

	public void createUser(CreateUserRequest request) {

		ValidationUtil.valid(request);
		UserTool.checkEmailUnique(request.email());

		long userId = IdUtil.getSnowflakeNextId();
		accountService.addAccount(userId, request.email());
		request.deptIds().forEach(item -> deptService.addUserToDept(userId, item));
		UserConvert.INSTANCE.toEntity(request, userId).insert();
	}
}
