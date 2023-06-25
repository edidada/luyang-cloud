package club.luyang.framework.mybatisplus.config;

import club.luyang.framework.mybatisplus.handler.DefaultFieldHandler;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author lu_yang
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration(proxyBeanMethods = false)
@EnableTransactionManagement
@PropertySource(value = "classpath:luyang-mp.properties", encoding = "UTF-8")
public class MybatisPlusAutoConfiguration {

    /**
     * mp拦截器
     *
     * @return MybatisPlusInterceptor mp拦截器
     * @author lu_yang
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        // 分页插件
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setMaxLimit(500L);
        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);
        return mybatisPlusInterceptor;
    }

    /**
     * 字段自动填充
     *
     * @return MetaObjectHandler 元数据字段填充控制器
     * @author lu_yang
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new DefaultFieldHandler();
    }
}
