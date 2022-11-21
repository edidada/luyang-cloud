package club.luyang.platform.contact.repository.entity;

import club.luyang.framework.mybatis.base.BaseEntity;
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
@TableName("contact_department")
public class DepartmentEntity extends BaseEntity {

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
