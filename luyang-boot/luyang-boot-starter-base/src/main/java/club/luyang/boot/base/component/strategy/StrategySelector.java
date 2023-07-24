package club.luyang.boot.base.component.strategy;

import cn.hutool.core.annotation.AnnotationUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 策略选择器
 *
 * @author lu_yang
 */
public class StrategySelector {

    private static final Map<String, StrategyHandler<?, ?>> STRATEGY_MAP = new ConcurrentHashMap<>();

    protected StrategySelector() {
    }

    protected void set(List<StrategyHandler<?, ?>> strategyHandlers) {
        strategyHandlers.forEach(handler ->
                STRATEGY_MAP.put(AnnotationUtil.getAnnotation(handler.getClass(), StrategyMark.class).value(), handler)
        );
    }

    @SuppressWarnings("unchecked")
    public static <R, T> Optional<StrategyHandler<R, T>> get(String markName) {
        return Optional.ofNullable((StrategyHandler<R, T>) STRATEGY_MAP.get(markName));
    }
}
