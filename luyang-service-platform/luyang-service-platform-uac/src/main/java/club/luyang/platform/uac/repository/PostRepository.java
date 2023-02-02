package club.luyang.platform.uac.repository;

import club.luyang.platform.uac.repository.entity.PostEntity;

/**
 * @author lu_yang
 */
public interface PostRepository {

	PostEntity selectByCode(String code);

	PostEntity selectByName(String name);
}
