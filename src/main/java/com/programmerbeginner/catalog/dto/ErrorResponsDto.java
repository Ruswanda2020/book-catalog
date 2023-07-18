package com.programmerbeginner.catalog.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.programmerbeginner.catalog.enums.ErrorCode;

import lombok.Data;

@Data
public class ErrorResponsDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date timesamp;
	
	private String message;
	
	private ErrorCode errorCode;
	
	private List<String> details;
	
	private HttpStatus status;
	
	
	public  static ErrorResponsDto of(final String message,List<String> details,final ErrorCode errorCode,HttpStatus status) {
		return new ErrorResponsDto(message, errorCode, details, status);
	}

	public ErrorResponsDto(String message, ErrorCode errorCode, List<String> details, HttpStatus status) {
		super();
		this.timesamp = new Date();
		this.message = message;
		this.errorCode = errorCode;
		this.details = details;
		this.status = status;
	}
	
	
	

}
