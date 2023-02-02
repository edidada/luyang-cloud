package club.luyang.platform.uac.controller;

import club.luyang.framework.base.api.Result;
import club.luyang.platform.uac.controller.request.CreatePostRequest;
import club.luyang.platform.uac.service.PostService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lu_yang
 */
@RestController
@RequestMapping("/post")
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping
	public Result<Void> createPost(@RequestBody CreatePostRequest request){
		postService.createPost(request);
		return Result.success();
	}
}
