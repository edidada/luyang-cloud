package club.luyang.platform.uac.repository.post;

import club.luyang.platform.uac.repository.post.entity.PostEntity;
import club.luyang.platform.uac.repository.post.mapper.PostMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * @author lu_yang
 */
@Repository
public class PostRepositoryImpl extends ServiceImpl<PostMapper, PostEntity> implements PostRepository, IService<PostEntity> {

	@Override
	public PostEntity findByCode(String postCode) {
		return this.lambdaQuery().eq(PostEntity::getPostCode, postCode).one();
	}

	@Override
	public PostEntity findByName(String postName) {
		return this.lambdaQuery().eq(PostEntity::getPostCode, postName).one();
	}

	@Override
	public boolean existById(Long postId) {
		return this.lambdaQuery().eq(PostEntity::getId, postId).exists();
	}

	@Override
	public void removeById(Long postId) {
		if (null != postId) {
			super.removeById(postId);
		}
	}
}
