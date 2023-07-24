package club.luyang.boot.base.util.jackson;

import club.luyang.boot.base.util.jackson.module.CustomSimpleModule;
import cn.hutool.core.text.CharSequenceUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author lu_yang
 */
@UtilityClass
public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.setLocale(Locale.CHINA);
        OBJECT_MAPPER.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, Boolean.FALSE);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
        OBJECT_MAPPER.registerModules(new CustomSimpleModule());
    }

    @SneakyThrows
    public static String toJsonString(Object object) {
        return OBJECT_MAPPER.writeValueAsString(object);
    }

    public static boolean isJson(String text) {
        if (CharSequenceUtil.isBlank(text)) {
            return false;
        }

        String str = CharSequenceUtil.trim(text);
        return CharSequenceUtil.isWrap(str, '{', '}')
                || CharSequenceUtil.isWrap(str, '[', ']');
    }
}
