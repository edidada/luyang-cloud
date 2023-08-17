package club.luyang.boot.base.api;


import club.luyang.boot.base.IBaseEnum;

/**
 * 通用响应码
 *
 * @author lu_yang
 */
public enum ResultEnum implements IBaseEnum {

    /** 成功 */
    SUCCESS(0, "success"),
    /** 失败 */
    FAILURE(-1, "failure"),

    INTERNAL_SERVER_ERROR(500, "系统异常"),

    PARAM_MISS(400, "参数缺失"),
    PARAM_TYPE_ERROR(400, "参数类型错误"),
    PARAM_BIND_ERROR(400, "参数绑定错误"),
    PARAM_VALID_ERROR(400, "参数校验失败"),
    MSG_NOT_READABLE(400, "无法解析请求体"),

    REQ_REJECT(403, "请求被拒绝"),
    NOT_FOUND(404, "请求的资源不存在或已删除"),
    METHOD_NOT_SUPPORTED(405, "请求方法不被允许或不适用于请求的资源"),
    MEDIA_TYPE_NOT_SUPPORTED(415, "不支持请求中提交的媒体类型")
    ;

    private final int code;
    private final String desc;

    ResultEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
