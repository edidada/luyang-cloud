package club.luyang.platform.uac.user;

import club.luyang.platform.uac.user.request.AddUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author lu_yang
 */
@Mapper
public interface UserConvert {

	UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

	@Mapping(target = "userGender", expression = "java(club.luyang.framework.base.IBaseEnum.getByCode(club.luyang.platform.uac.user.enums.UserGenderEnum.class, request.getUserGender()))")
	UserEntity toEntity(AddUserRequest request);
}
