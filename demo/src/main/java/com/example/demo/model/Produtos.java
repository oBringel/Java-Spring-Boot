package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Produtos {


    public Produtos(){
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private String id_uuid = UUID.randomUUID().toString();
    private String nomeProd;
    private String descricaoProd;
    private String validadeProd;
    private String loteProd;
    private boolean ativo = true;



    public Produtos(Long id,String nomeProd, String descricaoProd, String validadeProd, String loteProd) {
        this.id = id;
        this.nomeProd = nomeProd;
        this.descricaoProd = descricaoProd;
        this.validadeProd = validadeProd;
        this.loteProd = loteProd;
    }
    public Long getId() {
        return id;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public String getDescricaoProd() {
        return descricaoProd;
    }

    public String getValidadeProd() {
        return validadeProd;
    }

    public String getLoteProd() {
        return loteProd;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
