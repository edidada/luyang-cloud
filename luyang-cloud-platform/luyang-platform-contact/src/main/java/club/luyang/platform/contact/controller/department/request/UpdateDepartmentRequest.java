package club.luyang.platform.contact.controller.department.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


/**
 * 更新部门请求体
 *
 * @author lu_yang
 */
@Getter
@Setter
public class UpdateDepartmentRequest {

	@NotBlank(message = "部门ID为空")
	private String departmentId;

	private String parentDepartmentId;

	private String departmentName;

	private int sortNum;
}
