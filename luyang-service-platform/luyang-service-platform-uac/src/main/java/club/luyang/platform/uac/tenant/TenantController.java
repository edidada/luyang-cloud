package club.luyang.platform.uac.tenant;

import club.luyang.framework.base.api.Result;
import club.luyang.platform.uac.tenant.request.AddTenantRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lu_yang
 */
@RestController
@RequestMapping("/tenant")
@RequiredArgsConstructor
public class TenantController {

	private final TenantService tenantService;

	@PostMapping
	public Result<Void> addTenant(@RequestBody AddTenantRequest request){
		tenantService.addTenant(request);
		return Result.success();
	}
}
