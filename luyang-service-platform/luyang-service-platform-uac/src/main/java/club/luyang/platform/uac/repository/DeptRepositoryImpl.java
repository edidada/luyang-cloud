package club.luyang.platform.uac.repository;

import club.luyang.platform.uac.repository.entity.DeptEntity;
import club.luyang.platform.uac.repository.mapper.DeptMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * 部门仓储接口实现
 *
 * @author lu_yang
 */
@Repository
public class DeptRepositoryImpl extends ServiceImpl<DeptMapper, DeptEntity> implements DeptRepository, IService<DeptEntity> {

	@Override
	public DeptEntity findById(String parentId) {
		return super.getById(parentId);
	}
}
