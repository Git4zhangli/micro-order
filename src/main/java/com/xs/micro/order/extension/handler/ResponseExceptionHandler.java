package com.xs.micro.order.extension.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xs.micro.order.domain.pojo.dto.FailResponseEntity;
import com.xs.micro.order.domain.pojo.dto.ResponseEntity;
import com.xs.micro.order.extension.exception.BadOperationException;
import com.xs.micro.order.extension.exception.InvalidAuthException;
import com.xs.micro.order.extension.exception.InvalidParamException;

@ControllerAdvice
@ResponseBody
public class ResponseExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(ResponseExceptionHandler.class);

	@ExceptionHandler
	public ResponseEntity<?> exceptionHandler(InvalidAuthException ex) {
		LOG.info("用户没有登录或登录失效：{}", ex.getMessage());
		return new FailResponseEntity<>(HttpStatus.UNAUTHORIZED.value(), "用户没有登录或登录失效");
	}
	
	@ExceptionHandler
	public ResponseEntity<?> exceptionHandler(IllegalArgumentException ex) {
		LOG.info("参数校验错误：{}", ex.getMessage());
		return new FailResponseEntity<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}

	@ExceptionHandler
	public ResponseEntity<?> exceptionHandler(InvalidParamException ex) {
		LOG.info("参数校验错误：{}", ex.getMessage());
		return new FailResponseEntity<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}

	@ExceptionHandler
	public ResponseEntity<?> exceptionHandler(BadOperationException ex) {
		LOG.info("操作不能执行：{}", ex.getMessage());
		return new FailResponseEntity<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}
	
	@ExceptionHandler
	public ResponseEntity<?> exceptionHandler(RuntimeException ex) {
		LOG.info("操作执行失败：{}", ex.getMessage());
		return new FailResponseEntity<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}

	@ExceptionHandler
	public ResponseEntity<?> exceptionHandler(Exception ex) {
		LOG.info("服务器开小差了，请稍后再试！", ex);
		return new FailResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器开小差了，请稍后再试！");
	}
}
