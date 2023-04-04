package com.prgrms.kwonjs.catsearch.cat.service;

import java.net.URI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.prgrms.kwonjs.catsearch.cat.model.Cat;

@FeignClient(name = "CatClient", url = "URL")
public interface CatClient {

	@GetMapping
	Cat get(
		URI requestUri,
		@RequestHeader("x-api-key") String apiKey,
		@RequestParam(name = "limit") Integer limit
	);

	@GetMapping
	Cat search(
		URI requestUri,
		@RequestHeader("x-api-key") String apiKey,
		@RequestParam(name = "limit") String breedId
	);

	Cat getById(
		URI requestUri,
		@RequestHeader("x-api-key") String apiKey
	);
}