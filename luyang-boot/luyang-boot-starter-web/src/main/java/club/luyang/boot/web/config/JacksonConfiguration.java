package club.luyang.boot.web.config;

import club.luyang.boot.base.util.jackson.module.CustomSimpleModule;
import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.math.BigInteger;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 自定义 Jackson 对象映射器（ObjectMapper）的构建
 *
 * @author lu_yang
 */
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JacksonConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(JacksonConfiguration.class);

    @Bean
    @ConditionalOnMissingBean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            builder.locale(Locale.CHINA);
            builder.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
            // 针对于Date类型，文本格式化
            builder.simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
            // 若POJO对象的属性值为null，序列化时不进行显示
            builder.serializationInclusion(JsonInclude.Include.NON_NULL);
            // DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES相当于配置，JSON串含有未知字段时，反序列化依旧可以成功
            builder.failOnUnknownProperties(false);

            CustomSimpleModule customSimpleModule = new CustomSimpleModule();
            customSimpleModule.addSerializer(Long.class, ToStringSerializer.instance);
            customSimpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
            customSimpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
            builder.modules(customSimpleModule);

            logger.info("[LUYANG] |- Bean [ObjectMapper] Auto Configure.");
        };
    }
}
