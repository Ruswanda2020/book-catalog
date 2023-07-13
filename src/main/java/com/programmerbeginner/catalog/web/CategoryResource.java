package com.programmerbeginner.catalog.web;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.programmerbeginner.catalog.dto.CategoryCreateAndUpdateRequestDto;
import com.programmerbeginner.catalog.dto.CategoryListResponsDto;
import com.programmerbeginner.catalog.dto.ResultPageResponseDto;
import com.programmerbeginner.catalog.service.CategoryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class CategoryResource {
	
	private final CategoryService categoryService;
	
	@PostMapping("/v1/category")
	public ResponseEntity<Void> createUpdateCategory(@RequestBody CategoryCreateAndUpdateRequestDto dto){
		
		categoryService.createAndUpdateCategory(dto);
		
		return ResponseEntity.created(URI.create("/v1/category")).build();
		
	}
	
	@GetMapping("/v1/category")
	public ResponseEntity<ResultPageResponseDto<CategoryListResponsDto>> findByCategoryList(
			@RequestParam( name = "pages" ,required = true , defaultValue = "0")Integer pages,
			@RequestParam( name = "limit",required = true, defaultValue = "10") Integer limit,
			@RequestParam( name = "sortBy",required = true , defaultValue ="name") String name,
			@RequestParam( name = "direction",required = true , defaultValue= "asc") String direction,
			@RequestParam( name = "categoryName",required = false)String categoryName){
		return ResponseEntity.ok().body(categoryService.findByCategoryList(pages, limit, name, direction, categoryName));
		
	}
	
	

}
