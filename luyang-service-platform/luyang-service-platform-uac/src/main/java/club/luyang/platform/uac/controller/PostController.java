package club.luyang.platform.uac.controller;

import club.luyang.framework.base.api.PageResult;
import club.luyang.framework.base.api.Result;
import club.luyang.platform.uac.controller.request.CreatePostRequest;
import club.luyang.platform.uac.controller.request.UpdatePostRequest;
import club.luyang.platform.uac.service.PostService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping("/page")
	public Result<PageResult<?>> getPostPage(){
		return null;
	}

	@PostMapping("/create")
	public Result<Void> createPost(@RequestBody CreatePostRequest request){
		postService.createPost(request);
		return Result.success();
	}

	@PutMapping("/update")
	public Result<Void> updatePost(@RequestBody UpdatePostRequest request){
		postService.updatePost(request);
		return Result.success();
	}

	@DeleteMapping("/remove")
	public Result<Void> removePost(@RequestParam("id") String id) {
		postService.removePost(id);
		return Result.success();
	}
}
