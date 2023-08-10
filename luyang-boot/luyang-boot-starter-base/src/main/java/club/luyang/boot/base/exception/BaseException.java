package club.luyang.boot.base.exception;

import club.luyang.boot.base.IBaseEnum;
import club.luyang.boot.base.api.ResultEnum;

import java.io.Serial;

/**
 * 业务异常基类
 *
 * @author lu_yang
 */
public class BaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 896984768974997385L;

    private final IBaseEnum baseEnum;

    public BaseException(String message) {
        super(message);
        this.baseEnum = ResultEnum.FAILURE;
    }

    public BaseException(IBaseEnum baseEnum) {
        super(baseEnum.getDesc());
        this.baseEnum = baseEnum;
    }

    public BaseException(IBaseEnum baseEnum, String message) {
        super(message);
        this.baseEnum = baseEnum;
    }

    public IBaseEnum getBaseEnum() {
        return baseEnum;
    }
}
