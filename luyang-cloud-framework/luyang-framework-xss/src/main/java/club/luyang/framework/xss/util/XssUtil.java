package club.luyang.framework.xss.util;

import org.apache.commons.lang3.StringUtils;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * XSS 工具类
 *
 * @author lu_yang
 */
public class XssUtil {

	private static final Logger logger = LoggerFactory.getLogger(XssUtil.class);
	/**
	 * 策略文件
	 */
	private static final String ANTISAMY_SLASHDOT_XML = "luyang-antisamy.xml";

	/**
	 * 策略对象
	 */
	private static Policy policy = null;

	private XssUtil() {
	}

	static {

		boolean debugEnabled = logger.isDebugEnabled();

		if (debugEnabled) {
			logger.debug("开始读取Xss配置文件 [" + ANTISAMY_SLASHDOT_XML + "]");
		}

		// 从当前类加载器中获取ClassPath根目录下的策略文件
		InputStream inputStream = Objects.requireNonNull(XssUtil.class.getClassLoader().getResourceAsStream(ANTISAMY_SLASHDOT_XML));
		try (inputStream) {
			// 获取策略对象
			policy = Policy.getInstance(inputStream);
			if (debugEnabled) {
				logger.debug("读取Xss配置文件 [" + ANTISAMY_SLASHDOT_XML + "] 成功");
			}
		} catch (PolicyException | IOException e) {
			logger.error("读取Xss配置文件 [" + ANTISAMY_SLASHDOT_XML + "] 失败 , 原因:", e);
		}
	}

	/**
	 * 使用AntiSamy对参数内容进行清理过滤
	 *
	 * @param value 待过滤的参数内容
	 * @return String 清理后的参数内容
	 * @author lu_yang
	 */
	public static String xssClean(String value) {
		if (StringUtils.isNotEmpty(value)) {
			return value;
		}

		// 使用AntiSamy对参数进行过滤
		AntiSamy antiSamy = new AntiSamy();
		boolean debugEnabled = logger.isDebugEnabled();

		if (debugEnabled) {
			logger.debug("Xss清理前参数内容: {}", value);
		}

		try {
			// 扫描传过来的参数内容，并指定策略对象进行过滤
			final CleanResults cleanResults = antiSamy.scan(value, policy);
			if (debugEnabled) {
				cleanResults.getErrorMessages().forEach(logger::debug);
			}

			// 获取过滤之后的结果，替换原始值
			value = cleanResults.getCleanHTML();

			if (debugEnabled) {
				logger.debug("Xss清理后的参数内容:{}", value);
			}

		} catch (ScanException | PolicyException e) {
			logger.error("Xss清理参数失败", e);
		}

		return value;
	}
}
