package club.luyang.framework.cache;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * Redis 助手类
 *
 * @author lu_yang
 */
public class RedisService {

	private RedisTemplate<String, Object> redisTemplate;

	public RedisService(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
}
