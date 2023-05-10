package club.luyang.platform.uac.account;

import club.luyang.platform.uac.account.bean.CreateAccountParam;
import club.luyang.platform.uac.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author lu_yang
 */
@Service
@RequiredArgsConstructor
public class AccountService {

	private final AccountRepository accountRepository;

	public void createAccount(CreateAccountParam param) {
		// 账号格式规范校验
		param.checkAccountNorm();
		AccountTool.checkAccountUnique(param.identifier());
		AccountConvert.INSTANCE.toEntity(param).insert();
	}
}
