package com.victor.cardapio_digital.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victor.cardapio_digital.dto.RestauranteRequestDTO;
import com.victor.cardapio_digital.dto.RestauranteResponseDTO;
import com.victor.cardapio_digital.exception.ResourceNotFoundException;
import com.victor.cardapio_digital.mapper.RestauranteMapper;
import com.victor.cardapio_digital.model.Restaurante;
import com.victor.cardapio_digital.repository.RestauranteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestauranteService {
	
	private final RestauranteRepository restauranteRepository;
	private final RestauranteMapper restauranteMapper;
	
	@Transactional(readOnly = true)
	public List<RestauranteResponseDTO> getAll() {
		return restauranteRepository.findAll().stream().map(restauranteMapper::toDto).toList();
	}
	
	@Transactional(readOnly = true)
	public RestauranteResponseDTO getById(Long id) {
		Restaurante restaurante = restauranteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado com ID: " + id));
		return restauranteMapper.toDto(restaurante);
	}
	
	@Transactional
	public RestauranteResponseDTO create(RestauranteRequestDTO dto) {
		Restaurante restaurante = restauranteMapper.toEntity(dto);
		restaurante = restauranteRepository.save(restaurante);
		return restauranteMapper.toDto(restaurante);
	}
	
	@Transactional
	public void delete(Long id) {
		if (!restauranteRepository.existsById(id)) {
			throw new ResourceNotFoundException("Restaurante não encontrado com ID: " + id);
		}
		restauranteRepository.deleteById(id);
	}
	
}
