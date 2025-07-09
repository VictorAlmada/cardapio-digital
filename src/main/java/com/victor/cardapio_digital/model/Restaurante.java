package com.victor.cardapio_digital.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_restaurantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String endereco;
	
	@OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Categoria> categorias;
	
}
