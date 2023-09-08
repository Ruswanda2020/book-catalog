package com.programmerbeginner.catalog.service.impl;

import java.time.LocalDate;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


import com.programmerbeginner.catalog.domain.Address;
import com.programmerbeginner.catalog.dto.AuthorQueryDto;
import org.springframework.stereotype.Service;

import com.programmerbeginner.catalog.domain.Author;
import com.programmerbeginner.catalog.dto.AuthorCreateRequestDto;
import com.programmerbeginner.catalog.dto.AuthorResponseDto;
import com.programmerbeginner.catalog.dto.AuthorUpdateRequestDto;
import com.programmerbeginner.catalog.exception.BadRequestException;
import com.programmerbeginner.catalog.exception.ResourceNotFound;
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
		.orElseThrow(()-> new ResourceNotFound("Record not found"));
		AuthorResponseDto dto = new AuthorResponseDto();
		dto.setAuthorName(author.getNama());
		dto.setBrithDate(author.getBirthDate().toEpochDay());
		return dto;
	}

	@Override
	public void createNewAuthorList(List<AuthorCreateRequestDto> dtos) {

		List<Author>authors = dtos.stream().map(dto->{
			Author author = new Author();
			author.setNama(dto.getAuthorName());
			author.setBirthDate(LocalDate.ofEpochDay(dto.getBirthDate()));
			List<Address> addresses = dto.getAddresses().stream().map(a -> {
				Address address = new Address();
				address.setStreet(a.getStreet());
				address.setCity(a.getCity());
				address.setZipCode(a.getZipCode());
				address.setAuthor(author);
				return address;
			}).toList();
			author.setAddresses(addresses);
			return author;
		}).toList();
		authorRepository.saveAll(authors);
	}
	
	@Override
	public void updateAuthor(String authorId, AuthorUpdateRequestDto dto) {
		Author author = authorRepository.findBySecureId(authorId)
				.orElseThrow(()-> new ResourceNotFound("invalid author id"));

		Map<Long, Address> addressMap = author.getAddresses()
				.stream()
				.map(a -> a).collect(Collectors.toMap(Address::getId,Function.identity()));

		List<Address> addresses = dto.getAddresses().stream().map(a->{
			Address address = addressMap.get(a.getAddressId());
			address.setStreet(a.getStreet());
			address.setCity(a.getCity());
			address.setZipCode(a.getZipCode());
			return address;
		}).toList();

		author.setAddresses(addresses);
		author.setNama(dto.getAuthorName()==null?author.getNama():dto.getAuthorName());
		author.setBirthDate(dto.getBirthDate()==null?author.getBirthDate():LocalDate.ofEpochDay(dto.getBirthDate()));
		
		authorRepository.save(author);
		
		
	}

	@Override
	public void deletAuthor(String authorId) {
		
				Author author = authorRepository.findBySecureId(authorId)
				.orElseThrow(()-> new BadRequestException("invalid.authorId"));
		
				authorRepository.delete(author);
	}

	@Override
	public List<Author> findAuthors(List<String> authorIdList) {
		List<Author> authors = authorRepository.findBySecureIdIn(authorIdList);
		if(authors.isEmpty())
			throw new ResourceNotFound("INVALID AUTHOR ID");
		return authors;
	}
	@Override
	public List<AuthorResponseDto> construckDto(List<Author> authors) {
		
		return authors.stream().map(a->{
			AuthorResponseDto dto = new AuthorResponseDto();
			dto.setAuthorName(a.getNama());
			dto.setBrithDate(a.getBirthDate().toEpochDay());
			return dto;
		}).toList();
	}

	@Override
	public Map<Long, List<String>> findAuthorMap(List<Long> bookIdList) {
		List<AuthorQueryDto> authorQuery = authorRepository.findAuthorByBookIdList(bookIdList);
		Map<Long, List<String>> authorMaps = new HashMap<>();
		List<String> authorNameList;

		for (AuthorQueryDto a : authorQuery) {
			if (!authorMaps.containsKey(a.getIdBook())) {
				authorNameList = new ArrayList<>();
			} else {
				authorNameList = authorMaps.get(a.getIdBook());
			}
			authorNameList.add(a.getAuthorName());
			authorMaps.put(a.getIdBook(), authorNameList);
		}
		return authorMaps;
	}


	@Override
	public void createNewAuthor(AuthorCreateRequestDto dto) {
		
		Author author = new Author();
		author.setNama(dto.getAuthorName());
		author.setBirthDate(LocalDate.ofEpochDay(dto.getBirthDate()));
		
		authorRepository.saveAndFlush(author);
		
	}

	

	

	

}
