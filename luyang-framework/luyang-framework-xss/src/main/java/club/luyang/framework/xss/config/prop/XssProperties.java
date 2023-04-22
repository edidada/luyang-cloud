package club.luyang.framework.xss.config.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Xss配置类
 *
 * @author yzc
 */
@ConfigurationProperties("xss.security")
public class XssProperties {

	/**
	 * 开启xss
	 */
	private Boolean enabled = true;

	/**
	 * 放行url
	 */
	private List<String> skipUrl = new ArrayList<>();



	public Boolean getEnabled() {
		return enabled;
	}

	public List<String> getSkipUrl() {
		return skipUrl;
	}
}
