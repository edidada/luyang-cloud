package club.luyang.platform.uac.repository.entity;

import club.luyang.framework.mybatisplus.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 部门表实体类
 *
 * @author lu_yang
 */
@Getter
@Setter
@TableName("uac_department")
public class DepartmentEntity extends BaseEntity<DepartmentEntity> {

	/**
	 * 父ID
	 */
	private String parentId;

	/**
	 * 部门名称
	 */
	private String name;

	/**
	 * 祖级列表路径
	 */
	private String fullPath;

	/**
	 * 祖级列表名称
	 */
	private String fullName;

	/**
	 * 排序号，值越大越靠前
	 */
	private int sortNum;
}
