package club.luyang.boot.web.config;

import club.luyang.boot.web.config.props.ThreadPoolProperties;
import cn.hutool.core.util.ReflectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.Serial;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.RejectedExecutionHandler;

/**
 * @author lu_yang
 */
@EnableAsync(proxyTargetClass = true)
@EnableConfigurationProperties(ThreadPoolProperties.class)
public class TaskExecutorConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(TaskExecutorConfiguration.class);

    /**
     * CPU核数
     */
    public static final int cpuNum = Runtime.getRuntime().availableProcessors();

    @Bean
    public TaskExecutor taskExecutor(ThreadPoolProperties properties) throws ClassNotFoundException {
        MdcThreadPoolTaskExecutor taskExecutor = new MdcThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(Optional.ofNullable(properties.getCorePoolSize()).orElse(cpuNum));
        taskExecutor.setMaxPoolSize(Optional.ofNullable(properties.getMaxPoolSize()).orElse(cpuNum * 2));
        taskExecutor.setQueueCapacity(Optional.ofNullable(properties.getQueueCapacity()).orElse(500));

        Class<?> rejectedExecutionHandlerClass = Class.forName("java.util.concurrent.ThreadPoolExecutor$" +
                Optional.ofNullable(properties.getRejectedExecutionHandler()).orElse("CallerRunsPolicy"));
        RejectedExecutionHandler rejectedExecutionHandler = (RejectedExecutionHandler) ReflectUtil.newInstance(rejectedExecutionHandlerClass);
        taskExecutor.setRejectedExecutionHandler(rejectedExecutionHandler);

        // 等待所有任务完成后才关闭线程池
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(Optional.ofNullable(properties.getAwaitTerminationSeconds()).orElse(60));
        taskExecutor.setKeepAliveSeconds(Optional.ofNullable(properties.getKeepAliveSeconds()).orElse(60));
        taskExecutor.setThreadNamePrefix(Optional.ofNullable(properties.getNamePrefix()).orElse("Task-Thread-"));
        taskExecutor.initialize();
        logger.info("[LUYANG] |- Bean [TaskExecutor] Auto Configure.");
        return taskExecutor;
    }

    public static class MdcThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        @SuppressWarnings("NullableProblems")
        public void execute(Runnable task) {
            super.execute(wrap(task, MDC.getCopyOfContextMap()));
        }

        private Runnable wrap(final Runnable task, final Map<String, String> contextMap) {
            return () -> {
                if (null != contextMap) {
                    MDC.setContextMap(contextMap);
                }

                try {
                    task.run();
                } finally {
                    MDC.clear();
                }
            };
        }
    }
}
