package com.prgrms.kwonjs.catsearch.cat.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prgrms.kwonjs.catsearch.cat.dto.CatApiData;

@FeignClient(name = "CatClient", url = "${cat-api.default-url}")
public interface CatApiClient {

	@GetMapping(value = "/v1/images/search")
	List<CatApiData> get(@RequestParam(value = "limit") int limit,
		@RequestParam("has_breeds") int hasBreeds);
}