package com.programmerbeginner.catalog.web;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.programmerbeginner.catalog.annotasi.LogThisMethod;
import com.programmerbeginner.catalog.dto.PublisherCreateRequestDto;
import com.programmerbeginner.catalog.dto.PublisherListResponseDto;
import com.programmerbeginner.catalog.dto.PublisherResponseDto;
import com.programmerbeginner.catalog.dto.PublisherUpdateRequestDto;
import com.programmerbeginner.catalog.dto.ResultPageResponseDto;
import com.programmerbeginner.catalog.exception.BadRequestException;
import com.programmerbeginner.catalog.service.PublisherService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor	
public class PublisherResource {
	
	private final PublisherService publisherService;

	@PostMapping("/v1/publisher")
	public ResponseEntity<Void> createNewPublishers( @RequestBody @Valid List<PublisherCreateRequestDto> dtos){

		publisherService.createPublishers(dtos);
		return ResponseEntity.created(URI.create("/V1/publisher")).build();
	}
	
	@PostMapping("/v1/publisher/single")
	public ResponseEntity<Void> createNewPublisher( @RequestBody @Valid PublisherCreateRequestDto dto){

		publisherService.createPublisher(dto);
		return ResponseEntity.created(URI.create("/V1/publisher/single")).build();
	}
	

	@PutMapping("/v1/publisher/{publisherId}")
	public ResponseEntity<Void> updatePublisher(@PathVariable String publisherId,@RequestBody @Valid PublisherUpdateRequestDto dto){
		publisherService.updatePublisher(publisherId, dto);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("v1/publisher/{publisherId}")
	public ResponseEntity<Void> deletePublisher(@PathVariable String publisherId){
		publisherService.deletePublisher(publisherId);
		return ResponseEntity.ok().build();
	}
	
	//@LogThisMethod
	@GetMapping("/v1/publisher")
	public ResponseEntity<ResultPageResponseDto<PublisherListResponseDto>> findPublisherList(
			@RequestParam(name = "pages",required = true,defaultValue = "0")Integer pages,
			@RequestParam(name = "limit",required = true,defaultValue = "10")Integer limit,
			@RequestParam(name = "sortBy",required = true,defaultValue = "name")String sortBy,
			@RequestParam(name = "direction",required = true,defaultValue = "asc")String direction,
			@RequestParam(name = "publisherName",required = false)String publisherName){
		return ResponseEntity.ok().body(publisherService.findPublisherList(pages, limit, sortBy, direction, publisherName));
	}
	
}
