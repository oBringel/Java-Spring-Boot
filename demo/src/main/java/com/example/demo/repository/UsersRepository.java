package com.example.demo.repository;

import com.example.demo.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Usuarios, Long> {
    Optional<Usuarios> findByCpf(Long cpf);
    Usuarios deleteByCpf(Long cpf);
}
