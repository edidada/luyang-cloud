package club.luyang.platform.uac.repository;

import club.luyang.platform.uac.domain.model.DepartmentDomain;
import club.luyang.platform.uac.domain.valueobject.DepartmentId;

/**
 * 部门仓储接口
 *
 * @author lu_yang
 */
public interface DepartmentRepository {

	/**
	 * 创建部门
	 *
	 * @param departmentDomain 部门领域实体
	 * @return DepartmentId 部门ID
	 * @author lu_yang
	 */
	DepartmentId createDepartment(DepartmentDomain departmentDomain);

	/**
	 * 根据部门ID更新部门信息
	 *
	 * @param departmentDomain 部门领域实体
	 * @author lu_yang
	 */
	void updateDepartmentById(DepartmentDomain departmentDomain);

	/**
	 * 是否存在根部门
	 *
	 * @return boolean {@code true}存在 {@code false}不存在
	 * @author lu_yang
	 */
	boolean hasRootDepartment();

	/**
	 * 根据部门ID获取部门信息
	 *
	 * @param departmentId 部门ID
	 * @return DepartmentEntity 部门表实体
	 * @author lu_yang
	 */
	DepartmentDomain getByDepartmentId(String departmentId);

	/**
	 * 同级部门名称是否重复
	 *
	 * @param parentId       部门父ID
	 * @param departmentName 部门名
	 * @return boolean {@code true}重复 {@code false}不重复
	 */
	boolean hasDepartmentNameSame(String parentId, String departmentName);
}
