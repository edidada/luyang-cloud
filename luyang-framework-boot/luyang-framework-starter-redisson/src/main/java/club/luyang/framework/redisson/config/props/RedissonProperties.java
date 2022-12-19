package club.luyang.framework.redisson.config.props;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lu_yang
 */
@ConfigurationProperties(prefix = "spring.data.redis")
public class RedissonProperties extends RedisProperties {

	/**
	 * 连接类型，支持 standalone-单机节点，sentinel-哨兵，cluster-集群，master-slave-主从
	 */
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
