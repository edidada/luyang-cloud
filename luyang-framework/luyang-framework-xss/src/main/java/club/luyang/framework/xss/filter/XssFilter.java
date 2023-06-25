package club.luyang.framework.xss.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Xss 过滤器，继承OncePerRequestFilter避免重复拦截过滤
 *
 * @author lu_yang
 */
public class XssFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 放行当前过滤器 交由request包装类XssRequestWrapper处理当前请求过滤
        filterChain.doFilter(new XssRequestWrapper(request), response);
    }
}
