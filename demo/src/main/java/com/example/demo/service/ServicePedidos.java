package com.example.demo.service;

import com.example.demo.model.Pedidos;
import com.example.demo.model.estoque.Estoque;
import com.example.demo.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ServicePedidos {

    @Autowired
    Estoque estoque;

    @Autowired
    ServiceEstoque serviceEstoque;

    @Autowired
    PedidoRepository pedidoRepository;



    public Pedidos fazerPedido(Long idProduto, Integer quantidade) throws IllegalAccessException {
        // Só posso fazer o pedido se o estoque tiver positivo
        
        var idBuscar = serviceEstoque.procurarPorId(idProduto);

        if (estoque.getQuantidade() >= quantidade && idBuscar.isPresent() ){
            Pedidos salvarPedido = new Pedidos(
                    idProduto,
                    quantidade
            );
            return pedidoRepository.save(salvarPedido);
        }
        throw new IllegalAccessException("erro ao cadastrar");
    }

}
