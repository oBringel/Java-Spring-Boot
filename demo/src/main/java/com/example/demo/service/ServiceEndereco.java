package com.example.demo.service;

import com.example.demo.model.DTOs.EnderecoLongarinaDTO;
import com.example.demo.model.Endereco;
import com.example.demo.model.Produtos;
import com.example.demo.repository.EndRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceEndereco {
    @Autowired
    EndRepository endRepository;
    Produtos produtos;
    ServiceProd serviceProd;

    public EnderecoLongarinaDTO cadastrandoSkuNoEndereco(Endereco dadosEndereco) {


    }

    




}
