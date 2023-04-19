package club.luyang.platform.uac.tenant;

import club.luyang.framework.base.util.validation.ValidationUtil;
import club.luyang.platform.uac.tenant.repository.TenantEntity;
import club.luyang.platform.uac.tenant.repository.TenantRepository;
import club.luyang.platform.uac.tenant.request.AddTenantRequest;
import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author lu_yang
 */
@Service
@RequiredArgsConstructor
public class TenantService {

	private final TenantRepository tenantRepository;

	protected void addTenant(AddTenantRequest request) {
		ValidationUtil.valid(request);
		TenantConvert.INSTANCE.toEntity(request).insert();
	}

	public boolean hasTenant(String tenantId) {
		TenantEntity entity = tenantRepository.findById(tenantId);
		return BeanUtil.isNotEmpty(entity);
	}
}
