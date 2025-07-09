package com.victor.cardapio_digital.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Getter @Setter
public class PedidoResponseDTO extends RepresentationModel<PedidoResponseDTO> {

	private Long id;
	private Instant momento;
	private BigDecimal total;
	private String restauranteNome;
	private List<ItemPedidoResponseDTO> itens;
}
