package club.luyang.framework.xss.config;

import club.luyang.framework.xss.config.prop.XssProperties;
import club.luyang.framework.xss.filter.XssFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.util.AntPathMatcher;

/**
 * 配置Xss过滤器
 *
 * @author lu_yang
 */
@EnableConfigurationProperties(XssProperties.class)
public class XssAutoConfiguration {

	private final XssProperties xssProperties;

	private final AntPathMatcher antPathMatcher;

	public XssAutoConfiguration(XssProperties xssProperties, AntPathMatcher antPathMatcher) {
		this.xssProperties = xssProperties;
		this.antPathMatcher = antPathMatcher;
	}

	/**
	 * 注册XssFilter实例
	 *
	 * @return FilterRegistrationBean<Filter>
	 * @author lu_yang
	 */
	@Bean
	public FilterRegistrationBean<XssFilter> filterRegistrationBean() {
		FilterRegistrationBean<XssFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new XssFilter(xssProperties, antPathMatcher));
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(1);
		return registrationBean;
	}
}
