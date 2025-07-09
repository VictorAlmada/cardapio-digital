package com.victor.cardapio_digital.controller;

import java.util.List;

import com.victor.cardapio_digital.hateoas.PratoModelAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victor.cardapio_digital.dto.PratoRequestDTO;
import com.victor.cardapio_digital.dto.PratoResponseDTO;
import com.victor.cardapio_digital.service.PratoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pratos")
@Validated
@RequiredArgsConstructor
public class PratoController {
	
	private final PratoService pratoService;
	private final PratoModelAssembler assembler;
	
	@GetMapping
	public ResponseEntity<List<PratoResponseDTO>> getAll() {
		List<PratoResponseDTO> list = pratoService.getAll();
		List<PratoResponseDTO> comLinks = list.stream().map(assembler::toModel).toList();
		return ResponseEntity.ok(comLinks);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PratoResponseDTO> getById(@PathVariable Long id) {
		PratoResponseDTO dto = pratoService.getById(id);
		return ResponseEntity.ok(assembler.toModel(dto));
	}
	
	@PostMapping
	public ResponseEntity<PratoResponseDTO> create(@RequestBody @Valid PratoRequestDTO dto) {
		PratoResponseDTO created = pratoService.create(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	public ResponseEntity<Void> delete(Long id) {
		pratoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
