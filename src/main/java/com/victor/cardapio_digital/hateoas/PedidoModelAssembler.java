package com.victor.cardapio_digital.hateoas;

import com.victor.cardapio_digital.controller.PedidoController;
import com.victor.cardapio_digital.controller.RestauranteController;
import com.victor.cardapio_digital.dto.PedidoResponseDTO;
import com.victor.cardapio_digital.mapper.PedidoMapper;
import com.victor.cardapio_digital.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PedidoModelAssembler implements RepresentationModelAssembler<Pedido, PedidoResponseDTO> {

    @Autowired
    private PedidoMapper mapper;

    @Override
    public PedidoResponseDTO toModel(Pedido entity) {
        PedidoResponseDTO dto = mapper.toDto(entity);

        dto.add(linkTo(methodOn(PedidoController.class).getById(entity.getId())).withSelfRel());
        dto.add(linkTo(methodOn(RestauranteController.class).getById(entity.getRestaurante().getId())).withRel("restaurante"));
        return dto;
    }
}
