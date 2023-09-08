package com.programmerbeginner.catalog.service;

import java.util.List;

import com.programmerbeginner.catalog.dto.*;


public interface BookService {
	
	public BookDetailResponseDto findBookDetailById(String id);
	
	public List<BookDetailResponseDto> findBookAll();
	
	public void createNewBook(BookCreateRequestDto dto);
	
	public void updateBook(Long id ,BookUpdateRequestDto dto);	
	
	public void deleteBook(Long id);

	public ResultPageResponseDto<BookListResponseDto> findBookList(Integer page, Integer limit, String sortBy,
																   String direction, String bookTitle, String publisherName, String authorName);

	

}
