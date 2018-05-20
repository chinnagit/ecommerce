package com.ecommerce.products.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(String name) {
		super("No such product: " + name);
	}
	
}
