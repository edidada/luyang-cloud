package club.luyang.platform.uac.repository;

import club.luyang.platform.uac.repository.entity.PostEntity;

/**
 * @author lu_yang
 */
public interface PostRepository {

	PostEntity findByCode(String code);

	PostEntity findByName(String name);

	boolean hasPostId(Long postId);

	void removeById(Long id);
}
