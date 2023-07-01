package com.subrutin.catalog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.subrutin.catalog.domain.Category;
import com.subrutin.catalog.dto.CategoryCreateAndUpdateRequestDto;
import com.subrutin.catalog.dto.CategoryListResponsDto;
import com.subrutin.catalog.dto.ResultPageResponseDto;
import com.subrutin.catalog.exception.BadRequestException;
import com.subrutin.catalog.repository.CategoryRepository;
import com.subrutin.catalog.service.CategoryService;
import com.subrutin.catalog.util.PaginationUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{
	
	private final CategoryRepository categoryRepository;
	
	@Override
	public void createAndUpdateCategory(CategoryCreateAndUpdateRequestDto dto) {
		
		Category category = categoryRepository.findByCode(dto.getCode().toLowerCase()).orElse(new Category());
		
		if(category.getCode() == null) {
			category.setCode(dto.getCode().toLowerCase());
		}
		
		category.setName(dto.getName());
		category.setDescription(dto.getDescription());
		
		categoryRepository.save(category);
		
	}

	@Override
	public ResultPageResponseDto<CategoryListResponsDto> findByCategoryList(Integer pages, Integer limit, String sortBy,
			String direction, String categoryName) {
		
		categoryName = StringUtils.isBlank(categoryName)? "%" :	categoryName + "%";
		Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction),sortBy));
		PageRequest pagable = PageRequest.of(pages, limit,sort);
		Page<Category> pageResult = categoryRepository.findByNameLikeIgnoreCase(categoryName, pagable);
		
		List<CategoryListResponsDto> dtos = pageResult.stream().map((p)->{
			
			CategoryListResponsDto dto = new CategoryListResponsDto();
			
			dto.setCode(p.getCode());	
			dto.setName(p.getName());
			dto.setDirection(p.getDescription());
			return dto;	
			}).collect(Collectors.toList());
		
		return PaginationUtil.createResultPageDto(dtos, pageResult.getTotalPages(), pageResult.getTotalElements());
	}

	@Override
	public List<Category> findCategories(List<String> categoryList) {
		List<Category> categories  = categoryRepository.findByCodeIn(categoryList);
		if(categories.isEmpty())
			throw new BadRequestException("can't empty");
		return categories;
	}
	
	
	
	
	

}
