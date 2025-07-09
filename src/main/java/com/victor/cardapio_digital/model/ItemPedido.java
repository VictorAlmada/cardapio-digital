package com.victor.cardapio_digital.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_itens_pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer quantidade;
	
	private BigDecimal precoUnitario;
	
	private BigDecimal subtotal;
	
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "prato_id")
	private Prato prato;

}
