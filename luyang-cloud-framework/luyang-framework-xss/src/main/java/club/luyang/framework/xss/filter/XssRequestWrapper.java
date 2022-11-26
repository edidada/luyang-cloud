package club.luyang.framework.xss.filter;

import club.luyang.framework.xss.util.XssUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.text.CharSequenceUtil;
import org.springframework.http.MediaType;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * 清理Request请求头和请求参数
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
		return CharSequenceUtil.isBlank(headerValue) ? headerValue : XssUtil.xssClean(headerValue);
	}

	@Override
	public String getParameter(String name) {
		String parameter = super.getParameter(name);
		return CharSequenceUtil.isBlank(parameter) ? parameter : XssUtil.xssClean(parameter);
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] parameterValues = super.getParameterValues(name);
		if (null == parameterValues) {
			return new String[0];
		}

		int length = parameterValues.length;
		String[] arr = new String[length];
		for (int i = 0; i < length; i++) {
			arr[i] = XssUtil.xssClean(parameterValues[i]);
		}

		return arr;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> parameterMap = super.getParameterMap();
		parameterMap.forEach((k, v) -> {
			for (int i = 0; i < v.length; i++) {
				v[i] = XssUtil.xssClean(v[i]);
			}
		});

		return parameterMap;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		boolean isJsonRequest = CharSequenceUtil.startWithIgnoreCase(this.getContentType(), MediaType.APPLICATION_JSON_VALUE);

		// 如果非 json 请求，不进行 Xss 处理
		if (!isJsonRequest) {
			return super.getInputStream();
		}

		// 读取内容，并过滤
		String content = IoUtil.readUtf8(super.getInputStream());
		content = XssUtil.xssClean(content);
		final ByteArrayInputStream newInputStream = new ByteArrayInputStream(content.getBytes());

		// 返回 ServletInputStream
		return new ServletInputStream() {

			@Override
			public int read() {
				return newInputStream.read();
			}

			@Override
			public boolean isFinished() {
				return true;
			}

			@Override
			public boolean isReady() {
				return true;
			}

			@Override
			public void setReadListener(ReadListener readListener) {
				// ignore
			}
		};
	}
}
