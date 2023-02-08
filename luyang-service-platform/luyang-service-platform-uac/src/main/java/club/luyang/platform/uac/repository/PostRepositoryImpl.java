package club.luyang.platform.uac.repository;

import club.luyang.platform.uac.repository.entity.PostEntity;
import club.luyang.platform.uac.repository.mapper.PostMapper;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * @author lu_yang
 */
@Repository
public class PostRepositoryImpl extends ServiceImpl<PostMapper, PostEntity> implements PostRepository, IService<PostEntity> {

	@Override
	public PostEntity findByCode(String code) {
		Assert.notEmpty(code);
		return this.lambdaQuery().eq(PostEntity::getCode, code).one();
	}

	@Override
	public PostEntity findByName(String name) {
		Assert.notEmpty(name);
		return this.lambdaQuery().eq(PostEntity::getCode, name).one();
	}

	@Override
	public boolean hasPostId(Long id) {
		Assert.notNull(id);
		return this.lambdaQuery().eq(PostEntity::getId, id).exists();
	}

	@Override
	public void removeById(Long id) {
		if (null == id) {
			return;
		}

		super.removeById(id);
	}
}
