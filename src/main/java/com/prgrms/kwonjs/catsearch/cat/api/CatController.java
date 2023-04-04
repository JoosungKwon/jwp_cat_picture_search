package com.prgrms.kwonjs.catsearch.cat.api;

import static org.springframework.http.MediaType.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prgrms.kwonjs.catsearch.cat.dto.CatResponse;
import com.prgrms.kwonjs.catsearch.cat.model.Cat;
import com.prgrms.kwonjs.catsearch.cat.service.CatService;
import com.prgrms.kwonjs.catsearch.common.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cats")
public class CatController {


	private final CatService catService;

	/**
	 * 외부에서 문제를 추가하는 기능 (관리자 기능)
	 */
	@GetMapping(value = "/random50", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse<List<CatResponse>>> getRandom50() {

		List<CatResponse> response = catService.getRandom(50);

		return ResponseEntity.ok()
			.body(new ApiResponse<>(response));
	}


	/**
	 * 외부에서 문제를 추가하는 기능 (관리자 기능)
	 */
	@GetMapping(value = "/search", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse<List<CatResponse>>> search(
		@RequestParam(name = "q") String breeds) {

		List<CatResponse> response = catService.search(breeds);

		return ResponseEntity.ok()
			.body(new ApiResponse<>(response));
	}

	/**
	 * 외부에서 문제를 추가하는 기능 (관리자 기능)
	 */
	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse<Cat>> getById(
		@PathVariable(name = "id") String catId) {

		Cat response = catService.getBy(catId);

		return ResponseEntity.ok()
			.body(new ApiResponse<>(response));
	}
}
