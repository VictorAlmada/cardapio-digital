package com.victor.cardapio_digital.exception;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class StandardError {
	
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	
}
