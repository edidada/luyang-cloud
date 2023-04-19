package club.luyang.platform.uac.account;

import club.luyang.platform.uac.account.repository.AccountEntity;
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

	public void addAccount(Long userId, String identifier) {
		AccountEntity entity = new AccountEntity();
		entity.setUserId(userId);
		entity.setIdentifier(identifier);
		entity.setCredential("123456");
		entity.insert();
		// 发送邮件
	}
}
