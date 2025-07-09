package com.victor.cardapio_digital.service;

import com.victor.cardapio_digital.dto.CategoriaRequestDTO;
import com.victor.cardapio_digital.dto.CategoriaResponseDTO;
import com.victor.cardapio_digital.mapper.CategoriaMapper;
import com.victor.cardapio_digital.model.Categoria;
import com.victor.cardapio_digital.model.Restaurante;
import com.victor.cardapio_digital.repository.CategoriaRepository;
import com.victor.cardapio_digital.repository.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoriaServiceTest {

    private CategoriaRepository categoriaRepository;
    private RestauranteRepository restauranteRepository;
    private CategoriaMapper categoriaMapper;
    private CategoriaService categoriaService;

    @BeforeEach
    void setup() {
        categoriaRepository = mock(CategoriaRepository.class);
        restauranteRepository = mock(RestauranteRepository.class);
        categoriaMapper = mock(CategoriaMapper.class);
        categoriaService = new CategoriaService(categoriaRepository, restauranteRepository, categoriaMapper);
    }

    @Test
    void getAll_deveRetornarListaDeDTOs() {
        Restaurante restaurante = new Restaurante(1L, "Pizzaria Napoli", "Rua Figueira, 176", null);

        Categoria categoria = new Categoria(1L, "Bebidas", null, null);
        CategoriaResponseDTO dto = new CategoriaResponseDTO(1L, "Bebidas", "Cia do Sabor");

        when(categoriaRepository.findAll()).thenReturn(List.of(categoria));
        when(restauranteRepository.findAll()).thenReturn(List.of(restaurante));
        when(categoriaMapper.toDto(categoria)).thenReturn(dto);

        List<CategoriaResponseDTO> result = categoriaService.getAll();

        assertEquals(1, result.size());
        assertEquals(dto, result.getFirst());
    }

    @Test
    void create_deveRetornarDTOCriado() {
        Restaurante restaurante = new Restaurante(1L, "Pizzaria Napoli", "Rua Figueira, 176", null);

        CategoriaRequestDTO requestDTO = new CategoriaRequestDTO("Bebidas", 1L);
        Categoria categoria = new Categoria(null, "Bebidas", restaurante, null);
        Categoria savedCategoria = new Categoria(1L, "Bebidas", restaurante, null);
        CategoriaResponseDTO responseDTO = new CategoriaResponseDTO(1L, "Bebidas", "Cia do Sabor");

        when(restauranteRepository.findById(1L)).thenReturn(Optional.of(restaurante));
        when(categoriaMapper.toEntity(requestDTO)).thenReturn(categoria);
        when(categoriaRepository.save(categoria)).thenReturn(savedCategoria);
        when(categoriaMapper.toDto(savedCategoria)).thenReturn(responseDTO);

        CategoriaResponseDTO result = categoriaService.create(requestDTO);

        assertEquals(1L, result.getId());
        assertEquals("Bebidas", result.getNome());
        assertEquals("Cia do Sabor", result.getNomeRestaurante());

    }

}
