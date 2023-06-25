package club.luyang.framework.redisson.context;

import club.luyang.framework.redisson.config.props.RedissonProperties;
import club.luyang.framework.redisson.strategy.RedissonConfigProvide;
import org.redisson.config.Config;

/**
 * Redisson 配置上下文
 *
 * @author lu_yang
 */
public class RedissonConfigContext {

    private final RedissonConfigProvide redissonConfigProvide;

    public RedissonConfigContext(RedissonConfigProvide redissonConfigProvide) {
        this.redissonConfigProvide = redissonConfigProvide;
    }

    /**
     * 构建策略产出的 Redisson 配置
     *
     * @param redissonProperties redisson 配置
     * @return org.redisson.config.Config
     * @author lu_yang
     */
    public Config buildRedissonConfig(RedissonProperties redissonProperties) {
        return this.redissonConfigProvide.buildRedissonConfig(redissonProperties);
    }
}
