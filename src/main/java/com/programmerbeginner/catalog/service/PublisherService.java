package com.programmerbeginner.catalog.service;

import java.util.List;

import com.programmerbeginner.catalog.domain.Publisher;
import com.programmerbeginner.catalog.dto.PublisherCreateRequestDto;
import com.programmerbeginner.catalog.dto.PublisherListResponseDto;
import com.programmerbeginner.catalog.dto.PublisherResponseDto;
import com.programmerbeginner.catalog.dto.PublisherUpdateRequestDto;
import com.programmerbeginner.catalog.dto.ResultPageResponseDto;


public interface PublisherService {
	
	public void createPublishers(List<PublisherCreateRequestDto> dto);
	
	public void createPublisher(PublisherCreateRequestDto dto);
	
	
	public void updatePublisher(String publisherId,PublisherUpdateRequestDto dto);
	public void deletePublisher(String publisherId); 
	
	public ResultPageResponseDto<PublisherListResponseDto> findPublisherList(Integer pages,Integer limit,
			String sortBy,String direction, String publisherName);
	
	
	public Publisher findPublisher(String publisherId);
	
	public PublisherResponseDto construckDto (Publisher publisher);
	

}
