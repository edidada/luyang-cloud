package club.luyang.framework.base.util.ability;

import com.alibaba.ttl.TransmittableThreadLocal;
import java.util.HashMap;
import java.util.Map;

/**
 * 能力上下文
 *
 * @author lu_yang
 */
public class AbilityContext {

    private static final ThreadLocal<Map<String, Object>> CONTEXT = new TransmittableThreadLocal<>();

    private AbilityContext() {
    }

    /**
     * 初始化上下文
     *
     * @author lu_yang
     */
    protected static void init() {
        var con = CONTEXT.get();
        if (null == con) {
            CONTEXT.set(new HashMap<>(8));
            return;
        }

        CONTEXT.get().clear();
    }

    /**
     * 清理上下文
     *
     * @author lu_yang
     */
    protected static void clear() {
        CONTEXT.remove();
    }

    /**
     * 获取上下文内容
     *
     * @param key 键
     * @return R 值
     * @author lu_yang
     */
    @SuppressWarnings("unchecked")
    public static <R> R getValue(String key) {
        var con = CONTEXT.get();
        return null == con ? null : (R) con.get(key);
    }

    /**
     * 设置上下文参数
     *
     * @param key   键
     * @param value 值
     * @author lu_yang
     */
    public static void putValue(String key, Object value) {
        var con = CONTEXT.get();
        if (null == con) {
            CONTEXT.set(new HashMap<>(8));
            con = CONTEXT.get();
        }

        con.put(key, value);
    }

}
