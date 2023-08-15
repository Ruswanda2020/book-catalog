package com.programmerbeginner.catalog.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;


import lombok.Data;
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookDetailResponseDto implements Serializable{


	@Serial
	private static final long serialVersionUID = 5613783778117939651L;

	private String bookId;
	private List<AuthorResponseDto> authors;
	private List<CategoryListResponsDto> categories;
	private PublisherResponseDto publisher;
	private String title;
	private String bookDescription;

}
	