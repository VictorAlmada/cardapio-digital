package com.victor.cardapio_digital.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.victor.cardapio_digital.dto.PratoRequestDTO;
import com.victor.cardapio_digital.dto.PratoResponseDTO;
import com.victor.cardapio_digital.model.Prato;

@Mapper(componentModel = "spring", uses = {CategoriaMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PratoMapper {
	
	@Mapping(source = "categoriaId", target = "categoria.id")
	Prato toEntity(PratoRequestDTO dto);
	
	@Mapping(source = "categoria.nome", target = "categoriaNome")
	PratoResponseDTO toDto(Prato entity);
	
}
