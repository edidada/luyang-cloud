package club.luyang.boot.i18n.config;

import club.luyang.boot.i18n.LocaleEnum;
import club.luyang.boot.i18n.config.props.I18nProperties;
import club.luyang.boot.i18n.interceptor.LangugeChangeInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Locale;

/**
 * i18n 国际化配置
 *
 * @author lu_yang
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
@EnableConfigurationProperties(I18nProperties.class)
@ConditionalOnProperty(name = "luyang.i18n.enabled", havingValue = "true", matchIfMissing = true)
public class I18nConfiguration implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(I18nConfiguration.class);

    private final I18nProperties i18nProperties;

    public I18nConfiguration(I18nProperties i18nProperties) {
        this.i18nProperties = i18nProperties;
    }

    private Locale getDefaultLocale() {
        return LocaleEnum.getLocaleByCode(i18nProperties.getDefaultLocale());
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookieMaxAge(Duration.ofSeconds(3600));
        resolver.setDefaultLocale(getDefaultLocale());
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        logger.info("[LUYANG] |- Bean [LocaleResolver] Auto Configure.");
        return cookieLocaleResolver;
    }

    @Bean
    public MessageSource messageSource() {

        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setBasenames(StringUtils.commaDelimitedListToStringArray(StringUtils.trimAllWhitespace("language/base")));

        // messageSource.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());
        // Duration cacheDuration = properties.getCacheDuration();
        // if (cacheDuration != null) {
        //     messageSource.setCacheMillis(cacheDuration.toMillis());
        // }
        // messageSource.setAlwaysUseMessageFormat(properties.isAlwaysUseMessageFormat());
        // messageSource.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());
        return messageSource;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LangugeChangeInterceptor localeInterceptor = new LangugeChangeInterceptor();
        localeInterceptor.setParamName(i18nProperties.getHeadName());
        registry.addInterceptor(localeInterceptor);
    }
}
