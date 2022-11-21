package club.luyang.framework.web.banner;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.thread.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;

import java.util.concurrent.TimeUnit;

/**
 * 项目启动成功后，提供文档相关的地址
 *
 * @author lu_yang
 */
@SuppressWarnings("all")
public record BannerApplicationStarter(Environment environment) implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(BannerApplicationStarter.class);

	@Override
	public void run(ApplicationArguments args) throws Exception {
		ThreadUtil.execute(() -> {
			ThreadUtil.sleep(1, TimeUnit.SECONDS);

			String ip = NetUtil.getLocalHostName();
			String port = CharSequenceUtil.emptyToDefault(environment.getProperty("server.port"), "8080");
			String path = CharSequenceUtil.emptyToDefault(environment.getProperty("server.servlet.context-path"), CharSequenceUtil.EMPTY);
			String serverName = CharSequenceUtil.emptyToDefault(environment.getProperty("spring.application.name"), CharSequenceUtil.EMPTY);

			logger.info("""
				\n==============  Application started successfully! Access URLs:  ==============
				服务名: {}
				接口文档: http://{}:{}{}/doc.html
				""", serverName, ip, port, path);
		});
	}
}
