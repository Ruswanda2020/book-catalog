package com.programmerbeginner.catalog.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.programmerbeginner.catalog.dto.ErrorResponseDto;
import com.programmerbeginner.catalog.enums.ErrorCode;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
																  HttpStatusCode status, WebRequest request) {

		List<String> details = new ArrayList<>();
		for (ObjectError or :ex.getBindingResult().getAllErrors()){
			details.add(or.getDefaultMessage());
		}
		ErrorResponseDto errorResponse = ErrorResponseDto.of("invalid data ",details, ErrorCode.INVALID_DATA, HttpStatus.BAD_REQUEST);
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ErrorResponseDto> resourceNotFound(ResourceNotFound ex, WebRequest request) {
		ArrayList<String> details = new ArrayList<>();
		details.add(ex.getMessage());

		ErrorResponseDto errorResponse =  ErrorResponseDto.of("data not found", details, ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND);

		return ResponseEntity.badRequest().body(errorResponse);
	}












}
