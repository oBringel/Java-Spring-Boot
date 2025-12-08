package com.example.demo.dto;

import com.example.demo.enums.UnidadeMedida;

public record MovimentacaoEstoqueDTO( Long produtoId,
                                     Integer quantidade,
                                     UnidadeMedida unidade) {

}
