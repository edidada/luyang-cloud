package club.luyang.framework.cache.config.props;

import net.oschina.j2cache.cache.support.redis.SpringRedisPubSubPolicy;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

/**
 * J2Cache 配置属性
 *
 * @author lu_yang
 */
@ConfigurationProperties(prefix = J2CacheProperties.PREFIX)
public class J2CacheProperties {

	public static final String PREFIX = "luyang.j2cache";

	/**
	 * 是否开启spring cache缓存,注意:开启后需要添加spring.cache.type=GENERIC,将缓存类型设置为GENERIC
	 */
	private Boolean openSpringCache = false;

	/**
	 * 缓存清除模式
	 * <ul>
	 *     <li>active:主动清除，二级缓存过期主动通知各节点清除，优点在于所有节点可以同时收到缓存清除</li>
	 *     <li>passive:被动清除，一级缓存过期进行通知各节点清除一二级缓存，</li>
	 *     <li>blend:两种模式一起运作，对于各个节点缓存准确以及及时性要求高的可以使用，正常用前两种模式中一个就可</li>
	 * </ul>
	 */
	private String cacheCleanMode = "passive";

	/**
	 * 是否缓存null对象（默认false）<br/>
	 * 注：同时设置J2Cache和Spring Cache
	 */
	private Boolean defaultCacheNullObject = false;

	/**
	 * 缓存变更广播方式（jgroups | redis | lettuce | rabbitmq | rocketmq | none | 自定义class）<br/>
	 * 注：默认SpringBoot Redis自定义实现SpringRedisPubSubPolicy
	 */
	private String broadcast = SpringRedisPubSubPolicy.class.getName();

	/**
	 * L1级缓存配置
	 */
	private J2CacheL1Properties l1 = new J2CacheL1Properties();

	/**
	 * L2缓存配置
	 */
	private J2CacheL2Properties l2 = new J2CacheL2Properties();

	/**
	 * 兼容原J2Cache属性配置
	 */
	private Properties properties = new Properties();

	public Boolean getOpenSpringCache() {
		return openSpringCache;
	}

	public void setOpenSpringCache(Boolean openSpringCache) {
		this.openSpringCache = openSpringCache;
	}

	public String getCacheCleanMode() {
		return cacheCleanMode;
	}

	public void setCacheCleanMode(String cacheCleanMode) {
		this.cacheCleanMode = cacheCleanMode;
	}

	public Boolean getDefaultCacheNullObject() {
		return defaultCacheNullObject;
	}

	public void setDefaultCacheNullObject(Boolean defaultCacheNullObject) {
		this.defaultCacheNullObject = defaultCacheNullObject;
	}

	public String getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(String broadcast) {
		this.broadcast = broadcast;
	}

	public J2CacheL1Properties getL1() {
		return l1;
	}

	public void setL1(J2CacheL1Properties l1) {
		this.l1 = l1;
	}

	public J2CacheL2Properties getL2() {
		return l2;
	}

	public void setL2(J2CacheL2Properties l2) {
		this.l2 = l2;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
