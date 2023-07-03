package club.luyang.framework.base.api;

import club.luyang.framework.base.IBaseEnum;

import java.io.Serial;
import java.io.Serializable;

/**
 * API 响应包装
 *
 * @author lu_yang
 */
public class Result<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 3083005836439673480L;

    /**
     * 状态码
     */
    private int code;

    /**
     * 状态码描述
     */
    private String message;

    /**
     * 状态标识
     */
    private boolean success;

    /**
     * 承载数据
     */
    private T data;

    private Result() {
    }

    private Result(IBaseEnum baseEnum) {
        this(baseEnum, baseEnum.getMessage(), null);
    }

    private Result(IBaseEnum baseEnum, String message) {
        this(baseEnum, message, null);
    }

    private Result(IBaseEnum baseEnum, String message, T data) {
        this(baseEnum.getCode(), message, data);
    }

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.success = ResultEnum.SUCCESS.getCode() == code;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> failure(String message) {
        return new Result<>(ResultEnum.FAILURE, message);
    }

    public static <T> Result<T> failure(int code, String message) {
        return new Result<>(code, message, null);
    }

    public static <T> Result<T> failure(IBaseEnum baseEnum) {
        return new Result<>(baseEnum);
    }

    public static <T> Result<T> failure(IBaseEnum baseEnum, String message) {
        return new Result<>(baseEnum, message);
    }

    public static <T> Result<T> condition(boolean flag) {
        return flag ? success() : failure(ResultEnum.FAILURE);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        final StringBuilder build = new StringBuilder("{");
        build.append("\"code\":").append(code).append(",");
        if (null != message) {
            build.append("\"message\":\"").append(message).append("\",");
        }
        build.append("\"success\":").append(success).append(",");
        if (null != data) {
            build.append("\"data\":").append(data).append(",");
        }
        if (build.lastIndexOf(",") != -1) {
            build.deleteCharAt(build.lastIndexOf(","));
        }
        build.append('}');
        return build.toString();
    }
}
