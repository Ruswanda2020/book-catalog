package com.programmerbeginner.catalog.exception;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.programmerbeginner.catalog.dto.ErrorResponsDto;
import com.programmerbeginner.catalog.enums.ErrorCode;




@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
	


	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ErrorResponsDto> resourceNotFound(ResourceNotFound ex, WebRequest request) {
		ArrayList<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		
		ErrorResponsDto errorRespons =  ErrorResponsDto.of("data not found", details, ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND);
		
		return ResponseEntity.badRequest().body(errorRespons);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		// TODO Auto-generated method stub
		return super.handleMethodArgumentNotValid(ex, headers, status, request);
	}
	
	

}
