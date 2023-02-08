package club.luyang.platform.uac.service;

import club.luyang.framework.base.api.ResultEnum;
import club.luyang.framework.base.exception.DataException;
import club.luyang.platform.uac.beans.constant.DepartmentConstant;
import club.luyang.platform.uac.controller.department.request.UpdateDepartmentRequest;
import club.luyang.platform.uac.convert.DepartmentConvert;
import club.luyang.platform.uac.domain.model.DepartmentDomain;
import club.luyang.platform.uac.repository.DepartmentRepository;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.CharSequenceUtil;
import org.springframework.stereotype.Service;

/**
 * 部门应用服务
 *
 * @author lu_yang
 */
@Service
public class DepartmentService {

	private final DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	/**
	 * 更新部门
	 *
	 * @param request 更新部门请求体
	 * @author lu_yang
	 */
	public void updateDepartment(UpdateDepartmentRequest request) {

		String departmentId = request.getDepartmentId();
		String parentDepartmentId = request.getParentDepartmentId();
		String departmentName = request.getDepartmentName();

		boolean fasIdSame = CharSequenceUtil.equals(request.getDepartmentId(), request.getParentDepartmentId());
		Assert.isFalse(fasIdSame, () -> new DataException(ResultEnum.DATA_EXCEPTION, "不能设置自己为父部门"));

		DepartmentDomain department = this.departmentRepository.getByDepartmentId(request.getDepartmentId());
		Assert.notNull(department, () -> new DataException(ResultEnum.DATA_NOT_FOUND, "部门ID不存在"));

		DepartmentDomain parentDepartment = this.departmentRepository.getByDepartmentId(request.getParentDepartmentId());
		Assert.notNull(parentDepartment, () -> new DataException(ResultEnum.DATA_NOT_FOUND, "父部门不存在"));

		boolean matchPid = DepartmentConstant.ROOT_PARENT_ID.equals(parentDepartmentId) && !departmentId.equals(parentDepartment.getDepartmentId());
		Assert.isFalse(matchPid, () -> new DataException(ResultEnum.DATA_EXCEPTION, "根部门ID不匹配"));

		boolean hasSame = departmentRepository.hasDepartmentNameSame(parentDepartmentId, departmentName);
		Assert.isFalse(hasSame, () -> new DataException(ResultEnum.DATA_EXCEPTION, "部门名称已存在，请使用其他名称"));

		DepartmentDomain departmentDomain = DepartmentConvert.INSTANCE.toDomain(request, parentDepartment.getFullPath(), parentDepartment.getFullName());
		departmentRepository.updateDepartmentById(departmentDomain);

		if (!CharSequenceUtil.equals(parentDepartmentId, parentDepartment.getParentDepartmentId())) {
			// TODO: 2022/11/20 更新子孙部门列表路径
		}
	}
}
