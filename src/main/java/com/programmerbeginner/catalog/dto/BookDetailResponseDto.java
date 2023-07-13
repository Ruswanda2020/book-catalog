package com.programmerbeginner.catalog.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.programmerbeginner.catalog.domain.Author;

import lombok.Data;
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookDetailResponseDto implements Serializable{


	private static final long serialVersionUID = 1234567L;
	private String bookId;
	private List<AuthorResponseDto> authors;
	private List<CategoryListResponsDto> categories;
	private PublisherResponseDto publisher;
	private String titel;
	private String bookDescription;

}
	