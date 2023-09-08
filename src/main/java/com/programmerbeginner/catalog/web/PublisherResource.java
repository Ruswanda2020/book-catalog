package com.programmerbeginner.catalog.web;

import java.net.URI;
import java.util.List;

import com.programmerbeginner.catalog.domain.Publisher;
import com.programmerbeginner.catalog.dto.*;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.programmerbeginner.catalog.service.PublisherService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Validated
public class PublisherResource {

	private final PublisherService publisherService;

	@PreAuthorize("hasAuthority('Role_ADMIN')")
	@PostMapping("/v2/publisher")
	public ResponseEntity<Void> createNewPublishers(@RequestBody @Valid List<PublisherCreateRequestDto> dtos) {
		publisherService.createPublishers(dtos);
		return ResponseEntity.created(URI.create("/v1/publisher")).build();
	}

	@PostMapping("/v1/publisher")
	public ResponseEntity<Void> createNewPublisher(@RequestBody @Valid PublisherCreateRequestDto dto) {
		publisherService.createPublisher(dto);
		return ResponseEntity.created(URI.create("/v1/publisher")).build();
	}

	@PutMapping("/v1/publisher/{publisherId}")
	public ResponseEntity<Void> updatePublisher(@PathVariable @Size(max = 36, min = 36, message = "publisher.id.not.uuid") String publisherId,
												@RequestBody @Valid PublisherUpdateRequestDto dto) {
		publisherService.updatePublisher(publisherId, dto);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("v1/publisher/{publisherId}")
	public ResponseEntity<Void> deletePublisher(@PathVariable String publisherId) {
		publisherService.deletePublisher(publisherId);
		return ResponseEntity.ok().build();
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/v1/publisher")
	public ResponseEntity<ResultPageResponseDto<PublisherListResponseDto>> findPublisherList(
			@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
			@RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
			@RequestParam(name = "sortBy", required = true, defaultValue = "name") String sortBy,
			@RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
			@RequestParam(name = "publisherName", required = false) String publisherName) {
		return ResponseEntity.ok().body(publisherService.findPublisherList(pages, limit, sortBy, direction, publisherName));
	}

	@GetMapping("v1/publisher/{publisherId}/detail")
	public ResponseEntity<Publisher> findPublisherDetail(@PathVariable  String publisherId){
		return ResponseEntity.ok().body(publisherService.findPublisher(publisherId));
	}
}
