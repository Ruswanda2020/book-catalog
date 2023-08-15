package com.programmerbeginner.catalog.web;

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

import com.programmerbeginner.catalog.dto.BookCreateRequestDto;
import com.programmerbeginner.catalog.dto.BookDetailResponseDto;
import com.programmerbeginner.catalog.dto.BookUpdateRequestDto;
import com.programmerbeginner.catalog.service.BookService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
public class BookResource {
	
	private BookService bookService;
	
	@GetMapping("/v1/book/{bookId}")
	public ResponseEntity<BookDetailResponseDto> findBookDetail(@PathVariable("bookId") String id){	
		BookDetailResponseDto result = bookService.findBookDetailById(id);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/v1/book")
	public ResponseEntity<Void> createNewBook(@RequestBody BookCreateRequestDto dto){
	    bookService.createNewBook(dto);
	    return ResponseEntity.created(URI.create("/v1/book")).build();
	}
	
	@GetMapping("/v1/book")
	public ResponseEntity<List<BookDetailResponseDto>> findBookList(){
		return ResponseEntity.ok().body(bookService.findBookAll());
	}
	
	@PutMapping("/v1/book/{bookId}")
	public ResponseEntity<Void> updateBook(@PathVariable("bookId") Long bookId,
	        @RequestBody BookUpdateRequestDto dto) {
	    bookService.updateBook(bookId, dto);
	    return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/v1/book/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") Long bookId){
		bookService.deleteBook(bookId);
		return ResponseEntity.ok().build();
	}

}
