package club.luyang.platform.contact.controller;

import club.luyang.framework.base.api.Result;
import club.luyang.platform.contact.controller.department.request.CreateDepartmentRequest;
import club.luyang.platform.contact.controller.department.request.UpdateDepartmentRequest;
import club.luyang.platform.contact.controller.department.response.CreateDepartmentResponse;
import club.luyang.platform.contact.domain.valueobject.DepartmentId;
import club.luyang.platform.contact.service.DepartmentService;
import club.luyang.platform.contact.service.ability.DepartmentCreateAbility;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门相关控制器
 *
 * @author lu_yang
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

	private final DepartmentService departmentService;
	private final DepartmentCreateAbility departmentCreateAbility;

	public DepartmentController(DepartmentService departmentService, DepartmentCreateAbility departmentCreateAbility) {
		this.departmentService = departmentService;
		this.departmentCreateAbility = departmentCreateAbility;
	}

	/**
	 * 创建部门
	 *
	 * @param request 请求参数体
	 * @return Result<CreateDepartmentResponse>
	 * @author lu_yang
	 */
	@PostMapping
	public Result<CreateDepartmentResponse> create(@RequestBody CreateDepartmentRequest request) {
		DepartmentId departmentId = this.departmentCreateAbility.executeAbility(request);
		return Result.success(new CreateDepartmentResponse(departmentId.value()));
	}

	/**
	 * 更新部门
	 *
	 * @param request 请求参数体
	 * @return Result<Void>
	 * @author lu_yang
	 */
	@PutMapping
	public Result<Void> update(@RequestBody UpdateDepartmentRequest request) {
		this.departmentService.updateDepartment(request);
		return Result.success();
	}
}
