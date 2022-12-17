package club.luyang.platform.uac.repository;

import club.luyang.platform.uac.constant.DepartmentConstant;
import club.luyang.platform.uac.domain.convert.DepartmentConvert;
import club.luyang.platform.uac.domain.model.DepartmentDomain;
import club.luyang.platform.uac.domain.valueobject.DepartmentId;
import club.luyang.platform.uac.repository.entity.DepartmentEntity;
import club.luyang.platform.uac.repository.mapper.DepartmentMapper;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * 部门仓储接口实现
 *
 * @author lu_yang
 */
@Repository
public class DepartmentRepositoryImpl extends ServiceImpl<DepartmentMapper, DepartmentEntity> implements DepartmentRepository, IService<DepartmentEntity> {

	@Override
	public boolean hasRootDepartment() {
		return this.lambdaQuery()
			.eq(DepartmentEntity::getParentId, DepartmentConstant.ROOT_PARENT_ID)
			.last("limit 1")
			.exists();
	}

	@Override
	public DepartmentDomain getByDepartmentId(String departmentId) {
		Assert.notBlank(departmentId);
		DepartmentEntity departmentEntity = this.getById(departmentId);
		return DepartmentConvert.INSTANCE.toDomain(departmentEntity);
	}

	@Override
	public boolean hasDepartmentNameSame(String parentId, String departmentName) {
		Assert.isFalse(CharSequenceUtil.hasBlank(parentId, departmentName));
		return this.lambdaQuery()
			.eq(DepartmentEntity::getParentId, parentId)
			.eq(DepartmentEntity::getName, departmentName)
			.last("limit 1")
			.exists();
	}

	@Override
	public DepartmentId createDepartment(DepartmentDomain departmentDomain) {
		DepartmentEntity departmentEntity = DepartmentConvert.INSTANCE.toEntity(departmentDomain);
		this.save(departmentEntity);
		return new DepartmentId(departmentEntity.getId());
	}

	@Override
	public void updateDepartmentById(DepartmentDomain departmentDomain) {
		DepartmentEntity departmentEntity = DepartmentConvert.INSTANCE.toEntity(departmentDomain);
		this.updateById(departmentEntity);
	}
}
