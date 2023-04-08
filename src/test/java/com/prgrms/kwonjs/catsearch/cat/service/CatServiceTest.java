package com.prgrms.kwonjs.catsearch.cat.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.prgrms.kwonjs.catsearch.cat.dto.CatResponse;
import com.prgrms.kwonjs.catsearch.cat.dto.CatSimpleResponse;
import com.prgrms.kwonjs.catsearch.cat.model.Cat;
import com.prgrms.kwonjs.catsearch.cat.model.vo.Breed;
import com.prgrms.kwonjs.catsearch.cat.repository.CatRepository;
import com.prgrms.kwonjs.catsearch.cat.util.CatProvider;

@SpringBootTest(args = {"0"})
@Transactional
class CatServiceTest {

	@Autowired
	private CatService catService;

	@Autowired
	private CatRepository catRepository;

	@ParameterizedTest
	@ValueSource(ints = {10, 50, 100})
	@DisplayName("매개변수로 받은 값만큼 고양이 데이터를 가져온다.")
	void getRandom(int size) {
		//given
		List<Cat> cats = CatProvider.create(size);
		catRepository.saveAll(cats);

		//when
		List<CatSimpleResponse> result = catService.getRandom(size);

		//then
		assertThat(result).hasSize(size);
		assertThat(result.get(0))
			.hasFieldOrProperty("id")
			.hasFieldOrProperty("name")
			.hasFieldOrProperty("url");
	}

	@ParameterizedTest
	@MethodSource("breedIdAndSize")
	@DisplayName("품종ID로 해당하는 고양이를 찾는다.")
	void searchBy(String breedId, int size) {
		//given
		Breed breed = new Breed(breedId, "name", "temperament", "origin");
		List<Cat> cats = CatProvider.create(size, breed);
		catRepository.saveAll(cats);

		//when
		List<CatSimpleResponse> result = catService.searchBy(breedId);

		//then
		assertThat(result).hasSize(size);
		assertThat(result.get(0))
			.hasFieldOrProperty("id")
			.hasFieldOrProperty("name")
			.hasFieldOrProperty("url");
	}

	@ParameterizedTest
	@ValueSource(strings = {"1", "2", "3"})
	@DisplayName("고양이 ID로 해당하는 고양이를 가져온다.")
	void getBy(String catId) {
		//given
		List<Cat> cats = CatProvider.create(10);
		catRepository.saveAll(cats);

		//when
		CatResponse result = catService.getBy(catId);

		//then
		assertThat(result)
			.hasFieldOrPropertyWithValue("id", catId)
			.hasFieldOrProperty("name")
			.hasFieldOrProperty("url")
			.hasFieldOrProperty("height")
			.hasFieldOrProperty("width")
			.hasFieldOrProperty("temperament")
			.hasFieldOrProperty("origin");
	}

	private static Stream<Arguments> breedIdAndSize(){
		return Stream.of(
			Arguments.of("breed1", 1),
			Arguments.of("breed5", 5),
			Arguments.of("breed10", 10)
		);
	}
}
