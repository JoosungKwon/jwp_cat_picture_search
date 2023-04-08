package com.prgrms.kwonjs.catsearch.cat.util;

import java.util.ArrayList;
import java.util.List;

import com.prgrms.kwonjs.catsearch.cat.dto.CatApiData;
import com.prgrms.kwonjs.catsearch.cat.model.Cat;
import com.prgrms.kwonjs.catsearch.cat.model.vo.Breed;

public class CatProvider {

	public static List<Cat> create(int size) {
		Breed breed = new Breed("testBreedId", "testBreedName", "testTemperament", "testOrigin");
		return create(size, breed);
	}

	public static List<Cat> create(int size, Breed breed) {
		List<Cat> cats = new ArrayList<>();

		for (int i = 0; i < size; i++) {
			Cat cat = Cat.builder()
				.catId(String.valueOf(i))
				.url("testImg")
				.width(100)
				.height(100)
				.breed(breed)
				.build();
			cats.add(cat);
		}

		return cats;
	}

	public static Breed createBreed(String breedId) {
		return new Breed(breedId, "testBreedName",
			"testTemperament", "testOrigin");
	}

	public static Breed createBreed() {
		return createBreed("testBreedId");
	}

	public static CatApiData createCatApiData() {
		return CatApiData.builder()
			.id("testId")
			.url("testUrl")
			.width(100)
			.height(100)
			.breeds(List.of(createBreed()))
			.build();
	}
}
