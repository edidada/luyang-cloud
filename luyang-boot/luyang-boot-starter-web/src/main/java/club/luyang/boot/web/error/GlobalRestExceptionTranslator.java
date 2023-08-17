package club.luyang.boot.web.error;

import club.luyang.boot.base.api.Result;
import club.luyang.boot.base.api.ResultEnum;
import club.luyang.boot.base.exception.BaseException;
import club.luyang.boot.i18n.I18nUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
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
    public Result<?> handleError(MissingServletRequestParameterException e) {
        String message = String.format("参数缺失: %s", e.getParameterName());
        return Result.failure(ResultEnum.PARAM_MISS, message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<?> handleError(MethodArgumentTypeMismatchException e) {
        String message = String.format("参数类型错误: %s", e.getName());
        return Result.failure(ResultEnum.PARAM_TYPE_ERROR, message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleError(MethodArgumentNotValidException e) {
        String message = this.handleError(e.getBindingResult());
        return Result.failure(ResultEnum.PARAM_VALID_ERROR, message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public Result<?> handleError(ValidationException e) {
        String message = e.getMessage();
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
        logger.error("请求的资源不存在或已删除, uri:{} | caused by: {}", request.getRequestURI(), e.getMessage());
        return Result.failure(ResultEnum.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<?> handleError(HttpMessageNotReadableException e, HttpServletRequest request) {
        logger.error("无法解析请求体, uri:{} | caused by: {}", request.getRequestURI(), e.getMessage());
        return Result.failure(ResultEnum.MSG_NOT_READABLE, e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result<?> handleError(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        logger.error("不支持当前请求方法, uri:{} | caused by: {}", request.getRequestURI(), e.getMessage());
        return Result.failure(ResultEnum.METHOD_NOT_SUPPORTED, e.getMessage());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public Result<?> handleError(HttpMediaTypeNotSupportedException e, HttpServletRequest request) {
        logger.error("不支持当前媒体类型, uri:{} | caused by: {}", request.getRequestURI(), e.getMessage());
        return Result.failure(ResultEnum.MEDIA_TYPE_NOT_SUPPORTED, e.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> handleError(BaseException e) {
        return Result.failure(e.getBaseEnum(), e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> handleError(Throwable e) {
        logger.error("服务器异常", e);
        return Result.failure(ResultEnum.INTERNAL_SERVER_ERROR);
    }
}
