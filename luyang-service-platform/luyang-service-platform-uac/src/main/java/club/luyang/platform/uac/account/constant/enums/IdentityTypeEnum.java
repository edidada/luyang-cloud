package club.luyang.platform.uac.account.constant.enums;

import club.luyang.framework.base.IBaseEnum;
import club.luyang.framework.base.api.ResultEnum;
import club.luyang.framework.base.exception.DataException;
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

	EMAIL(1, "邮箱号"),
	MOBILE(2, "手机号");

	@EnumValue
	private final int code;
	private final String message;

	@Override
	public int getCode() {
		return this.code;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	public static IdentityTypeEnum getByVal(String identityVal) {

		if (Validator.isEmail(identityVal)) {
			return IdentityTypeEnum.EMAIL;
		}

		if (Validator.isMobile(identityVal)) {
			return IdentityTypeEnum.MOBILE;
		}

		throw new DataException(ResultEnum.DATA_NOT_FOUND, "识别码格式有误");
	}
}
