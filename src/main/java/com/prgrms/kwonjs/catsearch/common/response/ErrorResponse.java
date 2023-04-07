package com.prgrms.kwonjs.catsearch.common.response;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

public record ErrorResponse(HttpStatus status, String message) {

	public static ErrorResponse runtimeException() {
		return new ErrorResponse(BAD_REQUEST, "올바르지 못한 요청입니다.");
	}

	public static ErrorResponse exception() {
		return new ErrorResponse(INTERNAL_SERVER_ERROR, "서버에 오류가 발생했습니다.");
	}
}
