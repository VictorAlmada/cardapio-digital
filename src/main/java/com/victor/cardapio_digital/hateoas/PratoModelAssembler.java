package com.victor.cardapio_digital.hateoas;

import com.victor.cardapio_digital.controller.PratoController;
import com.victor.cardapio_digital.dto.PratoResponseDTO;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PratoModelAssembler implements RepresentationModelAssembler<PratoResponseDTO, PratoResponseDTO> {

    @Override
    public PratoResponseDTO toModel(PratoResponseDTO dto) {
        dto.add(linkTo(methodOn(PratoController.class).getAll()).withRel("pratos"));
        dto.add(linkTo(methodOn(PratoController.class).getById(dto.getId())).withSelfRel());
        return dto;
    }
}
