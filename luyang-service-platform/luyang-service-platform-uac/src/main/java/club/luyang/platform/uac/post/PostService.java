package club.luyang.platform.uac.post;

import club.luyang.framework.base.api.ResultEnum;
import club.luyang.framework.base.exception.DataException;
import club.luyang.framework.base.util.validation.ValidationUtil;
import club.luyang.platform.uac.post.repository.PostEntity;
import club.luyang.platform.uac.post.repository.PostRepository;
import club.luyang.platform.uac.post.request.CreatePostRequest;
import club.luyang.platform.uac.post.request.UpdatePostRequest;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.NumberUtil;
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
		checkPostCodeUnique(null, request.getPostCode());
		// 校验岗位名称唯一性
		checkPostNameUnique(null, request.getPostName());
		PostConvert.INSTANCE.toEntity(request).insert();
	}

	public void updatePost(UpdatePostRequest request) {
		ValidationUtil.valid(request);
		Long postId = request.getId();
		// 校验岗位ID是否存在
		checkPostIdExist(postId);
		// 校验岗位编码唯一性
		checkPostCodeUnique(postId, request.getPostCode());
		// 校验岗位名称唯一性
		checkPostNameUnique(postId, request.getPostName());
		PostConvert.INSTANCE.toEntity(request).updateById();
	}

	public void removePost(Long postId) {
		postRepository.removeById(postId);
	}

	private void checkPostIdExist(Long postId) {
		boolean exist = postRepository.existById(postId);
		Assert.isTrue(exist, () -> new DataException(ResultEnum.DATA_NOT_FOUND, "岗位信息不存在"));
	}

	private void checkPostCodeUnique(Long postId, String postCode) {
		PostEntity entity = postRepository.findByCode(postCode);
		if (BeanUtil.isEmpty(entity)) {
			return;
		}

		if (null == postId || !NumberUtil.equals(postId, entity.getId())) {
			throw new DataException(ResultEnum.DATA_EXIST, "岗位编号已存在");
		}
	}

	private void checkPostNameUnique(Long postId, String postName) {
		PostEntity entity = postRepository.findByName(postName);
		if (BeanUtil.isEmpty(entity)) {
			return;
		}

		if (null == postId || !NumberUtil.equals(postId, entity.getId())) {
			throw new DataException(ResultEnum.DATA_EXIST, "岗位名称已存在");
		}
	}
}
