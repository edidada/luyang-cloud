package club.luyang.platform.uac.service;

import club.luyang.platform.uac.repository.DeptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 部门应用服务
 *
 * @author lu_yang
 */
@Service
@RequiredArgsConstructor
public class DeptService {

	private final DeptRepository deptRepository;
}
