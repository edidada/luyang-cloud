package club.luyang.platform.uac.user;

import club.luyang.framework.mybatisplus.base.BaseEntity;
import club.luyang.platform.uac.user.enums.UserGenderEnum;
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

	private String jobNum;
	private String name;
	private String email;
	private String remark;
	private UserGenderEnum userGender;
}
