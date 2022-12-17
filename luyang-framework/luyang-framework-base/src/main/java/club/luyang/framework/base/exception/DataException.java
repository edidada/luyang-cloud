package club.luyang.framework.base.exception;

import club.luyang.framework.base.IBaseEnum;
import club.luyang.framework.base.api.ResultEnum;

/**
 * 数据异常
 *
 * @author lu_yang
 */
public class DataException extends BaseException {

	public DataException(String message) {
		super(ResultEnum.DATA_EXCEPTION, message);
	}

	public DataException(IBaseEnum baseEnum, String message) {
		super(baseEnum, message);
	}
}
