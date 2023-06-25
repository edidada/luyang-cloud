package club.luyang.platform.uac.account.repository;

import club.luyang.framework.mybatisplus.base.BaseEntity;
import club.luyang.platform.uac.account.constant.enums.AccountStatusEnum;
import club.luyang.platform.uac.account.constant.enums.IdentityTypeEnum;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


/**
 * @author lu_yang
 */
@Getter
@Setter
@TableName("uac_account")
public class AccountEntity extends BaseEntity<AccountEntity> {

    private Long userId;
    private String identifier;
    private String credential;
    private String salt;
    private LocalDate credentialChangeDate;
    private IdentityTypeEnum identityType;
    private AccountStatusEnum status;
}
