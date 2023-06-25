package club.luyang.platform.uac.post;

import club.luyang.framework.base.api.Result;
import club.luyang.platform.uac.post.request.CreatePostRequest;
import club.luyang.platform.uac.post.request.UpdatePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lu_yang
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public Result<Void> createPost(@RequestBody CreatePostRequest request){
        postService.createPost(request);
        return Result.success();
    }

    @PutMapping
    public Result<Void> updatePost(@RequestBody UpdatePostRequest request){
        postService.updatePost(request);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> removePost(@PathVariable("id") Long id) {
        postService.removePost(id);
        return Result.success();
    }
}
