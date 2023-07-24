package club.luyang.boot.base.component.mask;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据脱敏标记
 *
 * @author lu_yang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize(using = DataMaskSerializer.class)
public @interface DataMask {

    /**
     * 脱敏规则
     */
    DataMaskRule maskRule();

    /**
     * 如需要动态控制是否脱敏，则需实现{@link DataMaskControl} 接口
     */
    Class<? extends DataMaskControl> visible() default DataMaskControl.class;
}
