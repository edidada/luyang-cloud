// package club.luyang.platform.uac.service.ability;
//
// import club.luyang.framework.base.api.ResultEnum;
// import club.luyang.framework.base.exception.DataException;
// import club.luyang.framework.base.util.ability.AbilityContext;
// import club.luyang.framework.base.util.ability.BaseAbility;
// import club.luyang.framework.base.util.validation.ValidationUtil;
// import club.luyang.platform.uac.beans.constant.DepartmentConstant;
// import club.luyang.platform.uac.controller.department.request.CreateDepartmentRequest;
// import club.luyang.platform.uac.domain.model.DepartmentDomain;
// import club.luyang.platform.uac.domain.valueobject.DepartmentId;
// import club.luyang.platform.uac.repository.DepartmentRepository;
// import cn.hutool.core.lang.Assert;
// import cn.hutool.core.text.CharSequenceUtil;
// import cn.hutool.core.text.StrPool;
// import cn.hutool.core.util.IdUtil;
// import org.springframework.stereotype.Service;
//
// /**
//  * 创建部门具体能力执行
//  *
//  * @author lu_yang
//  */
// @Service
// public class DepartmentCreateAbility extends BaseAbility<CreateDepartmentRequest, DepartmentId> {
//
// 	public static final String FULL_PATH = "fullPath";
// 	public static final String FULL_NAME = "fullName";
//
// 	private final DepartmentRepository repository;
//
// 	public DepartmentCreateAbility(DepartmentRepository repository) {
// 		this.repository = repository;
// 	}
//
// 	@Override
// 	public void checkHandler(CreateDepartmentRequest request) {
//
// 		ValidationUtil.valid(request);
//
// 		String departmentId = request.getDepartmentId();
// 		String parentDepartmentId = request.getParentDepartmentId();
// 		String departmentName = request.getDepartmentName();
//
// 		if (DepartmentConstant.ROOT_PARENT_ID.equals(parentDepartmentId)) {
// 			Assert.isFalse(repository.hasRootDepartment(), () -> new DataException(ResultEnum.DATA_EXIST, "根部门已存在，请勿重复创建"));
// 			return;
// 		}
//
// 		if (CharSequenceUtil.isNotBlank(departmentId)) {
// 			DepartmentDomain department = repository.getByDepartmentId(departmentId);
// 			Assert.isNull(department, () -> new DataException(ResultEnum.DATA_NOT_FOUND, "部门ID已存在"));
// 		}
//
// 		DepartmentDomain department = repository.getByDepartmentId(parentDepartmentId);
// 		Assert.notNull(department, () -> new DataException(ResultEnum.DATA_NOT_FOUND, "父部门不存在"));
//
// 		boolean hasSame = repository.hasDepartmentNameSame(parentDepartmentId, departmentName);
// 		Assert.isFalse(hasSame, () -> new DataException(ResultEnum.DATA_EXIST, "部门名称已存在，请使用其他名称"));
//
// 		int departmentDepth = department.getFullPath().split(StrPool.SLASH).length;
// 		Assert.isFalse((departmentDepth >= DepartmentConstant.DEPARTMENT_HIERARCHY_DEPTH), () -> new DataException("部门层级已超过最大限度"));
//
// 		AbilityContext.putValue(FULL_PATH, department.getFullPath());
// 		AbilityContext.putValue(FULL_NAME, department.getFullName());
// 	}
//
// 	@Override
// 	public DepartmentId execute(CreateDepartmentRequest request) {
//
// 		String departmentId = CharSequenceUtil.isBlank(request.getDepartmentId()) ? IdUtil.getSnowflakeNextIdStr() : request.getDepartmentId();
// 		String departmentName = request.getDepartmentName();
// 		String parentDepartmentId = request.getParentDepartmentId();
//
// 		DepartmentDomain departmentDomain = new DepartmentDomain();
// 		departmentDomain.setDepartmentId(departmentId);
// 		departmentDomain.setParentDepartmentId(parentDepartmentId);
// 		departmentDomain.setDepartmentName(departmentName);
// 		departmentDomain.setSortNum(request.getSortNum());
// 		if (DepartmentConstant.ROOT_PARENT_ID.equals(parentDepartmentId)) {
// 			departmentDomain.setFullPath(departmentId);
// 			departmentDomain.setFullName(departmentName);
// 		} else {
// 			departmentDomain.setFullPath(AbilityContext.getValue(FULL_PATH) + StrPool.SLASH + departmentId);
// 			departmentDomain.setFullName(AbilityContext.getValue(FULL_NAME) + StrPool.SLASH + departmentName);
// 		}
//
// 		return repository.createDepartment(departmentDomain);
// 	}
// }
