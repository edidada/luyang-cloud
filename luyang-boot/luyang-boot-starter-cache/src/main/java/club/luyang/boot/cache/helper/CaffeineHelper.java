package club.luyang.boot.cache.helper;

import club.luyang.boot.base.util.SpringUtil;
import org.springframework.cache.Cache;
import org.springframework.cache.caffeine.CaffeineCacheManager;

/**
 * Caffeine 助手类
 *
 * @author lu_yang
 */
public class CaffeineHelper {

    private static CaffeineCacheManager caffeineCacheManager;

    public static CaffeineCacheManager getCaffeineCacheManager() {
        if (null == caffeineCacheManager) {
            caffeineCacheManager = SpringUtil.getBean(CaffeineCacheManager.class);
        }

        return caffeineCacheManager;
    }

    public static Cache getCache(String name) {
        return getCaffeineCacheManager().getCache(name);
    }

    public static void put(String cacheName, String key, Object value) {
        Cache cache = getCache(cacheName);
        cache.put(key, value);
    }

    public static <T> T get(String cacheName, String key) {
        Cache cache = getCache(cacheName);
        Cache.ValueWrapper valueWrapper = cache.get(key);
        if (null == valueWrapper) {
            return null;
        }
        return (T) valueWrapper.get();
    }
}
