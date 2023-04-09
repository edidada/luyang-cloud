package club.luyang.platform.uac.account.enums;

import club.luyang.framework.base.IBaseEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * 账号类型
 *
 * @author lu_yang
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AccountTypeEnum implements IBaseEnum {

	MOBILE_NUM(1, "手机号"),
	MAILBOX_NUM(2, "邮箱号"),
	JOB_NUM(3, "工号");

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
