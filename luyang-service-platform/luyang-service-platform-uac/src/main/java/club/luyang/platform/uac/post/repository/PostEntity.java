package club.luyang.platform.uac.post.repository;

import club.luyang.framework.mybatisplus.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lu_yang
 */
@Getter
@Setter
@TableName("uac_post")
public class PostEntity extends BaseEntity<PostEntity> {

    /**
     * 岗位编码
     */
    private String postCode;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 排序号，值越大越靠前
     */
    private Integer sortNum;

    /**
     * 备注
     */
    private String remark;
}
