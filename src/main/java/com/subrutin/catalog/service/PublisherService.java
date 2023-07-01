package com.subrutin.catalog.service;

import com.subrutin.catalog.domain.Publisher;
import com.subrutin.catalog.dto.PublisherCreateRequestDto;
import com.subrutin.catalog.dto.PublisherListResponseDto;
import com.subrutin.catalog.dto.PublisherUpdateRequestDto;
import com.subrutin.catalog.dto.ResultPageResponseDto;


public interface PublisherService {
	
	public void createPubliser(PublisherCreateRequestDto dto);
	
	public void updatePublisher(String publisherId,PublisherUpdateRequestDto dto);
	
	public ResultPageResponseDto<PublisherListResponseDto> findPublisherList(Integer pages,Integer limit,
			String sortBy,String direction, String publisherName);
	
	
	public Publisher findPublisher(String publisherId);
	

}
