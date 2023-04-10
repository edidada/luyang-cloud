package club.luyang.platform.uac.account;

import cn.hutool.crypto.digest.BCrypt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author lu_yang
 */
@Service
@RequiredArgsConstructor
public class AccountService {

	private final AccountRepository accountRepository;

	public void addAccount(Long userId, String jobNum) {
		AccountEntity entity = new AccountEntity();
		entity.setUserId(userId);
		entity.setAccount(jobNum);
		entity.setCredential(BCrypt.hashpw("123456", BCrypt.gensalt(12)));
		entity.insert();
	}
}
