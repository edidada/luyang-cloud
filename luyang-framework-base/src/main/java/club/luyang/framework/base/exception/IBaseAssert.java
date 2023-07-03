package club.luyang.framework.base.exception;

import club.luyang.framework.base.IBaseEnum;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

public interface IBaseAssert extends IBaseEnum {

    private BaseException baseException(String message) {
        return new BaseException(this, message);
    }

    default void isTrue(boolean expression) {
        if (!expression) {
            throw new BaseException(this);
        }
    }

    default void isTrue(boolean expression, String errorMsgTemplate, Object... params) {
        if (!expression) {
            throw baseException(StrUtil.format(errorMsgTemplate, params));
        }
    }

    default void notNull(Object obj) {
        if (ObjectUtil.isNull(obj)) {
            throw new BaseException(this);
        }
    }

    default void notNull(Object obj, String errorMsgTemplate, Object... params) {
        if (ObjectUtil.isNull(obj)) {
            throw new BaseException(StrUtil.format(errorMsgTemplate, params));
        }
    }
}
