package com.programmerbeginner.catalog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuthorUpdateRequestDto implements Serializable {

	@Serial
	private static final long serialVersionUID = -5770952050768354355L;

	private String authorName;
	
	private Long birthDate;

	private List<AddressUpdateRequestDto> addresses;

}
