package com.victor.cardapio_digital.service;

import java.util.List;
import java.util.stream.Collectors;

import com.victor.cardapio_digital.model.Categoria;
import com.victor.cardapio_digital.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victor.cardapio_digital.dto.PratoRequestDTO;
import com.victor.cardapio_digital.dto.PratoResponseDTO;
import com.victor.cardapio_digital.exception.ResourceNotFoundException;
import com.victor.cardapio_digital.mapper.PratoMapper;
import com.victor.cardapio_digital.model.Prato;
import com.victor.cardapio_digital.repository.PratoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PratoService {
	
	private final PratoRepository pratoRepository;
	private final CategoriaRepository categoriaRepository;
	private final PratoMapper pratoMapper;
	
	@Transactional(readOnly = true)
	public List<PratoResponseDTO> getAll() {
        return pratoRepository.findAll()
                .stream().map(pratoMapper::toDto).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public PratoResponseDTO getById(Long id) {
		Prato prato = pratoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prato não encontrado com ID: " + id));
		return pratoMapper.toDto(prato);
	}
	
	@Transactional
	public PratoResponseDTO create(PratoRequestDTO dto) {
		Categoria categoria = categoriaRepository.findById(dto.categoriaId())
				.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

		Prato prato = pratoMapper.toEntity(dto);
		prato.setCategoria(categoria);

		prato = pratoRepository.save(prato);
		return pratoMapper.toDto(prato);
	}
	
	@Transactional
	public void delete(Long id) {
		if (!pratoRepository.existsById(id)) {
			throw new ResourceNotFoundException("Prato não encontrado com ID: " + id);
		}
		pratoRepository.deleteById(id);
	}
	
}
