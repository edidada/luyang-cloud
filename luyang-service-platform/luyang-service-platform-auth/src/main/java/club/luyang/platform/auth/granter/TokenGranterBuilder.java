package club.luyang.platform.auth.granter;

import club.luyang.boot.base.util.SpringUtil;
import club.luyang.platform.auth.granter.branch.PasswordTokenGranter;
import club.luyang.platform.auth.granter.branch.SmsTokenGranter;
import cn.hutool.core.lang.Assert;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lu_yang
 */
@UtilityClass
public class TokenGranterBuilder {

    private static final Map<String, ITokenGranter> GRANTER_POOL = new ConcurrentHashMap<>();

    static {
        GRANTER_POOL.put(PasswordTokenGranter.GRANT_TYPE, SpringUtil.getBean(PasswordTokenGranter.class));
        GRANTER_POOL.put(SmsTokenGranter.GRANT_TYPE, SpringUtil.getBean(SmsTokenGranter.class));
    }

    public static ITokenGranter getGranter(String grantType) {
        ITokenGranter tokenGranter = GRANTER_POOL.getOrDefault(grantType, SpringUtil.getBean(PasswordTokenGranter.class));
        Assert.notNull(tokenGranter, "no grantType was found");
        return tokenGranter;
    }
}
