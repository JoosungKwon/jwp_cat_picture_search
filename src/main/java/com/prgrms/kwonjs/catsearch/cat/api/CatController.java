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
import com.prgrms.kwonjs.catsearch.cat.dto.CatSimpleResponse;
import com.prgrms.kwonjs.catsearch.cat.service.CatService;
import com.prgrms.kwonjs.catsearch.common.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cats")
public class CatController {

	private final CatService catService;

	@GetMapping(value = "/random50", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse<List<CatSimpleResponse>>> getRandom50() {

		List<CatSimpleResponse> response = catService.getRandom(50);

		return ResponseEntity.ok()
			.body(new ApiResponse<>(response));
	}

	@GetMapping(value = "/search", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse<List<CatSimpleResponse>>> searchByBreedId(
		@RequestParam(name = "q") String breedId) {

		List<CatSimpleResponse> response = catService.searchBy(breedId);

		return ResponseEntity.ok()
			.body(new ApiResponse<>(response));
	}

	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse<CatResponse>> getByCatId(
		@PathVariable(name = "id") String catId) {

		CatResponse response = catService.getBy(catId);

		return ResponseEntity.ok()
			.body(new ApiResponse<>(response));
	}
}
