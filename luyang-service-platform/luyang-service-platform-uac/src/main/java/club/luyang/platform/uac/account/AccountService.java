package club.luyang.platform.uac.account;

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
//		entity.setCredential(BCrypt.hashpw("123456", BCrypt.gensalt(12)));
		entity.insert();
	}
}
