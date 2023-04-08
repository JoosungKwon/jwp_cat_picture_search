package com.prgrms.kwonjs.catsearch.cat.repository;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.prgrms.kwonjs.catsearch.cat.model.Cat;
import com.prgrms.kwonjs.catsearch.cat.model.vo.Breed;
import com.prgrms.kwonjs.catsearch.cat.util.CatProvider;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CatRepositoryTest {

	@Autowired
	private CatRepository catRepository;

	@ParameterizedTest
	@ValueSource(ints = {10, 30, 50, 70})
	@DisplayName("랜덤으로 고양이 데이터를 주어진 사이즈 만큼 찾는 쿼리")
	void findCatByRandomSuccessTest(int size) {
		// given
		dataInsert(size);
		// when
		List<Cat> catByRandom = catRepository.findByRandom(size);
		//then
		assertThat(catByRandom).hasSize(size);
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 5})
	@DisplayName("고양이 ID로 해당하는 고양이를 찾는 쿼리")
	void findByCatIdSuccessTest(int size) {
		// given
		dataInsert(size);
		// when
		Optional<Cat> cat = catRepository.findByCatId(String.valueOf(size-1));
		//then
		assertThat(cat).isNotEmpty();
	}

	@Test
	@DisplayName("품종ID으로 해당하는 고양이를 찾는 쿼리")
	void findByBreedIdSuccessTest() {
		// given
		String breedId = "breedId";
		Breed breed = CatProvider.createBreed(breedId);
		List<Cat> cats = CatProvider.create(1, breed);
		catRepository.saveAll(cats);
		// when
		List<Cat> catByBreedId = catRepository.findByBreedId(breedId);
		//then
		assertThat(catByBreedId).isNotEmpty();
		assertThat(catByBreedId.get(0).getBreed()).isEqualTo(breed);
	}

	@Test
	@DisplayName("동일한 데이터는 무시하고 새로운 데이터만 삽입하는 쿼리")
	void ignoreInsertSuccessTest() {
		// 동일한 데이터를 10개 넣어도 1개만 들어 있다.
		for (int i = 0; i < 3; i++) {

			Cat cat = Cat.builder()
				.catId("동일한 데이터")
				.url("testImg")
				.width(100)
				.height(100)
				.build();

			catRepository.ignoreInsert(cat);
		}

		long existSize = catRepository.count();

		assertThat(existSize).isOne();
	}

	private void dataInsert(int size) {
		List<Cat> cats = CatProvider.create(size);
		catRepository.saveAll(cats);
	}

}