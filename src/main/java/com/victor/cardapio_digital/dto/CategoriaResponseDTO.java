package com.victor.cardapio_digital.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoriaResponseDTO extends RepresentationModel<CategoriaResponseDTO> {
	Long id;
	String nome;
	String nomeRestaurante;
}
