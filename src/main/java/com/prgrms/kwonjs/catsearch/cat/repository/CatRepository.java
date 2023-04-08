package com.prgrms.kwonjs.catsearch.cat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prgrms.kwonjs.catsearch.cat.model.Cat;

public interface CatRepository extends JpaRepository<Cat, String> {

	@Query(nativeQuery = true, value = "SELECT * FROM cat ORDER BY RAND() LIMIT :size")
	List<Cat> findByRandom(@Param(value = "size") int size);

	@Query(value = "SELECT c FROM Cat c WHERE c.breed.id = :breedId")
	List<Cat> findByBreedId(@Param(value = "breedId") String breedId);

	@Query(value = "SELECT c FROM Cat c WHERE c.catId = :catId")
	Optional<Cat> findByCatId(@Param(value = "catId") String catId);

	@Modifying
	@Query(nativeQuery = true,
		value = "INSERT IGNORE INTO "
		            + "cat(cat_id, url, width, height, breed_id, breed_name, temperament, origin) "
		        + "VALUES "
		            + "(:#{#cat.catId}, :#{#cat.url}, :#{#cat.width}, :#{#cat.height}, "
		            + ":#{#cat.breedId}, :#{#cat.breedName}, :#{#cat.temperament}, :#{#cat.origin})")
	int ignoreInsert(@Param(value = "cat") Cat cat);
}
