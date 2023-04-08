package com.prgrms.kwonjs.catsearch.common.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.prgrms.kwonjs.catsearch.common.response.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RuntimeException.class)
	public ErrorResponse handleException(HttpServletRequest request, RuntimeException e) {
		log.warn("[RUNTIME_EXCEPTION] ", e);
		log.warn("[REQUEST] {}", request.getRequestURI());
		return ErrorResponse.runtimeException();
	}

	@ResponseStatus(INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ErrorResponse handleException(HttpServletRequest request, Exception e) {
		log.error("[EXCEPTION] ", e);
		log.error("[REQUEST] {}", request.getRequestURI());
		return ErrorResponse.exception();
	}

}

