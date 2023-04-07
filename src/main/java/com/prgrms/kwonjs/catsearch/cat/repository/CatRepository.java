package com.prgrms.kwonjs.catsearch.cat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prgrms.kwonjs.catsearch.cat.model.Cat;

public interface CatRepository extends JpaRepository<Cat, String> {

	@Query(nativeQuery = true, value = "SELECT * FROM cat ORDER BY RAND() LIMIT :size")
	List<Cat> findByRandom(@Param(value = "size") int size);

	@Query(value = "SELECT c FROM Cat c WHERE c.breed.id = :breedId")
	List<Cat> findByBreedId(@Param(value = "breedId") String breedId);
}
