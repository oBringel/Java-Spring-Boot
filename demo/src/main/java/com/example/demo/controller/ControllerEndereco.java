package com.example.demo.controller;

import com.example.demo.model.DTOs.EnderecoLongarinaDTO;
import com.example.demo.service.ServiceEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerEndereco {

    @Autowired
    ServiceEndereco serviceEndereco;
    @PostMapping("/endereco/cadastro")

    public ResponseEntity<EnderecoLongarinaDTO> criando( @RequestBody EnderecoLongarinaDTO enderecoLongarina){
        EnderecoLongarinaDTO enderecoLongarinaDTO = serviceEndereco.cadastrandoSkuNoEndereco(enderecoLongarina);
        return ResponseEntity.ok(enderecoLongarinaDTO);
    }
}
