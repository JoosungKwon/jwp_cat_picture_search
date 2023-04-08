package com.prgrms.kwonjs.catsearch.cat.model.vo;

import static lombok.AccessLevel.*;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class Breed {

	@Column(name = "breed_id")
	private String id;

	@Column(name = "breed_name")
	private String name;

	@Column
	private String temperament;

	@Column
	private String origin;

	@Builder
	public Breed(String id, String name, String temperament, String origin) {
		this.id = id;
		this.name = name;
		this.temperament = temperament;
		this.origin = origin;
	}
}