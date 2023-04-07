package com.prgrms.kwonjs.catsearch.cat.service;

import java.net.URI;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prgrms.kwonjs.catsearch.cat.dto.CatApiData;
import com.prgrms.kwonjs.catsearch.cat.dto.CatResponse;
import com.prgrms.kwonjs.catsearch.cat.dto.CatSimpleResponse;
import com.prgrms.kwonjs.catsearch.cat.model.Cat;
import com.prgrms.kwonjs.catsearch.cat.repository.CatRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CatService {

	public static final String CAT_NOT_FOUND = "해당하는 고양이가 없습니다.";

	private final CatApiClient catClient;
	private final CatRepository catRepository;

	private String REQUEST_URI = "https://api.thecatapi.com/v1/images/search/";
	private String API_KEY = "live_mNhy71rfbMXZsW5pYW0c5eWHAZKeLqGIKyKaILKvmjno0H0bHVyaclVQfCKR6LHh";

	public List<CatSimpleResponse> getRandom(int size) {
		return catRepository.findByRandom(size)
			.stream()
			.map(CatSimpleResponse::of)
			.toList();
	}

	public List<CatSimpleResponse> search(String breedId) {
		return catRepository.findByBreedId(breedId)
			.stream()
			.map(CatSimpleResponse::of)
			.toList();
	}

	public CatResponse getBy(String catId) {
		Cat cat = catRepository.findById(catId)
			.orElseThrow(() -> new IllegalArgumentException(CAT_NOT_FOUND));
		return CatResponse.of(cat);
	}

	@Transactional
	public void fillData() {
		log.info("start fill data");
		List<CatApiData> responses = catClient.get(URI.create(REQUEST_URI), API_KEY, 100);
		List<Cat> cats = responses.stream().map(CatApiData::toEntity).toList();
		catRepository.saveAll(cats);
		log.info("finish fill data");
	}
}
