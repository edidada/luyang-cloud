package club.luyang.platform.uac.dept.constant;

import lombok.experimental.UtilityClass;

/**
 * 部门相关常量
 *
 * @author lu_yang
 */
@UtilityClass
public class DeptConstant {

    /** 根部门父ID */
    public static final Long ROOT_PARENT_ID = 0L;

    /** 部门层级深度 */
    public static final int DEPARTMENT_HIERARCHY_DEPTH = 15;

    /** 祖级列表路径 */
    public static final String FULL_PATH = "fullPath";

    /** 祖级列表名称 */
    public static final String FULL_NAME = "fullName";
}
