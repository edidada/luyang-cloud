package club.luyang.platform.uac.account.enums;

import club.luyang.framework.base.IBaseEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * 账号来源
 *
 * @author lu_yang
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AccountSourceEnum implements IBaseEnum {

	INSIDE(1, "内部系统");

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
