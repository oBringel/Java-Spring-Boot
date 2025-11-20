package com.example.demo.repository;

import com.example.demo.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Usuarios, Long> {

    Optional<Usuarios> findByCpf(Long cpf);
    Usuarios deleteByCpf(Long cpf);


    @Modifying
    @Query("UPDATE Usuarios u SET u.ativo = false WHERE u.cpf = :cpf")
    void desativar(@Param("cpf") Long cpf);


    @Modifying
    @Query("UPDATE Usuarios u SET u.ativo = ativo WHERE u.cpf = :cpf")
    void ativar (@Param("cpf") Long cpf);

}
