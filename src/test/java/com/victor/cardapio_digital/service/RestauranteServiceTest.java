package com.victor.cardapio_digital.service;

import com.victor.cardapio_digital.dto.RestauranteResponseDTO;
import com.victor.cardapio_digital.mapper.RestauranteMapper;
import com.victor.cardapio_digital.model.Restaurante;
import com.victor.cardapio_digital.repository.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RestauranteServiceTest {

    private RestauranteRepository restauranteRepository;
    private RestauranteMapper restauranteMapper;
    private RestauranteService restauranteService;

    @BeforeEach
    void setup() {
        restauranteRepository = mock(RestauranteRepository.class);
        restauranteMapper = mock(RestauranteMapper.class);
        restauranteService = new RestauranteService(restauranteRepository, restauranteMapper);
    }

    @Test
    void getAll_deveRetornarListaDeDTOs() {
        // dado
        Restaurante restaurante = Restaurante.builder()
                .id(1L)
                .nome("Pizzaria Napoli")
                .endereco("Rua Figueira, 176")
                .build();

        RestauranteResponseDTO dto = new RestauranteResponseDTO(1L, "Pizzaria Napoli", "Rua Figueira, 176");

        when(restauranteRepository.findAll()).thenReturn(List.of(restaurante));
        when(restauranteMapper.toDto(restaurante)).thenReturn(dto);

        // quando
        List<RestauranteResponseDTO> resultado = restauranteService.getAll();

        // então
        assertEquals(1, resultado.size());
        assertEquals("Pizzaria Napoli", resultado.getFirst().getNome());
        verify(restauranteRepository, times(1)).findAll();


    }
}
