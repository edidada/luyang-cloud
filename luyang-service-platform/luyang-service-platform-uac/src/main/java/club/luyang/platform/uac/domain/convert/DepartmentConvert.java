package club.luyang.platform.uac.domain.convert;

import club.luyang.platform.uac.controller.department.request.UpdateDepartmentRequest;
import club.luyang.platform.uac.domain.model.DepartmentDomain;
import club.luyang.platform.uac.repository.entity.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 部门相关实体转换
 *
 * @author lu_yang
 */
@Mapper
public interface DepartmentConvert {

	DepartmentConvert INSTANCE = Mappers.getMapper(DepartmentConvert.class);

	/**
	 * Update request body -> Domain
	 *
	 * @param request  更新部门请求体
	 * @param fullPath 祖级列表路径
	 * @param fullName 祖级列表名称
	 * @return DepartmentDomain 部门领域实体
	 * @author lu_yang
	 */
	DepartmentDomain toDomain(UpdateDepartmentRequest request, String fullPath, String fullName);

	/**
	 * Entity -> Domain
	 *
	 * @param departmentEntity 部门表实体
	 * @return DepartmentDomain 部门领域实体
	 * @author lu_yang
	 */
	@Mapping(source = "id", target = "departmentId")
	@Mapping(source = "parentId", target = "parentDepartmentId")
	@Mapping(source = "name", target = "departmentName")
	DepartmentDomain toDomain(DepartmentEntity departmentEntity);

	/**
	 * Domain -> Entity
	 *
	 * @param departmentDomain 部门领域实体
	 * @return DepartmentEntity 部门表实体
	 * @author lu_yang
	 */
	@Mapping(source = "departmentId", target = "id")
	@Mapping(source = "parentDepartmentId", target = "parentId")
	@Mapping(source = "departmentName", target = "name")
	DepartmentEntity toEntity(DepartmentDomain departmentDomain);
}
