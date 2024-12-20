package com.quest.shoppingcart.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static final String ERROR_MESSAGE = "product doesn't exist ";

	private Integer recordId;
	private String errorCode = "PNF";
	
	public ProductNotFoundException(DataIntegrityViolationException e, Integer recordId) {
		super(ERROR_MESSAGE.concat(recordId.toString()), e);
		this.recordId = recordId;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
