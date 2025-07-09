package com.victor.cardapio_digital.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PratoRequestDTO(
		
		@NotBlank(message = "Nome é obrigatório")
		String nome,
		
		@NotBlank(message = "Descrição é obrigatória")
		String descricao,
		
		@NotNull(message = "Preço é obrigatório")
		@Positive(message = "Preço deve ser positivo")
		BigDecimal preco,
		
		String imagemUrl,
		
		@NotNull(message = "ID da categoria é obrigatório")
		Long categoriaId
		
) {}
