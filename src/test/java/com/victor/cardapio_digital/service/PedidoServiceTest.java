package com.victor.cardapio_digital.service;

import com.victor.cardapio_digital.dto.ItemPedidoResponseDTO;
import com.victor.cardapio_digital.dto.PedidoResponseDTO;
import com.victor.cardapio_digital.mapper.PedidoMapper;
import com.victor.cardapio_digital.model.ItemPedido;
import com.victor.cardapio_digital.model.Pedido;
import com.victor.cardapio_digital.model.Prato;
import com.victor.cardapio_digital.model.Restaurante;
import com.victor.cardapio_digital.repository.ItemPedidoRepository;
import com.victor.cardapio_digital.repository.PedidoRepository;
import com.victor.cardapio_digital.repository.PratoRepository;
import com.victor.cardapio_digital.repository.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PedidoServiceTest {

    private PedidoRepository pedidoRepository;
    private RestauranteRepository restauranteRepository;
    private PratoRepository pratoRepository;
    private ItemPedidoRepository itemPedidoRepository;
    private PedidoMapper pedidoMapper;
    private PedidoService pedidoService;


    @BeforeEach
    void setup() {
        pedidoRepository = mock(PedidoRepository.class);
        restauranteRepository = mock(RestauranteRepository.class);
        pratoRepository = mock(PratoRepository.class);
        itemPedidoRepository = mock(ItemPedidoRepository.class);
        pedidoMapper = mock(PedidoMapper.class);
        pedidoService = new PedidoService(pedidoRepository, restauranteRepository, pratoRepository, itemPedidoRepository, pedidoMapper);
    }

    @Test
    void getAll_deveRetornarListaDePedidosComItensCorretos() {

        // Arrange
        Restaurante restaurante = new Restaurante(1L, "ABC", "Rua Manaus, 123", null);
        Prato prato = new Prato(1L, "Salada", "Tomate, cebola e alface", new BigDecimal("4.99"), null, null);
        Pedido pedido = new Pedido(1L, Instant.now(), BigDecimal.ZERO, restaurante, null);
        ItemPedido item = new ItemPedido(1L, 1, prato.getPreco(), prato.getPreco(), pedido, prato);

        List<ItemPedido> itens = new ArrayList<>();
        itens.add(item);
        pedido.setItens(itens);

        PedidoResponseDTO dto = new PedidoResponseDTO(
                pedido.getId(),
                pedido.getMomento(),
                pedido.getTotal(),
                restaurante.getNome(),
                List.of(new ItemPedidoResponseDTO(prato.getNome(), item.getQuantidade(), item.getPrecoUnitario(), item.getSubtotal()))
        );

        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));
        when(pedidoMapper.toDto(pedido)).thenReturn(dto);

        // Act
        List<PedidoResponseDTO> resultado = pedidoService.getAll();

        // Assert
        assertEquals(1, resultado.size());
        PedidoResponseDTO primeiro = resultado.getFirst();
        assertEquals("ABC", primeiro.getRestauranteNome());
        assertEquals(1, primeiro.getItens().size());

        ItemPedidoResponseDTO primeiroItem = primeiro.getItens().getFirst();
        assertEquals("Salada", primeiroItem.nomePrato());
        assertEquals(new BigDecimal("4.99"), primeiroItem.precoUnitario());
    }
}
