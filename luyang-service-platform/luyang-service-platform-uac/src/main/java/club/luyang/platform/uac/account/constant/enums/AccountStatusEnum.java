package club.luyang.platform.uac.account.constant.enums;

import club.luyang.boot.base.IBaseEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * 账号状态
 *
 * @author lu_yang
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AccountStatusEnum implements IBaseEnum {

    UNACTIVATED(1, "未激活"),
    ACTIVATE(2, "激活");

    @EnumValue
    private final int code;
    private final String desc;

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
