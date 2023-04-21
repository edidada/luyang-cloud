package club.luyang.platform.uac.dept;

import club.luyang.framework.base.api.ResultEnum;
import club.luyang.framework.base.exception.DataException;
import club.luyang.framework.base.util.validation.ValidationUtil;
import club.luyang.platform.uac.dept.repository.DeptEntity;
import club.luyang.platform.uac.dept.repository.DeptRepository;
import club.luyang.platform.uac.dept.request.UpdateDeptRequest;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.NumberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author lu_yang
 */
@Service
@RequiredArgsConstructor
public class DeptService {

	private final DeptRepository deptRepository;

	public void updateDept(UpdateDeptRequest request) {
		ValidationUtil.valid(request);

		Long deptId = request.getDeptId();
		Long parentDeptId = request.getParentDeptId();
		String deptName = request.getDeptName();

		Assert.isFalse(NumberUtil.equals(deptId, parentDeptId), () -> new DataException(ResultEnum.DATA_EXCEPTION, "不能设置自己为父部门"));
		DeptEntity deptEntity = deptRepository.findById(deptId);
		Assert.notNull(deptEntity, () -> new DataException(ResultEnum.DATA_NOT_FOUND, "部门ID不存在"));
		DeptTool.checkParentDept(deptId, parentDeptId, deptName);

		DeptConvert.INSTANCE.toEntity(request).updateById();
		// TODO: 2022/11/20 更新子孙部门列表路径
	}

	public void addUserToDept(Long userId, Long deptId) {
		boolean hasBindDept = deptRepository.hasBindDept(userId, deptId);
		Assert.isFalse(hasBindDept, () -> new DataException(ResultEnum.DATA_EXIST, "请勿重复绑定部门"));
		deptRepository.saveUserToDept(userId, deptId);
	}
}
