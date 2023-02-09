package club.luyang.platform.uac.service;

import club.luyang.framework.base.api.ResultEnum;
import club.luyang.framework.base.exception.DataException;
import club.luyang.framework.base.util.ability.AbilityContext;
import club.luyang.framework.base.util.validation.ValidationUtil;
import club.luyang.platform.uac.beans.constant.DeptConstant;
import club.luyang.platform.uac.beans.request.dept.UpdateDeptRequest;
import club.luyang.platform.uac.repository.DeptRepository;
import club.luyang.platform.uac.repository.entity.DeptEntity;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.StrPool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 部门应用服务
 *
 * @author lu_yang
 */
@Service
@RequiredArgsConstructor
public class DeptService {

	private final DeptRepository deptRepository;

	public void updateDept(UpdateDeptRequest request) {
		ValidationUtil.valid(request);
	}

	public void checkRootDeptExist() {
		boolean exist = deptRepository.hasRootDept();
		Assert.isFalse(exist, () -> new DataException(ResultEnum.DATA_EXIST, "根部门已存在，请勿重复创建"));
	}

	public void checkParentDept(Long parentDeptId, String deptName) {
		checkParentDept(null, parentDeptId, deptName);
	}

	private void checkParentDept(Long deptId, Long parentDeptId, String deptName) {
		DeptEntity entity = deptRepository.findById(parentDeptId);
		Assert.notNull(entity, () -> new DataException(ResultEnum.DATA_NOT_FOUND, "父部门不存在"));

		boolean hasSame = deptRepository.hasDeptNameSame(parentDeptId, deptName);
		Assert.isFalse(hasSame, () -> new DataException(ResultEnum.DATA_EXIST, "部门名称已存在，请使用其他名称"));

		if (null == deptId) {
			int deptDepth = entity.getFullPath().split(StrPool.SLASH).length;
			Assert.isFalse((deptDepth >= DeptConstant.DEPARTMENT_HIERARCHY_DEPTH), () -> new DataException("部门层级已超过最大限度"));
			AbilityContext.putValue(DeptConstant.FULL_PATH, entity.getFullPath());
			AbilityContext.putValue(DeptConstant.FULL_NAME, entity.getFullName());
		}
	}
}
