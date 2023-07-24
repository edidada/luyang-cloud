package club.luyang.boot.base.api;


import club.luyang.boot.base.IBaseEnum;

/**
 * 通用响应码
 *
 * @author lu_yang
 */
public enum ResultEnum implements IBaseEnum {

    SUCCESS(0, "success"),
    FAILURE(-1, "failure"),
    UN_AUTHORIZED(401, "请求未授权"),
    NOT_FOUND(404, "服务未找到"),
    MSG_NOT_READABLE(400, "消息不能读取"),
    METHOD_NOT_SUPPORTED(405, "不支持当前请求方法"),
    MEDIA_TYPE_NOT_SUPPORTED(415, "不支持当前媒体类型"),
    REQ_REJECT(403, "请求被拒绝"),
    INTERNAL_SERVER_ERROR(500, "服务器异常");

    private final int code;
    private final String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
