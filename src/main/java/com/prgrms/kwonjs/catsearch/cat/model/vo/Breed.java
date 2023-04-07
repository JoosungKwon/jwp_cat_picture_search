package com.prgrms.kwonjs.catsearch.cat.model.vo;

import static lombok.AccessLevel.*;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
}