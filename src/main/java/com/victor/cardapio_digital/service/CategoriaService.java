package com.victor.cardapio_digital.service;

import java.util.List;
import java.util.stream.Collectors;

import com.victor.cardapio_digital.model.Restaurante;
import com.victor.cardapio_digital.repository.RestauranteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victor.cardapio_digital.dto.CategoriaRequestDTO;
import com.victor.cardapio_digital.dto.CategoriaResponseDTO;
import com.victor.cardapio_digital.exception.ResourceNotFoundException;
import com.victor.cardapio_digital.mapper.CategoriaMapper;
import com.victor.cardapio_digital.model.Categoria;
import com.victor.cardapio_digital.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {
	
	private final CategoriaRepository categoriaRepository;
	private  final RestauranteRepository restauranteRepository;
	private final CategoriaMapper categoriaMapper;
	
	@Transactional(readOnly = true)
	public List<CategoriaResponseDTO> getAll() {
        return categoriaRepository.findAll().stream().map(categoriaMapper::toDto).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public CategoriaResponseDTO getById(Long id) {
		Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com ID: " + id));
		return categoriaMapper.toDto(categoria);
	}
	
	@Transactional
	public CategoriaResponseDTO create(CategoriaRequestDTO dto) {
		Restaurante restaurante = restauranteRepository.findById(dto.restauranteId())
				.orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado"));

		Categoria categoria = categoriaMapper.toEntity(dto);
		categoria.setRestaurante(restaurante);

		categoria = categoriaRepository.save(categoria);
		return categoriaMapper.toDto(categoria);
	}
	
	@Transactional
	public void delete(Long id) {
		if (!categoriaRepository.existsById(id)) {
			throw new ResourceNotFoundException("Categoria não encontrada com ID: " + id);
		}
		categoriaRepository.deleteById(id);
	}
	
}
