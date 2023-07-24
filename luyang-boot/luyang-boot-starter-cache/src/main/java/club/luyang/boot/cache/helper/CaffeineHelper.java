package club.luyang.boot.cache.helper;

import org.springframework.cache.Cache;
import org.springframework.cache.caffeine.CaffeineCacheManager;

/**
 * Caffeine 助手类
 *
 * @author lu_yang
 */
public class CaffeineHelper {

    private final CaffeineCacheManager caffeineCacheManager;

    public CaffeineHelper(CaffeineCacheManager caffeineCacheManager) {
        this.caffeineCacheManager = caffeineCacheManager;
    }

    public Cache getCache(String name) {
        return caffeineCacheManager.getCache(name);
    }

    public void put(String cacheName, String key, Object value) {
        Cache cache = this.getCache(cacheName);
        cache.put(key, value);
    }

    public <T> T get(String cacheName, String key) {
        Cache cache = this.getCache(cacheName);
        Cache.ValueWrapper valueWrapper = cache.get(key);
        if (null == valueWrapper) {
            return null;
        }
        return (T) valueWrapper.get();
    }
}
