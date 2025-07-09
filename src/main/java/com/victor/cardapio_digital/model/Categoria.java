package com.victor.cardapio_digital.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_categorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "restaurante_id")
	private Restaurante restaurante;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Prato> pratos;
	
}
