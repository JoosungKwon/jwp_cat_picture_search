package com.prgrms.kwonjs.catsearch.cat.dto;

import com.prgrms.kwonjs.catsearch.cat.model.Cat;

public record CatSimpleResponse(
	String id,
	String name,
	String url
) {
	public static CatSimpleResponse of(Cat cat) {
		return new CatSimpleResponse(cat.getCatId(), cat.getBreedName(), cat.getUrl());
	}
}


