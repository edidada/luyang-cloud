package club.luyang.boot.cache.config;

import club.luyang.boot.cache.config.props.CaffeineProperties;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Caffeine 配置
 *
 * @author lu_yang
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(CaffeineProperties.class)
@ConditionalOnProperty(value = CaffeineProperties.PREFIX + ".enable", havingValue = "true", matchIfMissing = true)
public class CaffeineConfiguration {

    private final CaffeineProperties caffeineProperties;

    public CaffeineConfiguration(CaffeineProperties caffeineProperties) {
        this.caffeineProperties = caffeineProperties;
    }

    @Bean
    public CaffeineCacheManager caffeineCacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.registerCustomCache("sss", caffeineBuilder());
        caffeineCacheManager.registerCustomCache("xxx", caffeineBuilder());
        return new CaffeineCacheManager();
    }

    private Cache<Object, Object> caffeineBuilder() {
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder();
        Optional<CaffeineProperties> properties = Optional.ofNullable(this.caffeineProperties);
        properties.map(CaffeineProperties::getInitialCapacity).ifPresent(caffeine::initialCapacity);
        properties.map(CaffeineProperties::getMaximumSize).ifPresent(caffeine::maximumSize);
        properties.map(CaffeineProperties::getExpireAfterAccess).ifPresent(v -> caffeine.expireAfterAccess(v, TimeUnit.SECONDS));
        properties.map(CaffeineProperties::getExpireAfterWrite).ifPresent(v -> caffeine.expireAfterWrite(v, TimeUnit.SECONDS));
        properties.map(CaffeineProperties::getRefreshAfterWrite).ifPresent(v -> caffeine.refreshAfterWrite(v, TimeUnit.SECONDS));
        return caffeine.build();
    }
}
