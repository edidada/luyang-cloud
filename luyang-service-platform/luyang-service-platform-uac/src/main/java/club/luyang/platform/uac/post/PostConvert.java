package club.luyang.platform.uac.post;

import club.luyang.platform.uac.post.repository.PostEntity;
import club.luyang.platform.uac.post.request.CreatePostRequest;
import club.luyang.platform.uac.post.request.UpdatePostRequest;
import org.mapstruct.Mapper;
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
	PostEntity toEntity(CreatePostRequest request);

	/**
	 * Update request body -> Entity
	 *
	 * @param request 更新岗位请求体
	 * @return PostEntity
	 * @author lu_yang
	 */
	PostEntity toEntity(UpdatePostRequest request);
}
