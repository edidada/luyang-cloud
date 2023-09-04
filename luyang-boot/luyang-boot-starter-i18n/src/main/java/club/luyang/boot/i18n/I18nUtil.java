package club.luyang.boot.i18n;

import club.luyang.boot.base.util.SpringUtil;
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

    public static String get(String key, Object... args) {
        try {
            return getMessageSource().getMessage(key, args, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            logger.warn("未获取到:[ {} ]国际化.", key);
            return key;
        }
    }

    public static String get(String key, LocaleEnum localeEnum, Object... args) {
        try {
            return getMessageSource().getMessage(key, args, localeEnum.getLocale());
        } catch (NoSuchMessageException e) {
            logger.warn("未获取到:[ {} ]国际化.", key);
            return key;
        }
    }

    public static String getCurrentLanguage() {
        return LocaleContextHolder.getLocale().toLanguageTag();
    }
}
