package club.luyang.platform.uac.user;

import club.luyang.boot.base.api.Result;
import club.luyang.platform.uac.user.bean.UserId;
import club.luyang.platform.uac.user.request.CreateUserRequest;
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
    public Result<UserId> createUser(@RequestBody CreateUserRequest request) {
        UserId userId = userService.createUser(request);
        return Result.success(userId);
    }
}
