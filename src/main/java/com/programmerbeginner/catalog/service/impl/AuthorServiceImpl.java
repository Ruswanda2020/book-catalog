package com.programmerbeginner.catalog.service.impl;

import java.time.LocalDate;


import java.util.List;


import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.programmerbeginner.catalog.domain.Author;
import com.programmerbeginner.catalog.dto.AuthorCreateRequestDto;
import com.programmerbeginner.catalog.dto.AuthorResponseDto;
import com.programmerbeginner.catalog.dto.AuthorUpdateRequestDto;
import com.programmerbeginner.catalog.exception.BadRequestException;
import com.programmerbeginner.catalog.repository.AuthorRepository;
import com.programmerbeginner.catalog.service.AuthorService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService{
	
	private final AuthorRepository authorRepository;
	
	@Override
	public AuthorResponseDto findAuthorById(String id) {
		
		Author author = authorRepository.findBySecureId(id)
		.orElseThrow(()-> new BadRequestException("invalid.authorId"));
		AuthorResponseDto dto = new AuthorResponseDto();
		dto.setAuthorName(author.getNama());
		dto.setBrithDate(author.getBirthDate().toEpochDay());
		return dto;
	}

	@Override
	public void createNewAuthorList(List<AuthorCreateRequestDto> dtos) {
		
		List<Author>autors = dtos.stream().map((dto)->{
			Author author = new Author();
			author.setNama(dto.getAuthorName());
			author.setBirthDate(LocalDate.ofEpochDay(dto.getBirthDate()));
			return author;
		}).collect(Collectors.toList());
		
		authorRepository.saveAll(autors);
		
	}
	
	@Override
	public void updateAuthor(String authorId, AuthorUpdateRequestDto dto) {
		Author author = authorRepository.findBySecureId(authorId)
				.orElseThrow(()-> new BadRequestException("invalid.authorId"));
		
		author.setNama(dto.getAuthorName()==null?author.getNama():dto.getAuthorName());
		author.setBirthDate(dto.getBirthDate()==null?author.getBirthDate():LocalDate.ofEpochDay(dto.getBirthDate()));
		
		authorRepository.save(author);
		
		
	}

	

	@Override
	public void deletAuthor(String authorId) {
		// 1.ambil terlebih dahulu datanya
		//2.kemudian delete
		
		
		Author author = authorRepository.findBySecureId(authorId)
				.orElseThrow(()-> new BadRequestException("invalid.authorId"));
		
		authorRepository.delete(author);
		
		/*
		 * Optional<Author> authorOptional = authorRepository.findByScureId(authorId);
		 * if(authorOptional.isPresent()) { authorRepository.deleteById(authorId); }
		 * else { throw new NoSuchElementException("Author with ID " + authorId +
		 * " not found"); }
		 */
		 
		// authorRepository.deleteById(authorId);
			
		
		  //softdelete //1.select deleted = false 
		
	/*	Author author = authorRepository.findByIdAndDeletedFalse(authorId) 
				 .orElseThrow(()-> new BadRequestException("invalid.authorId"));
		  
		  //2.update deleted = true
		   author.setDeleted(Boolean.TRUE);
		   authorRepository.save(author);
		 
		*/
		
		
	}

	@Override
	public List<Author> findAuthors(List<String> authorIdList) {
		List<Author> authors = authorRepository.findBySecureIdIn(authorIdList);
		if(authors.isEmpty())
			throw new BadRequestException("can't empty");
		return authors;
	}
	@Override
	public List<AuthorResponseDto> construckDto(List<Author> authors) {
		
		return authors.stream().map((a)->{
			AuthorResponseDto dto = new AuthorResponseDto();
			dto.setAuthorName(a.getNama());
			dto.setBrithDate(a.getBirthDate().toEpochDay());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void createNewAuthor(AuthorCreateRequestDto dto) {
		
		Author author = new Author();
		author.setNama(dto.getAuthorName());
		author.setBirthDate(LocalDate.ofEpochDay(dto.getBirthDate()));
		
		authorRepository.saveAndFlush(author);
		
	}

	

	

	

}
