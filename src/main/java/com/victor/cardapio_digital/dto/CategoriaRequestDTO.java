package com.victor.cardapio_digital.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoriaRequestDTO(
		
		@NotBlank(message = "Nome é obrigatório")
		String nome,
		
		@NotNull(message = "ID do restaurante é obrigatório")
		Long restauranteId
				
) {}
