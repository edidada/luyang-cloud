package club.luyang.platform.uac.account;

import club.luyang.framework.mybatisplus.base.BaseEntity;
import club.luyang.platform.uac.account.enums.AccountSourceEnum;
import club.luyang.platform.uac.account.enums.AccountStatusEnum;
import club.luyang.platform.uac.account.enums.AccountTypeEnum;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author lu_yang
 */
@Getter
@Setter
@TableName("uac_account")
public class AccountEntity extends BaseEntity<AccountEntity> {

	private Long userId;
	private String account;
	private String credential;
	private AccountTypeEnum accountType;
	private AccountSourceEnum accountSource;
	private AccountStatusEnum accountStatus;
	private LocalDate credentialChangeDate;
}
