package club.luyang.boot.cache.config;

import club.luyang.boot.cache.config.serializer.RedisKeyStringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(RedisConfiguration.class);

    @Value("${spring.data.redis.key-prefix:}")
    private String keyPrefix;

    @Bean(name = "jsonRedisTemplate")
    @ConditionalOnClass(RedisOperations.class)
    public RedisTemplate<String, Object> jsonRedisTemplate(LettuceConnectionFactory factory) {
        RedisTemplate<String, Object> template = buildRedisTemplate(factory, RedisSerializer.json());
        logger.info("[LUYANG] |- Bean [JsonRedisTemplate] Auto Configure.");
        return template;
    }

    @Bean(name = "javaRedisTemplate")
    @ConditionalOnClass(RedisOperations.class)
    public RedisTemplate<String, Object> javaRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = buildRedisTemplate(factory, RedisSerializer.java());
        logger.info("[LUYANG] |- Bean [JavaRedisTemplate] Auto Configure.");
        return template;
    }

    private RedisTemplate<String, Object> buildRedisTemplate(RedisConnectionFactory factory, RedisSerializer<?> serializer){
        RedisKeyStringSerializer keyStringSerializer = new RedisKeyStringSerializer(keyPrefix);
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        // Key 采用String 的序列化方式
        template.setKeySerializer(keyStringSerializer);
        // hash 的 key 也采用 String 的序列化方式
        template.setHashKeySerializer(keyStringSerializer);
        // value 序列化方式采用 jackson
        template.setValueSerializer(serializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(serializer);
        template.afterPropertiesSet();
        return template;
    }
}
