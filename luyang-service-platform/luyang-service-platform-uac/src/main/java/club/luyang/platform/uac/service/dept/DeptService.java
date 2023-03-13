package club.luyang.platform.uac.service.dept;

import club.luyang.framework.base.api.ResultEnum;
import club.luyang.framework.base.exception.DataException;
import club.luyang.framework.base.util.ability.AbilityContext;
import club.luyang.framework.base.util.validation.ValidationUtil;
import club.luyang.platform.uac.beans.constant.DeptConstant;
import club.luyang.platform.uac.beans.request.dept.UpdateDeptRequest;
import club.luyang.platform.uac.convert.DeptConvert;
import club.luyang.platform.uac.repository.dept.DeptRepository;
import club.luyang.platform.uac.repository.dept.entity.DeptEntity;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.NumberUtil;
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

	public synchronized void updateDept(UpdateDeptRequest request) {
		ValidationUtil.valid(request);

		Long deptId = request.getDeptId();
		Long parentDeptId = request.getParentDeptId();
		String deptName = request.getDeptName();

		Assert.isFalse(NumberUtil.equals(deptId, parentDeptId), () -> new DataException(ResultEnum.DATA_EXCEPTION, "不能设置自己为父部门"));
		DeptEntity deptEntity = deptRepository.findById(deptId);
		Assert.notNull(deptEntity, () -> new DataException(ResultEnum.DATA_NOT_FOUND, "部门ID不存在"));
		checkParentDept(deptId, parentDeptId, deptName);

		DeptConvert.INSTANCE.toEntity(request).updateById();
		// TODO: 2022/11/20 更新子孙部门列表路径
	}

	public void checkRootDeptExist() {
		boolean exist = deptRepository.hasRootDept();
		Assert.isFalse(exist, () -> new DataException(ResultEnum.DATA_EXIST, "根部门已存在，请勿重复创建"));
	}

	public void checkParentDept(Long parentDeptId, String deptName) {
		checkParentDept(null, parentDeptId, deptName);
	}

	/**
	 * 父部门创建或更新校验 部门ID为空表示新增
	 *
	 * @param deptId       部门ID
	 * @param parentDeptId 父部门ID
	 * @param deptName     部门名称
	 * @author lu_yang
	 */
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
			return;
		}

		boolean matchPid = DeptConstant.ROOT_PARENT_ID.equals(parentDeptId) && !deptId.equals(entity.getId());
		Assert.isFalse(matchPid, () -> new DataException(ResultEnum.DATA_EXCEPTION, "根部门ID不匹配"));
	}
}
