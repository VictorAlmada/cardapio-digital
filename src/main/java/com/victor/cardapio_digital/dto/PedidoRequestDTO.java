package com.victor.cardapio_digital.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PedidoRequestDTO(
		
		@NotNull(message = "O ID do restaurante é obrigatório")
		Long restauranteId,
		
		@NotEmpty(message = "O pedido deve conter pelo menos um item")
		List<@NotNull ItemPedidoRequestDTO> itens
		
) {}
