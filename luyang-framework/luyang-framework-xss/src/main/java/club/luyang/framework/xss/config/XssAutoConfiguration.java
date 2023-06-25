package club.luyang.framework.xss.config;

import club.luyang.framework.xss.filter.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * 配置Xss过滤器
 *
 * @author lu_yang
 */
public class XssAutoConfiguration {

    /**
     * 注册XssFilter实例
     *
     * @return FilterRegistrationBean<Filter>
     * @author lu_yang
     */
    @Bean
    public FilterRegistrationBean<XssFilter> filterRegistrationBean() {
        FilterRegistrationBean<XssFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new XssFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
