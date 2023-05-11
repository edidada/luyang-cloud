package club.luyang.platform.auth.granter.branch;

import club.luyang.platform.auth.granter.ITokenGranter;
import org.springframework.stereotype.Component;

/**
 * @author lu_yang
 */
@Component
public class PasswordTokenGranter implements ITokenGranter {
	@Override
	public void grant() {
		System.out.println("密码登录");
	}
}
