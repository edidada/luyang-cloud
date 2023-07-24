package club.luyang.platform.uac.dept.repository;

import club.luyang.boot.mybatisplus.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lu_yang
 */
@Getter
@Setter
@TableName("uac_dept")
public class DeptEntity extends BaseEntity<DeptEntity> {

    private Long parentId;
    private String name;
    private String fullPath;
    private String fullName;
    private Integer sortNum;
}
