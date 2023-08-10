package club.luyang.platform.uac.user;

import club.luyang.boot.base.util.SpringUtil;
import club.luyang.platform.uac.user.repository.UserRepository;
import cn.hutool.core.bean.BeanUtil;
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
}
