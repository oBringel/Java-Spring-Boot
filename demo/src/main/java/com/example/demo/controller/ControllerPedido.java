package com.example.demo.controller;

import com.example.demo.dto.PedidoDTO;
import com.example.demo.model.Pedidos;
import com.example.demo.service.ServicePedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class ControllerPedido {
    @Autowired
    ServicePedidos servicePedidos;

    @GetMapping("/lista")
    public ResponseEntity<List<Pedidos>> getPedidos() {
        return ResponseEntity.ok(servicePedidos.listaDePedidos());
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<Pedidos> getIdPedidos(@PathVariable Long id) {
        return ResponseEntity.ok(servicePedidos.listarPorId(id));
    }

    @PostMapping("/criar")
    ResponseEntity<Pedidos> postPedidos(@RequestBody PedidoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicePedidos.fazerPedido(dto.id(), dto.quantidade()));
    }

    @PutMapping("/editar")
    ResponseEntity<Pedidos> putPedidos(@RequestBody PedidoDTO dto) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicePedidos.editarPedido(dto.id(), dto.quantidade()));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletePedidos(@PathVariable Long id){
        servicePedidos.excluindoPedido(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/confirmar/{id}")
    public  ResponseEntity<Void> postConfirma(@PathVariable Long id){
        servicePedidos.confirmarPedido(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<Void> putCancelar(@PathVariable Long id){
        servicePedidos.cancelarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
