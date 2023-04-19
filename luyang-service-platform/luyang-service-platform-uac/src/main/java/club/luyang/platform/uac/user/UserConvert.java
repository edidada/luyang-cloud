package club.luyang.platform.uac.user;

import club.luyang.platform.uac.user.repository.UserEntity;
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

	@Mapping(target = "gender", expression = "java(club.luyang.framework.base.IBaseEnum.getByCode(club.luyang.platform.uac.user.constant.enums.GenderEnum.class, request.gender()))")
	@Mapping(target = "id", source = "userId")
    UserEntity toEntity(AddUserRequest request, long userId);
}
