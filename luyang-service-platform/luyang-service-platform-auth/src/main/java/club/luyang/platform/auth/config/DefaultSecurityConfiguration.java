package club.luyang.platform.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 服务安全相关配置
 *
 * @author Luyang
 */
@Configuration(proxyBeanMethods = false)
public class DefaultSecurityConfiguration {


    /**
     * 配置认证相关的过滤器链 security核心配置类
     */
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                // 放行静态资源
                .requestMatchers("/assets/**", "/webjars/**", "/login").permitAll()
                .anyRequest().authenticated()
        ).formLogin(formLogin ->
                formLogin.loginPage("/login")
        );

        return http.build();
    }

    /**
     * 先暂时配置一个基于内存的用户，框架在用户认证时会默认调用
     * {@link UserDetailsService#loadUserByUsername(String)} 方法根据
     * 账号查询用户信息，一般是重写该方法实现自己的逻辑
     *
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService users() {
        UserDetails user = User
                .withUsername("admin")
                .password("{noop}123456")
                .roles("admin", "normal", "unAuthentication")
                .authorities("app", "web", "/test2", "/test3")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
