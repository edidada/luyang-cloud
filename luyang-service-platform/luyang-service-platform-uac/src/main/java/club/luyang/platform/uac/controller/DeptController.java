package club.luyang.platform.uac.controller;

import club.luyang.platform.uac.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lu_yang
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/dept")
public class DeptController {

	private final DeptService deptService;
}
