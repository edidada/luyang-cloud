package club.luyang.boot.cache.helper;

import club.luyang.boot.base.util.SpringUtil;
import cn.hutool.core.text.CharSequenceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Redis 助手类
 *
 * @author lu_yang
 */
@SuppressWarnings("all")
public class RedisHelper {

    private static final Logger logger = LoggerFactory.getLogger(RedisHelper.class);

    private static RedisTemplate<String, Object> redisTemplate;

    public static RedisTemplate<String, Object> getRedisTemplate() {
        if (null == redisTemplate) {
            redisTemplate = SpringUtil.getBean("redisTemplate");
        }

        return redisTemplate;
    }

    public static <T> void set(String key, T value) {
        if (CharSequenceUtil.isBlank(key)) {
            return;
        }

        getRedisTemplate().opsForValue().set(key, value);
    }

    public static <T> void set(String key, T value, long time, TimeUnit timeUnit) {
        if (CharSequenceUtil.isBlank(key)) {
            return;
        }

        if (time > 0) {
            getRedisTemplate().opsForValue().set(key, value, time, timeUnit);
            return;
        }

        getRedisTemplate().opsForValue().set(key, value);
    }

    public static <T> T get(String key) {
        if (CharSequenceUtil.isBlank(key)) {
            return null;
        }

        return (T) getRedisTemplate().opsForValue().get(key);
    }
}
