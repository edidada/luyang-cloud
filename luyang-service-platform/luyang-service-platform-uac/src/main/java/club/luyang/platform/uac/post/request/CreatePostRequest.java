package club.luyang.platform.uac.post.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;


/**
 * @author lu_yang
 */
@Getter
@Setter
public class CreatePostRequest {

    /**
     * 岗位编码
     */
    @NotBlank
    @Length(max = 16)
    private String postCode;

    /**
     * 岗位名称
     */
    @NotBlank
    @Length(max = 64)
    private String postName;

    /**
     * 排序号，值越大越靠前 <br/>
     * 取值范围(0, 2^32)
     */
    @NotNull
    @Range(min = 1, max = 255)
    private Integer sortNum;

    /**
     * 备注
     */
    @Length(max = 255)
    private String remark;
}
