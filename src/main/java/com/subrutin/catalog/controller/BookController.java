package com.subrutin.catalog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.subrutin.catalog.dto.BookCreateRequestDto;
import com.subrutin.catalog.dto.BookDetailDto;
import com.subrutin.catalog.service.BookService;

import jakarta.validation.Valid;

import org.springframework.ui.Model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/book")
public class BookController {

	private final BookService bookService;

	@GetMapping("/list")
	public String findBookList(Model model) {
		List<BookDetailDto> books = bookService.findBookListDetail();
		model.addAttribute("book", books);
		return "book/list";
	}

	@GetMapping("/new")
	public String loadBookFrom(Model model) {
		BookCreateRequestDto dto = new BookCreateRequestDto();
		model.addAttribute("bookCreateDto", dto);
		return "/book/book-new";

	}

	@PostMapping("/new")
	public String addNewBook(@ModelAttribute("bookCreateDto") @Valid BookCreateRequestDto dto, Model model) {

		bookService.createNewBook(dto);
		return "redirect:/book/list";
	}

}
