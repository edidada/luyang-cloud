package club.luyang.platform.uac.dept;

import club.luyang.platform.uac.dept.repository.DeptEntity;
import club.luyang.platform.uac.dept.request.CreateDeptRequest;
import club.luyang.platform.uac.dept.request.UpdateDeptRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 部门相关实体转换
 *
 * @author lu_yang
 */
@Mapper
public interface DeptConvert {

    DeptConvert INSTANCE = Mappers.getMapper(DeptConvert.class);

    /**
     * Create request body -> Entity
     *
     * @param request  创建部门请求体
     * @return DeptEntity 部门实体
     * @author lu_yang
     */
    @Mapping(source = "deptName", target = "name")
    @Mapping(source = "parentDeptId", target = "parentId")
    DeptEntity toEntity(CreateDeptRequest request);

    /**
     * Update request body -> Entity
     *
     * @param request  创建部门请求体
     * @return DeptEntity 部门实体
     * @author lu_yang
     */
    @Mapping(source = "deptName", target = "name")
    @Mapping(source = "parentDeptId", target = "parentId")
    DeptEntity toEntity(UpdateDeptRequest request);
}
