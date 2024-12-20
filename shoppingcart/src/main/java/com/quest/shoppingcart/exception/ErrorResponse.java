package com.quest.shoppingcart.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

	private String errorCode;
	private String message;

	public ErrorResponse( String errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}
}