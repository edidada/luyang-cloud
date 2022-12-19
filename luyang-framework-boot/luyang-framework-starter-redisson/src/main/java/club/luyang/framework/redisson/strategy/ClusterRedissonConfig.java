package club.luyang.framework.redisson.strategy;

import club.luyang.framework.redisson.config.props.RedissonProperties;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 集群环境配置
 *
 * @author lu_yang
 */
public class ClusterRedissonConfig implements RedissonConfigProvide {

	private static final Logger logger = LoggerFactory.getLogger(ClusterRedissonConfig.class);

	@Override
	public Config buildRedissonConfig(RedissonProperties redissonProperties) {

		Config config = new Config();

		String password = redissonProperties.getPassword();
		RedisProperties.Cluster cluster = redissonProperties.getCluster();

		List<String> nodes = cluster.getNodes();
		nodes.forEach(v -> {
			config.useClusterServers().addNodeAddress(RedissonConfigProvide.REDIS_CONNECTION_PREFIX.concat(v));
			if (StringUtils.hasText(password)) {
				config.useClusterServers().setPassword(password);
			}
			logger.debug("集群环境Redisson初始化配置，Redisson连接地址:{}", v);
		});

		return config;
	}
}
