package club.luyang.platform.uac.repository;

import club.luyang.platform.uac.repository.entity.PostEntity;

/**
 * @author lu_yang
 */
public interface PostRepository {

	PostEntity findByCode(String code);

	PostEntity findByName(String name);

	boolean hasPostId(String postId);

	void removeById(String id);
}
