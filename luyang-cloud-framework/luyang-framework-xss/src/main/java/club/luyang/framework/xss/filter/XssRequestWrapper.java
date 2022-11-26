package club.luyang.framework.xss.filter;

import club.luyang.framework.xss.util.XssUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 基于AntiSamy清理Request请求头和请求参数
 *
 * @author lu_yang
 */
public class XssRequestWrapper extends HttpServletRequestWrapper {

	public XssRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getHeader(String headerKey) {
		String headerValue = super.getHeader(headerKey);
		// 如果Header为空，则直接返回，否则进行清洗
		return StringUtils.isBlank(headerValue) ? headerValue : XssUtil.xssClean(headerValue);
	}
}
