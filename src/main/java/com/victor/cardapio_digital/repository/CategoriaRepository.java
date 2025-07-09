package com.victor.cardapio_digital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victor.cardapio_digital.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
