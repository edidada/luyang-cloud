package club.luyang.platform.uac.account;

import club.luyang.platform.uac.account.repository.AccountRepository;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.extra.spring.SpringUtil;
import lombok.experimental.UtilityClass;

/**
 * @author lu_yang
 */
@UtilityClass
public class AccountTool {

    static AccountRepository accountRepository;

    private static AccountRepository getAccountRepository() {
        return BeanUtil.isNotEmpty(accountRepository) ? accountRepository : SpringUtil.getBean(AccountRepository.class);
    }

    static void checkAccountUnique(String identifier) {
        Assert.isFalse(getAccountRepository().hasAccount(identifier));
    }
}
