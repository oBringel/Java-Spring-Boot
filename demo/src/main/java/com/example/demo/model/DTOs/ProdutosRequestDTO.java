package com.example.demo.model.DTOs;

import jakarta.persistence.Entity;


public record ProdutosRequestDTO( Long id ,String nomeProd, String descricaoProd, String validadeProd , String loteProd) {
}
