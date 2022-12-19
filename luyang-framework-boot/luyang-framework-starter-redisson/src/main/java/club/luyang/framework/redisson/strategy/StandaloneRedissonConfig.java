package club.luyang.framework.redisson.strategy;

import club.luyang.framework.redisson.config.props.RedissonProperties;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 单机环境配置
 *
 * @author lu_yang
 */
public class StandaloneRedissonConfig implements RedissonConfigProvide {

	private static final Logger logger = LoggerFactory.getLogger(StandaloneRedissonConfig.class);

	@Override
	public Config buildRedissonConfig(RedissonProperties redissonProperties) {
		Config config = new Config();

		try {
			String host = redissonProperties.getHost();
			String password = redissonProperties.getPassword();

			config.useSingleServer().setAddress(RedissonConfigProvide.REDIS_CONNECTION_PREFIX.concat(host));
			config.useSingleServer().setDatabase(redissonProperties.getDatabase());
			if (StringUtils.hasText(password)) {
				config.useSingleServer().setPassword(password);
			}

			if (logger.isDebugEnabled()) {
				logger.debug("单机环境Redisson初始化配置，Redisson连接地址:{}", host);
			}
		} catch (Exception e) {
			logger.error("单机环境Redisson初始化配置异常", e);
		}

		return config;
	}
}
