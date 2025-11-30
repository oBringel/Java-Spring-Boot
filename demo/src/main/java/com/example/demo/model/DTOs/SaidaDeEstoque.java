package com.example.demo.model.DTOs;

import com.example.demo.model.estoque.Estoque;

public record SaidaDeEstoque (Estoque estoque, Long id, Integer quantidadeSaida) {
}
