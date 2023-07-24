package club.luyang.boot.base.component.mask;

import cn.hutool.core.util.DesensitizedUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.function.Function;

/**
 * 脱敏规则
 *
 * @author lu_yang
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum DataMaskRule {

    /**
     * 证件号
     */
    ID_CARD(str -> DesensitizedUtil.idCardNum(str, 3, 4)),

    /**
     * 手机号
     */
    PHONE(DesensitizedUtil::mobilePhone),

    /**
     * 银行卡
     */
    BANK_CARD(DesensitizedUtil::bankCard),

    /**
     * 邮箱
     */
    EMAIL(DesensitizedUtil::email);

    public final Function<String, String> fun;
}
