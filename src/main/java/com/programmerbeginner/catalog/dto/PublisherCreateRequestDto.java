package com.programmerbeginner.catalog.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.programmerbeginner.catalog.annotasi.LogThisArg;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@LogThisArg
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublisherCreateRequestDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	String PublisherName;
	
	@NotBlank(message = "must not blank")
	String companyName;
	
	@NotBlank
	String address;

}
