package club.luyang.platform.uac.user.constant.enums;

import club.luyang.boot.base.IBaseEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * 性别
 *
 * @author lu_yang
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum GenderEnum implements IBaseEnum {
    MEN(1, "男"),
    WOMEN(2, "女"),
    SECRECY(3, "保密");

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
