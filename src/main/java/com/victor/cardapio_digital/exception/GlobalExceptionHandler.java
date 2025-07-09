package com.victor.cardapio_digital.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> handleNotFound(ResourceNotFoundException ex) {
		StandardError error = StandardError.builder()
				.timestamp(Instant.now())
				.status(HttpStatus.NOT_FOUND.value())
				.error("Recurso não encontrado")
				.message(ex.getMessage())
				.path("/")
				.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> handleValidation(MethodArgumentNotValidException ex) {
		ValidationError error = new ValidationError(
				Instant.now(),
				HttpStatus.BAD_REQUEST.value(),
				"Erro de validação",
				"Campos inválidos",
				"/"
		);
		
		for (FieldError x : ex.getBindingResult().getFieldErrors()) {
			error.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardError> handleGeneric(Exception ex) {	
		StandardError error = StandardError.builder()
				.timestamp(Instant.now())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error("Erro interno")
				.message(ex.getMessage())
				.path("/")
				.build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}
