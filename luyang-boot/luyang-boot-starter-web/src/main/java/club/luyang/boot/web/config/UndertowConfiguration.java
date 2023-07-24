package club.luyang.boot.web.config;

import io.undertow.Undertow;
import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;

import static io.undertow.UndertowOptions.ENABLE_HTTP2;

/**
 * Undertow 配置
 *
 * @author lu_yang
 */
@AutoConfiguration
@ConditionalOnClass(Undertow.class)
@AutoConfigureBefore(ServletWebServerFactoryAutoConfiguration.class)
public class UndertowConfiguration implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {

    /**
     * 添加自定义部署的Web应用程序的配置信息
     *
     * @author lu_yang
     */
    @Override
    public void customize(UndertowServletWebServerFactory factory) {
        factory.addDeploymentInfoCustomizers(deploymentInfo -> {
            WebSocketDeploymentInfo webSocketDeploymentInfo = new WebSocketDeploymentInfo();
            webSocketDeploymentInfo.setBuffers(new DefaultByteBufferPool(false, 512));
            deploymentInfo.addServletContextAttribute("io.undertow.websockets.jsr.WebSocketDeploymentInfo", webSocketDeploymentInfo);
        });
    }

    /**
     * 添加自定义的构建器定制器，启用对HTTP/2的支持
     *
     * @author lu_yang
     */
    @Bean
    public WebServerFactoryCustomizer<UndertowServletWebServerFactory> undertowHttp2WebServerFactoryCustomizer() {
        return factory -> factory.addBuilderCustomizers(builder -> builder.setServerOption(ENABLE_HTTP2, true));
    }
}
