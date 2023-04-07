package com.prgrms.kwonjs.catsearch.cat.dto;

import com.prgrms.kwonjs.catsearch.cat.model.Cat;

public record CatResponse (
	String id,
	String name,
	String url,
	Integer width,
	Integer height,
	String temperament,
	String origin
){
	public static CatResponse of(Cat cat) {
		return new CatResponse(
			cat.getCatId(),
			cat.getBreedName(),
			cat.getUrl(),
			cat.getWidth(),
			cat.getHeight(),
			cat.getTemperament(),
			cat.getOrigin()
		);
	}
}
