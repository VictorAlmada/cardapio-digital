package com.victor.cardapio_digital.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemPedidoRequestDTO(
		@NotNull(message = "O ID do prato é obrigatório")
		Long pratoId,

		@NotNull(message = "A quantidade é obrigatória")
		@Min(value = 1, message = "A quantidade deve ser no mínimo 1")
		Integer quantidade
		
) {}
