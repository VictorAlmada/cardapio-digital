package com.victor.cardapio_digital.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victor.cardapio_digital.dto.ItemPedidoRequestDTO;
import com.victor.cardapio_digital.dto.PedidoRequestDTO;
import com.victor.cardapio_digital.dto.PedidoResponseDTO;
import com.victor.cardapio_digital.exception.ResourceNotFoundException;
import com.victor.cardapio_digital.mapper.PedidoMapper;
import com.victor.cardapio_digital.model.ItemPedido;
import com.victor.cardapio_digital.model.Pedido;
import com.victor.cardapio_digital.model.Prato;
import com.victor.cardapio_digital.model.Restaurante;
import com.victor.cardapio_digital.repository.ItemPedidoRepository;
import com.victor.cardapio_digital.repository.PedidoRepository;
import com.victor.cardapio_digital.repository.PratoRepository;
import com.victor.cardapio_digital.repository.RestauranteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {
	
	private final PedidoRepository pedidoRepository;
	private final RestauranteRepository restauranteRepository;
	private final PratoRepository pratoRepository;
	private final ItemPedidoRepository itemPedidoRepository;
	private final PedidoMapper pedidoMapper;
	
	@Transactional(readOnly = true)
	public List<PedidoResponseDTO> getAll() {
		return pedidoRepository.findAll().stream().map(pedidoMapper::toDto).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public PedidoResponseDTO getById(Long id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com ID: " + id));
		return pedidoMapper.toDto(pedido);
	}

	public List<Pedido> getAllEntities() {
		return pedidoRepository.findAll();
	}

	public Pedido getEntityById(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com ID: " + id));
	}
	
	//  ### CRIAR PEDIDO ###
	
	@Transactional
	public PedidoResponseDTO create(PedidoRequestDTO dto) {
		
		// buscar restaurante
		Restaurante restaurante = restauranteRepository.findById(dto.restauranteId()).orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado"));
		
		// criar pedido vazio
		Pedido pedido = Pedido.builder()
				.momento(Instant.now())
				.restaurante(restaurante)
				.total(BigDecimal.ZERO)
				.build();
		
		pedido = pedidoRepository.save(pedido); // salvar para gerar o ID
		
		List<ItemPedido> itens = new ArrayList<>();
		BigDecimal total = BigDecimal.ZERO;
		
		// para cada item enviado no DTO
		for (ItemPedidoRequestDTO itemDto : dto.itens()) {
			Prato prato = pratoRepository.findById(itemDto.pratoId())
					.orElseThrow(() -> new ResourceNotFoundException("Prato não encontrado. ID=" + itemDto.pratoId()));
			
			BigDecimal subtotal = prato.getPreco().multiply(BigDecimal.valueOf(itemDto.quantidade()));
			
			ItemPedido item = ItemPedido.builder()
					.pedido(pedido)
					.prato(prato)
					.quantidade(itemDto.quantidade())
					.precoUnitario(prato.getPreco())
					.subtotal(subtotal)
					.build();
			
			itens.add(item);
			total = total.add(subtotal);
		}
		
		itemPedidoRepository.saveAll(itens);
		
		pedido.setItens(itens);
		pedido.setTotal(total);
		pedido = pedidoRepository.save(pedido);
		
		return pedidoMapper.toDto(pedido);	
	}
}
