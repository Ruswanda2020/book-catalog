package com.subrutin.catalog.service.impl;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.subrutin.catalog.domain.Publisher;
import com.subrutin.catalog.domain.Author;
import com.subrutin.catalog.domain.Book;
import com.subrutin.catalog.domain.Category;
import com.subrutin.catalog.dto.BookCreateRequestDto;
import com.subrutin.catalog.dto.BookDetailDto;
import com.subrutin.catalog.dto.BookUpdateRequestDto;
import com.subrutin.catalog.exception.BadRequestException;
import com.subrutin.catalog.repository.BookRepository;
import com.subrutin.catalog.service.AuthorService;
import com.subrutin.catalog.service.BookService;
import com.subrutin.catalog.service.CategoryService;
import com.subrutin.catalog.service.PublisherService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service("bookService")
public class BookServiceImpl implements BookService{
	
	private final BookRepository bookRepository;
	
	private final AuthorService authorService;
	
	private final CategoryService categoryService;
	
	private final PublisherService publisherService;

	@Override
	public BookDetailDto findBookDetailById(Long bookId) {
		Book book = bookRepository.findById(bookId).
				orElseThrow(()-> new BadRequestException("book id invalid"));	
		
		BookDetailDto dto = new BookDetailDto();
		dto.setBookId(book.getId());
		//dto.setAuthorName(book.getAuthor().getNama());
		dto.setBookTitle(book.getTitle());
		dto.setBookDescription(book.getDescription());
		return dto;
	}

	@Override
	public List<BookDetailDto> findBookListDetail() {
		List<Book> book = bookRepository.findAll();
		return book.stream().map((b) -> {
			BookDetailDto dto = new BookDetailDto();
			//dto.setAuthorName(b.getAuthor().getNama());
			dto.setBookId(b.getId());
			dto.setBookTitle(b.getTitle());
			dto.setBookDescription(b.getDescription());
			return dto;
		}).collect(Collectors.toList());
	}

	

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
		
		
			Book book = new Book();
			book.setAuthors(authors);
			book.setCategories(categories);
			book.setPublisher(publisher);;
			book.setTitle(dto.getBookTitle());
			book.setDescription(dto.getDescription());
			
			bookRepository.save(book);
			
			System.out.print("test");
	 	
			
	}

	
	
	
	
	

	
	
	

}
