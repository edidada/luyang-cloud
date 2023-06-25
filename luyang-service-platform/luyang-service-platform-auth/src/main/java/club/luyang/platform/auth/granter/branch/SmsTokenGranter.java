package club.luyang.platform.auth.granter.branch;

import club.luyang.platform.auth.granter.ITokenGranter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author lu_yang
 */
@Component
public class SmsTokenGranter implements ITokenGranter {

    private static final Logger logger = LoggerFactory.getLogger(SmsTokenGranter.class);

    public static final String GRANT_TYPE = "sms";

    @Override
    public void grant() {
        logger.info("短信登录");
    }
}
