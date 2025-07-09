package com.victor.cardapio_digital.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.victor.cardapio_digital.dto.RestauranteRequestDTO;
import com.victor.cardapio_digital.dto.RestauranteResponseDTO;
import com.victor.cardapio_digital.model.Restaurante;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestauranteMapper {
	
	Restaurante toEntity(RestauranteRequestDTO dto);
	
	RestauranteResponseDTO toDto(Restaurante entity);
	
}
