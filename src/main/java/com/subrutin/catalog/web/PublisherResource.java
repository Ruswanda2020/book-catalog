package com.subrutin.catalog.web;

import java.net.URI;
import java.security.PublicKey;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.PublisherCreateRequestDto;
import com.subrutin.catalog.dto.PublisherListResponseDto;
import com.subrutin.catalog.dto.PublisherUpdateRequestDto;
import com.subrutin.catalog.dto.ResultPageResponseDto;
import com.subrutin.catalog.service.PublisherService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor	
public class PublisherResource {
	
	private final PublisherService publisherService;
	
	@PostMapping("/v1/publisher")
	public ResponseEntity<Void> createNewPublisher(@RequestBody @Valid PublisherCreateRequestDto dto){
		publisherService.createPubliser(dto);
		return ResponseEntity.created(URI.create("/V1/publisher")).build();
	}
	
	@PutMapping("/v1/publisher/{publisherId}")
	public ResponseEntity<Void> createNewPublisher(@PathVariable String publisherId,@RequestBody @Valid PublisherUpdateRequestDto dto){
		publisherService.updatePublisher(publisherId, dto);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/v1/publisher")
	public ResponseEntity<ResultPageResponseDto<PublisherListResponseDto>> findPublisherList(
			@RequestParam(name = "pages",required = true,defaultValue = "0")Integer pages,
			@RequestParam(name = "limit",required = true,defaultValue = "10")Integer limit,
			@RequestParam(name = "sortBy",required = true,defaultValue = "name")String sortBy,
			@RequestParam(name = "direction",required = true,defaultValue = "asc")String direction,
			@RequestParam(name = "publisherName",required = false)String pablisherName){
		return ResponseEntity.ok().body(publisherService.findPublisherList(pages, limit, sortBy, direction, pablisherName));
	}
	
}
