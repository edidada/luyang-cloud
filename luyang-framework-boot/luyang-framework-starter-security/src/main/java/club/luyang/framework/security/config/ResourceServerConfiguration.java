package club.luyang.framework.security.config;

import club.luyang.framework.security.config.props.AnonymousUriProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * 资源服务认证授权配置
 *
 * @author lu_yang
 */
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(AnonymousUriProperties.class)
public class ResourceServerConfiguration {

	private final AnonymousUriProperties anonymousUriProperties;

	public ResourceServerConfiguration(AnonymousUriProperties anonymousUriProperties) {
		this.anonymousUriProperties = anonymousUriProperties;
	}

//	@Bean
//	@Order(Ordered.HIGHEST_PRECEDENCE)
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http
//			.authorizeHttpRequests(authorize ->
//				authorize
//					// 允许匿名访问
//					.requestMatchers(ArrayUtil.toArray(anonymousUriProperties.getUrls(), String.class)).permitAll()
//					// 所有请求都需要认证
//					.anyRequest().authenticated()
//			);
//
//		return http.build();
//	}
}
