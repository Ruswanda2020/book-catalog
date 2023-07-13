package com.programmerbeginner.catalog.service;

import java.util.List;

import com.programmerbeginner.catalog.dto.BookCreateRequestDto;
import com.programmerbeginner.catalog.dto.BookDetailResponseDto;
import com.programmerbeginner.catalog.dto.BookUpdateRequestDto;


public interface BookService {
	
	public BookDetailResponseDto findBookDetailById(String id);
	
	public List<BookDetailResponseDto> findBookListDetail();
	
	public void createNewBook(BookCreateRequestDto dto);
	
	public void updateBook(Long id ,BookUpdateRequestDto dto);
	
	public void deleteBook(Long id);

	

}
