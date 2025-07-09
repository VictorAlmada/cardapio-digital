package com.victor.cardapio_digital.service;

import com.victor.cardapio_digital.dto.PratoResponseDTO;
import com.victor.cardapio_digital.mapper.PratoMapper;
import com.victor.cardapio_digital.model.Categoria;
import com.victor.cardapio_digital.model.Prato;
import com.victor.cardapio_digital.repository.CategoriaRepository;
import com.victor.cardapio_digital.repository.PratoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PratoServiceTest {

    private PratoRepository pratoRepository;
    private PratoMapper pratoMapper;
    private PratoService pratoService;

    @BeforeEach
    void setup() {
        pratoRepository = mock(PratoRepository.class);
        CategoriaRepository categoriaRepository = mock(CategoriaRepository.class);
        pratoMapper = mock(PratoMapper.class);
        pratoService = new PratoService(pratoRepository, categoriaRepository, pratoMapper);
    }

    @Test
    void getAll_deveRetornarListaDTOs() {
        Categoria categoria = new Categoria(1L, "Bebidas", null, null);

        Prato prato1 = new Prato(1L, "Coca-Cola", "Refrigerante da marca Coca-Cola",
                new BigDecimal("2.99"), null, categoria);

        Prato prato2 = new Prato(2L, "Pepsi ", "Refrigerante da marca Pepsi",
                new BigDecimal("2.99"), null, categoria);

        PratoResponseDTO dto1 = new PratoResponseDTO(1L, "Coca-Cola", "Refrigerante da marca Coca-Cola",
                new BigDecimal("2.99"), null, "Bebidas");

        PratoResponseDTO dto2 = new PratoResponseDTO(2L, "Pepsi", "Refrigerante da marca Pepsi",
                new BigDecimal("2.99"), null, "Bebidas");

        when(pratoRepository.findAll()).thenReturn(List.of(prato1, prato2));
        when(pratoMapper.toDto(prato1)).thenReturn(dto1);
        when(pratoMapper.toDto(prato2)).thenReturn(dto2);

        List<PratoResponseDTO> list = pratoService.getAll();

        assertEquals(2, list.size());
        assertEquals("Pepsi", dto2.getNome());
        assertEquals("Bebidas", dto1.getCategoriaNome());
        assertEquals(new BigDecimal("2.99"), dto1.getPreco());
        assertEquals(dto1, list.getFirst());
        assertNull(dto2.getImagemUrl());
    }
}
