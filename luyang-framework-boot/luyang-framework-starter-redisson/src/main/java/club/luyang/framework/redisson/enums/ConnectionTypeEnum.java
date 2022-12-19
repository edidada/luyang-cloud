package club.luyang.framework.redisson.enums;

import java.util.Arrays;

/**
 * Redisson 连接方式
 *
 * @author lu_yang
 */
public enum ConnectionTypeEnum {

	/**
	 * 单节点
	 */
	STANDALONE("standalone"),
	/**
	 * 哨兵
	 */
	SENTINEL("sentinel"),
	/**
	 * 集群
	 */
	CLUSTER("cluster"),
	/**
	 * 主从
	 */
	MASTER_SLAVE("masterslave");

	private final String code;

	ConnectionTypeEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	/**
	 * 根据code找枚举
	 *
	 * @param code 连接类型code
	 * @return ConnectionTypeEnum 连接类型枚举
	 * @author lu_yang
	 */
	public static ConnectionTypeEnum getEnumByCode(String code) {
		return
			Arrays
				.stream(ConnectionTypeEnum.values())
				.filter(v -> v.getCode().equals(code))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Redisson连接类型 [" + code + "] 不存在"));
	}
}
