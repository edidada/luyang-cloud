package club.luyang.framework.cache.helper;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * Redis 助手类
 *
 * @author lu_yang
 */
public class RedisHelper {

	private RedisTemplate<String, Object> redisTemplate;

	public RedisHelper(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
}
