package com.prgrms.kwonjs.catsearch;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.prgrms.kwonjs.catsearch.cat.dto.CatApiData;
import com.prgrms.kwonjs.catsearch.cat.repository.CatRepository;
import com.prgrms.kwonjs.catsearch.cat.service.CatApiClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoadManager implements CommandLineRunner {

	private static final int DEFAULT_DATA_SIZE = 250;
	private static final int MAX_REQUEST_DATA_SIZE = 100;
	private static final int TRUE = 1;

	private final CatApiClient catClient;
	private final CatRepository catRepository;

	/*
	Data Loading
	 */
	@Override
	@Transactional
	public void run(String... args) { // 최초 실행 시점에 데이터를 채우는 메서드 실행

		int dataSize = DEFAULT_DATA_SIZE;

		if (args.length > 0) { // 인자를 통해서 데이터의 개수를 세팅할 수 있게 함
			try {
				dataSize = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				log.warn("Invalid Argument : {}", args[0]);
				log.info("Use Default Data Size : {}", DEFAULT_DATA_SIZE);
			}
		}

		long existDataSize = catRepository.count();

		dataSize -= existDataSize;

		if (dataSize <= 0) { // 채워야할 데이터의 개수가 0보다 작거나 같으면 이미 데이터가 채워져있는 것이므로 채우지 않음
			log.info("already exist data : {} ", existDataSize);
			return;
		}

		log.info("start fill data : need {}", dataSize);

		while (dataSize > 0) {
			int requestDataSize = MAX_REQUEST_DATA_SIZE;

			if (dataSize < requestDataSize) { // 남은 데이터의 개수가 한번에 요청할 수 있는 데이터의 개수보다 작으면 남은 데이터의 개수만큼 요청
				requestDataSize = dataSize;
			}

			List<CatApiData> responses = catClient.get(requestDataSize, TRUE);

			int insertSize = responses.stream()
				.map(CatApiData::toEntity)
				.mapToInt(catRepository::ignoreInsert)
				.sum();

			dataSize -= insertSize;
			log.info("filling data... : remain {} ", dataSize);
		}

		existDataSize = catRepository.count();

		log.info("finish fill data : now {}", existDataSize);
	}

}
