package club.luyang.framework.cache.config.props;

import java.util.HashMap;
import java.util.Map;

/**
 * J2Cache Caffeine 配置属性
 *
 * @author lu_yang
 */
public class J2CacheCaffeineProperties {

	/**
	 * Caffeine缓存region配置<br/>
	 * 格式如下（目前仅支持配置设置cache中最多缓存对象数量及过期时间）：
	 * [regionName] = size, xxxx[s|m|h|d]
	 */
	private Map<String, String> region = new HashMap<>();

	public Map<String, String> getRegion() {
		return region;
	}

	public void setRegion(Map<String, String> region) {
		this.region = region;
	}
}
