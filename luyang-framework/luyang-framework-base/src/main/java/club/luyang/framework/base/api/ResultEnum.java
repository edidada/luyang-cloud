package club.luyang.framework.base.api;

import club.luyang.framework.base.IBaseEnum;

/**
 * 通用响应码
 *
 * @author lu_yang
 */
public enum ResultEnum implements IBaseEnum {

	/** 成功 */
	SUCCESS("0", "success"),
	/** 失败 */
	FAILURE("-1", "failure"),
	/** 请求未授权 */
	UN_AUTHORIZED("401", "请求未授权"),
	/** 404 没找到请求 */
	NOT_FOUND("404", "服务未找到"),
	/** 消息不能读取 */
	MSG_NOT_READABLE("400", "消息不能读取"),
	/** 不支持当前请求方法 */
	METHOD_NOT_SUPPORTED("405", "不支持当前请求方法"),
	/** 不支持当前媒体类型 */
	MEDIA_TYPE_NOT_SUPPORTED("415", "不支持当前媒体类型"),
	/** 请求被拒绝 */
	REQ_REJECT("403", "请求被拒绝"),
	/** 服务器异常 */
	INTERNAL_SERVER_ERROR("500", "服务器异常"),

	/** 参数异常 */
	PARAM_EXCEPTION("1001001", "参数异常"),
	/** 缺失参数 */
	PARAM_MISS("1001002", "缺失参数"),
	/** 参数类型错误 */
	PARAM_TYPE_ERROR("1001003", "参数类型错误"),
	/** 参数绑定错误 */
	PARAM_BIND_ERROR("1001004", "参数绑定错误"),
	/** 参数校验失败 */
	PARAM_VALID_ERROR("1001005", "参数校验失败"),

	/** 数据异常 */
	DATA_EXCEPTION("101101", "数据异常"),
	/** 数据已存在 */
	DATA_EXIST("101102", "数据已存在"),
	/** 数据未找到 */
	DATA_NOT_FOUND("101103", "数据未找到"),
	/** 数据不完整 */
	DATA_INCOMPLETE("101104", "数据不完整"),
	/** 数据格式错误 */
	DATA_FORMAT_ERROR("101105", "数据格式错误");

	private final String code;
	private final String message;

	ResultEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
