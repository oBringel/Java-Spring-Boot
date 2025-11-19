package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean ativo;
    private String nome;
    private Long cpf;

    public Usuarios(Long id, String nome, Long cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Usuarios() {
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
