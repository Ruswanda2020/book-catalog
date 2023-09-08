package com.programmerbeginner.catalog.web;

import java.net.URI;
import java.util.List;

import com.programmerbeginner.catalog.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.programmerbeginner.catalog.service.BookService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@AllArgsConstructor
@RestController
public class BookResource {

	private final BookService bookService;

	@GetMapping("/v1/book/{bookId}")
	public ResponseEntity<BookDetailResponseDto> findBookDetail(@PathVariable("bookId") String id) {
		BookDetailResponseDto result = bookService.findBookDetailById(id);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/v1/book")
	public ResponseEntity<Void> createNewBook(@RequestBody BookCreateRequestDto dto) {
		bookService.createNewBook(dto);
		return ResponseEntity.created(URI.create("/v1/book")).build();
	}

	@GetMapping("/v2/book")
	public ResponseEntity<ResultPageResponseDto<BookListResponseDto>> findBookListDetail(
			@RequestParam(name = "pages", required = true, defaultValue = "0") Integer page,
			@RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
			@RequestParam(name = "sortBy", required = true, defaultValue = "title") String sortBy,
			@RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
			@RequestParam(name = "bookTitle", required = false, defaultValue = "") String bookTitle,
			@RequestParam(name = "publisherName", required = false, defaultValue = "") String publisherName,
			@RequestParam(name = "authorName", required = false, defaultValue = "") String authorName
	) {
		return ResponseEntity.ok().body(bookService.findBookList(page, limit, sortBy, direction, bookTitle, publisherName, authorName));
	}

	@GetMapping("/v1/book")
	public ResponseEntity<List<BookDetailResponseDto>> findBookList() {
		return ResponseEntity.ok().body(bookService.findBookAll());
	}

	@PutMapping("/v1/book/{bookId}")
	public ResponseEntity<Void> updateBook(@PathVariable("bookId") Long bookId, @RequestBody BookUpdateRequestDto dto) {
		bookService.updateBook(bookId, dto);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/v1/book/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") Long bookId) {
		bookService.deleteBook(bookId);
		return ResponseEntity.ok().build();
	}
}
