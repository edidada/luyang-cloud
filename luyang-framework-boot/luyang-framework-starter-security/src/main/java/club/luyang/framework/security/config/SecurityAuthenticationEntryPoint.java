package club.luyang.framework.security.config;

import club.luyang.framework.base.api.Result;
import club.luyang.framework.base.util.json.JsonUtil;
import cn.hutool.core.text.StrPool;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * 客户端向未授权访问的资源发出未经身份验证的请求异常处理
 * 官方文档：<a href="https://docs.spring.io/spring-security/reference/servlet/authentication/architecture.html#servlet-authentication-authenticationprovider">...</a>
 *
 * @author Luyang
 */
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(SecurityAuthenticationEntryPoint.class);

    @Override
    @SneakyThrows
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        Result<String> failure = null;
        // 没有携带token访问接口，没有客户端认证信息
        if (authException instanceof InsufficientAuthenticationException) {
            failure = Result.failure(401, "无效请求令牌");
        }

        response.getWriter().write(JsonUtil.toString(failure));
        response.flushBuffer();
    }
}
