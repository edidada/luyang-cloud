package club.luyang.platform.uac.dept;

import club.luyang.boot.base.api.Result;
import club.luyang.platform.uac.dept.request.CreateDeptRequest;
import club.luyang.platform.uac.dept.request.UpdateDeptRequest;
import club.luyang.platform.uac.dept.response.AddDeptResponse;
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

    @PostMapping
    public Result<AddDeptResponse> addDept(@RequestBody CreateDeptRequest request){
        Long deptId = deptCreateAbility.executeAbility(request);
        return Result.success(new AddDeptResponse(deptId));
    }

    @PutMapping("/update")
    public Result<Void> updateDept(@RequestBody UpdateDeptRequest request){
        deptService.updateDept(request);
        return Result.success();
    }
}
