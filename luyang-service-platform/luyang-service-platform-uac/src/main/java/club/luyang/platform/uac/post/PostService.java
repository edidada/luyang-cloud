package club.luyang.platform.uac.post;

import club.luyang.framework.base.util.validation.ValidationUtil;
import club.luyang.platform.uac.post.repository.PostRepository;
import club.luyang.platform.uac.post.request.CreatePostRequest;
import club.luyang.platform.uac.post.request.UpdatePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author lu_yang
 */
@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	public void createPost(CreatePostRequest request) {
		ValidationUtil.valid(request);
		// 校验岗位编码唯一性
		PostTool.checkPostCodeUnique(null, request.getPostCode());
		// 校验岗位名称唯一性
		PostTool.checkPostNameUnique(null, request.getPostName());
		PostConvert.INSTANCE.toEntity(request).insert();
	}

	public void updatePost(UpdatePostRequest request) {
		ValidationUtil.valid(request);
		Long postId = request.getId();
		// 校验岗位ID是否存在
		PostTool.checkPostIdExist(postId);
		// 校验岗位编码唯一性
		PostTool.checkPostCodeUnique(postId, request.getPostCode());
		// 校验岗位名称唯一性
		PostTool.checkPostNameUnique(postId, request.getPostName());
		PostConvert.INSTANCE.toEntity(request).updateById();
	}

	public void removePost(Long postId) {
		postRepository.removeById(postId);
	}
}
