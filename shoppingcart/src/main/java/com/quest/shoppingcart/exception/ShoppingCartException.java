package com.quest.shoppingcart.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCartException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private static final String INTERNAL_ERROR_MESSAGE = "Internal server Error";
	private static final String INTERNAL_ERROR_CODE = "ISR";

	private final String errorCode;
    private final String message;
    private HttpStatus status;
    
    public ShoppingCartException(String message, String errorCode, HttpStatus status) {
        this.errorCode = errorCode;
        this.status = status;
        this.message = message;
    }

    public ShoppingCartException() {
        this.errorCode = INTERNAL_ERROR_CODE;
        this.message = INTERNAL_ERROR_MESSAGE;
    }
   

}