package club.luyang.framework.security.config.props;

import club.luyang.framework.security.annotation.AnonymousAccess;
import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.spring.SpringUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * 匿名访问URL配置属性
 *
 * @author lu_yang
 */
@ConfigurationProperties(prefix = "luyang.anonymous")
public class AnonymousUriProperties implements InitializingBean {

	private Set<String> urls = new HashSet<>();

	@Override
	public void afterPropertiesSet() throws Exception {
		RequestMappingHandlerMapping mapping = SpringUtil.getBean("requestMappingHandlerMapping");
		Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = mapping.getHandlerMethods();
		if (MapUtil.isEmpty(handlerMethodMap)) {
			return;
		}

		handlerMethodMap.forEach((k, v) -> {
			AnonymousAccess methodAnnotation = AnnotationUtils.findAnnotation(v.getMethod(), AnonymousAccess.class);
			AnonymousAccess classAnnotation = AnnotationUtils.findAnnotation(v.getBeanType(), AnonymousAccess.class);
			if (null != methodAnnotation || null != classAnnotation) {
				Set<String> paths = Optional.ofNullable(k.getPathPatternsCondition())
					.map(PathPatternsRequestCondition::getPatternValues)
					.orElse(new HashSet<>());
				urls.addAll(paths);
			}
		});
	}

	public Set<String> getUrls() {
		return urls;
	}

	public void setUrls(Set<String> urls) {
		this.urls = urls;
	}
}
