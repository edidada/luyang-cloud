package club.luyang.framework.redisson.factory;

import club.luyang.framework.redisson.config.props.RedissonProperties;
import club.luyang.framework.redisson.context.RedissonConfigContext;
import club.luyang.framework.redisson.enums.ConnectionTypeEnum;
import club.luyang.framework.redisson.strategy.ClusterRedissonConfig;
import club.luyang.framework.redisson.strategy.StandaloneRedissonConfig;
import org.redisson.config.Config;
import org.springframework.util.Assert;

/**
 * @author lu_yang
 */
public class RedissonConfigFactory {

    private static RedissonConfigFactory factory;

    public static RedissonConfigFactory getInstance() {
        if (null == factory) {
            factory = new RedissonConfigFactory();
        }

        return factory;
    }

    /**
     * 根据连接类型获取对应连接方式的配置 基于策略模式
     *
     * @param redissonProperties redisson配置
     * @return org.redisson.config.Config
     * @author lu_yang
     */
    public Config createConfig(RedissonProperties redissonProperties) {

        Assert.notNull(redissonProperties.getType(), () -> "请配置Redisson连接类型");

        ConnectionTypeEnum connectionType = ConnectionTypeEnum.getEnumByCode(redissonProperties.getType());

        // 声明配置上下文
        var redissonConfigContext = switch (connectionType) {
            case STANDALONE -> new RedissonConfigContext(new StandaloneRedissonConfig());
            case CLUSTER -> new RedissonConfigContext(new ClusterRedissonConfig());
            default -> throw new IllegalArgumentException("Redisson 连接类型不匹配！当前连接方式:" + connectionType.getCode());
        };

        return redissonConfigContext.buildRedissonConfig(redissonProperties);
    }
}
