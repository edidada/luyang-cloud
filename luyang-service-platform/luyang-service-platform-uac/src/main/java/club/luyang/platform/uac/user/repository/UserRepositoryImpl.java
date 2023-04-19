package club.luyang.platform.uac.user.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * @author lu_yang
 */
@Repository
public class UserRepositoryImpl extends ServiceImpl<UserMapper, UserEntity> implements UserRepository, IService<UserEntity> {


	@Override
	public UserEntity findByJobNum(String jobNum) {
		return this.lambdaQuery().eq(UserEntity::getJobNum, jobNum).one();
	}

	@Override
	public UserEntity findByEmail(String email) {
		return this.lambdaQuery().eq(UserEntity::getEmail, email).one();
	}
}
