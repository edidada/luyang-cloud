package club.luyang.framework.web.i18n;

import cn.hutool.extra.spring.SpringUtil;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author lu_yang
 */
@UtilityClass
public class I18nUtil {

    private static final Logger logger = LoggerFactory.getLogger(I18nUtil.class);

    private static MessageSource messageSource;

    public static MessageSource getMessageSource() {
        if (null == messageSource) {
            messageSource = SpringUtil.getBean(MessageSource.class);
        }

        return messageSource;
    }

    public static String get(String key) {
        try {
            return getMessageSource().getMessage(key, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            logger.warn("未获取到:[ {} ]国际化.", key);
            return key;
        }
    }

    public static String getCurrentLanguage() {
        return LocaleContextHolder.getLocale().toLanguageTag();
    }
}
