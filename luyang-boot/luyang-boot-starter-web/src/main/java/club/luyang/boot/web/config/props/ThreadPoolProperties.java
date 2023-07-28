package club.luyang.boot.web.config.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lu_yang
 */
@Getter
@Setter
@ConfigurationProperties(prefix = ThreadPoolProperties.PREFIX)
public class ThreadPoolProperties {

    public static final String PREFIX = "luyang.thread-pool";

    private String namePrefix;

    /**
     * 线程池的核心线程数。在没有设置 allowCoreThreadTimeOut 为 true 的情况下，
     * 核心线程会在线程池中一直存活，即使处于闲置状态。
     */
    private Integer corePoolSize;

    /**
     * 线程池所能容纳的最大线程数。当活动线程(核心线程+非核心线程)达到这个数值后，
     * 后续任务将会根据 RejectedExecutionHandler 来进行拒绝策略处理。
     */
    private Integer maxPoolSize;

    /**
     * 线程池中的任务队列，通过线程池的 execute() 方法提交的 Runnable
     * 对象会存储在该队列中。
     */
    private Integer queueCapacity;

    /**
     * 当任务无法被执行时(超过线程最大容量 maximum 并且 workQueue 已经被排满了)的处理策略，
     * - AbortPolicy：丢弃任务并抛出RejectedExecutionException异常
     * - DiscardPolicy：丢弃任务，但是不抛出异常。
     * - DiscardOldestPolicy：丢弃队列最前面的任务，然后重新提交被拒绝的任务
     * - CallerRunsPolicy：由调用线程（提交任务的线程）处理该任务
     */
    private String rejectedExecutionHandler;

    /**
     * 非核心线程 闲置时的超时时长。超过该时长，非核心线程就会被回收。若线程池通设置
     * 核心线程也允许 timeOut，即 allowCoreThreadTimeOut 为 true，则该时长
     * 同样会作用于核心线程，在超过aliveTime 时，核心线程也会被回收，AsyncTask
     * 配置的线程池就是这样设置的。
     */
    private Integer keepAliveSeconds;

    /**
     * 线程池在关闭时等待时间等待所有任务完成
     * 如果在等待时间内所有任务都完成了，线程池会正常关闭。如果等待时间结束时还有任务未完成，线程池会强制关闭
     */
    private Integer awaitTerminationSeconds;
}
