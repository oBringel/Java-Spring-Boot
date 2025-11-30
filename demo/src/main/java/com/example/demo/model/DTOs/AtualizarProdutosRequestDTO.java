package com.example.demo.model.DTOs;

public record AtualizarProdutosRequestDTO(Long id,String nomeProd, String descricaoProd, String validadeProd , String loteProd, Integer quantidade) {
}
