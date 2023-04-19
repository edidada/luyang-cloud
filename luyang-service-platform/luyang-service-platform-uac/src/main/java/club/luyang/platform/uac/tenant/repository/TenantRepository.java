package club.luyang.platform.uac.tenant.repository;

/**
 * @author lu_yang
 */
public interface TenantRepository {

	TenantEntity findById(String tenantId);
}
