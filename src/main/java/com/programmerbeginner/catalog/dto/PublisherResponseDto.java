package com.programmerbeginner.catalog.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;



@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublisherResponseDto implements Serializable{


	@Serial
	private static final long serialVersionUID = 4952757090470980303L;

	private String publisherId;
	
	private String publisherName;



}
