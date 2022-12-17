package club.luyang.platform.uac.domain.valueobject;

import cn.hutool.core.lang.Assert;

/**
 * 部门ID值对象
 *
 * @author lu_yang
 */
public record DepartmentId(String value) {

	public DepartmentId {
		Assert.notBlank(value, "部门ID为空");
	}
}
