package com.victor.cardapio_digital.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.victor.cardapio_digital.dto.CategoriaRequestDTO;
import com.victor.cardapio_digital.dto.CategoriaResponseDTO;
import com.victor.cardapio_digital.model.Categoria;

@Mapper(componentModel = "spring", uses = {RestauranteMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoriaMapper {
	
	@Mapping(source = "restauranteId", target = "restaurante.id")
	Categoria toEntity(CategoriaRequestDTO dto);
	
	@Mapping(source = "restaurante.nome", target = "nomeRestaurante")
	CategoriaResponseDTO toDto(Categoria entity);
	
}