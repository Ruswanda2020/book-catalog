package com.programmerbeginner.catalog.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.programmerbeginner.catalog.domain.Publisher;
import com.programmerbeginner.catalog.dto.PublisherCreateRequestDto;
import com.programmerbeginner.catalog.dto.PublisherListResponseDto;
import com.programmerbeginner.catalog.dto.PublisherResponseDto;
import com.programmerbeginner.catalog.dto.PublisherUpdateRequestDto;
import com.programmerbeginner.catalog.dto.ResultPageResponseDto;
import com.programmerbeginner.catalog.exception.BadRequestException;
import com.programmerbeginner.catalog.exception.ResourceNotFound;
import com.programmerbeginner.catalog.repository.PublisherRepository;
import com.programmerbeginner.catalog.service.PublisherService;
import com.programmerbeginner.catalog.util.PaginationUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

	private final PublisherRepository publisherRepository;


	@Override
	public void createPublishers(List<PublisherCreateRequestDto> dtos) {
		
		 boolean hasIncompleteData = dtos.stream()
		            .anyMatch(dto -> dto.getPublisherName() == null || dto.getCompanyName() == null || dto.getAddress() == null);

		    if (hasIncompleteData) {
		        throw new BadRequestException("Incomplete data. All fields are required.");
		    }
		
		
		List<Publisher> publishers = dtos.stream()
				.map(dto -> {
					Publisher publisher = new Publisher();
					publisher.setName(dto.getPublisherName());
					publisher.setCompanyName(dto.getCompanyName());
					publisher.setAddress(dto.getAddress());
					return publisher;
				})
				.toList();

		publisherRepository.saveAll(publishers);
	}

	@Override
	public void updatePublisher(String publisherId, PublisherUpdateRequestDto dto) {

		Publisher publisher = publisherRepository.findBySecureId(publisherId)
				.orElseThrow(() -> new ResourceNotFound("invalid.id"));

		publisher.setName(dto.getPublisherName() == null || dto.getPublisherName().isBlank() ? publisher.getName()
				: dto.getPublisherName());
		publisher.setCompanyName(
				dto.getCompanyName() == null || dto.getCompanyName().isBlank() ? publisher.getCompanyName()
						: dto.getCompanyName());
		publisher.setAddress(dto.getAddress());

		publisherRepository.save(publisher);

	}

	@Override
	public ResultPageResponseDto<PublisherListResponseDto> findPublisherList(Integer pages, Integer limit,
			String sortBy, String direction, String publisherName) {

		publisherName = StringUtils.isBlank(publisherName) ? "%" : publisherName + "%";
		Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
		PageRequest pageable = PageRequest.of(pages, limit, sort);
		Page<Publisher> pageResult = publisherRepository.findByNameLikeIgnoreCase(publisherName, pageable);

		List<PublisherListResponseDto> dtos = pageResult.stream().map(p -> {
			PublisherListResponseDto dto = new PublisherListResponseDto();
			dto.setPublisherId(p.getSecureId());
			dto.setPublisherName(p.getName());
			dto.setCompanyName(p.getCompanyName());

			return dto;
		}).toList();

		return PaginationUtil.createResultPageDto(dtos, pageResult.getTotalPages(), pageResult.getTotalElements());

	}


	@Override
	public PublisherResponseDto construckDto(Publisher publisher) {
		PublisherResponseDto dto = new PublisherResponseDto();
		dto.setPublisherId(publisher.getSecureId());
		dto.setPublisherName(publisher.getName());
		return dto;
	}

	@Override
	public void deletePublisher(String publisherId) {
		Publisher publisher = publisherRepository.findBySecureId(publisherId)
				.orElseThrow(()-> new ResourceNotFound("invaliv publisher id"));
		
		publisherRepository.delete(publisher);
		
	}

	
	
	@Override
	public void createPublisher(PublisherCreateRequestDto dto) {
		
		Publisher publisher = new Publisher();
		publisher.setName(dto.getPublisherName());
		publisher.setCompanyName(dto.getCompanyName());
		publisher.setAddress(dto.getAddress());
		
		publisherRepository.save(publisher);
		
	}

	@Override
	public Publisher findPublisher(String publisherId) {
		
		return publisherRepository.findBySecureId(publisherId)
				.orElseThrow(()-> new ResourceNotFound("invalid publisherid"));
	}

	
	
	
}
