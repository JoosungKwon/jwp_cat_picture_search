package com.prgrms.kwonjs.catsearch.cat.service;

import java.net.URI;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.prgrms.kwonjs.catsearch.cat.dto.CatApiData;

@FeignClient(name = "CatClient", url = "URL")
public interface CatApiClient {

	@GetMapping
	List<CatApiData> get(
		URI requestUri,
		@RequestHeader(name = "x-api-key") String apiKey,
		@RequestParam(name = "limit") Integer limit
	);

	@GetMapping
	List<CatApiData> search(
		URI requestUri,
		@RequestHeader(name = "x-api-key") String apiKey,
		@RequestParam(name = "breedId") String breedId
	);

	@GetMapping
	CatApiData getById(
		URI requestUri,
		@RequestHeader("x-api-key") String apiKey
	);
}