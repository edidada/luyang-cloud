package club.luyang.boot.base.component.mask;

/**
 * 脱敏控制
 * 不同业务场景下，需要动态控制是否脱敏
 *
 * @author lu_yang
 */
@FunctionalInterface
public interface DataMaskControl {

    /**
     * 是否需要脱敏，true脱敏处理，false不做处理
     */
    boolean needMask();
}
