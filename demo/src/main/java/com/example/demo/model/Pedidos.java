package com.example.demo.model;

import com.example.demo.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "pedidos")
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_produto")
    private Long idProduto;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    private Integer quantidade;
    @Column(name = "local_date_time")
    private String dataRequisicao = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

    public Pedidos(Long idProduto, Integer quantidade, Status status, String data) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.status = status;
        this.dataRequisicao = data;
    }

    public Pedidos(Long id, Long idProduto, Integer quantidade, Status status, String data) {
        this.id = id;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.status = status;
        this.dataRequisicao = data;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Pedidos() {
    }


    public String getDataRequisicao() {
        return dataRequisicao;
    }

    public void setDataRequisicao(String dataRequisicao) {
        this.dataRequisicao = dataRequisicao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
