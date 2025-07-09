package com.victor.cardapio_digital.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PratoResponseDTO extends RepresentationModel<PratoResponseDTO> {
	Long id;
	String nome;
	String descricao;
	BigDecimal preco;
	String imagemUrl;
	String categoriaNome;

}
