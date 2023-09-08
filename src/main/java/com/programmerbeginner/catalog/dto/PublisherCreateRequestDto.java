package com.programmerbeginner.catalog.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.programmerbeginner.catalog.annotasi.LogThisArg;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@LogThisArg
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublisherCreateRequestDto implements Serializable {


	@Serial
	private static final long serialVersionUID = 5478672999825134031L;

	@NotBlank(message = " publisher name must not be blank")
	private String publisherName;
	
	@NotBlank(message = "company name must not be blank")
	private String companyName;
	
	@NotBlank
	private String address;

}
