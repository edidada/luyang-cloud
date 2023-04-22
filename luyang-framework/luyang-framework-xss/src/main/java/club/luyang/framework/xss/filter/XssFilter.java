package club.luyang.framework.xss.filter;

import club.luyang.framework.xss.config.prop.XssProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Xss 过滤器，继承OncePerRequestFilter避免重复拦截过滤
 *
 * @author lu_yang
 */
public class XssFilter extends OncePerRequestFilter {

	private final XssProperties xssProperties;

	private final AntPathMatcher antPathMatcher;

	public XssFilter(XssProperties xssProperties, AntPathMatcher antPathMatcher) {
		this.xssProperties = xssProperties;
		this.antPathMatcher = antPathMatcher;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String path = request.getServletPath();
		if (!xssProperties.getEnabled() || isXssSkip(path)) {
			// 放行
			filterChain.doFilter(request, response);
		} else {
			// 交由request包装类XssRequestWrapper处理当前请求过滤
			filterChain.doFilter(new XssRequestWrapper(request), response);
		}
	}

	/**
	 * Xss放行
	 */
	private boolean isXssSkip(String path) {
		return xssProperties.getSkipUrl().stream().anyMatch(pattern -> antPathMatcher.match(pattern, path));
	}
}
