package club.luyang.platform.uac.account.constant.enums;

import club.luyang.framework.base.IBaseEnum;
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
	MOBILE(1, "手机号"),
	JOB_NUM(1, "工号");

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
}
