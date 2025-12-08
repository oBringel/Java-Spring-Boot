package com.example.demo.model;

import com.example.demo.enums.Status;
import com.example.demo.enums.UnidadeMedida;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Enumerated(EnumType.STRING)

    private Status status;

    @Enumerated(EnumType.STRING)
    private UnidadeMedida unidadeMedida;

    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produtos produto;

    @OneToMany(mappedBy = "estoque")
    private List<MovimentacaoEstoque> historico;

    private String dataRequisicao = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

    public Estoque(Produtos produto, Integer quantidade, Status status, UnidadeMedida unidade, String dataRequisicao) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.status = status;
        this.unidadeMedida = unidade;
        this.dataRequisicao = dataRequisicao;
    }


    public Estoque(Long id, Produtos produto, Integer quantidade, Status status, UnidadeMedida unidade, String dataRequisicao) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.status = status;
        this.unidadeMedida = unidade;
        this.dataRequisicao = dataRequisicao;
    }


    public Estoque() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Integer setQuantidade(Integer quantidade) {
        return this.quantidade = quantidade;
    }


    public String getDataRequisicao() {
        return dataRequisicao;
    }

    public void setDataRequisicao(String dataRequisicao) {
        this.dataRequisicao = dataRequisicao;
    }

    public Long getId() {
        return id;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public List<MovimentacaoEstoque> getHistorico() {
        return historico;
    }

    public void setHistorico(List<MovimentacaoEstoque> historico) {
        this.historico = historico;
    }


    @Override
    public String toString() {
        return "Estoque{" +
                "localDateTime=" + dataRequisicao +
                ", historico=" + historico +
                ", produto=" + produto +
                ", unidadeMedida=" + unidadeMedida +
                ", status=" + status +
                ", id=" + id +
                '}';
    }
}
