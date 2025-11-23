package com.example.demo.repository;

import com.example.demo.model.DTOs.EnderecoLongarinaDTO;
import com.example.demo.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface EndRepository extends JpaRepository<Endereco, Long> {

    Optional<Endereco> findByUuid(UUID id);

}
