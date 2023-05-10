package club.luyang.platform.uac.user.repository;

import club.luyang.framework.mybatisplus.base.BaseEntity;
import club.luyang.platform.uac.user.constant.enums.GenderEnum;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lu_yang
 */
@Getter
@Setter
@TableName("uac_user")
public class UserEntity extends BaseEntity<UserEntity> {

	private String name;
	private String avatar;
	private String remark;
	private GenderEnum gender;
}
