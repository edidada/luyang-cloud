package club.luyang.platform.uac.convert;

import club.luyang.platform.uac.beans.request.post.CreatePostRequest;
import club.luyang.platform.uac.beans.request.post.UpdatePostRequest;
import club.luyang.platform.uac.repository.post.entity.PostEntity;
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
