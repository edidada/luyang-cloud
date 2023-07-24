package club.luyang.boot.cache.config;

import club.luyang.boot.cache.config.serializer.RedisKeyStringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis 配置
 *
 * @author lu_yang
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(RedisAutoConfiguration.class)
public class RedisConfiguration {

    @Value("${spring.data.redis.key-prefix:}")
    private String keyPrefix;

    @Bean(name = "redisTemplate")
    @ConditionalOnClass(RedisOperations.class)
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory factory) {
        RedisKeyStringSerializer keyStringSerializer = new RedisKeyStringSerializer(keyPrefix);
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        // Key 采用String 的序列化方式
        template.setKeySerializer(keyStringSerializer);
        // hash 的 key 也采用 String 的序列化方式
        template.setHashKeySerializer(keyStringSerializer);
        // value 序列化方式采用 jackson
        template.setValueSerializer(RedisSerializer.json());
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(RedisSerializer.json());
        template.afterPropertiesSet();
        return template;
    }
}
