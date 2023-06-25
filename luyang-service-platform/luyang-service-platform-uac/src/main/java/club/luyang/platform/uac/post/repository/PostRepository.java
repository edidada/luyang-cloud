package club.luyang.platform.uac.post.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

/**
 * @author lu_yang
 */
@Validated
public interface PostRepository {

    boolean existById(@NotNull Long postId);
    PostEntity findByCode(@NotBlank String postCode);
    PostEntity findByName(@NotBlank String postName);
    void removeById(Long postId);
}
