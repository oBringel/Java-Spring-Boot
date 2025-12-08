package com.example.demo.controller;

import com.example.demo.dto.MovimentacaoEstoqueDTO;
import com.example.demo.dto.SaidaDeEstoque;
import com.example.demo.model.Estoque;
import com.example.demo.service.ServiceEstoque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/estoque")
public class ControllerEstoque {
    @Autowired
    ServiceEstoque serviceEstoque;

    @PostMapping("/entrada")
    public ResponseEntity<String> registrarEntrada(@RequestBody MovimentacaoEstoqueDTO dto) {
        serviceEstoque.registrarEntrada(dto.produtoId(), dto.quantidade(), dto.unidade());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/saida")
    public ResponseEntity<String> registrarSaida(@RequestBody SaidaDeEstoque dto) {
        serviceEstoque.registrarSaida(dto.id(), dto.quantidadeSaida());
        return ResponseEntity.noContent().build();
    }

    // add enum ADMIN futuramente
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Estoque> atualizar(@PathVariable Long id, @RequestBody Estoque dados) {
        return ResponseEntity.ok(serviceEstoque.atualizarRegistro(id, dados));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        serviceEstoque.deletandoRegistro(id);
        return ResponseEntity.noContent().build();
    }

}
