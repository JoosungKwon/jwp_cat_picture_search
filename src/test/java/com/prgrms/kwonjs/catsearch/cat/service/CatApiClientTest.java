package com.prgrms.kwonjs.catsearch.cat.service;

import static com.prgrms.kwonjs.catsearch.cat.util.CatProvider.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prgrms.kwonjs.catsearch.cat.dto.CatApiData;

@ExtendWith(MockitoExtension.class)
class CatApiClientTest {

	@Mock
	private CatApiClient catApiClient;

	@Test
	@DisplayName("고양이 데이터를 가져오기 위해 the cat api 사이트에 Get 요청을 한다.")
	void testGetRandomCatImages() {
		//given
		List<CatApiData> catApiData = new ArrayList<>(List.of(createCatApiData()));
		given(catApiClient.get(anyInt(), anyInt())).willReturn(catApiData);

		//when
		List<CatApiData> response = catApiClient.get(1, 1);

		//then
		then(catApiClient).should().get(1, 1);
		then(catApiClient).shouldHaveNoMoreInteractions();
		assertThat(response.size()).isOne();
		assertThat(response.get(0)).isEqualTo(catApiData.get(0));
	}

}