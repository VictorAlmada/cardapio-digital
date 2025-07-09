package com.victor.cardapio_digital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victor.cardapio_digital.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}