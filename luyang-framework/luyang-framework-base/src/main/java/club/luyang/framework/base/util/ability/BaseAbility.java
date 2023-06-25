package club.luyang.framework.base.util.ability;

import org.springframework.transaction.annotation.Transactional;

/**
 * 基础能力抽象
 *
 * @author lu_yang
 */
public abstract class BaseAbility<T, R> {

    /**
     * 能力执行
     *
     * @param model 数据模型
     * @return R
     * @author lu_yang
     */
    @Transactional(rollbackFor = Exception.class)
    public R executeAbility(T model) {
        try {
            // 初始化能力上下文
            AbilityContext.init();
            // 执行前校验处理
            this.checkHandler(model);
            // 执行业务逻辑
            return execute(model);
        } finally {
            AbilityContext.clear();
        }
    }

    /**
     * 能力执行前校验
     *
     * @param model 数据模型
     * @author lu_yang
     */
    public abstract void checkHandler(T model);

    /**
     * 业务处理
     *
     * @param model 数据模型
     * @return R
     * @author lu_yang
     */
    public abstract R execute(T model);
}
