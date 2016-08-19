package net.busonline.api.aop;

import net.busonline.core.model.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ExceptionAdvice {
	private Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Response handleHttpMessageNotReadableException(
			HttpMessageNotReadableException e) {
		logger.error("参数解析失败", e);
		return new Response().failure("could_not_read_json","20100");
	}

	/*@ResponseBody
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Response handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException e) {
		logger.error("不支持当前请求方法", e);
		return new Response().failure("request_method_not_supported");
	}*/

	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Response handleException(Exception e) {
		System.out.println(e);
		logger.error("服务运行异常", e);
		System.out.println("======拦截异常");
		return new Response().failure(e.getMessage(),"500");
	}
}
