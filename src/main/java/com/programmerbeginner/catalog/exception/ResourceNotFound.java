package com.programmerbeginner.catalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException{


	@Serial
	private static final long serialVersionUID = -8356970642110157027L;

	public ResourceNotFound(String message) {
		super(message);
		
	}

	

}
