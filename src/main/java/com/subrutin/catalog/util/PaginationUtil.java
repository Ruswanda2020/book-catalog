package com.subrutin.catalog.util;

import java.util.List;


import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.subrutin.catalog.dto.PublisherListResponseDto;
import com.subrutin.catalog.dto.ResultPageResponseDto;

public class PaginationUtil {
	
	public static <T> ResultPageResponseDto<T> createResultPageDto(List<T> dtos, Integer pages,Long totalElement){
		ResultPageResponseDto<T> result = new ResultPageResponseDto<>();
		
		result.setElements(totalElement);
		result.setPages(pages);
		result.setResult(dtos);
		
		return result;	
	}
	
	public static Sort.Direction getSortBy(String sortBy){
		
		if(sortBy.equalsIgnoreCase("asc")) {
			return Sort.Direction.ASC;
		}else {
			return Sort.Direction.DESC;
		}
		
		
	}
	

}
