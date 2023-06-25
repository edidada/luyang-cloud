package club.luyang.platform.uac.dept.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lu_yang
 */
@Mapper
public interface DeptMapper extends BaseMapper<DeptEntity> {

    int insertUserToDept(@Param("userId") Long userId, @Param("deptId")Long deptId);
    List<Long> selectUserBindDept(@Param("userId") Long userId);
}
