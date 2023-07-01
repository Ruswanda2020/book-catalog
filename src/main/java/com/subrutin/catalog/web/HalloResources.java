package com.subrutin.catalog.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.HelloMessageResponsDto;
import com.subrutin.catalog.service.GreetingService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class HalloResources {
	
	private GreetingService greetingService;
	
	public HalloResources(GreetingService greetingService) {
		super();
		this.greetingService = greetingService;
	}
	
	//get adalah salah satu jenis http method/http verb
	//post,put,delete,option,trace,head,patch
	@GetMapping("/hello")
    public ResponseEntity<HelloMessageResponsDto> helloWorld(){
		log.info("this is info ");
		log.debug("this is debug");
		log.warn("this is warning");
		log.error("this is eror");
		log.trace("this is trac");
		HelloMessageResponsDto dto = new HelloMessageResponsDto();
		dto.setMessage(greetingService.sayGreeting());
        return ResponseEntity.accepted().body(dto);
    }

}
