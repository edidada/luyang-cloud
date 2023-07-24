package club.luyang.boot.cache.config.serializer;

import cn.hutool.core.text.CharSequenceUtil;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.StandardCharsets;

/**
 * Redis key序列化
 *
 * @author lu_yang
 */
public record RedisKeyStringSerializer(String keyPrefix) implements RedisSerializer<String> {

    private static final String SEPARATOR = "::";

    @Override
    public byte[] serialize(String key) throws SerializationException {
        if (CharSequenceUtil.isBlank(key)) {
            return null;
        }

        key = joinKeyPrefix(key);
        return key.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String deserialize(byte[] bytes) throws SerializationException {
        String key = new String(bytes, StandardCharsets.UTF_8);
        if (CharSequenceUtil.isBlank(this.keyPrefix)) {
            return key;
        }

        int indexOf = key.indexOf(this.keyPrefix);
        if (indexOf == 0) {
            key = key.substring(this.keyPrefix.length() + SEPARATOR.length());
        }

        return key;
    }

    public String joinKeyPrefix(String key) {
        if (CharSequenceUtil.isNotBlank(key) && !key.startsWith("NOPREFIX#") && !key.startsWith(this.keyPrefix)) {
            key = CharSequenceUtil.join(SEPARATOR, this.keyPrefix, key);
        }

        return key;
    }
}
