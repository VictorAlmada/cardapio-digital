package com.victor.cardapio_digital.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.victor.cardapio_digital.dto.PedidoRequestDTO;
import com.victor.cardapio_digital.dto.PedidoResponseDTO;
import com.victor.cardapio_digital.model.Pedido;

@Mapper(componentModel = "spring", uses = {ItemPedidoMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PedidoMapper {
	
	@Mapping(source = "restaurante.nome", target = "restauranteNome")
	PedidoResponseDTO toDto(Pedido entity);
	
	@Mapping(source = "restauranteId", target = "restaurante.id")
	Pedido toEntity(PedidoRequestDTO dto);
	
}
