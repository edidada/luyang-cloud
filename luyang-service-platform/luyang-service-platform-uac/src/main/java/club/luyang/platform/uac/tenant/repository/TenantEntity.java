package club.luyang.platform.uac.tenant.repository;

import club.luyang.framework.mybatisplus.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lu_yang
 */
@Getter
@Setter
@TableName("uac_tenant")
public class TenantEntity extends BaseEntity<TenantEntity> {

	private String name;
}
