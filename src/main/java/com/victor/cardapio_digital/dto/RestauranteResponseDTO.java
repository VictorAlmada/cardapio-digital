package com.victor.cardapio_digital.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestauranteResponseDTO extends RepresentationModel<RestauranteResponseDTO> {
	Long id;
	String nome;
	String endereco;
}
