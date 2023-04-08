package com.prgrms.kwonjs.catsearch.cat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prgrms.kwonjs.catsearch.cat.dto.CatResponse;
import com.prgrms.kwonjs.catsearch.cat.dto.CatSimpleResponse;
import com.prgrms.kwonjs.catsearch.cat.exception.CatNotFoundException;
import com.prgrms.kwonjs.catsearch.cat.repository.CatRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CatService {

	private final CatRepository catRepository;

	/*
    주어진 매개변수의 수(size) 만큼 랜덤한 고양이 정보(아이디, 품종명, 이미지)를 리스트로 반환
    */
	public List<CatSimpleResponse> getRandom(int size) {
		return catRepository.findByRandom(size)
			.stream()
			.map(CatSimpleResponse::of)
			.toList();
	}

	/*
    품종ID(breedId)에 해당하는 모든 고양이의 정보(아이디, 품종명, 이미지)를 리스트로 반환
    */
	public List<CatSimpleResponse> searchBy(String breedId) {
		return catRepository.findByBreedId(breedId)
			.stream()
			.map(CatSimpleResponse::of)
			.toList();
	}

	/*
	고양이ID(catId)에 해당하는 고양이의 디테일 정보(아이디, 품종명, 이미지, 이미지 크기, 성격, 출신지, 등)을 반환
	*/
	public CatResponse getBy(String catId) {
		return catRepository.findByCatId(catId)
			.map(CatResponse::of)
			.orElseThrow(CatNotFoundException::new);
	}
}
