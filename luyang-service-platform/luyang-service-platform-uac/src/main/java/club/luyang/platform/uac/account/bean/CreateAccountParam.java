package club.luyang.platform.uac.account.bean;

import club.luyang.platform.uac.account.constant.enums.IdentityTypeEnum;

/**
 * @author lu_yang
 */
public record CreateAccountParam(long userId, String identifier, IdentityTypeEnum identityType) {

	public void checkAccountNorm() {
		identityType.checkAccountNorm(identifier);
	}
}
