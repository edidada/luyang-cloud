package club.luyang.framework.redisson.config;

import club.luyang.framework.redisson.RedissonManager;
import club.luyang.framework.redisson.config.props.RedissonProperties;
import club.luyang.framework.redisson.helper.RedissonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Redisson 配置
 *
 * @author lu_yang
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonAutoConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(RedissonAutoConfiguration.class);

	@Bean
	@ConditionalOnMissingBean
	public RedissonManager redissonManager(RedissonProperties redissonProperties) {
		RedissonManager redissonManager = null;
		try {
			redissonManager = new RedissonManager(redissonProperties);
			logger.info("RedissonManager组装完毕,当前连接方式:{}", redissonProperties.getType());
		} catch (Exception e) {
			logger.error("Redisson 初始化失败", e);
		}

		return redissonManager;
	}

	@Bean
	@ConditionalOnMissingBean
	@DependsOn({"redissonManager"})
	@ConditionalOnBean(RedissonManager.class)
	public RedissonHelper redissonLock(RedissonManager redissonManager) {
		var redissonLock = new RedissonHelper();
		redissonLock.setRedissonManager(redissonManager);
		logger.info("RedissonLock 构建完成");
		return redissonLock;
	}
}
