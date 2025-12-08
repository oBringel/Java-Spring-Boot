package com.example.demo.controller;

import com.example.demo.dto.AtualizarProdutosRequestDTO;
import com.example.demo.dto.SalvarProdutosRequestDTO;
import com.example.demo.model.Produtos;
import com.example.demo.service.ServiceProd;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ControllerProd {

    @Autowired
    private ServiceProd serviceProd;

    @GetMapping
    public ResponseEntity<List<Produtos>> listar(){
        return ResponseEntity.ok(serviceProd.procurarTodos());
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Produtos> salvar(@RequestBody SalvarProdutosRequestDTO produtosRequestDTO){
      Produtos salvo =  serviceProd.salvar(produtosRequestDTO);
      return ResponseEntity.ok(salvo);
    }
    @PutMapping("/atualizar")
    public ResponseEntity<Produtos> atualizar(@RequestBody AtualizarProdutosRequestDTO dto){
        Produtos atualizado = serviceProd.atualiza(dto);
        return  ResponseEntity.ok(atualizado);
    }
    @Transactional
    @PatchMapping("/inativar/{produtos}")
    public ResponseEntity<Produtos> inativar(@PathVariable Produtos produtos){
        Optional<Produtos>  buscarId = serviceProd.procurarPorId(produtos.getId());
        if (buscarId.isPresent()){
            Produtos atualizado = serviceProd.desativar(produtos.getId());
            return ResponseEntity.ok(atualizado);
        }else {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @Transactional
    @PatchMapping("/ativar/{produtos}")
    public  ResponseEntity<Produtos> ativar (@PathVariable Produtos produtos){
        Optional<Produtos>  buscarId = serviceProd.procurarPorId(produtos.getId());
        if (buscarId.isPresent()){
            Produtos atualizado = serviceProd.ativar(produtos.getId());
            return ResponseEntity.ok(atualizado);
        }else {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("/deletar/{produtos}")
    public ResponseEntity<Void> deletar(@PathVariable Produtos produtos){
    Optional<Produtos> buscar = serviceProd.procurarPorId(produtos.getId());
        if (buscar.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        serviceProd.excluir(produtos.getId());
        return ResponseEntity.noContent().build();
    }
}
