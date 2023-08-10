package club.luyang.boot.i18n.config.props;

import club.luyang.boot.i18n.LocaleEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lu_yang
 */
@Getter
@Setter
@ConfigurationProperties(prefix = I18nProperties.PREFIX)
public class I18nProperties {

    public static final String PREFIX = "luyang.i18n";

    /**
     * 默认语言 {@link LocaleEnum}
     */
    private String defaultLocale = "zh_CN";

    /**
     * 语言头参数名
     */
    private String headName = "locale";
}
