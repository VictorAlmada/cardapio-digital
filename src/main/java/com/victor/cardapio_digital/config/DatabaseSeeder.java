package com.victor.cardapio_digital.config;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.victor.cardapio_digital.security.Usuario;
import com.victor.cardapio_digital.security.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.victor.cardapio_digital.model.Categoria;
import com.victor.cardapio_digital.model.ItemPedido;
import com.victor.cardapio_digital.model.Pedido;
import com.victor.cardapio_digital.model.Prato;
import com.victor.cardapio_digital.model.Restaurante;
import com.victor.cardapio_digital.repository.CategoriaRepository;
import com.victor.cardapio_digital.repository.ItemPedidoRepository;
import com.victor.cardapio_digital.repository.PedidoRepository;
import com.victor.cardapio_digital.repository.PratoRepository;
import com.victor.cardapio_digital.repository.RestauranteRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {
	
	private final CategoriaRepository categoriaRepository;
	private final ItemPedidoRepository itemPedidoRepository;
	private final PedidoRepository pedidoRepository;
	private final PratoRepository pratoRepository;
	private final RestauranteRepository restauranteRepository;
	private final PasswordEncoder passwordEncoder;
	private final UsuarioRepository usuarioRepository;
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {

		// ## SECURITY ##

		Usuario user = Usuario.builder()
				.username("user")
				.password(passwordEncoder.encode("123456"))
				.roles(Set.of("ROLE_USER"))
				.build();

		Usuario admin = Usuario.builder()
				.username("admin")
				.password(passwordEncoder.encode("admin123"))
				.roles(Set.of("ROLE_ADMIN"))
				.build();

		usuarioRepository.saveAll(List.of(user, admin));
		
		// RESTAURANTES
		
		Restaurante r1 = Restaurante.builder()
				.nome("Pizzaria Napoli")
				.endereco("Rua Figueira, 176")
				.categorias(null)
				.build();
		
		restauranteRepository.save(r1);
		
		// CATEGORIAS
		
		Categoria cat1 = Categoria.builder()
				.nome("Pizzas")
				.restaurante(r1)
				.pratos(null)
				.build();
		
		Categoria cat2 = Categoria.builder()
				.nome("Bebidas")
				.restaurante(r1)
				.pratos(null)
				.build();
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
		// PRATOS
		
		Prato p1 = Prato.builder()
				.nome("Pizza Margherita")
				.descricao("Queijo, tomate e manjericão")
				.preco(new BigDecimal("39.90"))
				.imagemUrl(null)
				.categoria(cat1)
				.build();
		
		Prato p2 = Prato.builder()
				.nome("Suco Natural")
				.descricao("Suco de laranja espremido")
				.preco(new BigDecimal("9.90"))
				.imagemUrl(null)
				.categoria(cat2)
				.build();
		
		pratoRepository.saveAll(Arrays.asList(p1, p2));
		
		// PEDIDO
		
		Pedido pedido1 = Pedido.builder()
				.momento(Instant.now())
				.total(null)
				.restaurante(r1)
				.itens(null)
				.build();
		
		pedidoRepository.save(pedido1);
		
		// ITENS DO PEDIDO
		
		ItemPedido item1 = ItemPedido.builder()
				.quantidade(2)
				.precoUnitario(p1.getPreco())
				.subtotal(p1.getPreco().multiply(new BigDecimal(2)))
				.pedido(pedido1)
				.prato(p1)
				.build();
		
		ItemPedido item2 = ItemPedido.builder()
				.quantidade(1)
				.precoUnitario(p2.getPreco())
				.subtotal(p2.getPreco())
				.pedido(pedido1)
				.prato(p2)
				.build();
		
		itemPedidoRepository.saveAll(Arrays.asList(item1, item2));
		
		// TOTAL DO PEDIDO
		
		pedido1.setItens(new ArrayList<>(Arrays.asList(item1, item2)));
		pedido1.setTotal(item1.getSubtotal().add(item2.getSubtotal()));
		pedidoRepository.save(pedido1);
	}
	
}
