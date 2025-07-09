package com.victor.cardapio_digital.dto;

import java.math.BigDecimal;

public record ItemPedidoResponseDTO (
		String nomePrato,
		Integer quantidade,
		BigDecimal precoUnitario,
		BigDecimal subtotal
) {}
