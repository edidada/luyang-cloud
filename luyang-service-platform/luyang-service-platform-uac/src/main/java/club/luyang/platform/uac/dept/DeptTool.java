package club.luyang.platform.uac.dept;

import club.luyang.framework.base.api.ResultEnum;
import club.luyang.framework.base.exception.DataException;
import club.luyang.framework.base.util.ability.AbilityContext;
import club.luyang.platform.uac.dept.constant.DeptConstant;
import club.luyang.platform.uac.dept.repository.DeptEntity;
import club.luyang.platform.uac.dept.repository.DeptRepository;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.StrPool;
import cn.hutool.extra.spring.SpringUtil;
import lombok.experimental.UtilityClass;

/**
 * @author lu_yang
 */
@UtilityClass
public class DeptTool {

    static DeptRepository deptRepository;

    private static DeptRepository getDeptRepository() {
        return BeanUtil.isNotEmpty(deptRepository) ? deptRepository : SpringUtil.getBean(DeptRepository.class);
    }

    /**
     * 校验是否存在根部门
     */
    static void checkRootDeptExist() {
        boolean exist = getDeptRepository().hasRootDept();
        Assert.isFalse(exist, () -> new DataException(ResultEnum.DATA_EXIST, "根部门已存在，请勿重复创建"));
    }

    static void checkParentDept(Long parentDeptId, String deptName) {
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
    static void checkParentDept(Long deptId, Long parentDeptId, String deptName) {
        DeptEntity entity = getDeptRepository().findById(parentDeptId);
        Assert.notNull(entity, () -> new DataException(ResultEnum.DATA_NOT_FOUND, "父部门不存在"));

        boolean hasSame = getDeptRepository().hasDeptNameSame(parentDeptId, deptName);
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
