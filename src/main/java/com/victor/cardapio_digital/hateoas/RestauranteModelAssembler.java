package com.victor.cardapio_digital.hateoas;

import com.victor.cardapio_digital.controller.RestauranteController;
import com.victor.cardapio_digital.dto.RestauranteResponseDTO;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class RestauranteModelAssembler implements RepresentationModelAssembler<RestauranteResponseDTO, RestauranteResponseDTO> {

    @Override
    public RestauranteResponseDTO toModel(RestauranteResponseDTO dto) {
        dto.add(linkTo(methodOn(RestauranteController.class).getAll()).withRel("restaurantes"));
        dto.add(linkTo(methodOn(RestauranteController.class).getById(dto.getId())).withSelfRel());
        return dto;
    }

}
