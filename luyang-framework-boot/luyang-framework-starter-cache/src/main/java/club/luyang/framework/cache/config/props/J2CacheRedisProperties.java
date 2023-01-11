package club.luyang.framework.cache.config.props;

/**
 * J2Cache Redis 配置属性
 *
 * @author lu_yang
 */
public class J2CacheRedisProperties {

	/**
	 * 是否启用同步一级缓存的Time-To-Live超时时间到Redis TTL（true启用，false不启用则永不超时）
	 */
	private Boolean syncTtlToRedis = false;

	/**
	 * Key命名空间（前缀）
	 */
	private String namespace = "";

	/**
	 * Redis存储模式（generic | hash）
	 */
	private String storage = "generic";

	/**
	 * Redis发布订阅（Pub/Sub）对应的channel名称
	 */
	private String channel = "j2cache";

	/**
	 * Redis DB序号
	 */
	private Integer database = 0;

	public Boolean getSyncTtlToRedis() {
		return syncTtlToRedis;
	}

	public void setSyncTtlToRedis(Boolean syncTtlToRedis) {
		this.syncTtlToRedis = syncTtlToRedis;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getDatabase() {
		return database;
	}

	public void setDatabase(Integer database) {
		this.database = database;
	}
}
