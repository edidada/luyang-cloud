package club.luyang.framework.cache.config;

import club.luyang.framework.cache.config.props.J2CacheProperties;
import club.luyang.framework.cache.support.util.SpringJ2CacheConfigUtil;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.J2Cache;
import net.oschina.j2cache.J2CacheBuilder;
import net.oschina.j2cache.J2CacheConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * J2Cache 配置
 *
 * @author lu_yang
 */
@ConditionalOnClass(J2Cache.class)
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({J2CacheProperties.class})
public class J2CacheConfiguration {

	private final RedisProperties redisProperties;

	public J2CacheConfiguration(RedisProperties redisProperties) {
		this.redisProperties = redisProperties;
	}

	/**
	 * 转换SpringBoot配置为J2Cache底层配置
	 *
	 * @param j2CacheProperties J2Cache 配置属性
	 * @return J2CacheConfig
	 * @author lu_yang
	 */
	@Bean
	public J2CacheConfig j2CacheConfig(J2CacheProperties j2CacheProperties) {
		//覆盖设置redis database（以Spring Data Redis为准）
		j2CacheProperties.getL2().getRedis().setDatabase(this.redisProperties.getDatabase());
		//转换SpringBoot配置为J2Cache底层配置
		return SpringJ2CacheConfigUtil.initFromConfig(j2CacheProperties);
	}

	/**
	 * J2Cache核心类CacheChannel
	 *
	 * @param j2CacheConfig J2Cache配置
	 * @return CacheChannel
	 * @author lu_yang
	 */
	@Bean
	@DependsOn({"springUtil", "j2CacheConfig"})
	public CacheChannel cacheChannel(J2CacheConfig j2CacheConfig) {
		J2CacheBuilder builder = J2CacheBuilder.init(j2CacheConfig);
		return builder.getChannel();
	}
}
