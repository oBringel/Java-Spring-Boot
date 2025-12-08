package com.example.demo.repository;

import com.example.demo.model.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedidos, Long> {

    Optional<Pedidos> findPedidosById(Long id);

}
