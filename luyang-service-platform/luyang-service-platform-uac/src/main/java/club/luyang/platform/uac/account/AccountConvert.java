package club.luyang.platform.uac.account;

import club.luyang.platform.uac.account.bean.CreateAccountParam;
import club.luyang.platform.uac.account.constant.AccountConstant;
import club.luyang.platform.uac.account.constant.enums.AccountStatusEnum;
import club.luyang.platform.uac.account.repository.AccountEntity;
import cn.dev33.satoken.secure.BCrypt;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lu_yang
 */
@Mapper
public interface AccountConvert {

    AccountConvert INSTANCE = Mappers.getMapper(AccountConvert.class);

    default AccountEntity toEntity(CreateAccountParam param) {
        AccountEntity entity = new AccountEntity();
        String salt = BCrypt.gensalt();
        entity.setUserId(param.userId());
        entity.setIdentifier(param.identifier());
        entity.setSalt(salt);
        entity.setIdentityType(param.identityType());
        entity.setCredentialChangeDate(null);
        entity.setCredential(BCrypt.hashpw(AccountConstant.DEFAULT_CREDENTIAL, salt));
        entity.setStatus(AccountStatusEnum.UNACTIVATED);
        return entity;
    }
}
