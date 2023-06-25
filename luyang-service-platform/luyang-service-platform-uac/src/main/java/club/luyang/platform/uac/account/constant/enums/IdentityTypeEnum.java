package club.luyang.platform.uac.account.constant.enums;

import club.luyang.framework.base.IBaseEnum;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * 识别码类型(账号)
 *
 * @author lu_yang
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum IdentityTypeEnum implements IBaseEnum {

    EMAIL(1, "邮箱号"){
        @Override
        public void checkAccountNorm(String val) {
            Assert.isTrue(Validator.isEmail(val));
        }
    },

    MOBILE(2, "手机号"){
        @Override
        public void checkAccountNorm(String val) {
            // TODO document why this method is empty
        }
    };

    @EnumValue
    private final int code;
    private final String message;
    public abstract void checkAccountNorm(String identity);

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
