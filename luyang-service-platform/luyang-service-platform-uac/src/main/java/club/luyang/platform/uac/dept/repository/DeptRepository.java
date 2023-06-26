package club.luyang.platform.uac.dept.repository;


/**
 * 部门仓储接口
 *
 * @author lu_yang
 */
public interface DeptRepository {

    DeptEntity findById(Long deptId);

    boolean hasRootDept();

    boolean hasDeptNameSame(Long parentDeptId, String deptName);

    boolean hasBindDept(Long userId, Long deptId);

    void saveUserToDept(Long userId, Long deptId);
}
