package com.prgrms.kwonjs.catsearch.cat.api;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(args = {"0"})
@Transactional
@Sql(scripts = {"/sql/dummy_data.sql"})
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@AutoConfigureTestDatabase(replace = NONE)
class CatControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("랜덤한 50개의 고양이 목록을 가져옵니다.")
	void getRandom50Test() throws Exception {

		mockMvc.perform(
				get("/cats/random50"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON))
			.andExpect(jsonPath("$.data").exists())
			.andDo(print())
			.andDo(document("getRandom50",
				responseFields(
					fieldWithPath("data").type(ARRAY).description("랜덤한 50개의 고양이 사진 목록입니다."),
					fieldWithPath("data.[].id").type(STRING).description("ID"),
					fieldWithPath("data.[].url").type(STRING).description("이미지 URL"),
					fieldWithPath("data.[].name").type(STRING).description("품종명")
				))
			);
	}

	@Test
	@DisplayName("품종으로 해당하는 고양이 목록을 검색합니다.")
	void searchByBreedIdTest() throws Exception {
		// catRepository.ignoreInsert();
		String breedId = "beng";

		mockMvc.perform(
				get("/cats/search").param("q", breedId))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON))
			.andExpect(jsonPath("$.data").exists())
			.andDo(print())
			.andDo(document("searchByBreedId",
				queryParameters(
					parameterWithName("q").description("검색할 고양이 품종의 아이디")),
				responseFields(
					fieldWithPath("data").type(ARRAY).description("Keyword로 검색된 고양이 사진 목록입니다."),
					fieldWithPath("data.[].id").type(STRING).description("ID"),
					fieldWithPath("data.[].url").type(STRING).description("이미지 URL"),
					fieldWithPath("data.[].name").type(STRING).description("품종명")
				)
			));
	}

	@Test
	@DisplayName("고양이 고유 ID로 고양이 데이터를 가져옵니다.")
	void getByCatIdTest() throws Exception {

		String catId = "xnzzM6MBI";

		mockMvc.perform(get("/cats/{id}", catId))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON))
			.andExpect(jsonPath("$.data").exists())
			.andDo(print())
			.andDo(document("getByCatId",
				pathParameters(
					parameterWithName("id").description("고양이 사진의 id값 입니다")),
				responseFields(
					fieldWithPath("data").type(OBJECT).description("Id로 검색된 고양이 사진 입니다."),
					fieldWithPath("data.id").type(STRING).description("ID"),
					fieldWithPath("data.url").type(STRING).description("이미지 URL"),
					fieldWithPath("data.name").type(STRING).description("품종명"),
					fieldWithPath("data.width").description("이미지 너비"),
					fieldWithPath("data.height").description("이미 높이"),
					fieldWithPath("data.temperament").description("고양이 성"),
					fieldWithPath("data.origin").description("고양이 출신지")
				)));
	}
}