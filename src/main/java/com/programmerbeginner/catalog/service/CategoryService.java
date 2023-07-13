package com.programmerbeginner.catalog.service;

import java.util.List;

import com.programmerbeginner.catalog.domain.Category;
import com.programmerbeginner.catalog.dto.CategoryCreateAndUpdateRequestDto;
import com.programmerbeginner.catalog.dto.CategoryListResponsDto;
import com.programmerbeginner.catalog.dto.ResultPageResponseDto;

public interface CategoryService {
	
	public void createAndUpdateCategory(CategoryCreateAndUpdateRequestDto dto);
	
	public ResultPageResponseDto<CategoryListResponsDto > findByCategoryList(Integer pages,Integer limit,
			String sortBy,String direction, String categoryName);
	
	public List<Category> findCategories (List<String> categoryList);
	
	public List<CategoryListResponsDto> construckDto(List<Category> categories);
}
