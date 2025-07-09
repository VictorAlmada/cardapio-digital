package com.victor.cardapio_digital;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CardapioDigitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardapioDigitalApplication.class, args);
    }

    @PostConstruct
    public void printProfile() {
        System.out.println(">>> Perfil ativo: " + System.getProperty("spring.profiles.active"));
    }


}