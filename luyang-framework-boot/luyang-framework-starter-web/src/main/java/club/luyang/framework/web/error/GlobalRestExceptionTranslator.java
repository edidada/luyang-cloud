package club.luyang.framework.web.error;

import club.luyang.framework.base.api.Result;
import club.luyang.framework.base.api.ResultEnum;
import club.luyang.framework.base.exception.BaseException;
import io.micrometer.core.instrument.config.validate.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * 全局异常处理
 *
 * @author lu_yang
 */
@RestControllerAdvice
@PropertySource(value = "classpath:rest-error.properties", encoding = "UTF-8")
public class GlobalRestExceptionTranslator {

	private static final Logger logger = LoggerFactory.getLogger(GlobalRestExceptionTranslator.class);

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Result<?> handleError(MissingServletRequestParameterException e, HttpServletRequest request) {
		logger.error("缺少请求参数, uri:{}, exception:{}, caused by: {}", request.getRequestURI(), e.getClass(), e.getMessage());
		String message = String.format("缺少必要的请求参数: %s", e.getParameterName());
		return Result.failure(ResultEnum.PARAM_MISS, message);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public Result<?> handleError(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
		logger.error("请求参数格式错误, uri:{}, exception:{}, caused by: {}", request.getRequestURI(), e.getClass(), e.getMessage());
		String message = String.format("请求参数格式错误: %s", e.getName());
		return Result.failure(ResultEnum.PARAM_TYPE_ERROR, message);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Result<?> handleError(MethodArgumentNotValidException e, HttpServletRequest request) {
		String message = this.handleError(e.getBindingResult());
		logger.warn("参数验证失败, uri: {}, caused by: {}", request.getRequestURI(), message);
		return Result.failure(ResultEnum.PARAM_VALID_ERROR, message);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	public Result<?> handleError(ValidationException e, HttpServletRequest request) {
		String message = e.getMessage();
		logger.warn("参数验证失败, uri: {}, caused by: {}", request.getRequestURI(), message);
		return Result.failure(ResultEnum.PARAM_VALID_ERROR, message);
	}

	private String handleError(BindingResult result) {
		FieldError error = result.getFieldError();
		assert error != null;
		return String.format("%s:%s", error.getField(), error.getDefaultMessage());
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Result<?> handleError(NoHandlerFoundException e, HttpServletRequest request) {
		logger.error("404没找到请求, uri:{}, exception:{}, caused by: {}", request.getRequestURI(), e.getClass(), e.getMessage());
		return Result.failure(ResultEnum.NOT_FOUND, e.getMessage());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Result<?> handleError(HttpMessageNotReadableException e, HttpServletRequest request) {
		logger.error("消息不能读取, uri:{}, exception:{}, caused by: {}", request.getRequestURI(), e.getClass(), e.getMessage());
		return Result.failure(ResultEnum.MSG_NOT_READABLE, e.getMessage());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public Result<?> handleError(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
		logger.error("不支持当前请求方法, uri:{}, caused by: {}", request.getRequestURI(), e.getMessage());
		return Result.failure(ResultEnum.METHOD_NOT_SUPPORTED, e.getMessage());
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	public Result<?> handleError(HttpMediaTypeNotSupportedException e, HttpServletRequest request) {
		logger.error("不支持当前媒体类型, uri:{}, exception:{}, caused by: {}", request.getRequestURI(), e.getClass(), e.getMessage());
		return Result.failure(ResultEnum.MEDIA_TYPE_NOT_SUPPORTED, e.getMessage());
	}

	@ExceptionHandler(BaseException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Result<?> handleError(BaseException e) {
		return Result.failure(e.getBaseEnum(), e.getMessage());
	}
}
