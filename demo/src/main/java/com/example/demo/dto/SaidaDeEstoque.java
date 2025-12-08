package com.example.demo.dto;

import com.example.demo.model.Estoque;

public record SaidaDeEstoque (Estoque estoque, Long id, Integer quantidadeSaida) {
}
