package com.programmerbeginner.catalog.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {
	
	INVALID_DATA(1),INTERNAL_ERROR(2),NETWORK_ERROR(3),OTHER_ERROR(4),NOT_FOUND(5),BAD_REQUEST(6);
	
	private int code;
	ErrorCode(int code) {
	
	  this.code = code;	
	}
	
	@JsonValue
	public int code() {
		return code;
	}

}
