package club.luyang.framework.cache.support.util;

import club.luyang.framework.cache.config.props.J2CacheProperties;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.text.StrPool;
import net.oschina.j2cache.J2CacheConfig;

import java.util.Properties;

/**
 * 重写 {@link net.oschina.j2cache.cache.support.util.SpringJ2CacheConfigUtil}
 *
 * @author lu_yang
 */
public class SpringJ2CacheConfigUtil {

	private static final String PREFIX_REGION = "region.";

	private SpringJ2CacheConfigUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static J2CacheConfig initFromConfig(J2CacheProperties j2CacheProperties) {
		J2CacheConfig config = new J2CacheConfig();
		config.setSerialization(j2CacheProperties.getL2().getSerialization());
		config.setBroadcast(j2CacheProperties.getBroadcast());
		config.setL1CacheName(j2CacheProperties.getL1().getProviderClass());
		config.setL2CacheName(j2CacheProperties.getL2().getProviderClass());
		config.setSyncTtlToRedis(j2CacheProperties.getL2().getRedis().getSyncTtlToRedis());
		config.setDefaultCacheNullObject(j2CacheProperties.getDefaultCacheNullObject());
		String l2ConfigSection = j2CacheProperties.getL2().getConfigSection();
		if (CharSequenceUtil.isBlank(l2ConfigSection)) {
			l2ConfigSection = config.getL2CacheName();
		}

		// 转换L1缓存属性
		matchPropsPrefixAndSet(j2CacheProperties.getProperties(), config.getL1CacheProperties(), config.getL1CacheName());
		//转换L2缓存属性
		matchPropsPrefixAndSet(j2CacheProperties.getProperties(), config.getL2CacheProperties(), l2ConfigSection);
		//转换广播属性
		matchPropsPrefixAndSet(j2CacheProperties.getProperties(), config.getBroadcastProperties(), config.getBroadcast());

		//特殊处理caffeine配置
		j2CacheProperties.getL1().getCaffeine().getRegion()
			.forEach((k, v) -> config.getL1CacheProperties().setProperty(PREFIX_REGION + k, v));

		//特殊处理L2配置
		config.getL2CacheProperties().setProperty("namespace", j2CacheProperties.getL2().getRedis().getNamespace());
		config.getL2CacheProperties().setProperty("storage", j2CacheProperties.getL2().getRedis().getStorage());
		config.getL2CacheProperties().setProperty("channel", j2CacheProperties.getL2().getRedis().getChannel());
		config.getL2CacheProperties().setProperty("database", String.valueOf(j2CacheProperties.getL2().getRedis().getDatabase()));

		return config;
	}

	/**
	 * 匹配指定前缀（且删除前缀）提取J2Cache配置属性
	 *
	 * @param properties
	 * @param destProps
	 * @param prefix
	 * @author lu_yang
	 */
	private static void matchPropsPrefixAndSet(Properties properties, Properties destProps, String prefix) {
		properties.forEach((k, v) -> {
			String key = (String) k;
			String value = String.valueOf(v);
			String prefixWithDot = prefix + StrPool.DOT;
			if (key.startsWith(prefixWithDot)) {
				destProps.setProperty(key.substring(prefixWithDot.length()), value);
			}
		});
	}
}
