package club.luyang.platform.uac.account.enums;

import club.luyang.framework.base.IBaseEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * @author lu_yang
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AccountStatusEnum implements IBaseEnum {

	ACTIVATE(1, "未激活"),
	UNACTIVATED(2, "激活");

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
