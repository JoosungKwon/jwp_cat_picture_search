package com.prgrms.kwonjs.catsearch.cat.model;

import static lombok.AccessLevel.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Cat {

	@Id
	private String id;

	private String name;

	private String url;

	private Integer width;

	private Integer height;

	private String temperament;

	private String origin;

	@Builder
	public Cat(String id, String name, String url, Integer width, Integer height, String temperament, String origin) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.width = width;
		this.height = height;
		this.temperament = temperament;
		this.origin = origin;
	}
}
