package com.programmerbeginner.catalog.service;

import java.util.List;
import java.util.Map;

import com.programmerbeginner.catalog.domain.Author;
import com.programmerbeginner.catalog.dto.AuthorCreateRequestDto;
import com.programmerbeginner.catalog.dto.AuthorResponseDto;
import com.programmerbeginner.catalog.dto.AuthorUpdateRequestDto;


public interface AuthorService {
	
	public AuthorResponseDto findAuthorById(String id);
	
	public void createNewAuthorList(List<AuthorCreateRequestDto> dto);
	
	public void createNewAuthor(AuthorCreateRequestDto dto);

	public void updateAuthor(String authorId, AuthorUpdateRequestDto dto);
	
	public void deletAuthor(String authorId);

	public List<Author> findAuthors(List<String> authorIdList);
	
	public List<AuthorResponseDto> construckDto(List<Author> authors);

	public Map<Long, List<String>> findAuthorMap(List<Long> bookIdList);
	
	
	

}
