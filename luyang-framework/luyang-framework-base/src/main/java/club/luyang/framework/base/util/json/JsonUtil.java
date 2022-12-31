package club.luyang.framework.base.util.json;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.Map;

/**
 * JSON 工具类
 *
 * @author lu_yang
 */
@UtilityClass
public class JsonUtil {

	@SneakyThrows
	public static String toString(Object v) {
		return ObjectMapperSingleton.INSTANCE.getObjectMapper().writeValueAsString(v);
	}

	@SneakyThrows
	public static <T> T toBean(String text, Class<T> clazz) {
		if (CharSequenceUtil.isEmpty(text) || ObjectUtil.isNull(clazz)) {
			return null;
		}

		return ObjectMapperSingleton.INSTANCE.getObjectMapper().readValue(text, clazz);
	}

	/**
	 * JSON 文本转 Map
	 *
	 * @param text JSON文本
	 * @param kt   Map Key 类型 如 String.class
	 * @param vt   Map value 类型 如 String.class
	 * @return Map
	 * @author lu_yang
	 */
	@SneakyThrows
	public static <K, V> Map<K, V> toMap(String text, Class<K> kt, Class<V> vt) {
		if (CharSequenceUtil.isEmpty(text) || ObjectUtil.hasEmpty(kt, vt)) {
			return Collections.emptyMap();
		}

		ObjectMapper objectMapper = ObjectMapperSingleton.INSTANCE.getObjectMapper();
		JavaType javaType = objectMapper.getTypeFactory().constructMapType(Map.class, kt, vt);

		return objectMapper.readValue(text, javaType);
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
