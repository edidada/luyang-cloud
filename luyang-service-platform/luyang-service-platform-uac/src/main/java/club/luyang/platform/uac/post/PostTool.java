package club.luyang.platform.uac.post;

import club.luyang.framework.base.api.ResultEnum;
import club.luyang.framework.base.exception.DataException;
import club.luyang.platform.uac.post.repository.PostEntity;
import club.luyang.platform.uac.post.repository.PostRepository;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.experimental.UtilityClass;

/**
 * @author lu_yang
 */
@UtilityClass
public class PostTool {

    static PostRepository postRepository;

    private static PostRepository getPostRepository() {
        return BeanUtil.isNotEmpty(postRepository) ? postRepository : SpringUtil.getBean(PostRepository.class);
    }

    static void checkPostIdExist(Long postId) {
        boolean exist = getPostRepository().existById(postId);
        Assert.isTrue(exist, () -> new DataException(ResultEnum.DATA_NOT_FOUND, "岗位信息不存在"));
    }

    static void checkPostCodeUnique(Long postId, String postCode) {
        PostEntity entity = getPostRepository().findByCode(postCode);
        if (BeanUtil.isEmpty(entity)) {
            return;
        }

        if (null == postId || !NumberUtil.equals(postId, entity.getId())) {
            throw new DataException(ResultEnum.DATA_EXIST, "岗位编号已存在");
        }
    }

    static void checkPostNameUnique(Long postId, String postName) {
        PostEntity entity = getPostRepository().findByName(postName);
        if (BeanUtil.isEmpty(entity)) {
            return;
        }

        if (null == postId || !NumberUtil.equals(postId, entity.getId())) {
            throw new DataException(ResultEnum.DATA_EXIST, "岗位名称已存在");
        }
    }
}
