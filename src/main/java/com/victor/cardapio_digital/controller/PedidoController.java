package com.victor.cardapio_digital.controller;

import java.util.List;

import com.victor.cardapio_digital.hateoas.PedidoModelAssembler;
import com.victor.cardapio_digital.model.Pedido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victor.cardapio_digital.dto.PedidoRequestDTO;
import com.victor.cardapio_digital.dto.PedidoResponseDTO;
import com.victor.cardapio_digital.service.PedidoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
@Validated
public class PedidoController {
	
	private final PedidoService pedidoService;
	private final PedidoModelAssembler assembler;
	
	@GetMapping
	public ResponseEntity<List<PedidoResponseDTO>> getAll() {
		List<Pedido> list = pedidoService.getAllEntities();
		List<PedidoResponseDTO> comLinks = list.stream()
				.map(assembler::toModel).toList();
		return ResponseEntity.ok(comLinks);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoResponseDTO> getById(@PathVariable Long id) {
		Pedido pedido = pedidoService.getEntityById(id);
		PedidoResponseDTO dto = assembler.toModel(pedido);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<PedidoResponseDTO> create(@RequestBody @Valid PedidoRequestDTO dto) {
		PedidoResponseDTO response = pedidoService.create(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
}
