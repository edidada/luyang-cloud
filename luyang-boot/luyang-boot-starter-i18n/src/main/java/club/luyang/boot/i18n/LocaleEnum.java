package club.luyang.boot.i18n;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Locale;

/**
 * @author lu_yang
 */
@Getter
@AllArgsConstructor
public enum LocaleEnum {

    SIMPLIFIED_CHINESE("zh_CN", "简体中文", Locale.SIMPLIFIED_CHINESE),
    US("en_US", "美式英语", Locale.US),
    FRANCE("fr_FR", "法语", Locale.FRANCE),
    JAPAN("ja_JP", "日语", Locale.JAPAN),
    KOREA("ko_KR", "韩语", Locale.KOREA),
    SPAIN("es_ES", "西班牙语", new Locale("es", "ES")),
    RUSSIA("ru_RU", "俄语", new Locale("ru", "RU")),
    ITALY("it_IT", "意大利语", Locale.ITALY);

    private final String code;

    private final String desc;

    private final Locale locale;

    public static Locale getLocaleByCode(String code) {
        return Arrays.stream(LocaleEnum.values())
                .filter(localeEnum -> localeEnum.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported internationalization language(" + code + ")"))
                .locale;
    }
}