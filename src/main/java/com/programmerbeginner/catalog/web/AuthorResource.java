package com.programmerbeginner.catalog.web;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.programmerbeginner.catalog.dto.AuthorCreateRequestDto;
import com.programmerbeginner.catalog.dto.AuthorResponseDto;
import com.programmerbeginner.catalog.dto.AuthorUpdateRequestDto;
import com.programmerbeginner.catalog.service.AuthorService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@Validated
public class AuthorResource {

	private final AuthorService authorService;

	@GetMapping("/v1/author/{id}/detail")
	public ResponseEntity<AuthorResponseDto> findAuthorById(@PathVariable String id) {
		return ResponseEntity.ok().body(authorService.findAuthorById(id));
	}

	@PostMapping("/v1/author/detail")
	public ResponseEntity<Void> createNewAuthor(@RequestBody @Valid AuthorCreateRequestDto dto) {
		authorService.createNewAuthor(dto);
		return ResponseEntity.created(URI.create("/v1/author/detail")).build();
	}

	@PostMapping("/v1/author")
	public ResponseEntity<Void> createNewAuthors(@RequestBody @Valid List<AuthorCreateRequestDto> dto) {
		authorService.createNewAuthorList(dto);
		return ResponseEntity.created(URI.create("/v1/author")).build();
	}

	@PutMapping("/v1/author/{authorId}")
	public ResponseEntity<Void> updateAuthor(@PathVariable String authorId, @RequestBody AuthorUpdateRequestDto dto) {
		authorService.updateAuthor(authorId, dto);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/v1/author/{authorId}")
	public ResponseEntity<Void> deleteAuthor(@PathVariable String authorId) {
		authorService.deletAuthor(authorId);
		return ResponseEntity.ok().build();
	}
}
