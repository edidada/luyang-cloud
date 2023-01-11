package club.luyang.framework.cache.config.props;

import net.oschina.j2cache.cache.support.redis.SpringRedisProvider;

/**
 * J2Cache 二级缓存配置属性
 *
 * @author lu_yang
 */
public class J2CacheL2Properties {

	/**
	 * L2 缓存provider名称（redis | lettuce | readonly-redis | memcached）<br/>
	 * 注：默认SpringBoot Redis自定义实现SpringRedisProvider
	 */
	private String providerClass = SpringRedisProvider.class.getName();

	/**
	 * 二级缓存序列化格式（fst（推荐） | kryo | json | fastjson | java（默认） | fse | 自定义classname）
	 */
	private String serialization = "fastjson";

	/**
	 * 二级缓存配置属性前缀（若使用Spring Data Redis则此处可为空）
	 */
	private String configSection;

	/**
	 * Redis配置
	 */
	private J2CacheRedisProperties redis = new J2CacheRedisProperties();

	public String getProviderClass() {
		return providerClass;
	}

	public void setProviderClass(String providerClass) {
		this.providerClass = providerClass;
	}

	public String getSerialization() {
		return serialization;
	}

	public void setSerialization(String serialization) {
		this.serialization = serialization;
	}

	public String getConfigSection() {
		return configSection;
	}

	public void setConfigSection(String configSection) {
		this.configSection = configSection;
	}

	public J2CacheRedisProperties getRedis() {
		return redis;
	}

	public void setRedis(J2CacheRedisProperties redis) {
		this.redis = redis;
	}
}
