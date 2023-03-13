package club.luyang.platform.uac.controller;

import club.luyang.framework.base.api.Result;
import club.luyang.platform.uac.beans.request.dept.CreateDeptRequest;
import club.luyang.platform.uac.beans.request.dept.UpdateDeptRequest;
import club.luyang.platform.uac.beans.response.CreateDeptResponse;
import club.luyang.platform.uac.service.dept.DeptService;
import club.luyang.platform.uac.service.dept.ability.DeptCreateAbility;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lu_yang
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/dept")
public class DeptController {

	private final DeptCreateAbility deptCreateAbility;
	private final DeptService deptService;

	@PostMapping("/create")
	public Result<CreateDeptResponse> createDept(@RequestBody CreateDeptRequest request){
		Long deptId = deptCreateAbility.executeAbility(request);
		return Result.success(new CreateDeptResponse(deptId));
	}

	@PutMapping("/update")
	public Result<Void> updateDept(@RequestBody UpdateDeptRequest request){
		deptService.updateDept(request);
		return Result.success();
	}
}
