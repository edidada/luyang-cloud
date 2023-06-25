package club.luyang.platform.uac.dept.repository;

import club.luyang.platform.uac.dept.constant.DeptConstant;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * 部门仓储接口实现
 *
 * @author lu_yang
 */
@Repository
public class DeptRepositoryImpl extends ServiceImpl<DeptMapper, DeptEntity> implements DeptRepository, IService<DeptEntity> {

    @Override
    public DeptEntity findById(Long parentId) {
        Assert.notNull(parentId);
        return super.getById(parentId);
    }

    @Override
    public boolean hasRootDept() {
        return this.lambdaQuery()
            .eq(DeptEntity::getParentId, DeptConstant.ROOT_PARENT_ID)
            .exists();
    }

    @Override
    public boolean hasDeptNameSame(Long parentId, String deptName) {
        Assert.notNull(parentId);
        Assert.notEmpty(deptName);
        return this.lambdaQuery()
            .eq(DeptEntity::getParentId, parentId)
            .eq(DeptEntity::getName, deptName)
            .last("limit 1")
            .exists();
    }

    @Override
    public boolean hasBindDept(Long userId, Long deptId) {
        List<Long> deptIds = this.baseMapper.selectUserBindDept(userId);
        if (CollUtil.isEmpty(deptIds)) {
            return false;
        }

        return deptIds.stream().anyMatch(item -> Objects.equals(item, deptId));
    }

    @Override
    public void saveUserToDept(Long userId, Long deptId) {
        this.baseMapper.insertUserToDept(userId, deptId);
    }
}
