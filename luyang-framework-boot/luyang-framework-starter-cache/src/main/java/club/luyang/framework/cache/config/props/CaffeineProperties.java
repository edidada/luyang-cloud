package club.luyang.framework.cache.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Caffeine 配置属性
 *
 * @author lu_yang
 */
@ConfigurationProperties(prefix = CaffeineProperties.PREFIX)
public class CaffeineProperties {

	public static final String PREFIX = "spring.data.caffeine";

	/** 初始容量 */
	private Integer initialCapacity = 100;

	/** key 上限，超过则丢弃 */
	private Long maximumSize = 1000L;

	/** 是否缓存 Null 值 */
	private Boolean allowNull = Boolean.FALSE;

	/** 最后一次写入或访问后经过固定时间过期 单位:秒 */
	private Long expireAfterAccess;

	/** 最后一次写入后经过固定时间过期 单位:秒 */
	private Long expireAfterWrite;

	/** 创建缓存或者最近一次更新缓存后经过固定的时间间隔，刷新缓存 单位:秒 */
	private Long refreshAfterWrite;

	/** 是否启用 caffeine */
	private Boolean enable = Boolean.FALSE;


	public Integer getInitialCapacity() {
		return initialCapacity;
	}

	public void setInitialCapacity(Integer initialCapacity) {
		this.initialCapacity = initialCapacity;
	}

	public Long getMaximumSize() {
		return maximumSize;
	}

	public void setMaximumSize(Long maximumSize) {
		this.maximumSize = maximumSize;
	}

	public Boolean getAllowNull() {
		return allowNull;
	}

	public void setAllowNull(Boolean allowNull) {
		this.allowNull = allowNull;
	}

	public Long getExpireAfterAccess() {
		return expireAfterAccess;
	}

	public void setExpireAfterAccess(Long expireAfterAccess) {
		this.expireAfterAccess = expireAfterAccess;
	}

	public Long getExpireAfterWrite() {
		return expireAfterWrite;
	}

	public void setExpireAfterWrite(Long expireAfterWrite) {
		this.expireAfterWrite = expireAfterWrite;
	}

	public Long getRefreshAfterWrite() {
		return refreshAfterWrite;
	}

	public void setRefreshAfterWrite(Long refreshAfterWrite) {
		this.refreshAfterWrite = refreshAfterWrite;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
}
