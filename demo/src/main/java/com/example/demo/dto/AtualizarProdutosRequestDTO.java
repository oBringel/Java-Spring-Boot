package com.example.demo.dto;

public record AtualizarProdutosRequestDTO(Long id,String nomeProd, String descricaoProd, String validadeProd , String loteProd, Integer quantidade) {
}
