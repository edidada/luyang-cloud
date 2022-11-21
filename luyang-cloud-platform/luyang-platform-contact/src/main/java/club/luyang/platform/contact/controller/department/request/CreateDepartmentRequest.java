package club.luyang.platform.contact.controller.department.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

/**
 * 创建部门请求体
 *
 * @author lu_yang
 */
@Getter
@Setter
public class CreateDepartmentRequest {

	/**
	 * 部门ID，为空则自动生成 <br/>
	 * 长度限制为1~64个字符
	 */
	@Length(max = 64)
	private String departmentId;

	/**
	 * 父ID，创建根部门，该参数值为0 <br/>
	 * 长度限制为1~64个字符
	 */
	@NotBlank
	@Length(max = 64)
	private String parentDepartmentId;

	/**
	 * 部门名称
	 */
	@NotBlank
//	@Pattern(regexp = "")
	@Length(max = 128)
	private String departmentName;

	/**
	 * 排序号，值越大越靠前 <br/>
	 * 取值范围(0, 2^32)
	 */
	@Range(min = 1, max = 255)
	private int sortNum;
}
