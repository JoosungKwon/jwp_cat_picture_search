package com.prgrms.kwonjs.catsearch.cat.dto;

import java.util.List;

import com.prgrms.kwonjs.catsearch.cat.model.Cat;
import com.prgrms.kwonjs.catsearch.cat.model.vo.Breed;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CatApiData {

	private String id;
	private String url;
	private Integer width;
	private Integer height;
	private List<Breed> breeds;

	@Builder
	public CatApiData(String id, String url, Integer width, Integer height, List<Breed> breeds) {
		this.id = id;
		this.url = url;
		this.width = width;
		this.height = height;
		this.breeds = breeds;
	}

	public static Cat toEntity(CatApiData catApiData) {
		Breed breed = null;

		if (catApiData.getBreeds() != null && !catApiData.getBreeds().isEmpty()) {
			breed = catApiData.getBreeds().get(0);
		}

		return Cat.builder()
			.catId(catApiData.getId())
			.url(catApiData.getUrl())
			.width(catApiData.getWidth())
			.height(catApiData.getHeight())
			.breed(breed)
			.build();
	}
}
