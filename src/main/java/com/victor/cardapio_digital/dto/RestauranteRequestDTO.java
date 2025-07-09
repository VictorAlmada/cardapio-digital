package com.victor.cardapio_digital.dto;

import jakarta.validation.constraints.NotBlank;

public record RestauranteRequestDTO(
		
		@NotBlank(message = "Nome é obrigatório")
		String nome,
		
		@NotBlank(message = "Endereço é obrigatório")
		String endereco
		
) {}
