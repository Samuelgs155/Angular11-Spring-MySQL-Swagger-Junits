package com.spring.restaurant.exceptions;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.spring.restaurant.dtos.ErrorDto;

public class InternalServeErrorException extends BookingException {

	private static final long serialVersionUID = 1L;

	public InternalServeErrorException(String code, String message) {
		super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
	}

	public InternalServeErrorException(String code, String message, ErrorDto data) {
		super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message, Arrays.asList(data));
	}
}
