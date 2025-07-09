package com.victor.cardapio_digital.controller;

import java.util.List;

import com.victor.cardapio_digital.hateoas.RestauranteModelAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victor.cardapio_digital.dto.RestauranteRequestDTO;
import com.victor.cardapio_digital.dto.RestauranteResponseDTO;
import com.victor.cardapio_digital.service.RestauranteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
@Validated
public class RestauranteController {
	
	private final RestauranteService restauranteService;
	private final RestauranteModelAssembler assembler;
	
	@GetMapping
	public ResponseEntity<List<RestauranteResponseDTO>> getAll() {
		List<RestauranteResponseDTO> lista = restauranteService.getAll();
		List<RestauranteResponseDTO> comLinks = lista.stream().map(assembler::toModel).toList();
		return ResponseEntity.ok(comLinks);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RestauranteResponseDTO> getById(@PathVariable Long id) {
		RestauranteResponseDTO dto = restauranteService.getById(id);
		return ResponseEntity.ok(assembler.toModel(dto));
	}
	
	@PostMapping
	public ResponseEntity<RestauranteResponseDTO> create(@RequestBody @Valid RestauranteRequestDTO dto) {
		RestauranteResponseDTO saved = restauranteService.create(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		restauranteService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
