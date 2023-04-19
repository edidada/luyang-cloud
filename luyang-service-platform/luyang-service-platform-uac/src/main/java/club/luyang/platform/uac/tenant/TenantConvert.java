package club.luyang.platform.uac.tenant;

import club.luyang.platform.uac.tenant.repository.TenantEntity;
import club.luyang.platform.uac.tenant.request.AddTenantRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 部门相关实体转换
 *
 * @author lu_yang
 */
@Mapper
public interface TenantConvert {

	TenantConvert INSTANCE = Mappers.getMapper(TenantConvert.class);

	TenantEntity toEntity(AddTenantRequest request);
}
