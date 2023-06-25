package club.luyang.platform.uaa.controller;

import club.luyang.framework.security.annotation.AnonymousAccess;
import cn.hutool.core.date.DateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lu_yang
 */
@RestController
public class TimeController {

    @AnonymousAccess
    @GetMapping("/time")
    public String retrieveTime() {
        return DateUtil.now();
    }
}
