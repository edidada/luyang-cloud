package club.luyang.platform.uac.repository;

import club.luyang.platform.uac.repository.entity.DeptEntity;

/**
 * 部门仓储接口
 *
 * @author lu_yang
 */
public interface DeptRepository {

	DeptEntity findById(String parentId);
}
