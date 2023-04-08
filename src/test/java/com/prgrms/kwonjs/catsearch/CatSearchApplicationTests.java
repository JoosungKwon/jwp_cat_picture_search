package com.prgrms.kwonjs.catsearch;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.prgrms.kwonjs.catsearch.cat.repository.CatRepository;

@SpringBootTest(args = {"0"})
@Transactional
class CatSearchApplicationTests {

	@Autowired
	private DataLoadManager dataLoadManager;

	@Autowired
	private CatRepository catRepository;

	@Test
	@DisplayName("어플리케이션 실행 테스트")
	void contextLoads() {
	}

	@Disabled
	@ParameterizedTest
	@ValueSource(ints = {50, 100, 125})
	@DisplayName("데이터 로드 테스트")
	void dataLoadTest(int size) {
		dataLoadManager.run(String.valueOf(size));
		assertThat(catRepository.count()).isEqualTo(size);
	}
}
