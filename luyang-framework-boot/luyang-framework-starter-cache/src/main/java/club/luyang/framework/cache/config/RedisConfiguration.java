package club.luyang.framework.cache.config;

import club.luyang.framework.cache.helper.RedisHelper;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis 配置
 *
 * @author lu_yang
 */
@SuppressWarnings("all")
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(RedisAutoConfiguration.class)
public class RedisConfiguration {

	@Bean(name = "redisTemplate")
	@ConditionalOnClass(RedisOperations.class)
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		// Key 采用String 的序列化方式
		template.setKeySerializer(RedisSerializer.string());
		// hash 的 key 也采用 String 的序列化方式
		template.setHashKeySerializer(RedisSerializer.string());
		// value 序列化方式采用 jackson
		template.setValueSerializer(RedisSerializer.json());
		// hash的value序列化方式采用jackson
		template.setHashValueSerializer(RedisSerializer.json());
		template.afterPropertiesSet();

		return template;
	}

	@Bean
	@ConditionalOnBean(name = "redisTemplate")
	public RedisHelper redisHelper(RedisTemplate<String, Object> redisTemplate) {
		return new RedisHelper(redisTemplate);
	}
}
