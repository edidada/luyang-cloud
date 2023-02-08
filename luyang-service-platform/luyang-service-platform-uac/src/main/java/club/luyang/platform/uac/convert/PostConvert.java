package club.luyang.platform.uac.convert;

import club.luyang.platform.uac.beans.request.CreatePostRequest;
import club.luyang.platform.uac.beans.request.UpdatePostRequest;
import club.luyang.platform.uac.repository.entity.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 岗位相关实体转换
 *
 * @author lu_yang
 */
@Mapper
public interface PostConvert {

	PostConvert INSTANCE = Mappers.getMapper(PostConvert.class);

	/**
	 * Create request body -> Entity
	 *
	 * @param request 创建岗位请求体
	 * @return PostEntity
	 * @author lu_yang
	 */
	@Mapping(source = "postCode", target = "code")
	@Mapping(source = "postName", target = "name")
	PostEntity toEntity(CreatePostRequest request);

	/**
	 * Update request body -> Entity
	 *
	 * @param request 更新岗位请求体
	 * @return PostEntity
	 * @author lu_yang
	 */
	@Mapping(source = "postCode", target = "code")
	@Mapping(source = "postName", target = "name")
	PostEntity toEntity(UpdatePostRequest request);
}
