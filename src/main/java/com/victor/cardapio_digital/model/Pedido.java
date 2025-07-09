package com.victor.cardapio_digital.model;

import java.math.BigDecimal;
import java.time.Instant;
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
@Table(name = "tb_pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Instant momento;
	
	private BigDecimal total;
	
	@ManyToOne
	@JoinColumn(name = "restaurante_id")
	private Restaurante restaurante;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemPedido> itens;
	
}