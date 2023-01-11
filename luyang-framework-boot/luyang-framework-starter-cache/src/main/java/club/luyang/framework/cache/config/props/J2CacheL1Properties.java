package club.luyang.framework.cache.config.props;

/**
 * J2Cache 一级缓存配置属性
 *
 * @author lu_yang
 */
public class J2CacheL1Properties {

	/**
	 * L1 缓存provider名称（caffeine | ehcache | ehcache3）
	 */
	private String providerClass = "caffeine";

	/**
	 * Caffeine缓存配置
	 */
	private J2CacheCaffeineProperties caffeine = new J2CacheCaffeineProperties();

	public String getProviderClass() {
		return providerClass;
	}

	public void setProviderClass(String providerClass) {
		this.providerClass = providerClass;
	}

	public J2CacheCaffeineProperties getCaffeine() {
		return caffeine;
	}

	public void setCaffeine(J2CacheCaffeineProperties caffeine) {
		this.caffeine = caffeine;
	}
}
