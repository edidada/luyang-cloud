package club.luyang.platform.uac.user;

import club.luyang.framework.web.validation.ValidationUtil;
import club.luyang.platform.uac.account.AccountService;
import club.luyang.platform.uac.account.bean.CreateAccountParam;
import club.luyang.platform.uac.account.constant.enums.IdentityTypeEnum;
import club.luyang.platform.uac.dept.DeptService;
import club.luyang.platform.uac.user.request.CreateUserRequest;
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
    private final DeptService deptService;

    @Transactional
    public void createUser(CreateUserRequest request) {

        ValidationUtil.valid(request);

        long userId = IdUtil.getSnowflakeNextId();
        accountService.createAccount(new CreateAccountParam(userId, request.email(), IdentityTypeEnum.EMAIL));
        deptService.addUserToDept(userId, request.deptIds());
        UserConvert.INSTANCE.toEntity(request, userId).insert();
    }
}
