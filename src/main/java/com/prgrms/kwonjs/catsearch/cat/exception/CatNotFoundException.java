package com.prgrms.kwonjs.catsearch.cat.exception;

public class CatNotFoundException extends RuntimeException{

	public static final String CAT_NOT_FOUND = "해당하는 고양이를 찾지 못했습니다.";

	public CatNotFoundException() {
		super(CAT_NOT_FOUND);
	}

	public CatNotFoundException(String message) {
		super(message);
	}

}
