package club.luyang.boot.i18n.config.props;

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

}
