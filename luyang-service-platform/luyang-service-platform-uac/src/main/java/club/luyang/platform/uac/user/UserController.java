package club.luyang.platform.uac.user;

import club.luyang.framework.base.api.Result;
import club.luyang.platform.uac.user.request.AddUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lu_yang
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping
	public Result<Void> addUser(@RequestBody AddUserRequest request) {
		userService.addUser(request);
		return Result.success();
	}
}
