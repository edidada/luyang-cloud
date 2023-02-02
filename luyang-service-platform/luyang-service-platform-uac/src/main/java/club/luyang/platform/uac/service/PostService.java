package club.luyang.platform.uac.service;

import club.luyang.framework.base.api.ResultEnum;
import club.luyang.framework.base.exception.DataException;
import club.luyang.framework.base.util.validation.ValidationUtil;
import club.luyang.platform.uac.controller.request.CreatePostRequest;
import club.luyang.platform.uac.convert.PostConvert;
import club.luyang.platform.uac.repository.PostRepository;
import club.luyang.platform.uac.repository.entity.PostEntity;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.CharSequenceUtil;
import org.springframework.stereotype.Service;

/**
 * @author lu_yang
 */
@Service
public class PostService {

	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public void createPost(CreatePostRequest request) {
		ValidationUtil.valid(request);
		// 校验岗位编码唯一性
		checkPostCodeUnique(request.getPostCode());
		// 校验岗位名称唯一性
		checkPostNameUnique(request.getPostName());
		PostConvert.INSTANCE.toEntity(request).insert();
	}

	private void checkPostCodeUnique(String code) {
		checkPostCodeUnique(null, code);
	}
	
	private void checkPostCodeUnique(String id, String code){
		PostEntity entity = postRepository.selectByCode(code);
		if (BeanUtil.isEmpty(entity)) {
			return;
		}

		Assert.isFalse(!CharSequenceUtil.equals(id, entity.getId()), () -> new DataException(ResultEnum.DATA_EXIST, "岗位编号已存在，请勿重复创建"));
	}

	private void checkPostNameUnique(String name) {
		checkPostNameUnique(null, name);
	}

	private void checkPostNameUnique(String id, String name){
		PostEntity entity = postRepository.selectByName(name);
		if (BeanUtil.isEmpty(entity)) {
			return;
		}

		Assert.isFalse(!CharSequenceUtil.equals(id, entity.getId()), () -> new DataException(ResultEnum.DATA_EXIST, "岗位名称已存在，请勿重复创建"));
	}
}
