package com.subrutin.catalog.web;

import java.net.URI;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.AuthorCreateRequestDto;
import com.subrutin.catalog.dto.AuthorResponseDto;
import com.subrutin.catalog.dto.AuthorUpdateRequestDto;
import com.subrutin.catalog.service.AuthorService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
public class AuthorResource {
	
	private AuthorService authorService;
	
	@GetMapping("/v1/author/{id}/detail")
	public ResponseEntity<AuthorResponseDto> findAuthorById(@PathVariable String id){
		return ResponseEntity.ok().body( authorService.findAuthorById(id));
	}
	
	@PostMapping("/v1/author")
	public ResponseEntity<Void> createNewAuthor ( @RequestBody @Valid List<AuthorCreateRequestDto> dto){
		authorService.createNewAuthor(dto);
		return ResponseEntity.created(URI.create("/author")).build();
	}
	
	@PutMapping("/author/{authorId}")
	public ResponseEntity<Void> updateAuthor(@PathVariable String authorId,
		 @RequestBody	AuthorUpdateRequestDto dto){
		authorService.updateAuthor(authorId, dto);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/v1/author/{authorId}")
	public ResponseEntity<Void> deleteAuthor (@PathVariable String authorId){
		authorService.deletAuthor(authorId);
		return ResponseEntity.ok().build();
	}
	

}
