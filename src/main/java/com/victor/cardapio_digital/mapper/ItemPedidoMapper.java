package com.victor.cardapio_digital.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.victor.cardapio_digital.dto.ItemPedidoRequestDTO;
import com.victor.cardapio_digital.dto.ItemPedidoResponseDTO;
import com.victor.cardapio_digital.model.ItemPedido;

@Mapper(componentModel = "spring", uses = {PratoMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemPedidoMapper {
	
	@Mapping(source = "prato.nome", target = "nomePrato")
	ItemPedidoResponseDTO toDto(ItemPedido entity);
	
	@Mapping(source = "pratoId", target = "prato.id")
	ItemPedido toEntity(ItemPedidoRequestDTO dto);
	
}