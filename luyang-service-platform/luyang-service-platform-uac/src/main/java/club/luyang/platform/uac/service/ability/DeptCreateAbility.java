package club.luyang.platform.uac.service.ability;

import club.luyang.framework.base.util.ability.AbilityContext;
import club.luyang.framework.base.util.ability.BaseAbility;
import club.luyang.framework.base.util.validation.ValidationUtil;
import club.luyang.platform.uac.beans.constant.DeptConstant;
import club.luyang.platform.uac.beans.request.dept.CreateDeptRequest;
import club.luyang.platform.uac.convert.DeptConvert;
import club.luyang.platform.uac.repository.entity.DeptEntity;
import club.luyang.platform.uac.service.DeptService;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 创建部门具体能力执行
 *
 * @author lu_yang
 */
@Service
@RequiredArgsConstructor
public class DeptCreateAbility extends BaseAbility<CreateDeptRequest, Long> {

	private final DeptService deptService;

	@Override
	public void checkHandler(CreateDeptRequest model) {
		ValidationUtil.valid(model);
		Long parentDeptId = model.getParentDeptId();

		if (DeptConstant.ROOT_PARENT_ID.equals(parentDeptId)) {
			// 校验是否存在根部门
			deptService.checkRootDeptExist();
			return;
		}

		deptService.checkParentDept(parentDeptId, model.getDeptName());
	}

	@Override
	public Long execute(CreateDeptRequest model) {
		Long deptId = IdUtil.getSnowflakeNextId();
		Long parentId = model.getParentDeptId();
		String deptName = model.getDeptName();

		DeptEntity entity = DeptConvert.INSTANCE.toEntity(model);
		entity.setId(deptId);
		if (DeptConstant.ROOT_PARENT_ID.equals(parentId)) {
			entity.setFullPath(deptId.toString());
			entity.setFullName(deptName);
		} else {
			entity.setFullPath(AbilityContext.getValue(DeptConstant.FULL_PATH) + StrPool.SLASH + deptId);
			entity.setFullName(AbilityContext.getValue(DeptConstant.FULL_NAME) + StrPool.SLASH + deptName);
		}

		entity.insert();
		return deptId;
	}
}
