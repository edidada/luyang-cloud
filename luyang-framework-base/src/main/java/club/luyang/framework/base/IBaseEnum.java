package club.luyang.framework.base;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 业务枚举基类接口
 *
 * @author lu_yang
 */
public interface IBaseEnum extends Serializable {


    /**
     * 获取状态码
     *
     * @return int 状态码
     * @author lu_yang
     */
    int getCode();

    /**
     * 获取状态码描述
     *
     * @return String 状态码描述
     * @author lu_yang
     */
    String getMessage();

    /**
     * 根据Code码匹配对应枚举 <br/>
     *
     * <pre class="code">
     *     ResultEnum SUCCESS_ENUM = IBaseEnum.getByCode(ResultEnum.class, 200);
     * </pre>
     *
     * @param tClass 待匹配枚举类
     * @param code   需匹配枚举的Code码
     * @return T
     * @author lu_yang
     */
    static <T extends IBaseEnum> T getByCode(Class<T> tClass, int code) {
        return Arrays.stream(tClass.getEnumConstants())
            .filter(v -> v.getCode() == code)
            .findFirst()
            .orElse(null);
    }

    /**
     * 根据Code匹配对应枚举 <br/>
     *
     * <pre class="code">
     *     ResultEnum SUCCESS_ENUM = IBaseEnum.getByCode(ResultEnum.class, 999, () -> new IllegalArgumentException(""));
     * </pre>
     *
     * @param tClass        待匹配枚举类
     * @param code          需匹配枚举的Code码
     * @param errorSupplier 错误抛出异常附带的消息生产接口
     * @return T
     * @author lu_yang
     */
    static <T extends IBaseEnum, X extends Throwable> T getByCode(Class<T> tClass, int code, Supplier<X> errorSupplier) throws X {
        T anyEnum = getByCode(tClass, code);
        if (null == anyEnum) {
            throw errorSupplier.get();
        }

        return anyEnum;
    }

    /**
     * 根据枚举属性值匹配对应的枚举 <br/>
     *
     * <pre class="code">
     *     ResultEnum SUCCESS_ENUM = IBaseEnum.getByProperty(ResultEnum.class, ResultEnum::getMessage, "success");
     * </pre>
     *
     * @param tClass   待匹配枚举类
     * @param property 待匹配枚举属性
     * @param value    需匹配枚举的属性值
     * @return T
     * @author lu_yang
     */
    static <T extends IBaseEnum, P> T getByProperty(Class<T> tClass, Function<T, P> property, P value) {
        return Arrays.stream(tClass.getEnumConstants())
            .filter(v -> Objects.equals(property.apply(v), value))
            .findFirst()
            .orElse(null);
    }

    /**
     * 根据枚举属性值匹配对应的枚举 <br/>
     *
     * <pre class="code">
     *     ResultEnum SUCCESS_ENUM = IBaseEnum.getByProperty(ResultEnum.class, ResultEnum::getMessage, "success", () -> new IllegalArgumentException(""));
     * </pre>
     *
     * @param tClass        待匹配枚举类
     * @param property      待匹配枚举属性
     * @param value         需匹配枚举的属性值
     * @param errorSupplier 错误抛出异常附带的消息生产接口
     * @return T
     * @author lu_yang
     */
    static <T extends IBaseEnum, P, X extends Throwable> T getByProperty(Class<T> tClass, Function<T, P> property, P value, Supplier<X> errorSupplier) throws X {
        T anyProperty = getByProperty(tClass, property, value);
        if (null == anyProperty) {
            throw errorSupplier.get();
        }

        return anyProperty;
    }
}
