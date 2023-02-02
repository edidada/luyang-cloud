package club.luyang.framework.mybatisplus.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体类
 *
 * @author lu_yang
 */
public class BaseEntity<T extends Model<?>> extends Model<T> implements Serializable {

	@Serial
	private static final long serialVersionUID = -6169504899532789482L;

	/**
	 * 主键ID
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private String id;

	/**
	 * 已删除 [删除:true] [未删除:false]
	 */
	private Boolean hasDelete;

	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getHasDelete() {
		return hasDelete;
	}

	public void setHasDelete(Boolean hasDelete) {
		this.hasDelete = hasDelete;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
}
