package club.luyang.platform.auth.granter.branch;

import club.luyang.platform.auth.granter.ITokenGranter;
import org.springframework.stereotype.Component;

/**
 * @author lu_yang
 */
@Component
public class SmsTokenGranter implements ITokenGranter {
	@Override
	public void grant() {
		System.out.println("短信登录");
	}
}
