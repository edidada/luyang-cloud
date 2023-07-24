package club.luyang.boot.base.component.mask;

import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.SneakyThrows;

import java.io.IOException;

/**
 * @author lu_yang
 */
public class DataMaskSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private DataMaskRule maskRule;

    private DataMaskControl dataMaskControl;

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (dataMaskControl.needMask()) {
            jsonGenerator.writeString(maskRule.fun.apply(value));
            return;
        }
        jsonGenerator.writeString(value);
    }


    @Override
    @SneakyThrows
    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) {
        DataMask annotation = property.getAnnotation(DataMask.class);
        if (null == annotation || ObjectUtil.notEqual(String.class, property.getType().getRawClass())) {
            return provider.findValueSerializer(property.getType(), property);
        }

        this.maskRule = annotation.maskRule();
        Class<? extends DataMaskControl> handler = annotation.visible();
        // 无需动态控制是否脱敏，则默认为true
        if (handler.isInterface()) {
            dataMaskControl = () -> true;
        } else {
            dataMaskControl = handler.getDeclaredConstructor().newInstance();
        }

        return this;
    }
}
