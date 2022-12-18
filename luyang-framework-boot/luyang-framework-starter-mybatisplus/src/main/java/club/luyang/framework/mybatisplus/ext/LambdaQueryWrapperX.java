package club.luyang.framework.mybatisplus.ext;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * LambdaQueryWrapper 扩展
 *
 * @author lu_yang
 */
public class LambdaQueryWrapperX<T> extends LambdaQueryWrapper<T> {

	/**
	 * like条件值为空则不拼接
	 *
	 * @param column 字段
	 * @param val    字段值
	 * @return LambdaQueryWrapperX<T> mp条件构造器
	 * @author lu_yang
	 */
	public LambdaQueryWrapperX<T> likeIfPresent(SFunction<T, ?> column, String val) {
		if (StringUtils.hasText(val)) {
			return (LambdaQueryWrapperX<T>) super.like(column, val);
		}

		return this;
	}

	/**
	 * in条件值为空则不拼接
	 *
	 * @param column 字段集合
	 * @param values 字段值
	 * @return LambdaQueryWrapperX<T> mp条件构造器
	 * @author lu_yang
	 */
	public LambdaQueryWrapperX<T> inIfPresent(SFunction<T, ?> column, Collection<?> values) {
		if (CollectionUtils.isEmpty(values)) {
			return this;
		}

		return (LambdaQueryWrapperX<T>) super.in(column, values);
	}

	/**
	 * in条件值为空则不拼接
	 *
	 * @param column 字段数组
	 * @param values 字段值
	 * @return LambdaQueryWrapperX<T> mp条件构造器
	 * @author lu_yang
	 */
	public LambdaQueryWrapperX<T> inIfPresent(SFunction<T, ?> column, Object... values) {
		if (null == values || values.length == 0) {
			return this;
		}

		return (LambdaQueryWrapperX<T>) super.in(column, values);
	}

	/**
	 * eq条件值为null则不拼接
	 *
	 * @param column 字段
	 * @param val    字段值
	 * @return LambdaQueryWrapperX<T> mp条件构造器
	 * @author lu_yang
	 */
	public LambdaQueryWrapperX<T> eqIfPresent(SFunction<T, ?> column, Object val) {
		if (null == val) {
			return this;
		}

		return (LambdaQueryWrapperX<T>) super.eq(column, val);
	}

	/**
	 * ne条件值为null则不拼接
	 *
	 * @param column 字段
	 * @param val    字段值
	 * @return LambdaQueryWrapperX<T> mp条件构造器
	 * @author lu_yang
	 */
	public LambdaQueryWrapperX<T> neIfPresent(SFunction<T, ?> column, Object val) {
		if (null == val) {
			return this;
		}

		return (LambdaQueryWrapperX<T>) super.ne(column, val);
	}

	/**
	 * gt条件值为null则不拼接
	 *
	 * @param column 字段
	 * @param val    字段值
	 * @return LambdaQueryWrapperX<T> mp条件构造器
	 * @author lu_yang
	 */
	public LambdaQueryWrapperX<T> gtIfPresent(SFunction<T, ?> column, Object val) {
		if (null == val) {
			return this;
		}

		return (LambdaQueryWrapperX<T>) super.gt(column, val);
	}

	/**
	 * ge条件值为null则不拼接
	 *
	 * @param column 字段
	 * @param val    字段值
	 * @return LambdaQueryWrapperX<T> mp条件构造器
	 * @author lu_yang
	 */
	public LambdaQueryWrapperX<T> geIfPresent(SFunction<T, ?> column, Object val) {
		if (null == val) {
			return this;
		}

		return (LambdaQueryWrapperX<T>) super.ge(column, val);
	}

	/**
	 * lt条件值为null则不拼接
	 *
	 * @param column 字段
	 * @param val    字段值
	 * @return LambdaQueryWrapperX<T> mp条件构造器
	 * @author lu_yang
	 */
	public LambdaQueryWrapperX<T> ltIfPresent(SFunction<T, ?> column, Object val) {
		if (null == val) {
			return this;
		}

		return (LambdaQueryWrapperX<T>) super.lt(column, val);
	}

	/**
	 * le条件值为null则不拼接
	 *
	 * @param column 字段
	 * @param val    字段值
	 * @return LambdaQueryWrapperX<T> mp条件构造器
	 * @author lu_yang
	 */
	public LambdaQueryWrapperX<T> leIfPresent(SFunction<T, ?> column, Object val) {
		if (null == val) {
			return this;
		}

		return (LambdaQueryWrapperX<T>) super.le(column, val);
	}

	/**
	 * 范围拼接 起始值和目标值 不为null则拼接
	 *
	 * @param column 字段
	 * @param val1   起始值
	 * @param val2   目标值
	 * @return LambdaQueryWrapperX<T> mp条件构造器
	 * @author lu_yang
	 */
	public LambdaQueryWrapperX<T> betweenIfPresent(SFunction<T, ?> column, Object val1, Object val2) {
		if (null != val1 && null != val2) {
			return (LambdaQueryWrapperX<T>) super.between(column, val1, val2);
		}

		if (null != val1) {
			return (LambdaQueryWrapperX<T>) ge(column, val1);
		}

		if (null != val2) {
			return (LambdaQueryWrapperX<T>) le(column, val2);

		}

		return this;
	}
}
