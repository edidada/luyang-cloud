package club.luyang.boot.mybatisplus.handler;

import club.luyang.boot.mybatisplus.base.BaseEntity;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 自动填充字段
 *
 * @author lu_yang
 */
public class DefaultFieldHandler implements MetaObjectHandler {

    /**
     * 创建时间
     */
    private static final String FIELD_CREATE_TIME = "createTime";

    /**
     * 更新时间
     */
    private static final String FIELD_UPDATE_TIME = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.isNull(metaObject) || !(metaObject.getOriginalObject() instanceof BaseEntity)) {
            return;
        }

        LocalDateTime currentTime = LocalDateTime.now();
        this.strictInsertFill(metaObject, FIELD_CREATE_TIME, LocalDateTime.class, currentTime);
        this.strictInsertFill(metaObject, FIELD_UPDATE_TIME, LocalDateTime.class, currentTime);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateTime = getFieldValByName(FIELD_UPDATE_TIME, metaObject);
        if (Objects.nonNull(updateTime)) {
            return;
        }

        this.strictUpdateFill(metaObject, FIELD_UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
    }
}
