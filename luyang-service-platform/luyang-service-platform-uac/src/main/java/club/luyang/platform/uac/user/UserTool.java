package club.luyang.platform.uac.user;

import club.luyang.platform.uac.user.repository.UserRepository;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.extra.spring.SpringUtil;
import lombok.experimental.UtilityClass;

/**
 * @author lu_yang
 */
@UtilityClass
public class UserTool {

	static UserRepository userRepository;

	private static UserRepository getUserRepository() {
		return BeanUtil.isNotEmpty(userRepository) ? userRepository : SpringUtil.getBean(UserRepository.class);
	}

	static void checkJobNumUnique(String jobNum){
		Assert.isNull(userRepository.findByJobNum(jobNum));
	}

	static void checkEmailUnique(String email){
		Assert.isNull(userRepository.findByEmail(email));
	}

}
