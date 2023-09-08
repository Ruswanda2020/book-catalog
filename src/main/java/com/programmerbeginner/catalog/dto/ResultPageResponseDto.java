package com.programmerbeginner.catalog.dto;

import java.io.Serial;
import java.io.Serializable;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class ResultPageResponseDto<T> implements Serializable{

	@Serial
	private static final long serialVersionUID = -2309881540829899924L;

	private List<T> result;
	
	private Integer pages;
	
	private Long elements;
	
	

}
