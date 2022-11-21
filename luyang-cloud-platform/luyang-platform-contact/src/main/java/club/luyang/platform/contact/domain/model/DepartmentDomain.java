package club.luyang.platform.contact.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lu_yang
 */
@Getter
@Setter
public class DepartmentDomain {

	private String departmentId;

	private String parentDepartmentId;

	private String departmentName;

	private String fullPath;

	private String fullName;

	private int sortNum;
}
