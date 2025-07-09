package com.victor.cardapio_digital.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ValidationError extends StandardError{
	
	private final List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(Instant timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}
	
	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}
}
