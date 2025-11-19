package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Produtos {

    public Produtos(Long id, String nome_prod, String descricao_prod, String validade_prod, String lote_prod) {
        this.id = id;
        this.nome_prod = nome_prod;
        this.descricao_prod = descricao_prod;
        this.validade_prod = validade_prod;
        this.lote_prod = lote_prod;
    }
    public Produtos(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private String id_uuid = UUID.randomUUID().toString();
    private String nome_prod;
    private String descricao_prod;
    private String validade_prod;
    private String lote_prod;

    public Long getId() {
        return id;
    }

    public String getNome_prod() {
        return nome_prod;
    }

    public String getDescricao_prod() {
        return descricao_prod;
    }

    public String getValidade_prod() {
        return validade_prod;
    }

    public String getLote_prod() {
        return lote_prod;
    }
}
