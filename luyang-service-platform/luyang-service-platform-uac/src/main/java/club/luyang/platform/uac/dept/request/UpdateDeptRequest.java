package club.luyang.platform.uac.dept.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;


/**
 * 创建部门请求体
 *
 * @author lu_yang
 */
@Getter
@Setter
public class UpdateDeptRequest {

	/**
	 * 部门ID
	 */
	@NotNull
	private Long deptId;

	/**
	 * 父ID
	 */
	@NotNull
	private Long parentDeptId;

	/**
	 * 部门名称
	 */
	@NotBlank
	@Length(max = 128)
	private String deptName;

	/**
	 * 排序号，值越大越靠前 <br/>
	 * 取值范围(0, 2^32)
	 */
	@Range(min = 1, max = 255)
	private int sortNum;
}
