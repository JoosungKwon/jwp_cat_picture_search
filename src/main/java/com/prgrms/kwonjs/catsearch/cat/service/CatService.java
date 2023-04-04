package com.prgrms.kwonjs.catsearch.cat.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prgrms.kwonjs.catsearch.cat.dto.CatResponse;
import com.prgrms.kwonjs.catsearch.cat.model.Cat;
import com.prgrms.kwonjs.catsearch.cat.repository.CatRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CatService {

	public static final String SLASH = "/";
	private final CatClient catClient;
	private final CatRepository catRepository;

	private URI DEFAULT_URI = URI.create("https://api.thecatapi.com/v1/images/search");
	private @Value("api-key") String API_KEY;

	public List<CatResponse> getRandom(int amount) {


		catClient.get(DEFAULT_URI, API_KEY, amount);
		return null;
	}

	public List<CatResponse> search(String breedId) {


		catClient.search(DEFAULT_URI, API_KEY, breedId);
		return null;
	}

	public Cat getBy(String catId) {


		URI requestUri = URI.create(DEFAULT_URI.toString() + SLASH + catId);
		catClient.getById(requestUri, API_KEY);
		return null;
	}
}
