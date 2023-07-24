package club.luyang.boot.base.component.strategy;

/**
 * 策略模式处理接口
 *
 * @author lu_yang
 */
public interface StrategyHandler<R, T> {

    /**
     * 业务处理
     */
    public R handler(T t);
}
