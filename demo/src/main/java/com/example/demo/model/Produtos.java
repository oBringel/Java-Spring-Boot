package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Produtos {


    public Produtos() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeProd;
    private String descricaoProd;
    private String validadeProd;
    private String loteProd;
    private boolean ativo = true;

    public Produtos(Long id, String nomeProd, String descricaoProd, String validadeProd, String loteProd) {
        this.id = id;
        this.nomeProd = nomeProd;
        this.descricaoProd = descricaoProd;
        this.validadeProd = validadeProd;
        this.loteProd = loteProd;
    }

    public Produtos(String s, String s1, String s2, String s3) {
        this.nomeProd = s;
        this.descricaoProd = s1;
        this.validadeProd = s2;
        this.loteProd = s3;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public void setDescricaoProd(String descricaoProd) {
        this.descricaoProd = descricaoProd;
    }

    public void setValidadeProd(String validadeProd) {
        this.validadeProd = validadeProd;
    }

    public void setLoteProd(String loteProd) {
        this.loteProd = loteProd;
    }
}
