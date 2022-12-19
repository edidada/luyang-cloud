package club.luyang.framework.redisson;

import club.luyang.framework.redisson.config.props.RedissonProperties;
import club.luyang.framework.redisson.factory.RedissonConfigFactory;
import org.redisson.Redisson;

/**
 * Redisson 核心配置，初始化的redisson实例
 *
 * @author lu_yang
 */
public class RedissonManager {

	private final Redisson redisson;

	public Redisson getRedisson() {
		return this.redisson;
	}

	public RedissonManager(RedissonProperties redissonProperties) {
		var config = RedissonConfigFactory.getInstance().createConfig(redissonProperties);
		this.redisson = (Redisson) Redisson.create(config);
	}
}
