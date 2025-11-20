package com.example.demo.repository;

import com.example.demo.model.Produtos;
import com.example.demo.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdRepository extends JpaRepository<Produtos, Long> {

    Optional<Produtos> findByNomeProdAndDescricaoProdAndValidadeProdAndLoteProd(
            String nomeProd,
            String descricaoProd,
            String validadeProd,
            String loteProd
    );


    @Modifying
    @Query("UPDATE Produtos p SET p.ativo = false WHERE p.id = :id")
    void desativarProduto(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Produtos p SET p.ativo = true WHERE p.id = :id")
    void ativarProduto (@Param("id") Long id);

}
