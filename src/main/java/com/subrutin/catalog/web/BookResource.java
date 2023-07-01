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

import com.subrutin.catalog.dto.BookCreateRequestDto;
import com.subrutin.catalog.dto.BookDetailDto;
import com.subrutin.catalog.dto.BookUpdateRequestDto;
import com.subrutin.catalog.service.BookService;

import io.github.resilience4j.core.StopWatch;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
public class BookResource {
	
	private BookService bookService;
	
	@GetMapping("/book/{bookId}")
	public BookDetailDto findBookDetail(@PathVariable("bookId") Long id){
		org.springframework.util.StopWatch stopWatch = new org.springframework.util.StopWatch();
		log.info("start finBookDetail "+ id);
		stopWatch.start();
		bookService.findBookDetailById(id);
		log.info("finish findBookDetail .exceution time "+ stopWatch.getTotalTimeMillis());
		return bookService.findBookDetailById(id);
	}
	
	@PostMapping("/v1/book")
	public ResponseEntity<Void> createNewBook(@RequestBody BookCreateRequestDto dto){
		
		bookService.createNewBook(dto);
		return ResponseEntity.created(URI.create("/v1/book")).build();
		
	}
	
	@GetMapping("/book")
	public ResponseEntity<List<BookDetailDto>> findBookList(){
		return ResponseEntity.ok().body(bookService.findBookListDetail());
	}
	
	@PutMapping("/book/{bookId}")
	public ResponseEntity<Void> updateBook(@PathVariable("bookId") Long bookId,
	        @RequestBody BookUpdateRequestDto dto) {
	    bookService.updateBook(bookId, dto);
	    return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/book/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") Long bookId){
		bookService.deleteBook(bookId);
		return ResponseEntity.ok().build();
	}

}
