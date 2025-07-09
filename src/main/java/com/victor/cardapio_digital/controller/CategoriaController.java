package com.victor.cardapio_digital.controller;

import java.util.List;

import com.victor.cardapio_digital.hateoas.CategoriaModelAssembler;
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

import com.victor.cardapio_digital.dto.CategoriaRequestDTO;
import com.victor.cardapio_digital.dto.CategoriaResponseDTO;
import com.victor.cardapio_digital.service.CategoriaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
@Validated
public class CategoriaController {
	
	private final CategoriaService categoriaService;
	private final CategoriaModelAssembler assembler;
	
	@GetMapping
	public ResponseEntity<List<CategoriaResponseDTO>> getAll() {
		List<CategoriaResponseDTO> list = categoriaService.getAll();
		List<CategoriaResponseDTO> comLinks = list.stream().map(assembler::toModel).toList();
		return ResponseEntity.ok(comLinks);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaResponseDTO> getById(@PathVariable Long id) {
		CategoriaResponseDTO dto = categoriaService.getById(id);
		return ResponseEntity.ok(assembler.toModel(dto));
	}
	
	@PostMapping
	public ResponseEntity<CategoriaResponseDTO> create(@RequestBody @Valid CategoriaRequestDTO dto) {
		CategoriaResponseDTO saved = categoriaService.create(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
