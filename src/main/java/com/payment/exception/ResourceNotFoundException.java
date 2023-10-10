package com.payment.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {

		super("Resource Not Found !!");

	}

	public ResourceNotFoundException(String message) {

		super("Resource Not Found With ID :" + message);

	}

	public ResourceNotFoundException(Long message) {

		super("Resource Not Found With ID :" + message);

	}

}
