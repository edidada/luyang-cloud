package club.luyang.platform.uac.dept.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lu_yang
 */
@Mapper
public interface DeptMapper extends BaseMapper<DeptEntity> {

    int insertUserToDept(@Param("userId") Long userId, @Param("deptId")Long deptId);

    List<Long> selectUserBindDept(@Param("userId") Long userId);
}
