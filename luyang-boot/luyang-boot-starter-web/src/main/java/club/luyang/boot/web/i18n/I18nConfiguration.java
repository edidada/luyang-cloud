package club.luyang.boot.web.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.time.Duration;
import java.util.Locale;

/**
 * i18n 国际化配置
 *
 * @author lu_yang
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = "luyang.i18n.enabled", havingValue = "true", matchIfMissing = true)
public class I18nConfiguration implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(I18nConfiguration.class);

    @Value("${luyang.i18n.local-head-name:locale}")
    private String localHeadName;

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookieMaxAge(Duration.ofSeconds(3600));
        resolver.setDefaultLocale(Locale.US);
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        logger.info("[LUYANG] |- Bean [LocaleResolver] Auto Configure.");
        return cookieLocaleResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        RedefineLocaleChangeInterceptor localeInterceptor = new RedefineLocaleChangeInterceptor();
        localeInterceptor.setParamName(localHeadName);
        registry.addInterceptor(localeInterceptor);
    }
}
