package com.victor.cardapio_digital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victor.cardapio_digital.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

}
