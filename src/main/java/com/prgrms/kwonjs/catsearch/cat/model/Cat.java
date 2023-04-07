package com.prgrms.kwonjs.catsearch.cat.model;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import com.prgrms.kwonjs.catsearch.cat.model.vo.Breed;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Cat {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String catId;

	@Column(nullable = false, length = 500)
	private String url;

	@Column(nullable = false)
	private Integer width;

	@Column(nullable = false)
	private Integer height;

	@Embedded
	private Breed breed;

	@Builder
	public Cat(String catId, String url, Integer width, Integer height, Breed breed) {
		this.catId = catId;
		this.url = url;
		this.width = width;
		this.height = height;
		this.breed = breed;
	}

	public String getBreedId() {
		if (this.breed == null) {
			return null;
		}
		return this.breed.getId();
	}

	public String getBreedName() {
		if (this.breed == null) {
			return null;
		}
		return this.breed.getName();
	}

	public String getTemperament() {
		if (this.breed == null) {
			return null;
		}
		return this.breed.getTemperament();
	}

	public String getOrigin() {
		if (this.breed == null) {
			return null;
		}
		return this.breed.getOrigin();
	}
}
