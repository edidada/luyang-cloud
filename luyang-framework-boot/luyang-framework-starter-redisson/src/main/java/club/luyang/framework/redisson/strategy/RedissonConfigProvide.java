package club.luyang.framework.redisson.strategy;

import club.luyang.framework.redisson.config.props.RedissonProperties;
import org.redisson.config.Config;

/**
 * Redisson 环境构建接口
 *
 * @author lu_yang
 */
public interface RedissonConfigProvide {

	/**
	 * 构建 Redisson 配置环境
	 *
	 * @param redissonProperties redisson 配置属性
	 * @return Config
	 * @author lu_yang
	 */
	public Config buildRedissonConfig(RedissonProperties redissonProperties);

	/**
	 * Redis 地址连接前缀
	 */
	String REDIS_CONNECTION_PREFIX = "redis://";
}
