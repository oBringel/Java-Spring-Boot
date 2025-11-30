package com.example.demo.model.estoque;

import com.example.demo.model.estoque.Enum.Status;
import com.example.demo.model.estoque.Enum.UnidadeMedida;
import com.example.demo.model.Produtos;
import jakarta.persistence.*;

import java.time.LocalDateTime;
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
    private Produtos produto;

    @OneToMany(mappedBy = "estoque")
    private List<MovimentacaoEstoque> historico;

    private LocalDateTime localDateTime;

    public Estoque(Produtos produto, Integer quantidade , Status status, UnidadeMedida unidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.status = status;
        this.unidadeMedida = unidade;
        this.localDateTime = LocalDateTime.now();
    }


    public Estoque( Long id,Produtos produto, Integer quantidade , Status status, UnidadeMedida unidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.status = status;
        this.unidadeMedida = unidade;
        this.localDateTime = LocalDateTime.now();
    }


    public Estoque() {
    }

    public Estoque(Estoque atualizado) {
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
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
                "localDateTime=" + localDateTime +
                ", historico=" + historico +
                ", produto=" + produto +
                ", unidadeMedida=" + unidadeMedida +
                ", status=" + status +
                ", id=" + id +
                '}';
    }
}
