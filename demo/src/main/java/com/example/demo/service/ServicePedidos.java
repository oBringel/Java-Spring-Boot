package com.example.demo.service;

import com.example.demo.enums.Status;
import com.example.demo.model.Estoque;
import com.example.demo.model.Pedidos;
import com.example.demo.repository.EstoqueRepository;
import com.example.demo.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class ServicePedidos {

    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    EstoqueRepository estoqueRepository;
    @Autowired
    ServiceEstoque serviceEstoque;


    public Pedidos fazerPedido(Long id, Integer quantidadeRequisitado) {
        Pedidos pedidos = new Pedidos();
        Estoque estoque = estoqueRepository.findByProduto_Id(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        Pedidos criar = new Pedidos(id, quantidadeRequisitado, Status.PENDENTE, pedidos.getDataRequisicao());
        return pedidoRepository.save(criar);
    }

    public Pedidos editarPedido(Long id, Integer quantidade) {

        return pedidoRepository.findPedidosById(id).map(
                atualizar -> {
                    atualizar.setQuantidade(quantidade);
                    return pedidoRepository.save(atualizar);
                }
        ).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public void excluindoPedido(Long id) {
        Pedidos buscarId = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("pedido n encontrado"));
        pedidoRepository.deleteById(id);

    }

    public List<Pedidos> listaDePedidos() {
        return pedidoRepository.findAll();
    }

    public Pedidos listarPorId(Long id) {
        return pedidoRepository.findById(id).map(pedido -> new Pedidos(pedido.getId(), pedido.getIdProduto(), pedido.getQuantidade(), pedido.getStatus(), pedido.getDataRequisicao()))
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public void confirmarPedido(Long id) {

        Pedidos idDoPedido = pedidoRepository
                .findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (idDoPedido.getStatus() == Status.PENDENTE) {
            serviceEstoque.registrarSaida(idDoPedido.getIdProduto(), idDoPedido.getQuantidade());

            pedidoRepository.findById(id).map(pedidos -> {
                        pedidos.setStatus(Status.CONFIRMADO);
                        pedidos.setDataRequisicao(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
                        return pedidoRepository.save(pedidos);
                    }
            ).orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Quantidade insuficiente!"));

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pedido já confirmado!");
        }
    }

    public void cancelarPedido(Long id) {
        Pedidos buscarId = pedidoRepository.findById(id).map(cancelar -> {
            cancelar.setStatus(Status.CANCELADO);
            cancelar.setDataRequisicao(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            return pedidoRepository.save(cancelar);
        }).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

}
