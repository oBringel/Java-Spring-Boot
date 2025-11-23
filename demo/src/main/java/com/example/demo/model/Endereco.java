package com.example.demo.model;

import jakarta.persistence.*;

import java.util.UUID;
@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid = UUID.randomUUID();

    private String nomeDaRua;
    private String complementoDaRua;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produtos produtos;

    public Endereco(String nomeDaRua, String complementoDaRua, Produtos produtos) {
        this.uuid = UUID.randomUUID();
        this.nomeDaRua = nomeDaRua;
        this.complementoDaRua = complementoDaRua;
        this.produtos = produtos;
    }

    public Endereco (){

    }

    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getNomeDaRua() {
        return nomeDaRua;
    }

    public String getComplementoDaRua() {
        return complementoDaRua;
    }

    public Produtos getProdutos() {
        return produtos;
    }
}
