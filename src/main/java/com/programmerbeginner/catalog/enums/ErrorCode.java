package com.programmerbeginner.catalog.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {
	
	INVALID_DATA(400),INTERNAL_ERROR(2),NETWORK_ERROR(3),OTHER_ERROR(400),NOT_FOUND(404),BAD_REQUEST(6);
	
	private int code;
	ErrorCode(int code) {
	
	  this.code = code;	
	}
	
	@JsonValue
	public int code() {
		return code;
	}

}
