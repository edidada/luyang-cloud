package club.luyang.platform.auth.granter.branch;

import club.luyang.platform.auth.granter.ITokenGranter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author lu_yang
 */
@Component
public class PasswordTokenGranter implements ITokenGranter {

	private static final Logger logger = LoggerFactory.getLogger(PasswordTokenGranter.class);

	public static final String GRANT_TYPE = "password";

	@Override
	public void grant() {
		logger.info("密码登录");
	}
}
