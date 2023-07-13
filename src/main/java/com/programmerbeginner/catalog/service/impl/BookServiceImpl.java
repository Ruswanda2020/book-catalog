package com.programmerbeginner.catalog.service.impl;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.programmerbeginner.catalog.domain.Author;
import com.programmerbeginner.catalog.domain.Book;
import com.programmerbeginner.catalog.domain.Category;
import com.programmerbeginner.catalog.domain.Publisher;
import com.programmerbeginner.catalog.dto.BookCreateRequestDto;
import com.programmerbeginner.catalog.dto.BookDetailResponseDto;
import com.programmerbeginner.catalog.dto.BookUpdateRequestDto;
import com.programmerbeginner.catalog.exception.BadRequestException;
import com.programmerbeginner.catalog.repository.BookRepository;
import com.programmerbeginner.catalog.service.AuthorService;
import com.programmerbeginner.catalog.service.BookService;
import com.programmerbeginner.catalog.service.CategoryService;
import com.programmerbeginner.catalog.service.PublisherService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookServiceImpl implements BookService{
	
	private final BookRepository bookRepository;
	
	private final AuthorService authorService;
	
	private final CategoryService categoryService;
	
	private final PublisherService publisherService;

	@Override
	public BookDetailResponseDto findBookDetailById(String bookId) {
		Book book = bookRepository.findBySecureId(bookId).
				orElseThrow(()-> new BadRequestException("book id invalid"));	
		
		BookDetailResponseDto dto = new BookDetailResponseDto();
		dto.setBookId(book.getSecureId());
		dto.setCategories(categoryService.construckDto(book.getCategories()));	
		dto.setAuthors(authorService.construckDto(book.getAuthors()));
		dto.setPublisher(publisherService.construckDto(book.getPublisher()));
		dto.setTitel(book.getTitle());
		dto.setBookDescription(book.getDescription());
		return dto;
	}

	/* @Override
	public List<BookDetailResponseDto> findBookListDetail() {
		List<Book> book = bookRepository.findAll();
		return book.stream().map((b) -> {
			BookDetailResponseDto dto = new BookDetailResponseDto();
			//dto.setAuthorName(b.getAuthor().getNama());
			dto.setBookId(b.getId());
			dto.setBookTitle(b.getTitle());
			dto.setBookDescription(b.getDescription());
			return dto;
		}).collect(Collectors.toList());
	}

	*/

	@Override
	public void updateBook(Long bookId, BookUpdateRequestDto dto) {
		
		Book book = bookRepository.findById(bookId).
				orElseThrow(()-> new BadRequestException("book id invalid"));
		book.setTitle(dto.getBookTitle());
		book.setDescription(dto.getDescription());
		bookRepository.save(book);
		
	}

	@Override
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
		
	}

	@Override
	public void createNewBook(BookCreateRequestDto dto) {
		
		List<Author> authors = authorService.findAuthors(dto.getAuthorIdList());
		List<Category> categories = categoryService.findCategories(dto.getCategoryList());
		Publisher publisher = publisherService.findPublisher(dto.getPublisherId());
		
		log.info(authors.toString());
		log.info(categories.toString());
		log.info(publisher.getId().toString());
		
			Book book = new Book();
			book.setAuthors(authors);
			book.setCategories(categories);
			book.setPublisher(publisher);;
			book.setTitle(dto.getBookTitle());
			book.setDescription(dto.getDescription());
			
			bookRepository.save(book);
			
			log.info(book.getTitle());
			
			
			
			
			
	}

	@Override
	public List<BookDetailResponseDto> findBookListDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	

	
	
	

}