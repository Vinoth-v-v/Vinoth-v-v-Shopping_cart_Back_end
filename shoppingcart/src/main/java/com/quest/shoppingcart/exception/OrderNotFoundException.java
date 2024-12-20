package com.quest.shoppingcart.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public static final String ERROR_MESSAGE = "Invalid order id: ";
	public static final String ERROR_CODE = "ONF";

	private String message;
	private String errorCode;

	public OrderNotFoundException(String id) {
		this.message = ERROR_MESSAGE.concat(id);
		this.errorCode = ERROR_CODE;
	}

}
