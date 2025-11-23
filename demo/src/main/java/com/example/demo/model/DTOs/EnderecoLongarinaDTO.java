package com.example.demo.model.DTOs;

import com.example.demo.model.Endereco;
import com.example.demo.model.Produtos;

import java.util.UUID;

public record EnderecoLongarinaDTO( Long id,
                                    String nomeDaRua,
                                    String complementoDaRua,
                                    Long idProd) {


}
