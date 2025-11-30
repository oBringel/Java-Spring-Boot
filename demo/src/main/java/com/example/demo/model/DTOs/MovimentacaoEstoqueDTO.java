package com.example.demo.model.DTOs;

import com.example.demo.model.estoque.Enum.UnidadeMedida;

public record MovimentacaoEstoqueDTO( Long produtoId,
                                     Integer quantidade,
                                     UnidadeMedida unidade) {

}
