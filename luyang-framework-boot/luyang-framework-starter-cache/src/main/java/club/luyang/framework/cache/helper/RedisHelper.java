package club.luyang.framework.cache.helper;

import cn.hutool.core.text.CharSequenceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Redis 助手类
 *
 * @author lu_yang
 */
@SuppressWarnings("all")
public class RedisHelper {

    private static final Logger logger = LoggerFactory.getLogger(RedisHelper.class);

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisHelper(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public <T> void set(String key, T value) {
        if (CharSequenceUtil.isBlank(key)) {
            return;
        }

        this.redisTemplate.opsForValue().set(key, value);
    }

    public <T> T get(String key) {
        if (CharSequenceUtil.isBlank(key)) {
            return null;
        }

        return (T) redisTemplate.opsForValue().get(key);
    }
}
