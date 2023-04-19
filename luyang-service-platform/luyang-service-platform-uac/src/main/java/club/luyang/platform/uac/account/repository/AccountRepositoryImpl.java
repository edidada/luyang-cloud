package club.luyang.platform.uac.account.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * @author lu_yang
 */
@Repository
public class AccountRepositoryImpl extends ServiceImpl<AccountMapper, AccountEntity> implements AccountRepository, IService<AccountEntity> {
}
