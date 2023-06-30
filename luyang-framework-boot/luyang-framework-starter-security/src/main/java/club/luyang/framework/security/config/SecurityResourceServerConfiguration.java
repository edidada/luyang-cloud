package club.luyang.framework.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 资源服务认证授权配置
 *
 * @author lu_yang
 */
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class SecurityResourceServerConfiguration {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain resourceSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize ->
                        authorize
                                // 允许匿名访问
                                // .requestMatchers("/login", "/oauth2/authorize").permitAll()
                                // 所有请求都需要认证
                                .anyRequest().authenticated())
                .oauth2ResourceServer(resource ->
                        resource.opaqueToken(opaqueToken -> opaqueToken.introspector(new SecurityOpaqueTokenIntrospector()))
                                // .bearerTokenResolver(new SecurityBearerTokenResolver())
                                // .authenticationEntryPoint(new SecurityAuthenticationEntryPoint())
                );/* .headers(headersConfigurer ->
                        headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(CsrfConfigurer::disable); */

        return http.build();
    }
}
