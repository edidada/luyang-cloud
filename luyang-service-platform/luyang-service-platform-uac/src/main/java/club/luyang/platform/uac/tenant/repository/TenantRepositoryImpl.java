package club.luyang.platform.uac.tenant.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * @author lu_yang
 */
@Repository
public class TenantRepositoryImpl extends ServiceImpl<TenantMapper, TenantEntity> implements TenantRepository {

	@Override
	public TenantEntity findById(String tenantId) {
		return super.getById(tenantId);
	}
}
