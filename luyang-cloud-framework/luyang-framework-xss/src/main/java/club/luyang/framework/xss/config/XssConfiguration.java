package club.luyang.framework.xss.config;

import club.luyang.framework.xss.filter.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * 配置Xss过滤器
 *
 * @author lu_yang
 */
@Configuration
public class XssConfiguration {

	@Bean
	public FilterRegistrationBean<Filter> filterRegistrationBean() {
		FilterRegistrationBean<Filter> filterRegistration = new FilterRegistrationBean<>(new XssFilter());
		// 指定当前过滤器(XssFilter)过滤的url地址，这里表示所有请求
		filterRegistration.addUrlPatterns("/*");
		// 设置过滤器(XssFilter)执行的先后顺序
		filterRegistration.setOrder(1);
		return filterRegistration;
	}
}
