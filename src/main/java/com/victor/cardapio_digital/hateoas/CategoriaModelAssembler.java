package com.victor.cardapio_digital.hateoas;

import com.victor.cardapio_digital.controller.CategoriaController;
import com.victor.cardapio_digital.dto.CategoriaResponseDTO;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CategoriaModelAssembler implements RepresentationModelAssembler<CategoriaResponseDTO, CategoriaResponseDTO> {

    @Override
    public CategoriaResponseDTO toModel(CategoriaResponseDTO dto) {
        dto.add(linkTo(methodOn(CategoriaController.class).getAll()).withRel("categorias"));
        dto.add(linkTo(methodOn(CategoriaController.class).getById(dto.getId())).withSelfRel());
        return dto;
    }
}
