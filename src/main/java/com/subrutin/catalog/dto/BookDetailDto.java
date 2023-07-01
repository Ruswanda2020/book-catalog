package com.subrutin.catalog.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookDetailDto implements Serializable{


	private static final long serialVersionUID = 1234567L;
	private Long bookId;
	private String authorName;
	private String bookTitle;
	private String bookDescription;

}
	