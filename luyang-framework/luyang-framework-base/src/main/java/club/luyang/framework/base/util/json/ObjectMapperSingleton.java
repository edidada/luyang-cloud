package club.luyang.framework.base.util.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * 单例ObjectMapper
 *
 * @author lu_yang
 */
public enum ObjectMapperSingleton {

    /**
     * ObjectMapper实例
     */
    INSTANCE;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private ObjectMapperSingleton() {
        this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, Boolean.FALSE);
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
        this.objectMapper.registerModules(new JavaTimeModule());
    }

    public ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }
}
