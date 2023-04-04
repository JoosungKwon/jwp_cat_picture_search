package com.prgrms.kwonjs.catsearch.cat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prgrms.kwonjs.catsearch.cat.model.Cat;

public interface CatRepository extends JpaRepository<Cat, String> {
}
